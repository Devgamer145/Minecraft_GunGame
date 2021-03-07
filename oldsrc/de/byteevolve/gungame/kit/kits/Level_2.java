package de.byteevolve.gungame.kit.kits;

import de.byteevolve.gungame.itembuilder.ItemBuilder;
import de.byteevolve.gungame.itembuilder.LeatherBuilder;
import de.byteevolve.gungame.itembuilder.SkullBuilder;
import de.byteevolve.gungame.kit.KitInventory;
import net.minecraft.server.v1_8_R3.ItemBoat;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Level_2 implements KitInventory {
    @Override
    public void load(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.getInventory().setItem(0, new ItemBuilder(Material.WOOD_SWORD, 1).build());
        player.getInventory().setChestplate(new LeatherBuilder(Material.LEATHER_CHESTPLATE," ").setColor(Color.TEAL).build());
    }
}
