package de.byteevolve.gungame.kit.kits;

import de.byteevolve.gungame.itembuilder.ItemBuilder;
import de.byteevolve.gungame.kit.KitInventory;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class Level_30 implements KitInventory {

    @Override
    public void load(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.getInventory().setItem(0, new ItemBuilder(Material.STONE_AXE, 1).addEnchant(Enchantment.DAMAGE_ALL,1).build());
        player.getInventory().setChestplate(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE,1).setName(" ").build());
        player.getInventory().setLeggings(new ItemBuilder(Material.CHAINMAIL_LEGGINGS,1).setName(" ").build());
        player.getInventory().setBoots(new ItemBuilder(Material.CHAINMAIL_BOOTS,1).setName(" ").build());
        player.getInventory().setHelmet(new ItemBuilder(Material.CHAINMAIL_HELMET,1).setName(" ").build());
    }
}
