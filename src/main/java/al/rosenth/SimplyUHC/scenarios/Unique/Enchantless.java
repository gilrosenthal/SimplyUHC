package al.rosenth.SimplyUHC.scenarios.Unique;

import al.rosenth.SimplyUHC.scenarios.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by Gil on 8/19/2015.
 */
public class Enchantless implements Scenario {
     public void load(){
        Bukkit.getPluginManager().registerEvents(this, Bukkit.getPluginManager().getPlugin("SimplyUHC"));
    }
    public void unload(){}


        @EventHandler
        public void onCreatureSpawn(CreatureSpawnEvent event) {
            Entity e = event.getEntity();
            if(e instanceof ExperienceOrb){
                e.remove();
            }
        }
        @EventHandler
        public void onPlayerDeath(PlayerDeathEvent event)
        {
            Player player = event.getEntity();
            player.giveExpLevels(5);

        }

}
