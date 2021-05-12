package de.byteevolve.gungame.itembuilder;

import de.byteevolve.gungame.itembuilder.unbreakable.*;
import org.bukkit.Bukkit;
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
    private boolean unbreakable;

    public LeatherBuilder(Material material, String name) {
        this.material = material;
        this.name = name;
        this.unbreakable = false;
    }


    public ItemStack build() {
        ItemStack itemStack = new ItemStack(this.material, this.count);

        if(this.unbreakable) {
            Unbreakable unbreakable = null;

            String version = "N/A";
            try {
                version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

                switch (version) {
                    case "v1_8_R3":
                        unbreakable = new v1_8_R3_Unbreakable();
                        break;
                    case "v1_9_R2":
                        unbreakable = new v1_9_R2_Unbreakable();
                        break;
                    case "v1_10_R1":
                        unbreakable = new v1_10_R1_Unbreakable();
                        break;
                    case "v1_11_R1":
                        unbreakable = new v1_11_R1_Unbreakable();
                        break;
                    case "v1_12_R1":
                        unbreakable = new v1_12_R1_Unbreakable();
                        break;
                    case "v1_13_R2":
                        unbreakable = new v1_13_R2_Unbreakable();
                        break;
                    case "v1_14_R1":
                        unbreakable = new v1_14_R1_Unbreakable();
                        break;
                    case "v1_15_R1":
                        unbreakable = new v1_15_R1_Unbreakable();
                        break;
                    case "v1_16_R3":
                        unbreakable = new v1_16_R3_Unbreakable();
                        break;
                }

                itemStack = unbreakable.makeUnbreakable(itemStack);

            } catch (ArrayIndexOutOfBoundsException ex) {
                ex.printStackTrace();
            }

        }

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
    public LeatherBuilder setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
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

