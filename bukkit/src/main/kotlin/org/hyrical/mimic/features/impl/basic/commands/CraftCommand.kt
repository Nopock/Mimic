package org.hyrical.mimic.features.impl.basic.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import org.bukkit.entity.Player

class CraftCommand : BaseCommand() {

    @CommandAlias("craft")
    @CommandPermission("mimic.craft")
    fun craftCommand(player: Player) {
        player.openWorkbench(null, true)
    }
}