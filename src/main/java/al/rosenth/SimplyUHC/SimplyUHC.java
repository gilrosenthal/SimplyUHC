package al.rosenth.SimplyUHC;

import javafx.scene.paint.Material;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.GameMode;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gil on 8/3/2015.
 */
public class SimplyUHC extends JavaPlugin implements Listener{
    private int borderX;
    private int borderZ;
    private boolean isSet = false;
    private final PotionEffectType[] effects = { PotionEffectType.SLOW, PotionEffectType.SLOW_DIGGING, PotionEffectType.DAMAGE_RESISTANCE, PotionEffectType.INVISIBILITY, PotionEffectType.BLINDNESS };
    private static SimplyUHC instance;
    public static SimplyUHC getInstance(){
        return instance;
    }
    @Override
    public void onDisable(){
        getLogger().info("SimplyUHC shutting off");
    }
    @Override
    public void onEnable() {
        getLogger().info("SimplyUHC started");
        // TODO: Place any custom enable code here including the registration of any events

        // Register our events
        PluginManager pm = getServer().getPluginManager();

        // Register our commands


        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        PluginDescriptionFile pdfFile = this.getDescription();
        getLogger().info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
    }
    @Override
    public boolean onCommand(CommandSender sender,Command cmd,String commandLabel,String[] args){
        if(cmd.getName().equalsIgnoreCase("start")){
            if(args.length != 2){
                sender.sendMessage(ChatColor.RED+"Please provide all arguments");
                return true;
            }
            
            if(isSet) {
                Random random = new Random();
                Player playerSender = (Player) sender;
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "spreadplayers" + " " + playerSender.getLocation().getX() + " " + playerSender.getLocation().getZ() + " " + args[0] + " " + "@a[m=0]");
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "gamerule naturalRegeneration false");
                for(Player p:  Bukkit.getServer().getOnlinePlayers()){
                    p.sendMessage("All players are now teleported. We will now heal you and feed you one last time");
                    p.setHealth(20);
                    p.setFoodLevel(20);
                    p.sendMessage("You should now be healed and fed. Good Luck!");
                    freezePlayer(p, 1000);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1200, -127));

                }
                for(Player p:  Bukkit.getServer().getOnlinePlayers()) {
                    p.sendMessage("Starting in 5 Seconds");
                    BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                    StartTimer task = new StartTimer();
                    task.collec = Bukkit.getServer().getOnlinePlayers();

                    scheduler.scheduleSyncDelayedTask(this, task, 100);

                }
                return true;
            }
            else{
                sender.sendMessage("Please set the border with /setborder");
                return true;
            }
        }
        else if(cmd.getName().equalsIgnoreCase("setborder")){
            if(args.length != 2){
                sender.sendMessage(ChatColor.RED+"Please provide all arguments");
                return true;
            }
            for(Player p:  Bukkit.getServer().getOnlinePlayers()){
                p.sendMessage("Please prepare for a bit of lag, as we are pregenerating the chunks");
            }
            borderX = Integer.parseInt(args[0]);
            borderZ = Integer.parseInt(args[1]);
            isSet = true;
            World current =Bukkit.getWorlds().iterator().next();
            int xChunks =(int)Math.ceil(borderX/16);
            int zChunks = (int)Math.ceil(borderZ/16);

            for(int xx = 0;xx<xChunks;xx++){
                for(int zz = 0;zz<zChunks;zz++){
                    current.loadChunk(xx,zz);
                    getLogger().info("Chunk "+xx+", "+zz+" loaded.");
                }
            }
            sender.sendMessage("Border set");
            return true;
        }
        return false;
    }
    private void freezePlayer(Player player, int ticks) {
        ArrayList<PotionEffect> potions = new ArrayList<PotionEffect>();
        for (PotionEffectType type : effects)
            potions.add(new PotionEffect(type, ticks, Byte.MAX_VALUE));
        player.addPotionEffects(potions);
    }
    @EventHandler
    public void onPlayerRegainHealth(EntityRegainHealthEvent event) {
        if(event.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED || event.getRegainReason() == EntityRegainHealthEvent.RegainReason.REGEN)
            event.setCancelled(true);
    }
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        Player player = event.getEntity();
        player.setGameMode(GameMode.SPECTATOR);
    }
}
