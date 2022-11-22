package org.hyrical.mimic.features.impl.basic

import co.aikar.commands.BaseCommand
import org.bukkit.event.Listener
import org.hyrical.mimic.features.Feature
import org.hyrical.mimic.features.impl.basic.chat.ChatCommands
import org.hyrical.mimic.features.impl.basic.chat.ChatListener
import org.hyrical.mimic.features.impl.basic.commands.*
import org.hyrical.mimic.features.impl.basic.commands.screenshare.FrozenListener

class BasicFeature : Feature("basic") {

    override fun onEnable() {

    }

    override fun onDisable() {

    }

    override fun getCommands(): List<BaseCommand> {
        return listOf(
            BroadcastCommand(),
            CraftCommand(),
            FeedCommand(),
            GamemodeCommands(),
            HealCommand(),
            PingCommand(),
            SudoCommand(),
            FreezeCommand(),
            MoreCommand(),
            HeadCommand(),
            TeleportCommands(),
            RequestCommand(),
            ChatCommands()
        )
    }

    override fun getListeners(): List<Listener> {
        return listOf(
            FrozenListener(),
            ChatListener()
        )
    }

    override fun getConfigReloadAction(): () -> Unit {
        return {

        }
    }
}