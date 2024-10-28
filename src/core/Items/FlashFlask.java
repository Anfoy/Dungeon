package core.Items;

import core.enemies.Entity;

public class FlashFlask extends Item{
    /**
     * Name of item.
     *
     * @return The name of the item.
     */
    @Override
    public String getName() {
        return "Flash Flask";
    }

    /**
     * Description of Item
     *
     * @return The description of the item.
     */
    @Override
    public String getDescription() {
        return "Heard to give the holder a jolt of lightning in their movement!";
    }

    /**
     * Adds stats to the player object
     *
     * @param target player object to effect.
     */
    @Override
    public void addStats(Entity target) {
        target.setSpeed(target.getSpeed() + 5);
    }

    /**
     * Removes stats from player object
     *
     * @param target player object to effect
     */
    @Override
    public void removeStats(Entity target) {
        target.setSpeed(target.getSpeed() - 5);
    }
}
