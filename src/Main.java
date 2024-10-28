//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import core.Dungeon;
import core.Player;
public class Main {



    public static void main(String[] args) {
        Player tony = new Player( 30, 8, 5, 5,   null);
        Dungeon dungeon = new Dungeon(tony, 10);
        dungeon.run();
    }

}