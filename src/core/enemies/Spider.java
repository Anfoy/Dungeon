package core.enemies;

import core.Dungeon;

public class Spider extends Monster{
    public Spider(String name, Dungeon dungeon) {
        super(name, 10, 6, 2, 2, MonsterTypes.SPIDER, dungeon, 0, 2);
    }

    @Override
    public String getDialogue(){
        return "I am a spider that can speak english!";
    }

    @Override
    public void attack(Entity target) {
        super.attack(target);
    }
}
