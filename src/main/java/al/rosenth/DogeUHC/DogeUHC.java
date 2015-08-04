package al.rosenth.DogeUHC;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Gil on 8/3/2015.
 */
public class DogeUHC extends JavaPlugin {

    private static DogeUHC instance;
    public static DogeUHC getInstance(){
        return instance;
    }
    @Override
    public void onDisable(){
        getLogger().info("DogeUHC shutting off");
    }
    @Override
    public void onEnable() {
        getLogger().info("DogeUHC started");
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
            sender.sendMessage("Started");
            return true;
        }
        return false;
    }
}
