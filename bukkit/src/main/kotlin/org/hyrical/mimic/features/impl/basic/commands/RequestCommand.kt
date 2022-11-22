package org.hyrical.mimic.features.impl.basic.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.util.UUID
import java.util.concurrent.TimeUnit

class RequestCommand : BaseCommand() {

    val cooldowns = mutableMapOf<UUID, Long>()

    @CommandAlias("request|helpop")
    fun requestCommand(player: Player, message: String) {
        val cooldown = cooldowns[player.uniqueId]

        if (cooldown != null && cooldown > System.currentTimeMillis()) {
            player.sendMessage("§cYou must wait ${((cooldown - System.currentTimeMillis()) / 1000)} seconds before sending another request.")
            return
        }
        cooldowns[player.uniqueId] = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(2)
        player.sendMessage("${ChatColor.GREEN}You're request has been sent to the staff team.")
        player.server.onlinePlayers.forEach {
            if (it.hasPermission("mimic.staff")) {
                it.sendMessage("§7[§bRequest§7] §b${player.name}§7: §f$message")
            }
        }

        //TODO: Fix messages / maybe global msgs
    }

}