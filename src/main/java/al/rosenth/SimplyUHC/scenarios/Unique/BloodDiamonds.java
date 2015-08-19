package al.rosenth.SimplyUHC.scenarios;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by KalyanPalepu on 8/19/2015.
 */
public class BloodDiamonds {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Block b = e.getBlock();
        Player p = e.getPlayer();
        double h = p.getHealth();
        if(e.getBlock().getType()== Material.DIAMOND_ORE){
            p.setHealth(h - 1);
        }
    }
}
