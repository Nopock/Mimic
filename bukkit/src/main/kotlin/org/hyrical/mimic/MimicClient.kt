package org.hyrical.mimic

import co.aikar.commands.BukkitCommandManager
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin
import org.hyrical.mimic.features.Feature
import org.hyrical.mimic.features.impl.basic.BasicFeature
import org.hyrical.mimic.protocol.RankServiceGrpc
import java.util.logging.Level
import java.util.logging.LogRecord

class MimicClient : JavaPlugin() {

    companion object {
        lateinit var instance: MimicClient
    }

    lateinit var channel: ManagedChannel
    lateinit var rankService: RankServiceGrpc.RankServiceBlockingStub

    lateinit var commandManager: BukkitCommandManager

    override fun onEnable() {
        // Plugin startup logic
        instance = this
        saveDefaultConfig()

        commandManager = BukkitCommandManager(this)

        val client = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build().also {
            channel = it
        }

        rankService = RankServiceGrpc.newBlockingStub(client)

        getFeatures().forEach {
            if (config.getBoolean("features.${it.key}")) {
                logger.log(LogRecord(Level.INFO, "Enabling feature ${it.key}"))
                it.enable()
            }
        }
    }

    override fun onDisable() {
        // Plugin shutdown logic
        getFeatures().forEach {
            it.onDisable()
        }
    }

    fun getFeatures(): List<Feature> {
        return listOf(
            BasicFeature()
        )
    }
}

fun translate(string: String): String {
    return ChatColor.translateAlternateColorCodes('&', string)
}