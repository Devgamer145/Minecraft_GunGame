package de.byteevolve.gungame.player.respawn;

import de.byteevolve.gungame.GunGame;
import net.minecraft.server.v1_9_R2.PacketPlayInClientCommand;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class v1_9_R2_Respawn implements GGRespawn{

    @Override
    public void respawn(Player player) {
        final CraftPlayer craftPlayer = (CraftPlayer) player;
        GunGame.getInstance().getServer().getScheduler()
                .scheduleSyncDelayedTask(GunGame.getInstance(), new Runnable() {
                    public void run() {
                        if (player.isDead()) {
                            craftPlayer.getHandle().playerConnection
                                    .a(new PacketPlayInClientCommand(
                                            PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
                        }
                    }
                });
    }

}
