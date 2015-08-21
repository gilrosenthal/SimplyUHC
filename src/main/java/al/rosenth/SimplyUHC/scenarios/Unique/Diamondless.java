package al.rosenth.SimplyUHC.scenarios.Unique;

import al.rosenth.SimplyUHC.scenarios.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * Created by Gil on 8/19/2015.
 */
public class Diamondless implements Scenario {
     public void load(){
        Bukkit.getPluginManager().registerEvents(this, Bukkit.getPluginManager().getPlugin("SimplyUHC"));
    }
    public void unload(){}
    public void onBlockBreack(BlockBreakEvent e){
        if(e.getBlock().getType()== Material.DIAMOND_ORE){
            e.setCancelled(true);
            e.getBlock().setType(Material.AIR);
        }
    }
}
