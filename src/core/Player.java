package core;

import core.enemies.Entity;
import rooms.Room;

import java.util.Scanner;

public class Player extends Entity {

   private Room currentRoom = null;


    private int currentFloor;


    public Player( int health, int speed, int defense, int attack, Dungeon dungeon) {
        super(null, health, speed, defense, attack, dungeon);
        this.setName(choseName());
        this.currentFloor = 0;
    }

    private String choseName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        return scanner.nextLine();
    }


    public int getCurrentFloor() {
        return currentFloor;
    }



    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    @Override
    public void attack(Entity entity){
        if (entity.getDefense() > getAttack()){
            entity.setHealth(entity.getHealth() - 1);
        }else{
            entity.setHealth(entity.getHealth() - getAttack());
        }
    }

    public void fight(Entity target){
        while (this.getHealth() > 0 && target.getHealth() > 0){
            if (this.getSpeed() > target.getSpeed()) {
                this.attack(target);
                target.attack(this);
            }else{
                target.attack(this);
                this.attack(target);
            }
        }
        if (this.getHealth() <= 0){
            getDungeon().endGame();
        }else if (target.getHealth() <= 0){
            printVictory(target);
        }
    }

    private void printVictory(Entity entity){
        System.out.println("Victory!");
        System.out.println("You've just beaten a: " + entity.getName());
        this.currentRoom.removeMonster();
    }



}
