package org.hyrical.mimic.features.impl.basic.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Description
import co.aikar.commands.annotation.Optional
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class HealCommand : BaseCommand() {

    @CommandAlias("heal")
    @CommandPermission("mimic.heal")
    @Description("Heal yourself or another player.")
    fun healCommand(sender: Player, @Optional target: Player?) {
        val player = target ?: sender
        player.health = player.maxHealth
        player.foodLevel = 20
        player.saturation = 20f

        player.sendMessage("${ChatColor.GOLD}You have been healed.")

        if (player != sender) {
            sender.sendMessage("${ChatColor.GOLD}You have healed ${player.name}.")
        }
    }

}