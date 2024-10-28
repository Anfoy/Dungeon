package rooms.traprooms;

import rooms.TrapType;

public class LightningTrap extends TrapRoom{


    public LightningTrap(int index) {
        super(index);
    }

    /**
     * Name of the trap
     *
     * @return The Name of The Trap
     */
    @Override
    public String getName() {
        return "Lightning Trap";
    }

    /**
     * Description of the trap
     *
     * @return The description of the trap.
     */
    @Override
    public String getDescription() {
        return "Lightning shocking the walls and floor around you!";
    }

    /**
     * Damage of the trap
     *
     * @return the Damage of the trap
     */
    @Override
    public int getDamage() {
        return 2;
    }

    /**
     * Type of Trap
     *
     * @return the type of trap
     */
    @Override
    public TrapType getTrapType() {
        return TrapType.LIGHTNING;
    }
}
