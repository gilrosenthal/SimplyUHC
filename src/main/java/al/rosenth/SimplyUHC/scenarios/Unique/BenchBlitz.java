package al.rosenth.SimplyUHC.scenarios.Unique;

import al.rosenth.SimplyUHC.scenarios.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.CraftItemEvent;

import java.util.HashMap;

/**
 * Created by Gil on 8/16/2015.
 */
public class BenchBlitz implements Scenario {
    HashMap<Player,Boolean> playerCrafted = new HashMap();
    public void load(){
        Bukkit.getPluginManager().registerEvents(this,Bukkit.getPluginManager().getPlugin("SimplyUHC"));
        for(Player p:Bukkit.getOnlinePlayers()){
            playerCrafted.put(p,false);
        }
    }
    public void unload(){}
    @EventHandler
    public void onItemCraft(CraftItemEvent e){
        if(e.getInventory().getResult().getType()== Material.WORKBENCH){
            if(playerCrafted.get((Player)e.getWhoClicked())==false){
                e.setCancelled(false);
                playerCrafted.put((Player)e.getWhoClicked(),true);
                return;
            }
            else{
                e.setCancelled(true);
                e.getWhoClicked().sendMessage("Already made a workbench!");
                return;
            }
        }
    }
}
