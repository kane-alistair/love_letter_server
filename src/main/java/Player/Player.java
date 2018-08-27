package Player;

import java.lang.reflect.Array;

public class Player {
    private String name;
    private Integer hand[];

    public Player(String name) {
        this.name = name;
        this.hand = new Integer[2];
    }

    public int getHandCount() {
        return this.hand.length;
    }
}
