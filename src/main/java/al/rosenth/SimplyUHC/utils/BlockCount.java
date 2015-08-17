package al.rosenth.SimplyUHC.utils;

import org.bukkit.block.Block;

/**
 * Created by Gil on 8/15/2015.
 */
public class BlockCount {
    private int count;
    private Block block;
    public BlockCount(Block block,int count){
        this.block = block;
        this.count = count;
    }
    public void updateCount(int count){
        this.count = count;
    }
    public int getCount(){
        return count;
    }
    public Block getBlock(){
        return block;
    }
}
