/*
 * This file is part of MimicAPI.
 * Copyright (C) 2017 Osip Fatkullin
 *
 * MimicAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MimicAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MimicAPI.  If not, see <http://www.gnu.org/licenses/>.
 */

package ru.endlesscode.mimic.system;

import ru.endlesscode.mimic.system.registry.Metadata;
import ru.endlesscode.mimic.system.registry.SystemPriority;

/**
 * Basic implementation of LevelSystem independent from player-related object
 *
 * @author Osip Fatkullin
 * @since 1.0
 */
@Metadata(priority = SystemPriority.LOWEST)
public class BasicLevelSystemImpl extends LevelSystem {
    private int level = 0;
    private int exp = 0;

    /**
     * Constructor that initialize basic converter.
     */
    public BasicLevelSystemImpl() {
        super(new BasicConverterImp());
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return "Basic Level System";
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public void setLevel(int newLevel) {
        this.level = Math.max(0, newLevel);
    }

    @Override
    public int getExp() {
        return this.exp;
    }

    @Override
    public void setExp(int newExperience) {
        newExperience = Math.max(0, newExperience);
        newExperience = Math.min(converter.getExpToReachNextLevel(0) - 1, newExperience);

        this.exp = newExperience;
    }

    @Override
    public int getExpToNextLevel() {
        return converter.getExpToReachNextLevel(0) - this.exp;
    }
}