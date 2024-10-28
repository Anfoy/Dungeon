package core.Items;

import core.enemies.Entity;

public class RockhelmSheid extends Item {
    /**
     * Name of item.
     *
     * @return The name of the item.
     */
    @Override
    public String getName() {
        return "Rockhelm Sheid";
    }

    /**
     * Description of Item
     *
     * @return The description of the item.
     */
    @Override
    public String getDescription() {
        return "A durable and heavy shield able to withstand small attacks";
    }

    /**
     * Adds stats to the player object
     *
     * @param target player object to effect.
     */
    @Override
    public void addStats(Entity target) {
        target.setDefense(target.getDefense() + 5);
    }

    /**
     * Removes stats from player object
     *
     * @param target player object to effect
     */
    @Override
    public void removeStats(Entity target) {
        target.setDefense(target.getDefense() - 5);
    }
}
