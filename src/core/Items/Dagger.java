package core.Items;

import core.Player;
import core.enemies.Entity;
import core.enemies.Monster;

public class Dagger extends Weapon {

    @Override
    public String getName() {
        return "Dagger";
    }

    @Override
    public String getDescription() {
        return "Small blade that slashes enemies.";
    }

    @Override
    public void addStats(Entity target) {
        target.setAttack(target.getAttack() + getDamage());
    }

    @Override
    public void removeStats(Entity target) {
        target.setAttack(target.getAttack() - getDamage());
    }

    @Override
    public int getDamage() {
        return 3;
    }
}
