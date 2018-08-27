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
        addToDiscardPile(index);
        this.hand[index] = null;
    }

    public int getDiscardPileLength(){
        return this.discardPile.size();
    }

    private void addToDiscardPile(int card) {
        this.discardPile.add(card);
    }

    public void playCard(int card) {
        addToDiscardPile(card);
    }

    public void removeCard(int cardToRemove) {
        for (int i = 0; i < this.hand.length; i++){
            if (this.hand[i] != null) {
                if (this.hand[i] == cardToRemove){
                    this.hand[i] = null;
                    break;
                }
            }
        }
    }
}
