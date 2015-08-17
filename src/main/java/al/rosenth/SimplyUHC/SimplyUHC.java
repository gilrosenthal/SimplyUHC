package al.rosenth.SimplyUHC;

import al.rosenth.SimplyUHC.listeners.PlayerListener;
import al.rosenth.SimplyUHC.scenarios.ScenarioManager;
import al.rosenth.SimplyUHC.utils.ScoreboardTimer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.GameMode;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gil on 8/3/2015.
 */
public class SimplyUHC extends JavaPlugin implements Listener{
    private int borderX;
    private int borderZ;
    private boolean isSet = false;
    private boolean isRunning = false;
    private int timercount = 0;
    public static int id;
    private static ScoreboardTimer timer;
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
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        //getServer().getPluginManager().registerEvents(new CutClean(),this);
        getLogger().info("SimplyUHC started");

        // Register our events
        PluginManager pm = getServer().getPluginManager();

        // Register our commands

        timer = new al.rosenth.SimplyUHC.utils.ScoreboardTimer();
        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        PluginDescriptionFile pdfFile = this.getDescription();
        getLogger().info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
    }
    @Override
    public boolean onCommand(CommandSender sender,Command cmd,String commandLabel,String[] args){
        if(cmd.getName().equalsIgnoreCase("uhc")){
            if(isSet) {
                if (args[0].equals("start")) {
                    if(!isRunning) {
                        new ScenarioManager(getDataFolder().getPath()+"\\config.properties");
                        isRunning = true;
                        Random random = new Random();
                        for (Player p : getServer().getOnlinePlayers()) {
                            teleportPlayer(p);
                        }
                        playerCount(getServer().getOnlinePlayers().size());
                        displayHearts();
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "gamerule naturalRegeneration false");
                        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                            p.setGameMode(GameMode.SURVIVAL);
                            p.sendMessage("All players are now teleported. We will now heal you and feed you one last time");
                            p.setHealth(20);
                            p.setFoodLevel(20);
                            p.sendMessage("You should now be healed and fed. Good Luck!");
                            freezePlayer(p, 1000);
                            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1200, -127));

                        }
                        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                            p.sendMessage("Starting in 5 Seconds");
                            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                            StartTimer task = new StartTimer();
                            task.collec = Bukkit.getServer().getOnlinePlayers();
                            p.setMetadata("dead", new FixedMetadataValue(Bukkit.getPluginManager().getPlugin("SimplyUHC"), false));
                            scheduler.scheduleSyncDelayedTask(this, task, 100);
                        }
                            startCountdown();
                        return true;
                    }
                    else{
                        sender.sendMessage(ChatColor.DARK_RED+"Game already running!");
                    }
                }
                else if(args[0].equalsIgnoreCase("stop")){
                    for(Player p: Bukkit.getServer().getOnlinePlayers()){
                        p.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage("The game has been terminated early, there is no winner.");
                        p.setMetadata("dead", new FixedMetadataValue(this, true));
                        Bukkit.getScheduler().cancelTask(id);
                        isRunning = false;
                        return true;
                    }
                }
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

            borderX = Integer.parseInt(args[0]);
            borderZ = Integer.parseInt(args[1]);
            isSet = true;
            World current =Bukkit.getWorlds().iterator().next();
            int xChunks =(int)Math.ceil(borderX/16);
            int zChunks = (int)Math.ceil(borderZ/16);
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

    public void startCountdown(){

        for(Player p : getServer().getOnlinePlayers()){
            timer.sendPlayerScoreboard(p);
        }
      id = this.getServer().getScheduler()
                .scheduleSyncRepeatingTask(this, new Runnable() {
                    public void run() {
                        timer.updateTime(timercount);
                        timercount++;
                    }

                }, 0L, 20L);
    }
    private boolean safeSpawn(Player player){
        Block currentblock = player.getLocation().getBlock();
        if(currentblock.getBiome()!= Biome.OCEAN&&currentblock.getBiome()!=Biome.DEEP_OCEAN&&currentblock.getBiome()!=Biome.FROZEN_OCEAN&&currentblock.getType()== org.bukkit.Material.AIR){
            return true;
        }
        else{
            return false;
        }
    }
    private void teleportPlayer(Player p){
        Random random = new Random();
        int playerX = random.nextInt(borderX*2) - borderX;
        int playerZ = random.nextInt(borderZ*2) - borderZ;
        Location location = new Location(p.getWorld() , playerX, p.getWorld().getHighestBlockAt(playerX, playerZ).getY() ,playerZ);
        p.teleport(location);
        if(safeSpawn(p)){
            p.sendMessage("You are at your spawn. Game will start in 5 seconds");
            return;
        }
        else{
            teleportPlayer(p);
        }
    }
    public static void playerCount(int counter){
        timer.objective.getScore(counter+" Players alive").setScore(0);
        timer.objective.getScoreboard().resetScores((counter + 1) + " Players alive");
    }
    private void displayHearts(){
        timer.objective.getScoreboard().registerNewObjective("health","health").setDisplaySlot(DisplaySlot.PLAYER_LIST);
    }
}
