package org.hyrical.mimic.profiles.grants

import com.mongodb.client.model.Filters
import org.hyrical.mimic.profiles.grants.adapter.GrantAdapter
import org.hyrical.mimic.profiles.punishments.PunishmentService
import org.hyrical.mimic.profiles.punishments.adapter.PunishmentAdapter
import org.hyrical.mimic.protocol.Grant
import org.hyrical.mimic.protocol.Punishment
import org.hyrical.store.DataStoreController
import org.hyrical.store.repository.impl.MongoRepository
import org.hyrical.store.serializers.Serializers
import org.hyrical.store.type.StorageType

object GrantService {

    var controller: DataStoreController<Grant> = DataStoreController.of<Grant>(StorageType.MONGO).also {
        it.construct()
    }

    private val repository = controller.repository as MongoRepository<Grant>

    init {
        Serializers.registerTypeAdapter(GrantAdapter())
    }

    fun deleteGrant(grant: Grant) {
        repository.delete(grant.id)
    }

    fun getGrants(id: String): List<Grant> {
        return repository.collection.find(Filters.eq("addedTo_", id)).toList().map { Serializers.activeSerialize.deserialize(it.toJson(), controller.classType)!! }
    }

    fun grantUser(grant: Grant) {
        repository.save(grant, grant.id)
    }
}