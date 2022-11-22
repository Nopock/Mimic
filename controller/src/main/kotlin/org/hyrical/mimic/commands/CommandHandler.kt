package org.hyrical.mimic.commands

import revxrsal.commands.annotation.Command
import revxrsal.commands.annotation.Subcommand
import revxrsal.commands.cli.ConsoleCommandHandler
import kotlin.concurrent.thread

@Command("mimic")
object CommandHandler {

    init {
        ConsoleCommandHandler.create().also {
            it.register(this)

            thread {
                while (true) {
                    it.pollInput()
                }
            }
        }
    }
}