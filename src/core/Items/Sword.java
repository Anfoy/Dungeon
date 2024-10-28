package core.Items;

import core.Player;
import core.enemies.Entity;

public class Sword extends Weapon{
    /**
     * Damage of the item
     * @return The damage of the item.
     */
    @Override
    public int getDamage() {
        return 5;
    }

    /**
     * Name of item.
     *
     * @return The name of the item.
     */
    @Override
    public String getName() {
        return "Gilbreth";
    }

    /**
     * Description of Item
     *
     * @return The description of the item.
     */
    @Override
    public String getDescription() {
        return "A rusted sword once wielded by the greats!";
    }

    /**
     * Adds stats to the player object
     *
     * @param target player object to effect.
     */
    @Override
    public void addStats(Entity target) {
        target.setAttack(target.getAttack() + getDamage());
    }


    @Override
    public void removeStats(Entity target) {
        target.setAttack(target.getAttack() - getDamage());
    }
}
