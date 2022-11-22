package org.hyrical.mimic

import io.grpc.Server
import io.grpc.ServerBuilder
import io.grpc.netty.NettyServerBuilder
import org.hyrical.mimic.profiles.ProfileService
import org.hyrical.mimic.server.ranks.RankService
import org.hyrical.store.serializers.Serializers
import java.net.InetSocketAddress

class MimicEngine {

    lateinit var server: Server

    fun start() {
        this.server = ServerBuilder
            .forPort(8080)
            .addService(ProfileService())
            .addService(RankService())
            .build()

        Serializers.buildGSON()

        this.server.start()

        Runtime.getRuntime().addShutdownHook(Thread {
            server.shutdown()
        })

        server.awaitTermination();
    }
}