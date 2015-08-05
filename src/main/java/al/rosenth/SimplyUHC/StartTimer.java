package al.rosenth.SimplyUHC;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;

/**
 * Created by Gil on 8/4/2015.
 */
public class StartTimer implements Runnable {
    Collection<? extends Player> collec;
    int i;

    public void run(){
        for(Player p : collec) {

            p.sendMessage("Started!");
            for (PotionEffect effect : p.getActivePotionEffects()) {
                p.removePotionEffect(effect.getType());
            }
        }

    }
}
