package al.rosenth.SimplyUHC.scenarios;

import org.bukkit.Effect;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by KalyanPalepu on 8/18/2015.
 */
public class Enchantless {

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
