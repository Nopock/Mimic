package org.hyrical.mimic.features.impl.basic.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import org.bukkit.entity.Player
import org.hyrical.mimic.translate

class BroadcastCommand : BaseCommand() {

    @CommandAlias("broadcast|bc")
    fun broadcast(player: Player, message: String) {
        player.server.broadcastMessage(translate(message))
    }

}