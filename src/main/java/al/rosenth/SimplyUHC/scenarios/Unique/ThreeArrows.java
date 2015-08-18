package al.rosenth.SimplyUHC.scenarios.Unique;

import al.rosenth.SimplyUHC.scenarios.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityShootBowEvent;

/**
 * Created by Gil on 8/16/2015.
 */
public class ThreeArrows implements Scenario {
    public void load(){
        Bukkit.getPluginManager().registerEvents(this,Bukkit.getPluginManager().getPlugin("SimplyUHC"));
    }
    public void unload(){}
    @EventHandler
    public void onArrowShoot(EntityShootBowEvent e){
        if(e.getEntity()instanceof Player) {
            e.setCancelled(true);
            for (int i = 0; i < 3; i++) {
                e.getEntity().launchProjectile(Arrow.class);
            }
        }
    }
}
