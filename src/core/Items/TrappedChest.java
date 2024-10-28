package core.Items;

import core.Player;
import core.enemies.Entity;

public class TrappedChest extends Item {
    /**
     * Name of item.
     *
     * @return The name of the item.
     */
    @Override
    public String getName() {
        return "Trapped Chest";
    }

    /**
     * Description of Item
     *
     * @return The description of the item.
     */
    @Override
    public String getDescription() {
        return "A Chest casted with a damaging spell, unbeknownst to the average dungeon-goer...";
    }

    /**
     * Adds stats to the player object
     *
     * @param target player object to effect.
     */
    @Override
    public void addStats(Entity target) {

    }

    /**
     * Removes stats from player object
     *
     * @param target player object to effect
     */
    @Override
    public void removeStats(Entity target) {

    }

    @Override
    public void execute(Player player){
        player.setHealth(player.getHealth()-5);
    }


}
