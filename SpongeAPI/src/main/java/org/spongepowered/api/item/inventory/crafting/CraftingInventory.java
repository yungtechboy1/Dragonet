/*
 * This file is part of SpongeAPI, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.api.item.inventory.crafting;

import com.google.common.base.Optional;
import org.spongepowered.api.item.inventory.type.GridInventory;
import org.spongepowered.api.item.recipe.Recipe;

/**
 * A CraftingInventory represents the inventory of something that can craft items.
 */
public interface CraftingInventory extends GridInventory {

    /**
     * Gets the crafting matrix of this CraftingInventory.
     *
     * @return The crafting matrix
     */
    GridInventory getCraftingGrid();

    /**
     * Gets the result slot of this CraftingInventory.
     *
     * @return The result slot
     */
    CraftingOutput getResult();

    /**
     * Retrieves the recipe formed by this CraftingInventory, if any.
     *
     * @return The recipe or {@link Optional#absent()} if no recipe is formed
     */
    Optional<Recipe> getRecipe();

}
