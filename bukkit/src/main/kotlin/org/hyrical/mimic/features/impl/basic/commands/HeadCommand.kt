package org.hyrical.mimic.features.impl.basic.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Single
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

class HeadCommand : BaseCommand() {

    @CommandAlias("head")
    @CommandPermission("mimic.head")
    fun headCommand(player: Player, @Single target: String) {
        player.sendMessage("${ChatColor.GOLD}You have been given the head of ${ChatColor.WHITE}$target")
        
        player.inventory.addItem(
            ItemStack(Material.SKULL_ITEM).apply { 
                durability = 3
                (itemMeta as SkullMeta).apply {
                    owner = target
                    displayName = "${ChatColor.YELLOW}Head of ${ChatColor.GOLD}$target"
                }
            }
        )
    }
}