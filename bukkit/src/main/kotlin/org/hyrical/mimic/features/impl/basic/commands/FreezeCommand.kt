package org.hyrical.mimic.features.impl.basic.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import org.bukkit.entity.Player
import org.hyrical.mimic.features.impl.basic.commands.screenshare.FrozenHandler

class FreezeCommand : BaseCommand() {

    @CommandAlias("freeze|ss")
    @CommandPermission("mimic.freeze")
    fun freezeCommand(player: Player, target: Player) {
        if (FrozenHandler.isFrozen(target.uniqueId)) {
            FrozenHandler.unfreeze(target.uniqueId)
        }

    }
}