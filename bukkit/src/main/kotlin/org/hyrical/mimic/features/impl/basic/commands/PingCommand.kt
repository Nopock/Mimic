package org.hyrical.mimic.features.impl.basic.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Description
import co.aikar.commands.annotation.Optional
import org.bukkit.ChatColor
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
import org.bukkit.entity.Player

class PingCommand : BaseCommand() {

    @CommandAlias("ping")
    @Description("Check your ping")
    fun pingCommand(player: Player, @Optional target: Player?) {
        val ping = if (target == null) (player as CraftPlayer).handle.ping else (target as CraftPlayer).handle.ping

        player.sendMessage((if (target == null) "${ChatColor.GOLD}Ping" else "${target.displayName}${ChatColor.GOLD}'s Ping") + ": ${ChatColor.WHITE}$ping")
    }

}