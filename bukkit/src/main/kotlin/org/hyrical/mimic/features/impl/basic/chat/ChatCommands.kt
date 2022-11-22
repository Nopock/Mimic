package org.hyrical.mimic.features.impl.basic.chat

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.hyrical.mimic.translate

class ChatCommands : BaseCommand() {

    @CommandAlias("clearchat|cc|ccall")
    @CommandPermission("mimic.clearchat")
    fun clearChatCommand(player: Player) {
        for (i in 0..1001) {
            Bukkit.broadcastMessage(translate("&e"))
        }

        Bukkit.broadcastMessage(translate("&eChat cleared by a staff member."))
    }

    @CommandAlias("slowchat|slowmode|slow")
    @CommandPermission("mimic.slowchat")
    fun slowChatCommand(player: Player, seconds: Int) {
        if (seconds > 60) {
            player.sendMessage(translate("&cSlowchat cannot be longer than 60 seconds."))
            return
        }

        Bukkit.broadcastMessage(translate("&eChat has been slowed down by a staff member."))
        ChatHandler.slowChat = seconds
    }

    @CommandAlias("mc|mutechat")
    @CommandPermission("mimic.mutechat")
    fun muteChatCommand(player: Player) {
        ChatHandler.muted = !ChatHandler.muted
        Bukkit.broadcastMessage(translate("&eChat has been ${if (ChatHandler.muted) "muted" else "unmuted"} by a staff member."))
    }
}