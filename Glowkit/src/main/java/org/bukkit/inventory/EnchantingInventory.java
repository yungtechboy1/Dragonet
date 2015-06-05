package org.bukkit.inventory;

/**
 * Interface to the inventory of an Enchantment Table.
 */
public interface EnchantingInventory extends Inventory {

    /**
     * Set the item being enchanted.
     *
     * @param item The new item
     */
    void setItem(ItemStack item);

    /**
     * Get the item being enchanted.
     *
     * @return The current item.
     */
    ItemStack getItem();

    /**
     * Set the item in the resource slot (usually Lapis).
     *
     * @param item The new item
     */
    void setResource(ItemStack item);

    /**
     * Get the item in the resource slot (usually Lapis).
     *
     * @return The current item
     */
    ItemStack getResource();
}