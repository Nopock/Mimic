package org.hyrical.mimic.util

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.configuration.file.FileConfiguration
import org.hyrical.mimic.translate
import java.io.File
import kotlin.concurrent.thread

class ConfigWatcher(val config: FileConfiguration, val key: String) {

    val file = File(config.root.toString())

    private var lastModified = file.lastModified()

    fun watch(reloadAction: () -> Unit) {
        thread {
            while (true) {
                if (file.lastModified() != lastModified) {
                    lastModified = file.lastModified()
                    reloadAction()

                    Bukkit.getOnlinePlayers().filter {
                        it.hasPermission("mimic.reload")
                    }.forEach {
                        it.sendMessage("${ChatColor.GREEN}[Config Watcher] ${ChatColor.YELLOW}Detected changes to ${ChatColor.DARK_AQUA}${key}.yml${ChatColor.YELLOW} bound to plugin ${ChatColor.GOLD}Mimic${ChatColor.YELLOW}.")
                    }
                }
            }
        }
    }
}