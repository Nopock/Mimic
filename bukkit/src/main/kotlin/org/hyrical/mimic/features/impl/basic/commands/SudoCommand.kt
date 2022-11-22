package org.hyrical.mimic.features.impl.basic.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class SudoCommand : BaseCommand() {

    @CommandAlias("sudo")
    @CommandPermission("mimic.sudo")
    fun sudoCommand(player: Player, target: Player, command: String) {
        target.performCommand(command)

        player.sendMessage("${ChatColor.GOLD}You have successfully ran the command ${ChatColor.RED}$command ${ChatColor.GOLD}as ${target.displayName}")
    }
}