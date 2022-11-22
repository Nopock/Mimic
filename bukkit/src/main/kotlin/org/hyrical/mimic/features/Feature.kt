package org.hyrical.mimic.features

import co.aikar.commands.BaseCommand
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.event.Listener
import org.hyrical.mimic.MimicClient
import org.hyrical.mimic.util.ConfigWatcher
import java.io.File

abstract class Feature(val key: String) {

    val configFile = File(MimicClient.instance.dataFolder, "$key.yml");
    val config = YamlConfiguration.loadConfiguration(configFile)

    abstract fun onEnable()

    abstract fun onDisable()

    abstract fun getCommands(): List<BaseCommand>

    abstract fun getListeners(): List<Listener>

    abstract fun getConfigReloadAction(): () -> Unit

    fun enable() {
        onEnable()

        getCommands().forEach { MimicClient.instance.commandManager.registerCommand(it, true) }

        getListeners().forEach { MimicClient.instance.server.pluginManager.registerEvents(it, MimicClient.instance) }

        ConfigWatcher(config, key).watch(getConfigReloadAction())
    }

    fun disable() {
        onDisable()
    }
}