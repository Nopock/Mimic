package org.hyrical.mimic.features.impl.basic.commands.screenshare

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.hyrical.mimic.translate
import org.hyrical.mimic.util.ItemBuilder
import java.util.*

object FrozenHandler {

    val frozenPlayers = mutableListOf<UUID>()

    fun isFrozen(uuid: UUID): Boolean {
        return frozenPlayers.contains(uuid)
    }

    fun freeze(uuid: UUID) {
        frozenPlayers.add(uuid)

        val player = Bukkit.getPlayer(uuid)

    }

    fun unfreeze(uuid: UUID) {
        frozenPlayers.remove(uuid)

        Bukkit.getPlayer(uuid)!!.also {
            it.sendMessage(translate("&aYou have been unfrozen by a staff member."))
            it.closeInventory()
        }
    }

    fun getFrozenInventory(player: Player): Inventory {
        val inventory = Bukkit.createInventory(null, 9, "You're frozen!")

        inventory.setItem(4,
            ItemBuilder {
                name("${ChatColor.RED}You're frozen!")
                lore(
                    "${ChatColor.GRAY}You have been frozen by a staff member.",
                    "${ChatColor.YELLOW}",
                    "${ChatColor.GRAY}Please join our discord server:",
                    "           ${ChatColor.GRAY}${ChatColor.UNDERLINE}discord.gg/invite",
                )
            }
        )

        inventory.setItem(8,
            ItemBuilder {
                name("${ChatColor.RED}Disconnect")
                lore(
                    "${ChatColor.GRAY}You may get banned by",
                    "${ChatColor.GRAY}our staff for logging out!",
                    "",
                    "${ChatColor.WHITE}Logout at your own discretion!",
                    "",
                    "${ChatColor.DARK_RED}Staff will receive a notification",
                    "${ChatColor.DARK_RED}regarding your disconnection.",
                )
            }
        )

        return inventory
    }
}