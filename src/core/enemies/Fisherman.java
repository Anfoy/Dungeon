package core.enemies;

import core.Dungeon;

public class Fisherman extends Monster{
    public Fisherman(String name, Dungeon dungeon) {
        super(name, 15, 6, 5, 3, MonsterTypes.FISHERMAN, dungeon, 2, 10);
    }

    @Override
    public String getDialogue(){
        return "I've been fishing for the dead for years...";
    }

    @Override
    public void attack(Entity target) {
        super.attack(target);
    }
}
