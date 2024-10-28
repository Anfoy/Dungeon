package rooms.traprooms;

import rooms.TrapType;

public class ArrowTrap extends TrapRoom{


    public ArrowTrap(int index) {
        super(index);
    }

    /**
     * Name of the trap
     *
     * @return The Name of The Trap
     */
    @Override
    public String getName() {
        return "Arrow Trap";
    }

    /**
     * Description of the trap
     *
     * @return The description of the trap.
     */
    @Override
    public String getDescription() {
        return "Arrows shooting all over the Room!";
    }

    /**
     * Damage of the trap
     *
     * @return the Damage of the trap
     */
    @Override
    public int getDamage() {
        return 3;
    }

    /**
     * Type of Trap
     *
     * @return the type of trap
     */
    @Override
    public TrapType getTrapType() {
        return TrapType.ARROW;
    }


}
