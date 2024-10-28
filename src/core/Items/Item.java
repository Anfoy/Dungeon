package core.Items;

import core.Player;
import core.enemies.Entity;
import core.enemies.Monster;

public  abstract class Item {

    /**
     * Name of item.
     * @return The name of the item.
     */
    public abstract String getName();

    /**
     * Description of Item
     * @return The description of the item.
     */
    public abstract String getDescription();


    /**
     * Adds stats to the player object
     * @param target player object to effect.
     */
    public  abstract void addStats(Entity target);

    /**
     * Removes stats from player object
     * @param target player object to effect
     */
    public abstract void removeStats(Entity target);

    @Override
    public String toString() {
        return  "{" + "\n"
                + getName() +  "\n"
                + getDescription() + "\n"
                + "}";
    }

    public void execute(Player player){

    }
}
