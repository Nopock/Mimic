package org.hyrical.mimic.features.impl.basic.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class TeleportCommands : BaseCommand() {

    @CommandAlias("tp|teleport|tpto|goto")
    @CommandPermission("mimic.tp")
    fun teleportCommand(player: Player, target: Player) {
        player.teleport(target)

        player.sendMessage("${ChatColor.GOLD}Teleporting you to ${target.displayName}${ChatColor.GOLD}.")
    }

    @CommandAlias("tphere|bring")
    @CommandPermission("mimic.tphere")
    fun teleportHereCommand(player: Player, target: Player) {
        target.teleport(player)

        player.sendMessage("${ChatColor.GOLD}Teleporting ${target.displayName}${ChatColor.GOLD} to you.")
    }
}