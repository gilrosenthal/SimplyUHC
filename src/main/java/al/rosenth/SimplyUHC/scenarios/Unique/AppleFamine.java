package al.rosenth.SimplyUHC.scenarios.Unique;

import al.rosenth.SimplyUHC.scenarios.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;

/**
 * Created by Gil on 8/16/2015.
 */
public class AppleFamine implements Scenario {
    public void load(){
        Bukkit.getPluginManager().registerEvents(this,Bukkit.getPluginManager().getPlugin("SimplyUHC"));
    }
    public void unload(){}
    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockBreak(BlockBreakEvent e){
        if(e.getBlock().getType()== Material.LEAVES){
            e.setCancelled(true);
            e.getBlock().setType(Material.AIR);
        }
        else{}
    }
    @EventHandler
    public void onLeaveDecay(LeavesDecayEvent e){
        e.setCancelled(true);
        e.getBlock().setType(Material.AIR);
    }
}
