package org.hyrical.mimic

import io.grpc.okhttp.internal.Platform.logger

object MimicController {

}

fun main(args: Array<String>) {
    val engine = MimicEngine()

    logger.info("[Mimic] Starting Mimic Engine...")

    engine.start()
}