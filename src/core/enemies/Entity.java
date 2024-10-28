package core.enemies;

import core.Dungeon;
import core.Items.Item;

import java.util.ArrayList;
import java.util.List;

public class Entity implements  Combat{


    private  String name;

    private int health;

    private  int speed;

    private  int defense;

    private int attack;

    private Dungeon dungeon;

    private final List<Item> inventory;

    public Entity(String name, int health, int speed, int defense, int attack, Dungeon dungeon) {
        this.name = name;
        this.health = health;
        this.speed = speed;
        this.defense = defense;
        this.attack = attack;
        this.inventory = new ArrayList<Item>();
        this.dungeon = dungeon;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDefense() {
        return defense;
    }

    public int getAttack() {
        return attack;
    }

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void addItemToInventory(Item item) {
        this.inventory.add(item);
        item.addStats(this);
    }

    public void removeFromInventory(Item item) {
        inventory.remove(item);
        item.removeStats(this);
    }


    @Override
    public void attack(Entity entity) {

    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NAME: " + this.getName()
                + " HEALTH: " + this.getHealth()
                + " SPEED: " + this.getSpeed()
                + " DEFENSE: " + this.getDefense()
                + " ATTACK: " + this.attack;
    }
}
