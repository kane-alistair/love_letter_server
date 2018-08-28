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

    public void discard(int card) {
        removeCardFromHand(card);
        addCardToDiscardPile(card);
    }

    private void addCardToDiscardPile(int card){
        this.discardPile.add(card);
    }

    public int getDiscardPileLength(){
        return this.discardPile.size();
    }



    public void playCard(int card) {
        removeCardFromHand(card);
        addCardToDiscardPile(card);
    }

    public void removeCardFromHand(int cardToRemove) {
        for (int i = 0; i < this.hand.length; i++){
            if (this.hand[i] != null) {
                if (this.hand[i] == cardToRemove){
                    this.hand[i] = null;
                    break;
                }
            }
        }
    }

    public int getFirstCard() {
        return this.hand[0];
    }

    public int getSecondCard() {
        return this.hand[1];
    }

    public boolean isHolding(int card) {
        for (Integer holeCard : this.hand){
            if (holeCard != null){
                if (holeCard == card) return true;
            }
        }
        return false;
    }
}
