package core;

import core.Items.*;
import core.enemies.*;
import rooms.Room;
import rooms.traprooms.TrapRoom;

import java.util.*;

public class Dungeon {

    private final List<DungeonFloor> floors = new ArrayList<>();
    private final List<Item> items = new ArrayList<>();
    private final List<Monster> monsters = new ArrayList<>();

    private final Player player;
    private boolean isGameOver = false;
    private int playerScore = 0;
    private final int size;

    public Dungeon(Player player, int startingSize) {
        this.size = startingSize;
        this.player = player;
        player.setDungeon(this);
        initEntities();
        floors.add(new DungeonFloor(1, this, 2, size, 1, 5));
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (!isGameOver) {
                DungeonFloor currentFloor = getFloor(player.getCurrentFloor());
                String input = getInput(scanner, currentFloor);
                handleInput(input, currentFloor);
            }
        }
    }

    private void handleInput(String input, DungeonFloor floor) {
        if (isValidMove(input)) {
            switch (input.toLowerCase()) {
                case "left" -> movePlayer( player.getCurrentRoom().getRoomToLeft());
                case "right" -> movePlayer(player.getCurrentRoom().getRoomToRight());
                case "next" -> handleNextFloor(floor);
                case "exit" -> handleExit(floor);
                case "skip left" -> handleSkip(player.getCurrentRoom().getRoomToLeft());
                case "skip right" -> handleSkip(player.getCurrentRoom().getRoomToRight());
            }
        } else {
            System.out.println("Invalid move");
        }
    }

    private void handleNextFloor(DungeonFloor floor) {
        if (player.getCurrentRoom() == floor.getExit()) {
            DungeonFloor newFloor = createNewFloor(floor);
            floors.add(newFloor);
            player.setCurrentFloor(player.getCurrentFloor() + 1);
            movePlayer( newFloor.getEntrance());
            updateScore();
        } else {
            System.out.println("Invalid room for this move.");
        }
    }

    private void handleExit(DungeonFloor floor) {
        if (player.getCurrentRoom() == floor.getExit()) {
            endGame();
        } else {
            System.out.println("You can't exit from this room.");
        }
    }

    private void handleSkip(Room nextRoom) {
        if (nextRoom.isHasMonster()) {
            nextRoom.getMonster().attack(player);
            System.out.println("A monster caught you during the skip!");
        }
        movePlayer( nextRoom.getRoomToLeft());  // Handle skip to the left/right.
    }

    private void movePlayer(Room newRoom) {
        Room oldRoom = player.getCurrentRoom();
        updatePlayerPosition(oldRoom, newRoom, player);

        if (newRoom.isHasMonster()) {
            System.out.println(newRoom.getRoomDialogue());
            player.fight(newRoom.getMonster());
            return;
        }

        if (newRoom instanceof TrapRoom trapRoom) {
            player.setHealth(player.getHealth() - trapRoom.getDamage());
        }

        if (newRoom.isHasItem()) {
            player.addItemToInventory(newRoom.getItem());
            System.out.println(newRoom.getRoomDialogue());
            newRoom.getItem().execute(player);
            newRoom.removeItem();
            return;
        }
        System.out.println(newRoom.getRoomDialogue());
    }

    private DungeonFloor createNewFloor(DungeonFloor floor) {
        return new DungeonFloor(
                player.getCurrentFloor() + 1, this,
                floor.getNumOfMonsters() + 1,
                floor.getSize() + 2,
                floor.getNumOfItems() + 1,
                floor.getChanceOfTraps()
        );
    }

    public void updatePlayerPosition(Room oldRoom, Room newRoom, Player player) {
        if (!isGameOver) {
            oldRoom.removePlayerFromRoom();
            newRoom.addPlayerToRoom(player);
            System.out.println("--------------------");
        }
    }

    private String getInput(Scanner scanner, DungeonFloor floor) {
        printMovementOptions(floor);
        return scanner.nextLine();
    }

    private void printMovementOptions(DungeonFloor floor) {
        if (player.getCurrentRoom() == floor.getExit()) {
            System.out.println("""
                Moves:
                THIS IS AN EXIT/LADDER
                Choose: 'next', 'left', 'right', 'exit'
                To skip a room: 'skip left' or 'skip right'
                """);
        } else {
            System.out.println("""
                Moves:
                Choose: 'left', 'right'
                Or: 'skip left' or 'skip right'
                """);
        }
        System.out.println(player);
        System.out.println(floor.getPlayerPosFormatted());
        System.out.println("--------------------");
    }

    private boolean isValidMove(String input) {
        return Set.of("left", "right", "next", "exit", "skip left", "skip right")
                .contains(input.toLowerCase());
    }

    public void endGame() {
        isGameOver = true;
        System.out.println("The game has ended!");
        System.out.println("Final Score: " + playerScore);
    }

    private void updateScore() {
        playerScore += 500;
    }

    public DungeonFloor getFloor(int floorIndex) {
        return floors.size() <= floorIndex ? floors.getLast() : floors.get(floorIndex);
    }

    private void initEntities() {
        items.addAll(List.of(
                new Dagger(), new Sword(), new HeartCanister(),
                new TeleportItem(), new FlashFlask(),
                new RockhelmSheid(), new TrappedChest()
        ));

        monsters.addAll(List.of(
                new Goblin("Grizzy", this), new Goblin("Gerome", this),
                new Spider("Spizzy", this), new Fisherman("Bob the Fisher", this),
                new LavaSnake("Jake the Snake", this)
        ));
    }

    public List<DungeonFloor> getFloors() {
        return floors;
    }

    public int getSize() {
        return size;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public List<Item> getItems() {
        return items;
    }
}