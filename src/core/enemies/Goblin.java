package core.enemies;

import core.Dungeon;
import core.Items.Dagger;

public class Goblin extends Monster{

    public Goblin(String name, Dungeon dungeon) {
        super(name, 10, 3, 2, 3, MonsterTypes.GOBLIN, dungeon, 0, 100);
        Dagger goblinSlicer = new Dagger();
        super.addItemToInventory(goblinSlicer);
    }


    @Override
    public String getDialogue(){
        return "Goowetz- grrr- cruf cruf";
    }

    @Override
    public void attack(Entity target) {
        super.attack(target);
    }


}
