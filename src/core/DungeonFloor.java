package core;

import core.enemies.Monster;
import rooms.Room;
import rooms.traprooms.ArrowTrap;
import rooms.traprooms.LightningTrap;
import rooms.traprooms.SpikeTrap;
import rooms.traprooms.TrapRoom;
import rooms.TrapType;

import java.util.List;
import java.util.Random;

public class DungeonFloor {



    private final Room entrance;

    private Room exit;

    private final Player player;

    private final Dungeon dungeon;

    private final int size;

    private final int floor;

    private final int numOfMonsters;

    private final int chanceOfTraps;

    private final int numOfItems;


    public DungeonFloor(int floor, Dungeon dungeon, int numOfMonsters, int size, int numOfItems, int chanceOfTraps) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        entrance = new Room(0);
        entrance.addPlayerToRoom(dungeon.getPlayer());
        this.floor = floor;
        this.size = size;
        this.numOfMonsters = numOfMonsters;
        this.numOfItems = numOfItems;
        this.chanceOfTraps = chanceOfTraps;
        establishNewFloor(size);
    }

    /**
     * Creates the dungeon floor.
     * @param size How many rooms to create(excluding entrance)
     */
    private void establishNewFloor(int size) {
        Room pointer = entrance;
        for (int i = 1; i < size; i++) {
            Room room = new Room(pointer.getIndex() + 1);
            if (pointer.getIndex() == 0) {
                if (player.getCurrentRoom() == null){
                    player.setCurrentRoom(entrance);
                }
                //Set the right room of entrance as a generated one
                entrance.setRoomToRight(room);
                //set the left room of the generated one the entrance
                room.setRoomToLeft(entrance);
                pointer = room;
            } else {
                if (i == size -1){
                    //When at end of room creation, link tail to front.
                    pointer.setRoomToRight(room);
                    room.setRoomToLeft(pointer);
                    room.setRoomToRight(entrance);
                    entrance.setRoomToLeft(room);
                }else {
                    pointer.setRoomToRight(room);
                    room.setRoomToLeft(pointer);
                    pointer = room;
                }
            }


        }
        fillRooms();
        this.exit = findSafeExitRoom();
    }



    /**
     * Goes through daisy chain of Floor until it finds room with matching index.
     * @param index Index to search for
     * @return Room object that has parameterized index.
     */
    public Room findRoomAtIndex(int index){
        if (index > size || index < 0){
            return null;
        }else{
            Room pointer = entrance;
            while (pointer.getIndex() != index){
                pointer = pointer.getRoomToRight();
            }
            return pointer;
        }

    }

    /**
     * Finds a room that doesn't contain a monster, item, or is a trap room.
     * @return Room object that satisfies conditions.
     */
    private Room findSafeExitRoom(){
        Room pointer = findRoomAtIndex(getRandomNumber(size));
        while (pointer == null || pointer.isHasMonster() || pointer.isHasItem() || pointer instanceof TrapRoom){
            pointer = findRoomAtIndex(getRandomNumber(size));
        }
        return pointer;
    }

    private void fillRooms() {
        fillItemsInRooms();
        fillMonstersInRooms();
        possiblyPlaceTrapRoom();
    }

    /**
     * Fills random, unoccupied rooms with items.
     */
    private void fillItemsInRooms() {
        for (int i = 0; i < numOfItems; i++) {
            Room room = findValidRoom();
            if (room != null) {
                room.addItemToRoom(randomElement(dungeon.getItems()));
            }
        }
    }

    /**
     * Fills random, unoccupied rooms with monsters.
     */
    private void fillMonstersInRooms() {
        boolean foundMonster = false;
        for (int i = 0; i < numOfMonsters; i++) {
            Room room = findValidRoom();
            if (room != null) {
                Monster randMonster = randomElement(dungeon.getMonsters());
                while (!foundMonster) {
                   randMonster = randomElement(dungeon.getMonsters());
                   if ( floor < randMonster.getHighestFloor()) {
                       foundMonster = true;
                       break;
                   }

                }
                room.placeMonster(randMonster.clone());
            }
        }
    }

    private void possiblyPlaceTrapRoom() {
        if (oneInChance(chanceOfTraps)) {
            Room room = findValidRoom();
            if (room != null) {
                TrapRoom trapRoom = generateTrapRoom(randomElement(List.of(TrapType.values())), room);
                linkTrapRoom(trapRoom, room);
            }
        }
    }

    private Room findValidRoom() {
        Room room = null;
        while (room == null || (room.isHasItem()) || room.isHasMonster() || room instanceof TrapRoom) {
            room = findRoomAtIndex(getRandomNumber(size));
        }
        return room;
    }

    private <T> T randomElement(List<T> list) {
        return list.get(getRandomNumber(list.size()));
    }

    private void linkTrapRoom(TrapRoom trapRoom, Room room) {
        room.getRoomToRight().setRoomToLeft(trapRoom);
        room.getRoomToLeft().setRoomToRight(trapRoom);
        trapRoom.setRoomToLeft(room.getRoomToLeft());
        trapRoom.setRoomToRight(room.getRoomToRight());
    }

    private TrapRoom generateTrapRoom(TrapType type, Room room){
        return switch (type.toString().toUpperCase()) {
            case "SPIKE" -> new SpikeTrap(room.getIndex());
            case "LIGHTNING" -> new LightningTrap(room.getIndex());
            case "ARROW" -> new ArrowTrap(room.getIndex());
            default -> null;
        };
    }


    public Dungeon getDungeon() {
        return dungeon;
    }

    /**
     * Generates a number between 0 and whatever upper bound
     * @param bound highest value it can reach
     * @return true if chance was hit, false otherwise.
     */
    public boolean oneInChance(int bound) {
        Random random = new Random();
        int chance = random.nextInt(bound); // Generates a random number between 0 and upper bound
        return chance == 0; // Returns true if the number is 0, 1 in upper bound chance
    }


    public Room getEntrance() {
        return entrance;
    }

    public int getFloor() {
        return floor;
    }


    public Room getExit() {
        return exit;
    }

    public int getSize() {
        return size;
    }

    private boolean isLastRoom(Room room){
        return room.getIndex() == exit.getIndex();
    }

    /**
     * Recursively adds all rooms to a string.
     * @return String of all rooms added to one string.
     */
    private String getDungeonLayout(){
        Room pointer = entrance;
        if (pointer == null){
            System.out.println("entrance is null");
        }
        StringBuilder finalLayout = new StringBuilder();
        finalLayout.append(pointer.getRoomToRight());
        pointer = pointer.getRoomToRight();
        while (pointer.getIndex() != entrance.getIndex()){
            finalLayout.append(pointer.getRoomToRight());
            pointer = pointer.getRoomToRight();
        }
        return finalLayout.toString();
    }



    @Override
    public String toString() {
        return getDungeonLayout();

    }

    /**
     * Generates a string that shows the players position and the rooms left and right to them.
     * @return the formatted string of player position.
     */
    public String getPlayerPosFormatted(){
        return player.getCurrentRoom().getRoomToLeft().toString() + player.getCurrentRoom().toString() + player.getCurrentRoom().getRoomToRight().toString();
    }




    /**
     * // Generate a random number between 1 (inclusive) and size (exclusive)
     * @throws IllegalArgumentException throws exception if size is greater than or equal to 1.
     * @param size upper bound.
     * @return A random number.
     */
    public int getRandomNumber(int size) {
        if (size <= 1) {
            throw new IllegalArgumentException("Size must be greater than 1.");
        }

        Random random = new Random();
        return random.nextInt(size - 1) + 1;
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public int getChanceOfTraps() {
        return chanceOfTraps;
    }

    public int getNumOfMonsters() {
        return numOfMonsters;
    }
}
