package de.byteevolve.gungame.listener;

import de.byteevolve.gungame.GunGame;
import de.byteevolve.gungame.arena.Arena;
import de.byteevolve.gungame.arena.ArenaHandler;
import de.byteevolve.gungame.arena.ArenaTeamState;
import de.byteevolve.gungame.location.Loc;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Listener_ArenaEdit implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
            event.setCancelled(true);
            GunGame gunGame = GunGame.getInstance();
            ArenaHandler arenaHandler = gunGame.getArenaHandler();
            Arena arena = null;
            if(arenaHandler.getArenaEditList().containsKey(player)){
                arena = arenaHandler.getArenaEditList().get(player);
            } else if (arenaHandler.getArenaPlayerCreate().containsKey(player)) {
                arena = arenaHandler.getArenaPlayerCreate().get(player);
            }else{
                player.closeInventory();
                player.sendMessage(gunGame.getPrefix() + "§cDiese Arena gibt es nicht.");
            }
            ItemStack item = event.getCurrentItem();
            ItemMeta itemMeta = item.getItemMeta();
            String displayName = itemMeta.getDisplayName();
            if(displayName.equalsIgnoreCase("§7Spawn setzen")){
                Loc loc = new Loc(arena.getName() + "spawn");
                loc.setFromLocation(player.getLocation());
                loc.update();
                gunGame.getLocationHandler().loadLocs();
                arena.setSpawn(arena.getName() + "spawn");
                if(arena.getFinished() == 1) {
                    arena.update();
                    player.sendMessage(gunGame.getPrefix() + "§7Du hast die Arena: §a" + arena.getDisplayname() + "§7 geupdatet.");
                    player.closeInventory();
                    if(arenaHandler.getArenaEditList().containsKey(player)){
                        arenaHandler.getArenaEditList().remove(player);
                    }
                }else {
                    player.sendMessage(gunGame.getPrefix() + "§7Du hast die Spawn-Location gesetzt.");
                    player.closeInventory();
                }
            }else if(displayName.equalsIgnoreCase("§aObere-SpawnProt setzen")){
                Loc loc = new Loc(arena.getName() + "topprot");
                loc.setFromLocation(player.getLocation());
                loc.update();
                gunGame.getLocationHandler().loadLocs();
                arena.setMaxSpawn(arena.getName() + "topprot");
                if(arena.getFinished() == 1) {
                    arena.update();
                    player.sendMessage(gunGame.getPrefix() + "§7Du hast die Arena: §a" + arena.getDisplayname().replaceAll("&", "§") + "§7 geupdatet.");
                    player.closeInventory();
                    if(arenaHandler.getArenaEditList().containsKey(player)){
                        arenaHandler.getArenaEditList().remove(player);
                    }
                }else {
                    player.sendMessage(gunGame.getPrefix() + "§7Du hast die ObereSpawnProt-Location gesetzt.");
                    player.closeInventory();
                }
            }else if(displayName.equalsIgnoreCase("§aUntere-SpawnProt setzen")){
                Loc loc = new Loc(arena.getName() + "bottomprot");
                loc.setFromLocation(player.getLocation());
                loc.update();
                gunGame.getLocationHandler().loadLocs();
                arena.setMinSpawn(arena.getName() + "bottomprot");
                if(arena.getFinished() == 1) {
                    arena.update();
                    player.sendMessage(gunGame.getPrefix() + "§7Du hast die Arena: §a" + arena.getDisplayname().replaceAll("&", "§") + "§7 geupdatet.");
                    player.closeInventory();
                    if(arenaHandler.getArenaEditList().containsKey(player)){
                        arenaHandler.getArenaEditList().remove(player);
                    }
                }else {
                    player.sendMessage(gunGame.getPrefix() + "§7Du hast die UntereSpawnProt-Location gesetzt.");
                    player.closeInventory();
                }
            }else if(displayName.equalsIgnoreCase("§aTeamsState §7setzen")){
                String state = itemMeta.getLore().get(0).replaceAll("§7State: §a", "");
                ArenaTeamState arenaTeamState = ArenaTeamState.valueOf(state);
                if(arenaTeamState.equals(ArenaTeamState.ALLOWED)){
                    arena.setArenaTeamState(ArenaTeamState.DISALLOWED);
                }else{
                    arena.setArenaTeamState(ArenaTeamState.ALLOWED);
                }
                if(arena.getFinished() ==  1) {
                    arena.update();
                    player.sendMessage(gunGame.getPrefix() + "§7Du hast die Arena: §a" + arena.getDisplayname().replaceAll("&", "§") + "§7 geupdatet.");
                    player.closeInventory();
                    if(arenaHandler.getArenaEditList().containsKey(player)){
                        arenaHandler.getArenaEditList().remove(player);
                    }
                }else {
                    player.sendMessage(gunGame.getPrefix() + "§7Du hast den TeamState geändert.");
                    player.closeInventory();
                }
            }else if(displayName.equalsIgnoreCase("§aBeenden")){
                if(arena.getFinished() == 0) {
                    if (arena.getSpawn() != null && arena.getMinSpawn() != null && arena.getMaxSpawn() != null) {
                        arena.setFinished(1);
                        arena.update();
                        if(arenaHandler.getArenaPlayerCreate().containsKey(player)){
                            arenaHandler.getArenaPlayerCreate().remove(player);
                        }
                        player.sendMessage(gunGame.getPrefix() + "§7Die Arena:§a" + arena.getDisplayname().replaceAll("&", "§") + " §7wurde beendet.");
                        player.closeInventory();
                        if(arenaHandler.getArenaEditList().containsKey(player)){
                            arenaHandler.getArenaEditList().remove(player);
                        }
                    }else{
                        player.sendMessage(gunGame.getPrefix() + "§cDie Arena ist noch nicht fertig.");
                        player.closeInventory();
                    }
                }else{
                    player.sendMessage(gunGame.getPrefix() + "§cDie Arena ist schon beendet.");
                    player.closeInventory();

            }

        }
    }
}
