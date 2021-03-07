package de.byteevolve.gungame.itembuilder;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.List;


public class LeatherBuilder {
    private Material material;
    private String name;
    private List<String> lore;
    private Color color;
    private int count;

    public LeatherBuilder(Material material, String name) {
        this.material = material;
        this.name = name;
    }


    public ItemStack build() {
        ItemStack itemStack = new ItemStack(this.material, this.count);
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
        leatherArmorMeta.setColor(this.color);
        leatherArmorMeta.setDisplayName(this.name);
        leatherArmorMeta.setLore(this.lore);
        itemStack.setItemMeta((ItemMeta) leatherArmorMeta);
        return itemStack;
    }

    public LeatherBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public LeatherBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public LeatherBuilder setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public LeatherBuilder setColor(Color color) {
        this.color = color;
        return this;
    }

    public LeatherBuilder setCount(int count) {
        this.count = count;
        return this;
    }
}

