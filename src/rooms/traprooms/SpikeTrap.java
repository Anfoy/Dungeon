package rooms.traprooms;

import rooms.TrapType;

public class SpikeTrap extends TrapRoom {

    public SpikeTrap(int index) {
        super(index);
    }

    @Override
    public String getName() {
        return "Spike Trap";
    }

    @Override
    public String getDescription() {
        return "Dangerous spikes pop out from the ground!";
    }

    @Override
    public int getDamage() {
        return 3;
    }

    @Override
    public TrapType getTrapType() {
        return TrapType.SPIKE;
    }

    @Override
    public String getRoomDialogue(){
        return "This is a " + getName() + "!" + "\n"
                + getDescription();

    }




}
