package org.hyrical.mimic.profiles.punishments

import com.mongodb.client.model.Filters
import org.hyrical.mimic.profiles.punishments.adapter.PunishmentAdapter
import org.hyrical.mimic.protocol.Profile
import org.hyrical.mimic.protocol.Punishment
import org.hyrical.mimic.protocol.PunishmentTypeRequest
import org.hyrical.mimic.server.ranks.adapter.RankAdapter
import org.hyrical.store.DataStoreController
import org.hyrical.store.repository.impl.MongoRepository
import org.hyrical.store.serializers.Serializers
import org.hyrical.store.type.StorageType

object PunishmentService {

    var controller: DataStoreController<Punishment> = DataStoreController.of<Punishment>(StorageType.MONGO).also {
        it.construct()
    }

    private val repository = controller.repository as MongoRepository<Punishment>

    init {
        Serializers.registerTypeAdapter(PunishmentAdapter())
    }

    fun getPunishments(id: String): List<Punishment> {
        return repository.collection.find(Filters.eq("addedTo_", id)).toList().map { Serializers.activeSerialize.deserialize(it.toJson(), controller.classType)!! }
    }

    fun deletePunishment(id: String) {
        repository.delete(id)
    }

    fun getPunishmentsByType(request: PunishmentTypeRequest): List<Punishment> {
        return getPunishments(request.uuid).filter { it.type == request.type }
    }

    fun punish(punishment: Punishment) {
        repository.save(punishment, punishment.id)
    }
}