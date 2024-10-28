package rooms.traprooms;

import rooms.Room;
import rooms.TrapType;

public abstract class TrapRoom extends Room {

    public TrapRoom(int index) {
        super(index);
    }

    /**
     * Name of the trap
     * @return The Name of The Trap
     */
    public abstract String getName();

    /**
     * Description of the trap
     * @return The description of the trap.
     */
    public abstract String getDescription();

    /**
     * Damage of the trap
     * @return the Damage of the trap
     */
    public abstract int getDamage();

    /**
     * Type of Trap
     * @return the type of trap
     */
    public abstract TrapType getTrapType();

}
