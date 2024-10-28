package core.Items;

import core.Dungeon;
import core.DungeonFloor;
import core.Player;
import core.enemies.Entity;
import rooms.Room;

public class TeleportItem extends Item{
    /**
     * Name of item.
     *
     * @return The name of the item.
     */
    @Override
    public String getName() {
        return "SwirlyPort";
    }

    /**
     * Description of Item
     *
     * @return The description of the item.
     */
    @Override
    public String getDescription() {
        return "AN object that telports all entities near it into a random location of the dungeon.";
    }

    /**
     * Adds stats to the player object
     *
     * @param target player object to effect.
     */
    @Override
    public void addStats(Entity target) {

    }

    /**
     * Removes stats from player object
     *
     * @param target player object to effect
     */
    @Override
    public void removeStats(Entity target) {

    }

    @Override
    public void execute(Player player){
        Dungeon dungeon = player.getDungeon();
        DungeonFloor floor = dungeon.getFloor(player.getCurrentFloor());
        Room randomRoom = floor.findRoomAtIndex(floor.getRandomNumber(floor.getSize()));
        while (randomRoom.isContainsPlayer()) {
             randomRoom = floor.findRoomAtIndex(floor.getRandomNumber(floor.getSize()));
        }
        dungeon.updatePlayerPosition(player.getCurrentRoom(), randomRoom, player);
        player.removeFromInventory(this);
    }
}
