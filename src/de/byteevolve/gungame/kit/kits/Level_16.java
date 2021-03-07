package de.byteevolve.gungame.kit.kits;

import de.byteevolve.gungame.itembuilder.ItemBuilder;
import de.byteevolve.gungame.itembuilder.LeatherBuilder;
import de.byteevolve.gungame.kit.KitInventory;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class Level_16 implements KitInventory {

    @Override
    public void load(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.getInventory().setItem(0, new ItemBuilder(Material.GOLD_SWORD, 1).build());
        player.getInventory().setChestplate(new ItemBuilder(Material.GOLD_CHESTPLATE,1).setName(" ").build());
        player.getInventory().setLeggings(new LeatherBuilder(Material.LEATHER_LEGGINGS," ").setCount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1).setColor(Color.TEAL).build());
        player.getInventory().setBoots(new ItemBuilder(Material.GOLD_BOOTS,1).setName(" ").build());
        player.getInventory().setHelmet(new ItemBuilder(Material.GOLD_HELMET,1).setName(" ").build());
    }
}
