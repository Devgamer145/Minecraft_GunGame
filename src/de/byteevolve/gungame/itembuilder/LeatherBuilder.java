package de.byteevolve.gungame.itembuilder;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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
    private Enchantment enchantment;
    private int level;

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
        if(enchantment != null){
            leatherArmorMeta.addEnchant(enchantment,level,true);
        }
        itemStack.setItemMeta((ItemMeta) leatherArmorMeta);
        return itemStack;
    }
    public LeatherBuilder addEnchant(Enchantment enchantment, int level) {
        this.enchantment = enchantment;
        this.level = level;
        return this;
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

