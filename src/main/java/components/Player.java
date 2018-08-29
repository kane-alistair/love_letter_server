package components;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private String name;
    private Integer hand[];
    private ArrayList<Integer> discardPile;
    private boolean knockedOut;
    private HashMap<Player, Integer> seenCards;
    private boolean attackable;

    public Player(String name) {
        this.name = name;
        this.hand = new Integer[2];
        this.discardPile = new ArrayList<>();
        this.knockedOut = false;
        this.seenCards = new HashMap<>();
        this.attackable = true;
    }

    public String getName() {
        return name;
    }
    public Integer[] getHand() { return hand; }
    public ArrayList<Integer> getDiscardPile() {
        return discardPile;
    }
    public boolean isKnockedOut() {
        return knockedOut;
    }
    public HashMap<Player, Integer> getSeenCards() { return seenCards; }
    public boolean isAttackable() {
        return attackable;
    }
    public int getHandCount() {
        return this.hand.length;
    }

    public int heldCard(){
        if (this.hand[0] != null){
            return this.hand[0];
        }
        return this.hand[1];
    }

    public int discardPileLength(){
        return this.discardPile.size();
    }

    public int seenPileLength(){
        return this.seenCards.size();
    }

    public void knockOut(){
        this.knockedOut = true;
        discard(this.heldCard());
    }

    public boolean isHoldingCard(){
        return (this.hand[0] != null) || (this.hand[1] != null);
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

    public int removeHeldCard(){
        if (this.hand[0] != null){
            int heldCard = this.hand[0];
            this.hand[0] = null;
            return heldCard;
        } else if (this.hand[1] != null){
            int heldCard = this.hand[1];
            this.hand[1] = null;
            return heldCard;
        }
        return 0;
    }

    public void discard(int card) {
        removeCardFromHand(card);
        addCardToDiscardPile(card);
    }

    private void addCardToDiscardPile(int card){
        if (card == 8) this.knockedOut = true;
        this.discardPile.add(card);
    }

    public void addSeenCard(Player player){
        this.seenCards.put(player, player.heldCard());
    }

    public void playCard(int card, Player selected, int guess) {
        if (isHolding(card)) {
            removeCardFromHand(card);
            addCardToDiscardPile(card);
            PlayerAction.handleAction(card, this, selected, guess);
        }
    }

    public void discardAndDraw(int newCard){
        discard(this.heldCard());
        this.hand[0] = newCard;
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

    public void roundRestart(){
        this.knockedOut = false;
        removeHeldCard();
        this.discardPile.clear();
        this.seenCards.clear();
        this.attackable = true;
    }

    public void protect() {
        this.attackable = false;
    }
}
