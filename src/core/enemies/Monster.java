package core.enemies;

import core.Dungeon;
import core.Items.Item;

import java.util.ArrayList;

public  class Monster extends Entity{

    private final MonsterTypes monsterTypes;

    private final int lowestFloor;

    private final int highestFloor;


    public Monster(String name, int health, int speed, int defense, int attack, MonsterTypes monsterTypes, Dungeon dungeon, int floor, int maxFloor) {
        super(name, health, speed, defense, attack, dungeon);
        this.monsterTypes = monsterTypes;
        this.lowestFloor = floor;
        this.highestFloor = maxFloor;
    }

    public MonsterTypes getMonsterTypes() {
        return monsterTypes;
    }

    public String getDialogue(){
        return "MONSTER DIALGOUE";
    }

    public int getLowestFloor() {
        return lowestFloor;
    }

    public int getHighestFloor() {
        return highestFloor;
    }

    @Override
    public void attack(Entity target){
        if (target.getDefense() > getAttack()){
            target.setHealth(target.getHealth() - 1);
        }else{
            target.setHealth(target.getHealth() - getAttack());
        }
    }

    public Monster clone(){
      return  new Monster(this.getName(), this.getHealth(), this.getSpeed(), this.getDefense(), this.getAttack(),
              this.monsterTypes, this.getDungeon(), this.lowestFloor, this.highestFloor);

    }


}
