package org.hyrical.mimic.profiles

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.hyrical.mimic.profiles.grants.GrantService
import org.hyrical.mimic.profiles.punishments.PunishmentService
import org.hyrical.mimic.protocol.*
import org.hyrical.mimic.server.ranks.adapter.RankAdapter
import org.hyrical.store.DataStoreController
import org.hyrical.store.serializers.Serializers
import org.hyrical.store.type.StorageType

class ProfileService : ProfileServiceGrpcKt.ProfileServiceCoroutineImplBase() {

    var controller: DataStoreController<Profile> = DataStoreController.of<Profile>(StorageType.MONGO).also {
        it.construct()
    }

    init {
        Serializers.registerTypeAdapter(RankAdapter())
    }

    override suspend fun allProfiles(request: Empty): ProfilesResponse {
        return ProfilesResponse.newBuilder()
            .addAllProfiles(controller.repository.findAll())
            .build()
    }

    override fun allPunishments(request: ProfileRequest): Flow<Punishment> {
        return flow {
            PunishmentService.getPunishments(request.uuid)
                .forEach {
                    emit(it)
                }
        }
    }

    override suspend fun createProfile(request: ProfileRequest): Profile {
        val profile = Profile.newBuilder()
            .setCreatedAt(System.currentTimeMillis())
            .setUuid(request.uuid)
            .setUsername(request.username)
            .setLastLogin(System.currentTimeMillis())
            .setOnline(true)
            .setPrefix("")
            .build()

        controller.repository.save(profile, request.uuid)
        return profile
    }

    override suspend fun getProfile(request: ProfileRequest): Profile {
        return controller.repository.search(request.uuid) ?: createProfile(request) /* TODO: Figure this out */
    }

    override suspend fun deletePunishment(request: Punishment): SuccessResponse {
        PunishmentService.deletePunishment(request.id)
        return SuccessResponse.newBuilder().setSuccess(true).build()
    }

    override suspend fun punishments(request: ProfileRequest): PunishmentsResponse {
        return PunishmentsResponse.newBuilder()
            .addAllPunishments(PunishmentService.getPunishments(request.uuid))
            .build()
    }

    override suspend fun punishmentsByType(request: PunishmentTypeRequest): PunishmentsResponse {
        return PunishmentsResponse.newBuilder()
            .addAllPunishments(PunishmentService.getPunishmentsByType(request))
            .build()
    }

    override suspend fun punish(request: Punishment): SuccessResponse {
        PunishmentService.punish(request)
        return SuccessResponse.newBuilder().setSuccess(true).build()
    }

    override suspend fun deleteGrant(request: Grant): SuccessResponse {
        GrantService.deleteGrant(request)
        return SuccessResponse.newBuilder().setSuccess(true).build()
    }

    override suspend fun grants(request: ProfileRequest): GrantsResponse {
        return GrantsResponse.newBuilder()
            .addAllGrants(GrantService.getGrants(request.uuid))
            .build()
    }

    override suspend fun grantUser(request: Grant): SuccessResponse {
        GrantService.grantUser(request)
        return SuccessResponse.newBuilder().setSuccess(true).build()
    }
}