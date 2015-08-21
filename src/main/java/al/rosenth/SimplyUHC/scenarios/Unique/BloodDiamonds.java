package al.rosenth.SimplyUHC.scenarios.Unique;

import al.rosenth.SimplyUHC.scenarios.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by KalyanPalepu on 8/19/2015.
 */
public class BloodDiamonds implements Scenario {
     public void load(){
        Bukkit.getPluginManager().registerEvents(this, Bukkit.getPluginManager().getPlugin("SimplyUHC"));
    }
    public void unload(){}
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Block b = e.getBlock();
        Player p = e.getPlayer();
        double h = p.getHealth();
        if(e.getBlock().getType()== Material.DIAMOND_ORE){
            p.setHealth(h - 1);
        }
    }
}