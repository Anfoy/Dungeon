package core.Items;

import core.enemies.Entity;

public class HeartCanister extends Item{
    /**
     * Name of item.
     *
     * @return The name of the item.
     */
    @Override
    public String getName() {
        return "Heart In-a-Bottle";
    }

    /**
     * Description of Item
     *
     * @return The description of the item.
     */
    @Override
    public String getDescription() {
        return "Hold the heart of an old-goose, granting the eater 10 extra health.";
    }

    /**
     * Adds stats to the player object
     *
     * @param target player object to effect.
     */
    @Override
    public void addStats(Entity target) {
target.setHealth(target.getHealth() + 10);
    }

    /**
     * Removes stats from player object
     *
     * @param target player object to effect
     */
    @Override
    public void removeStats(Entity target) {
        target.setHealth(target.getHealth() - 10);
    }
}
