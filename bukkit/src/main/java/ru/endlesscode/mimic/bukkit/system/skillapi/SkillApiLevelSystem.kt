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
package ru.endlesscode.mimic.bukkit.system.skillapi

import com.sucy.skill.api.enums.ExpSource
import com.sucy.skill.api.player.PlayerClass
import org.bukkit.entity.Player
import ru.endlesscode.mimic.api.system.registry.Subsystem
import ru.endlesscode.mimic.api.system.registry.SubsystemPriority
import ru.endlesscode.mimic.bukkit.system.BukkitLevelSystem

/** Implementation of LevelSystem that uses SkillAPI. */
@Subsystem(priority = SubsystemPriority.NORMAL, classes = ["com.sucy.skill.SkillAPI"])
class SkillApiLevelSystem internal constructor(
    player: Player,
    private val skillApi: SkillApiWrapper
) : BukkitLevelSystem(SkillApiConverter.getInstance(skillApi), player) {

    companion object {
        const val TAG = "SkillAPI"

        @JvmField
        val FACTORY = Factory(TAG, ::SkillApiLevelSystem)
    }

    private constructor(player: Player) : this(player, SkillApiWrapper())

    override val name: String = TAG
    override val isEnabled: Boolean
        get() = skillApi.isLoaded

    override var level: Int
        get() = playerClass?.level ?: 0
        set(newLevel) {
            playerClass?.level = newLevel
        }

    override var exp: Double
        get() = playerClass?.exp ?: 0.0
        set(newExperience) {
            playerClass?.exp = newExperience
        }

    override val totalExpToNextLevel: Double
        get() = playerClass?.requiredExp?.toDouble() ?: -1.0

    private val playerClass: PlayerClass?
        get() = skillApi.getPlayerData(player).mainClass

    override fun takeLevel(lvlAmount: Int) {
        if (playerClass != null) {
            val newLevel = (level - lvlAmount).coerceAtLeast(1)
            val fractional = fractionalExp
            level = newLevel
            fractionalExp = fractional
        }
    }

    override fun giveLevel(lvlAmount: Int) {
        playerClass?.giveLevels(lvlAmount)
    }

    override fun takeExp(expAmount: Double) {
        val playerClass = playerClass
        if (playerClass != null) {
            val percent = expAmount / playerClass.requiredExp
            playerClass.loseExp(percent)
        }
    }

    override fun giveExp(expAmount: Double) {
        playerClass?.giveExp(expAmount, ExpSource.SPECIAL)
    }
}
