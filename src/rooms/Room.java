package rooms;

import core.Player;
import core.enemies.Monster;
import core.Items.Item;

public class Room {

    private final int index;

    private boolean containsPlayer = false;

    private Player player = null;

    Room roomToLeft;

    Room roomToRight;

    boolean hasItem = false;

    boolean hasMonster = false;

    Monster monster = null;

    Item item = null;

    public Room(int index){
        this.index = index;
        roomToLeft = null;
        roomToRight = null;
    }


    public Room getRoomToLeft() {
        return roomToLeft;
    }

    public Room getRoomToRight() {
        return roomToRight;
    }

    public void setRoomToLeft(Room roomToLeft) {
        this.roomToLeft = roomToLeft;
    }

    public void setRoomToRight(Room roomToRight) {
        this.roomToRight = roomToRight;
    }

    public boolean isHasItem() {
        return hasItem;
    }

    public Item getItem() {
        return item;
    }

    public int getIndex() {
        return index;
    }

    public boolean isHasMonster() {
        return hasMonster;
    }

    public void addItemToRoom(Item item) {
        this.hasItem = true;
        this.item = item;
    }

    public boolean isContainsPlayer() {
        return containsPlayer;
    }

    public void addPlayerToRoom(Player player) {
        this.player = player;
        player.setCurrentRoom(this);
        this.containsPlayer = true;
    }

    public void removePlayerFromRoom() {
        player.setCurrentRoom(null);
        this.containsPlayer = false;
        this.player = null;
    }



    public void removeMonster(){
        this.hasMonster = false;
        this.monster = null;
    }


    public void removeItem(){
        this.hasItem = false;
        this.item = null;
    }

    public void placeMonster(Monster monster){
        this.monster = monster;
        this.hasMonster = true;
    }

    public Monster getMonster(){
        return monster;
    }

    @Override
    public String toString(){
        if (containsPlayer){
            return "[ P ]";
        }
        return "[ ]";
    }

    public String getRoomDialogue(){
        String dialogue = "";
        if (hasMonster){
            dialogue = "You've walked into a monster room!" + "\n" +
                    "You are going to battle a: " + monster.getMonsterTypes().getDescription();
        }else if (hasItem){
            dialogue = "There is an item in this room!: " + "\n" + item.toString();
        } else {
            dialogue = "This room is empty.";
        }


        return dialogue;
    }





}
