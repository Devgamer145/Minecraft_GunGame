package de.byteevolve.gungame.kit.kits;

import de.byteevolve.gungame.itembuilder.ItemBuilder;
import de.byteevolve.gungame.kit.KitInventory;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class Level_38 implements KitInventory {

    @Override
    public void load(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.getInventory().setItem(0, new ItemBuilder(Material.IRON_SWORD, 1).setUnbreakable(true).build());
        player.getInventory().setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE,1).setUnbreakable(true).setName(" ").build());
        player.getInventory().setLeggings(new ItemBuilder(Material.CHAINMAIL_LEGGINGS,1).setUnbreakable(true).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1).setName(" ").build());
        player.getInventory().setBoots(new ItemBuilder(Material.CHAINMAIL_BOOTS,1).setUnbreakable(true).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1).setName(" ").build());
        player.getInventory().setHelmet(new ItemBuilder(Material.CHAINMAIL_HELMET,1).setUnbreakable(true).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1).setName(" ").build());
    }
}
