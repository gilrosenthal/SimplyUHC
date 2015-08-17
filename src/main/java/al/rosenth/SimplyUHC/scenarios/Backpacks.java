package al.rosenth.SimplyUHC.scenarios;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gil on 8/16/2015.
 */
public class Backpacks implements Scenario{
    ItemStack customBackPack=new ItemStack(Material.CHEST,1);
    public void load(){
        Bukkit.getPluginManager().registerEvents(this,Bukkit.getPluginManager().getPlugin("SimplyUHC"));
        for(Player p:Bukkit.getOnlinePlayers()){
            ItemStack customBackPack=new ItemStack(Material.CHEST,1);
            ItemMeta im = customBackPack.getItemMeta();
            im.setDisplayName(ChatColor.BLUE + "" + "Backpack");
            List<String> lore = new ArrayList<String>();
            lore.add(ChatColor.DARK_AQUA + "Your backpack that has an extra inventory");
            im.setLore(lore);
            customBackPack.setItemMeta(im);
            p.getInventory().addItem(customBackPack);
        }
    }
    public void unload(){}
    @EventHandler
    public void onItemDrop (PlayerDropItemEvent e){
        Item drop = e.getItemDrop();
        if(e.getItemDrop().getName().equalsIgnoreCase(ChatColor.BLUE + "" + "Backpack")){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        try {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (e.getItem().getItemMeta().getLore().contains(ChatColor.DARK_AQUA + "Your backpack that has an extra inventory")) {
                    e.setCancelled(true);
                }
            } else {

                if (e.getItem().getItemMeta().getLore().contains(ChatColor.DARK_AQUA + "Your backpack that has an extra inventory")) {
                    e.getPlayer().openInventory(e.getPlayer().getEnderChest());
                }
            }
        }
        catch(NullPointerException ex){}
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
       Location deathLoc = e.getEntity().getLocation();
        deathLoc.getBlock().setType(Material.CHEST);
        Chest deathChest =(Chest)deathLoc.getBlock().getState();
        deathChest.getInventory().setContents(e.getEntity().getEnderChest().getContents());
    }

}
