package components;

import java.util.ArrayList;

public class Player {
    private String name;
    private Integer hand[];
    private ArrayList<Integer> discardPile;

    public Player(String name) {
        this.name = name;
        this.hand = new Integer[2];
        this.discardPile = new ArrayList<>();
    }

    public int getHandCount() {
        return this.hand.length;
    }

    public Integer[] getHand() {
        return this.hand.clone();
    }

    public String getName() {
        return name;
    }

    public void addCard(int cardValue){
        if (this.hand[0] == null){
            this.hand[0] = cardValue;
        } else if (this.hand[1] == null){
            this.hand[1] = cardValue;
        }
    }

    public void discard(int index) {
        this.hand[0] = null;
    }

    public int getDiscardPileLength(){
        return this.discardPile.size();
    }

    public void addToDiscardPile(int card) {
        this.discardPile.add(card);
    }
}
