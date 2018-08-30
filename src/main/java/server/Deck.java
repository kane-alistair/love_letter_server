package server;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Deck {
    private ArrayList<Integer> cards;
    private int burner;

    public Deck() {
        this.cards = new ArrayList<>();
        this.burner = 0;
    }

    public void prepStdDeck() {
        this.cards.clear();
        populateStdDeck();
        shuffle();
        burnCard();
    }

    private void populateStdDeck() {
        this.cards.addAll(Arrays.asList(1, 1, 1, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 7, 8));
    }

    private void shuffle() {
        Collections.shuffle(this.cards);
    }

    private void burnCard() {
        this.burner = this.cards.remove(0);
    }

    public int getNumberOfCards() {
        return this.cards.size();
    }

    public int getBurner() {
        return burner;
    }

    public boolean isCardBurned() {
        return (burner != 0);
    }

    public void dealCard(Player player) {
        player.addCard(this.cards.remove(0));
    }

    public void dealRound(Player[] players) {
        for (Player player : players){
            dealCard(player);
        }
    }
}
