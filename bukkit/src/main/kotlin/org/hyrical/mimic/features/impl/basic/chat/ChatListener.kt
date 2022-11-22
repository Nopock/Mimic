package org.hyrical.mimic.features.impl.basic.chat

import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class ChatListener : Listener {

    val lastChatted = mutableMapOf<String, Long>()

    @EventHandler
    fun onChat(event: AsyncPlayerChatEvent) {
        if (ChatHandler.slowChat == null) return

        if (event.player.hasPermission("mimic.staff")) return

        if (ChatHandler.muted) {
            event.isCancelled = true
            event.player.sendMessage("${ChatColor.RED}Chat is currently muted.")
            return
        }

        val last = lastChatted[event.player.name]

        if (last == null) {
            lastChatted[event.player.name] = System.currentTimeMillis()
            return
        }

        if (last < (System.currentTimeMillis() - ChatHandler.slowChat!!)) {
            lastChatted[event.player.name] = System.currentTimeMillis()
        } else {
            event.isCancelled = true
            event.player.sendMessage("§cYou must wait §l${ChatHandler.slowChat!! / 1000}§r§c seconds between messages.")
        }
    }
}