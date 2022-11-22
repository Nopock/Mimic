package org.hyrical.mimic.server.ranks

import org.hyrical.mimic.protocol.*
import org.hyrical.mimic.server.ranks.adapter.RankAdapter
import org.hyrical.store.DataStoreController
import org.hyrical.store.serializers.Serializers
import org.hyrical.store.type.StorageType

class RankService : RankServiceGrpcKt.RankServiceCoroutineImplBase() {

    private val rankCache: MutableMap<String, Rank> = mutableMapOf()

    var controller: DataStoreController<Rank> = DataStoreController.of<Rank>(StorageType.MONGO).also {
        it.construct()
    }

    init {
        Serializers.registerTypeAdapter(RankAdapter())

        controller.repository.findAll().forEach {
            rankCache[it.name] = it
        }
    }

    override suspend fun getRank(request: RankRequestById): Rank {
        return rankCache.values.first { it.id == request.id } ?: /* TODO: Need to figure this out */ Rank.getDefaultInstance()
    }

    override suspend fun ranks(request: Empty): RanksResponse {
        return RanksResponse.newBuilder()
            .addAllRanks(rankCache.values)
            .build()
    }

    override suspend fun createRank(request: Rank): Rank {
        rankCache[request.name] = request
        controller.repository.save(request, request.id)
        return request
    }

    override suspend fun updateRank(request: Rank): Rank {
        rankCache[request.id] = request
        controller.repository.save(request, request.id)
        return request
    }

    override suspend fun deleteRank(request: Rank): Rank {
        rankCache.remove(request.name)
        controller.repository.delete(request.id)
        return request
    }

    override suspend fun rankByName(request: RankRequestByName): Rank {
        return rankCache[request.name] ?: /* TODO: Need to figure this out */ Rank.getDefaultInstance()
    }
}