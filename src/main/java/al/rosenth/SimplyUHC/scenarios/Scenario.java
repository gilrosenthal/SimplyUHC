package al.rosenth.SimplyUHC.scenarios;

import org.bukkit.event.Listener;

/**
 * Created by Gil on 8/11/2015.
 */
public interface Scenario extends Listener {
    void load();
    void unload();
}
