package org.hyrical.mimic.features.impl.basic.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player

class MoreCommand : BaseCommand() {

    @CommandAlias("more")
    @CommandPermission("mimic.more")
    fun moreCommand(player: Player) {
        if (player.itemInHand == null || player.itemInHand.type == Material.AIR) {
            player.sendMessage("${ChatColor.GOLD}You must be holding an item to use this command.")
            return
        }

        player.itemInHand.amount = 64
        player.sendMessage("${ChatColor.GOLD}The item in your hand has been set to 64.")

    }
}