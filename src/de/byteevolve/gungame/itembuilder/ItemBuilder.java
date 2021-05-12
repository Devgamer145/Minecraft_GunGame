package de.byteevolve.gungame.itembuilder;

import de.byteevolve.gungame.itembuilder.unbreakable.*;
import de.byteevolve.gungame.player.scoreboard.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;


public class ItemBuilder {
    private Material material;
    private String name;
    private int count;
    private int subid;
    private List<String> lore;
    private Enchantment enchantment;
    private int level;
    private boolean unbreakable;

    public ItemBuilder(Material material, int count) {
        this.material = material;
        this.count = count;
        this.lore = new ArrayList<>();
        this.unbreakable = false;
    }

    public ItemStack build() {
        ItemStack itemStack = new ItemStack(this.material, this.count, (short) this.subid);


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

            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setLore(this.lore);
            itemMeta.setDisplayName(this.name);
            if (enchantment != null) {
                itemMeta.addEnchant(enchantment, level, true);
            }
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }


    public ItemBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }
    public ItemBuilder addEnchant(Enchantment enchantment, int level) {
        this.enchantment = enchantment;
        this.level = level;
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
        return this;
    }

    public ItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder setCount(int count) {
        this.count = count;
        return this;
    }

    public ItemBuilder setSubId(int subid) {
        this.subid = subid;
        return this;
    }

    public ItemBuilder addLore(String lore) {
        this.lore.add(lore);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }
}
