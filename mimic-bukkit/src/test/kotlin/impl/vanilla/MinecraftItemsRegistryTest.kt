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

package ru.endlesscode.mimic.impl.vanilla

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import ru.endlesscode.mimic.items.BukkitItemsRegistry
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class MinecraftItemsRegistryTest {

    // SUT
    private lateinit var itemsService: BukkitItemsRegistry

    @BeforeTest
    fun setUp() {
        itemsService = MinecraftItemsRegistry()
    }

    @Test
    fun `when get item - should return item stack`() {
        // When
        val item = itemsService.getItem("acacia_boat")!!

        // Then
        assertEquals(Material.ACACIA_BOAT, item.type)
        assertEquals(1, item.amount)
    }

    @Test
    fun `when get unknown item - should return null`() {
        // When
        val item = itemsService.getItem("super_duper_item")

        // Then
        assertNull(item)
    }

    @ParameterizedTest
    @CsvSource(
        "cobblestone,   0,  1",
        "cobblestone,   32, 32",
        "cobblestone,   65, 64",
        "acacia_boat,   2,  1"
    )
    fun `when get item with amount - should return item stack with right amount`(
        itemId: String,
        amount: Int,
        realAmount: Int
    ) {
        // When
        val item = itemsService.getItem(itemId, amount)!!

        // Then
        assertEquals(realAmount, item.amount)
    }

    @Test
    fun `when get id - should return id`() {
        // Given
        val item = ItemStack(Material.ACACIA_BOAT)

        // When
        val itemId = itemsService.getItemId(item)

        // Then
        assertEquals("acacia_boat", itemId)
    }

    @ParameterizedTest
    @CsvSource(
        "air,           true",
        "unknown,       false",
        "gold_sword,    false",
        "golden_sword,  true"
    )
    fun `when check is item exists`(itemId: String, shouldExist: Boolean) {
        // When
        val exists = itemsService.isItemExists(itemId)

        // Then
        assertEquals(shouldExist, exists)
    }
}
