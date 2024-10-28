package core.enemies;

import core.Dungeon;

public class LavaSnake extends Monster{
    public LavaSnake(String name, Dungeon dungeon) {
        super(name, 20, 10, 2, 2, MonsterTypes.LAVASAKE, dungeon, 2, 5);
    }

    @Override
    public String getDialogue(){
        return "Ssssss.....";
    }

    @Override
    public void attack(Entity target) {
        super.attack(target);
        System.out.println("The lavasnake spits a fire causing extra damage...");
        target.setHealth(target.getHealth()-2);
    }
}
