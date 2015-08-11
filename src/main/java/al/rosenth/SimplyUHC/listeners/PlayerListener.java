package al.rosenth.SimplyUHC.listeners;

import al.rosenth.SimplyUHC.SimplyUHC;
import al.rosenth.SimplyUHC.utils.ScoreboardTimer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scoreboard.Scoreboard;

/**
 * Created by Gil on 8/7/2015.
 */
public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        Player player = event.getEntity();
        player.setGameMode(GameMode.SPECTATOR);
        int count = 0;
        player.setMetadata("dead",new FixedMetadataValue(Bukkit.getPluginManager().getPlugin("SimplyUHC"),true));
        for(Player p:Bukkit.getServer().getOnlinePlayers()){
            if(p.getMetadata("dead").get(0).asBoolean()){}
            else{
                count++;
            }
        }
        if(count==1){
            String name=null;
            for(Player p: Bukkit.getServer().getOnlinePlayers()){
                if(p.getMetadata("dead").get(0).asBoolean()){}
                else{
                    name = p.getDisplayName();
                }
            }
            for(Player p: Bukkit.getServer().getOnlinePlayers()){
                p.setGameMode(GameMode.SPECTATOR);
                p.sendMessage("The UHC is over! The winner is "+name);
                Bukkit.getScheduler().cancelTask(SimplyUHC.id);
            }
        }
        SimplyUHC.playerCount(count);
    }
}
