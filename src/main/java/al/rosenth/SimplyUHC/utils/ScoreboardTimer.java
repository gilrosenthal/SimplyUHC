package al.rosenth.SimplyUHC.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

/**
 * Created by Gil on 8/6/2015.
 */
public class ScoreboardTimer {
    private Scoreboard scoreboard;
    public Objective objective;

    /**
     * Constructor. Just putting our scoreboard together here.
     */
    public ScoreboardTimer() {
        // This is going to be pretty much the same for every scoreboard you
        // create.
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective title = scoreboard.registerNewObjective("title","dummy");
        title.setDisplayName(ChatColor.GREEN+"UHC");
        objective = scoreboard.registerNewObjective("time", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        // This is where our timer is. In the scoreboard display name.
        objective.setDisplayName(ChatColor.DARK_AQUA + "UHC");

        // This doesn't matter. Just need a score to display.

    }

    /**
     * Sending the scoreboard to a player.
     * @param p player who is receiving the scoreboard
     */
    public void sendPlayerScoreboard(Player p) {
        p.setScoreboard(scoreboard);
    }

    /**
     * Updating the time that is displayed on the scoreboard
     */
    public void updateTime(int seconds) {

        int secondsss = seconds % 60;
        String secondsString = Integer.toString(secondsss);
        int minutes = seconds / 60;
        int secondss = seconds % 60;
        String toSend;
        if(secondss/10<1){
          toSend = "0"+secondss;
        }
        else {
            toSend = ""+secondss;
        }
        objective.getScore(ChatColor.DARK_AQUA + Integer.toString((int) minutes) + ":" + toSend).setScore(1);
        if(secondss<10){
            objective.getScoreboard().resetScores(ChatColor.DARK_AQUA + Integer.toString((int) minutes) + ":" + "0"+Integer.toString(Integer.parseInt(toSend) - 1));
        } else {
            objective.getScoreboard().resetScores(ChatColor.DARK_AQUA + Integer.toString(minutes)+":09");
            objective.getScoreboard().resetScores(ChatColor.DARK_AQUA + Integer.toString(minutes)+":59");
            objective.getScoreboard().resetScores(ChatColor.DARK_AQUA + Integer.toString((int) minutes) + ":" + Integer.toString(Integer.parseInt(toSend) - 1));
        }

        for(Player p:Bukkit.getServer().getOnlinePlayers()){
            sendPlayerScoreboard(p);
        }
    }

    /**
     * Private method to get the current time in the format
     * that we want.
     * @param format format to be displayed
     * @return the current formatted time as a string
     */

}
