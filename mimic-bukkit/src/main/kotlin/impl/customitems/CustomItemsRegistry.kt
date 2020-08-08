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

package ru.endlesscode.mimic.impl.customitems

import com.jojodmo.customitems.api.CustomItemsAPI
import org.bukkit.inventory.ItemStack
import ru.endlesscode.mimic.items.BukkitItemsRegistry

class CustomItemsRegistry : BukkitItemsRegistry {

    companion object {
        const val ID = "customitems"
    }

    override val isEnabled: Boolean get() = CustomItemsAPI.isEnabled()

    override val id: String = ID

    // INFO: CustomItems doesn't support returning list of known ids."
    override val knownIds: Collection<String> = emptyList()

    override fun isSameItem(item: ItemStack, itemId: String): Boolean = CustomItemsAPI.isCustomItem(item, itemId)

    override fun isItemExists(itemId: String): Boolean = CustomItemsAPI.getCustomItem(itemId) != null

    override fun getItemId(item: ItemStack): String? = CustomItemsAPI.getCustomItemID(item)

    override fun getItem(itemId: String, amount: Int): ItemStack? = CustomItemsAPI.getCustomItem(itemId, amount)
}