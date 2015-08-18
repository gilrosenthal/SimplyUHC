package al.rosenth.SimplyUHC.scenarios.Hybrid;

import al.rosenth.SimplyUHC.scenarios.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * Created by Gil on 8/17/2015.
 */
public class HalfOresCutClean implements Scenario {
     private HashMap<Player,Integer> iron_count = new HashMap();
    private HashMap<Player,Integer> gold_count = new HashMap();
    private HashMap<Player,Integer> coal_count = new HashMap();
    private HashMap<Player,Integer> diamond_count = new HashMap();
    private HashMap<Player,Integer> lapis_count = new HashMap();
    private HashMap<Player,Integer> redstone_count = new HashMap();


    public void load(){
        for(Player p:Bukkit.getServer().getOnlinePlayers()){
            iron_count.put(p,0);
            gold_count.put(p,0);
            coal_count.put(p,0);
            diamond_count.put(p,0);
            lapis_count.put(p,0);
            redstone_count.put(p,0);
        }
        Bukkit.getPluginManager().registerEvents(this, Bukkit.getPluginManager().getPlugin("SimplyUHC"));
    }
    public void unload(){};
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
          Material m = e.getBlock().getType();
        if(m==Material.IRON_ORE){
            if(iron_count.get(e.getPlayer())==null){
                iron_count.put(e.getPlayer(),1);
            }
            else{
                if(iron_count.get(e.getPlayer())==1){
                     e.setCancelled(true);
                     e.getBlock().setType(Material.AIR);
                     e.getPlayer().getItemInHand().setDurability((short) (e.getPlayer().getItemInHand().getDurability() - 1));
                     e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
                     iron_count.put(e.getPlayer(),0);
                }
                else if(iron_count.get(e.getPlayer())==0){
                    e.setCancelled(true);
                    e.getBlock().setType(Material.AIR);
                    e.getPlayer().getItemInHand().setDurability((short)(e.getPlayer().getItemInHand().getDurability()-1));
                    iron_count.put(e.getPlayer(),1);
                }
            }
        }
        else if(m==Material.GOLD_ORE){
            if(gold_count.get(e.getPlayer())==null){
                gold_count.put(e.getPlayer(),1);
            }
            else{
                if(gold_count.get(e.getPlayer())==1){
                    e.setCancelled(true);
                    e.getBlock().setType(Material.AIR);
                    e.getPlayer().getItemInHand().setDurability((short) (e.getPlayer().getItemInHand().getDurability() - 1));
                    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
                    gold_count.put(e.getPlayer(),0);
                }
                else if(gold_count.get(e.getPlayer())==0){
                    e.setCancelled(true);
                    e.getBlock().setType(Material.AIR);
                    e.getPlayer().getItemInHand().setDurability((short) (e.getPlayer().getItemInHand().getDurability() - 1));
                    gold_count.put(e.getPlayer(),1);
                }
            }
        }
        else if(m==Material.COAL_ORE){
            if(coal_count.get(e.getPlayer())==null){
                coal_count.put(e.getPlayer(),1);
            }
            else{
                if(coal_count.get(e.getPlayer())==1){
                    coal_count.put(e.getPlayer(),0);
                }
                else if(coal_count.get(e.getPlayer())==0){
                    e.setCancelled(true);
                    e.getBlock().setType(Material.AIR);
                    e.getPlayer().getItemInHand().setDurability((short) (e.getPlayer().getItemInHand().getDurability() - 1));
                    coal_count.put(e.getPlayer(),1);
                }
            }
        }
        else if(m==Material.DIAMOND_ORE){
            if(diamond_count.get(e.getPlayer())==null){
                diamond_count.put(e.getPlayer(),1);
            }
            else{
                if(diamond_count.get(e.getPlayer())==1){
                    diamond_count.put(e.getPlayer(),0);
                }
                else if(diamond_count.get(e.getPlayer())==0){
                    e.setCancelled(true);
                    e.getBlock().setType(Material.AIR);
                    e.getPlayer().getItemInHand().setDurability((short) (e.getPlayer().getItemInHand().getDurability() - 1));
                    diamond_count.put(e.getPlayer(),1);
                }
            }
        }
        else if(m==Material.LAPIS_ORE){
            if(lapis_count.get(e.getPlayer())==null){
                lapis_count.put(e.getPlayer(),1);
            }
            else{
                if(lapis_count.get(e.getPlayer())==1){
                    lapis_count.put(e.getPlayer(),0);
                }
                else if(lapis_count.get(e.getPlayer())==0){
                    e.setCancelled(true);
                    e.getBlock().setType(Material.AIR);
                    e.getPlayer().getItemInHand().setDurability((short) (e.getPlayer().getItemInHand().getDurability() - 1));
                    lapis_count.put(e.getPlayer(),1);
                }
            }
        }
        else if(m==Material.REDSTONE_ORE){
            if(redstone_count.get(e.getPlayer())==null){
                redstone_count.put(e.getPlayer(),1);
            }
            else{
                if(redstone_count.get(e.getPlayer())==1){
                    redstone_count.put(e.getPlayer(),0);
                }
                else if(redstone_count.get(e.getPlayer())==0){
                    e.setCancelled(true);
                    e.getBlock().setType(Material.AIR);
                    e.getPlayer().getItemInHand().setDurability((short)(e.getPlayer().getItemInHand().getDurability()-1));
                    redstone_count.put(e.getPlayer(),1);
                }
            }
        }
    }
}
