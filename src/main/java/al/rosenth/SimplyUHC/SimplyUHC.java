package al.rosenth.SimplyUHC;

import javafx.scene.paint.Material;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

/**
 * Created by Gil on 8/3/2015.
 */
public class SimplyUHC extends JavaPlugin {
    private int x;
    private int z;
    private boolean isSet = false;
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
            if(isSet) {
                Random random = new Random();
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    int playerX = random.nextInt(x);
                    int playerZ = random.nextInt(z);
                    Location location = new Location(p.getWorld() , playerX, p.getWorld().getHighestBlockAt(playerX, playerZ).getY() ,playerZ);
                    p.teleport(location);
                    if(p.getLocation().getBlock().getType()!= org.bukkit.Material.AIR){
                        playerX = random.nextInt(x);
                        playerZ = random.nextInt(z);
                        location = new Location(p.getWorld() , playerX, p.getWorld().getHighestBlockAt(playerX, playerZ).getY() ,playerZ);
                        p.teleport(location);
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10, 100));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 10, 100));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10, 100));
                    }
                }
                for(Player p:  Bukkit.getServer().getOnlinePlayers()){
                    p.sendMessage("All players are now teleported. We will now heal you and feed you one last time");
                    p.setHealth(20);
                    p.setFoodLevel(20);
                    p.sendMessage("You should now be healed and fed. Good Luck!");
                }
                for(Player p:  Bukkit.getServer().getOnlinePlayers()){
                   p.sendMessage("Starting in...");
                    for(int i =5;i>0;i--){
                        p.sendMessage("..." + i);

                        try {
                            Thread.sleep(1000);
                        }
                        catch(Exception e){
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }
                        }
                    for (PotionEffect effect : p.getActivePotionEffects()) {
                        p.removePotionEffect(effect.getType());
                    }
                    p.sendMessage("Started!");
                }
                return true;
            }
            else{
                sender.sendMessage("Please set the border with /setborder");
                return true;
            }
        }
        else if(cmd.getName().equalsIgnoreCase("setborder")){
            if(args.length<2){
                sender.sendMessage(ChatColor.RED+"Please provide all arguments");
                return true;
            }
            for(Player p:  Bukkit.getServer().getOnlinePlayers()){
                p.sendMessage("Please prepare for a bit of lag, as we are pregenerating the chunks");
            }
            x = Integer.parseInt(args[0]);
            z = Integer.parseInt(args[1]);
            isSet = true;
            World current =Bukkit.getWorlds().iterator().next();
            int xChunks =(int)Math.ceil(x/16);
            int zChunks = (int)Math.ceil(z/16);

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
}
