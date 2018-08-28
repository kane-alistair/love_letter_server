package components;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private String name;
    private Integer hand[];
    private ArrayList<Integer> discardPile;
    private boolean knockedOut;
    private HashMap<Player, Integer> seenCards;

    public Player(String name) {
        this.name = name;
        this.hand = new Integer[2];
        this.discardPile = new ArrayList<>();
        this.knockedOut = false;
        this.seenCards = new HashMap<>();
    }

    public int getHandCount() {
        return this.hand.length;
    }

    public Integer[] getHand() {
        return this.hand.clone();
    }

    public int getHeldCard(){
        if (this.hand[0] != null){
            return this.hand[0];
        }
        return this.hand[1];
    }

    public String getName() {
        return name;
    }

    public int getDiscardPileLength(){
        return this.discardPile.size();
    }

    public int getSeenPileLength(){
        return this.seenCards.size();
    }

    public void knockOut(){
        this.knockedOut = true;
        discard(this.hand[0]);
    }

    public boolean isKnockedOut() {
        return knockedOut;
    }

    public boolean isHolding(int card) {
        for (Integer holeCard : this.hand){
            if (holeCard != null){
                if (holeCard == card) return true;
            }
        }
        return false;
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

    public void addSeenCard(Player player){
        this.seenCards.put(player, player.getHeldCard());
    }

    public void playCard(int card, Player selected, int guess) {
        if (isHolding(card)) {
            removeCardFromHand(card);
            addCardToDiscardPile(card);
            PlayerAction.handleAction(this, card, selected, guess);
        }
    }

    private void removeCardFromHand(int cardToRemove) {
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
