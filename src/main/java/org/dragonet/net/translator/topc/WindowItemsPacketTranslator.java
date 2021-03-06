/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dragonet.net.translator.topc;

import com.flowpowered.networking.Message;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.dragonet.inventory.PEInventorySlot;
import org.dragonet.inventory.PEWindowConstantID;
import org.dragonet.net.DragonetSession;
import org.dragonet.net.packet.minecraft.WindowItemsPacket;
import org.dragonet.net.translator.PEPacketTranslatorToPC;
import org.dragonet.net.translator.Translator_v0_11;

public class WindowItemsPacketTranslator extends PEPacketTranslatorToPC<Translator_v0_11, WindowItemsPacket> {

    public WindowItemsPacketTranslator(Translator_v0_11 translator, DragonetSession session) {
        super(translator, session);
    }

    @Override
    public Message[] handleSpecific(WindowItemsPacket packet) {
        if (packet.windowID != PEWindowConstantID.PLAYER_CRAFTING) {
            return null; //We only handle crafting for now. 
        }
        if (packet.slots.length < 9) {
            emptyCrafting();
            return null;
        }
        ItemStack[] map = new ItemStack[9];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                map[x * 3 + y] = new ItemStack(this.getTranslator().getItemTranslator().translateToPC(packet.slots[x * 3 + y].id & 0xFFFF),
                        packet.slots[x * 3 + y].count & 0xFF, packet.slots[x * 3 + y].meta);
            }
        }
        Recipe recipe = this.getSession().getServer().getCraftingManager().getCraftingRecipe(map);
        if (recipe == null) {
            emptyCrafting();
            return null;
        }
        this.getSession().getPlayer().getInventory().addItem(recipe.getResult());
        this.getSession().getServer().getCraftingManager().removeItems(this.getSession().getPlayer().getInventory().getContents(), recipe);
        emptyCrafting();
        return null;
    }

    public void emptyCrafting() {
        WindowItemsPacket pk = new WindowItemsPacket();
        pk.windowID = PEWindowConstantID.PLAYER_CRAFTING;
        pk.slots = new PEInventorySlot[]{
            new PEInventorySlot((short) 0, (byte) 0, (short) 0),
            new PEInventorySlot((short) 0, (byte) 0, (short) 0),
            new PEInventorySlot((short) 0, (byte) 0, (short) 0),
            new PEInventorySlot((short) 0, (byte) 0, (short) 0),
            new PEInventorySlot((short) 0, (byte) 0, (short) 0),
            new PEInventorySlot((short) 0, (byte) 0, (short) 0),
            new PEInventorySlot((short) 0, (byte) 0, (short) 0),
            new PEInventorySlot((short) 0, (byte) 0, (short) 0),
            new PEInventorySlot((short) 0, (byte) 0, (short) 0),};
        this.getSession().sendInventory();
        this.getSession().send(pk);
    }
}
