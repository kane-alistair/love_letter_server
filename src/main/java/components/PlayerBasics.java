package components;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerBasics {
    private String name;
    private Integer hand[];
    private ArrayList<Integer> discardPile;
    private boolean knockedOut;
    private HashMap<Player, Integer> seenCards;
    private boolean attackable;

    public PlayerBasics(String name) {
        this.name = name;
        this.hand = new Integer[2];
        this.discardPile = new ArrayList<>();
        this.knockedOut = false;
        this.seenCards = new HashMap<>();
        this.attackable = true;
    }

    public boolean isAttackable() {
        return attackable;
    }

    public HashMap<Player, Integer> getSeenCards() {
        return seenCards;
    }

    public boolean isKnockedOut() {
        return knockedOut;
    }

    public ArrayList<Integer> getDiscardPile() {
        return discardPile;
    }

    public Integer[] getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }
}
