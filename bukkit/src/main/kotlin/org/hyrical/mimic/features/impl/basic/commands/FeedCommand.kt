package org.hyrical.mimic.features.impl.basic.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Optional
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class FeedCommand : BaseCommand() {

    @CommandAlias("feed")
    fun feed(player: Player, @Optional target: Player?) {
        val targetPlayer = target ?: player

        targetPlayer.foodLevel = 20
        targetPlayer.saturation = 20f

        if (targetPlayer != player) {
            player.sendMessage("${ChatColor.GOLD}You have fed ${targetPlayer.displayName}${ChatColor.GOLD}.")
            return
        }

        player.sendMessage("${ChatColor.GOLD}You have been fed.")
    }
}