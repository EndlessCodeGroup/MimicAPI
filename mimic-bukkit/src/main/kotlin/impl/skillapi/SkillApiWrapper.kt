/*
 * This file is part of BukkitMimic.
 * Copyright (C) 2020 Osip Fatkullin
 * Copyright (C) 2020 EndlessCode Group and contributors
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

package ru.endlesscode.mimic.impl.skillapi

import com.sucy.skill.SkillAPI
import com.sucy.skill.api.player.PlayerData
import com.sucy.skill.data.Settings
import org.bukkit.entity.Player

internal class SkillApiWrapper {
    val isLoaded: Boolean get() = SkillAPI.isLoaded()
    val settings: Settings get() = SkillAPI.getSettings()

    fun getPlayerData(player: Player): PlayerData = SkillAPI.getPlayerData(player)
}
