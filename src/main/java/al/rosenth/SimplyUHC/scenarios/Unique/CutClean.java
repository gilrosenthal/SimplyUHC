package al.rosenth.SimplyUHC.scenarios;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

/**
 * Created by Gil on 8/11/2015.
 */
public class CutClean implements Scenario {
    public void load(){
        Bukkit.getPluginManager().registerEvents(this,Bukkit.getPluginManager().getPlugin("SimplyUHC"));
    }
    public void unload(){

    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Block b = e.getBlock();
        Random r = new Random();
        if(e.getBlock().getType()== Material.IRON_ORE){
            e.setCancelled(true);
            b.setType(Material.AIR);
            e.getBlock().getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.IRON_INGOT));
        }
        else if(e.getBlock().getType()  ==  Material.GOLD_ORE){
            e.setCancelled(true);
            b.setType(Material.AIR);
            e.getBlock().getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.GOLD_INGOT));
        }
        else if(e.getBlock().getType()  ==  Material.GRAVEL){
            e.setCancelled(true);
            b.setType(Material.AIR);
            if(r.nextInt(4) != 0) {
                e.getBlock().getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.GOLD_INGOT));
            }
        }
        else if(e.getBlock().getType()  ==  Material.LEAVES){
            e.setCancelled(true);
            b.setType(Material.AIR);
            if(r.nextInt(4) != 0) {
                e.getBlock().getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.GOLD_INGOT));
            }
        }
    }
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            return;
        }
        if (event.getEntity() instanceof Cow) {
            event.getDrops().clear();
            event.getDrops().add(new ItemStack(Material.COOKED_BEEF, 3));
            event.getDrops().add(new ItemStack(Material.LEATHER, 1));
        } else if (event.getEntity() instanceof Pig) {
            event.getDrops().clear();
            event.getDrops().add(new ItemStack(Material.GRILLED_PORK, 3));
        } else if (event.getEntity() instanceof Chicken) {
            event.getDrops().clear();
            event.getDrops().add(new ItemStack(Material.COOKED_CHICKEN, 3));
            event.getDrops().add(new ItemStack(Material.FEATHER, 2));
        }
    }


}
