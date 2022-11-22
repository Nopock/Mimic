package org.hyrical.mimic.features.impl.basic.commands.screenshare

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.hyrical.mimic.MimicClient

class FrozenListener : Listener {

    @EventHandler
    fun onInventoryClose(event: InventoryCloseEvent) {
        if (FrozenHandler.isFrozen(event.player.uniqueId)) {
            Bukkit.getScheduler().runTaskLater(MimicClient.instance, {
                event.player.openInventory(FrozenHandler.getFrozenInventory(event.player.killer))
            }, 1L)
        }
    }

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        if (FrozenHandler.isFrozen(event.whoClicked.uniqueId)) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onInventoryDrag(event: InventoryDragEvent) {
        if (FrozenHandler.isFrozen(event.whoClicked.uniqueId)) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        if (FrozenHandler.isFrozen(event.player.uniqueId)) {
            FrozenHandler.frozenPlayers.remove(event.player.uniqueId)

            Bukkit.getOnlinePlayers().filter { it.hasPermission("mimic.staff")}.forEach {
                it.sendMessage(("\n&l${event.player.displayName} ${ChatColor.DARK_RED}has logged the game while frozen.\n"))
            }
        }
    }

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        if (FrozenHandler.isFrozen(event.player.uniqueId)) {
            event.isCancelled = true
        }
    }
}