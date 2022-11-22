package org.hyrical.mimic.features.impl.basic.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Optional
import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.entity.Player

class GamemodeCommands : BaseCommand() {

    @CommandAlias("gms")
    @CommandPermission("mimic.gamemode.gms")
    fun gmsCommand(player: Player, @Optional target: Player?) {
        modifyGamemode(player,  GameMode.SURVIVAL, target ?: player)
    }

    @CommandAlias("gmc")
    @CommandPermission("mimic.gamemode.gmc")
    fun gmcCommand(player: Player, @Optional target: Player?) {
        modifyGamemode(player,  GameMode.CREATIVE, target ?: player)
    }

    @CommandAlias("gma")
    @CommandPermission("mimic.gamemode.gma")
    fun gmaCommand(player: Player, @Optional target: Player?) {
        modifyGamemode(player,  GameMode.ADVENTURE, target ?: player)
    }

    @CommandAlias("gmsp")
    @CommandPermission("mimic.gamemode.gmsp")
    fun gmspCommand(player: Player, @Optional target: Player?) {
        modifyGamemode(player,  GameMode.SPECTATOR, target ?: player)
    }

    fun modifyGamemode(player: Player, gameMode: GameMode, target: Player) {
        target.gameMode = gameMode

        if (player != target) {
            player.sendMessage("${ChatColor.GOLD}You have set ${target.displayName}${ChatColor.GOLD}'s gamemode to ${gameMode.name.capitalize()}")
        } else {
            player.sendMessage("${ChatColor.GOLD}Your gamemode has been set to ${gameMode.name.capitalize()}")
        }
    }
}