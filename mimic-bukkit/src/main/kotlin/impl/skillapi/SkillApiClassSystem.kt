/*
 * This file is part of BukkitMimic.
 * Copyright (C) 2018 Osip Fatkullin
 * Copyright (C) 2018 EndlessCode Group and contributors
 *
 * BukkitMimic is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BukkitMimic is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with BukkitMimic.  If not, see <http://www.gnu.org/licenses/>.
 */
package ru.endlesscode.mimic.bukkit.impl.skillapi

import com.sucy.skill.api.player.PlayerData
import org.bukkit.entity.Player
import ru.endlesscode.mimic.bukkit.BukkitClassSystem
import ru.endlesscode.mimic.util.checkClassesExist

/** Implementation of ClassSystem that uses SkillAPI. */
class SkillApiClassSystem internal constructor(
    player: Player,
    private val skillApi: SkillApiWrapper
) : BukkitClassSystem(player) {

    companion object {
        const val ID = "skillapi"

        @JvmField
        val provider: Provider = object : Provider(ID) {

            private val skillApi = SkillApiWrapper()

            override val isEnabled: Boolean
                get() = checkClassesExist("com.sucy.skill.SkillAPI") && skillApi.isLoaded

            override fun getSystem(player: Player): BukkitClassSystem = SkillApiClassSystem(player, skillApi)
        }
    }

    override val classes: List<String>
        get() = playerData.classes.map { it.data.name }

    override val primaryClass: String?
        get() = playerData.mainClass?.data?.name

    private val playerData: PlayerData
        get() = skillApi.getPlayerData(player)
}