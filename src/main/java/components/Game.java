package components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {
    private Deck deck;
    private Player[] players;
    private HashMap<Player, Integer> wins;

    public Game(Deck deck, Player[] players){
        this.deck = deck;
        this.players = players;
        this.wins = new HashMap<>();
    }

    public void prepNewRound(){
        this.deck.prepStdDeck();
        prepPlayers();
    }

    private void prepPlayers() {
        for (Player player: this.players){
            player.roundRestart();
        }
    }

    public int getNumberOfPlayers() {
        return this.players.length;
    }

    public Deck getDeck() {
        return deck;
    }

    public boolean gameHasPlayer(Player player){
        for (Player gamePlayer : this.players){
            if (gamePlayer == player){
                return true;
            }
        }
        return false;
    }

    public Object getWins() {
        return this.wins;
    }

    public void incrementPlayerWins(Player player) {
        if (gameHasPlayer(player)) {
            if (this.wins.containsKey(player)) {
                wins.put(player, this.wins.get(player) + 1);
            } else {
                wins.put(player, 1);
            }
        }
    }

    public ArrayList<Player> currentWinner() {
        ArrayList<Player> winners = new ArrayList<>();
        int mostWins = 0;

        for (Map.Entry<Player, Integer> entry : this.wins.entrySet()){
            if (entry.getValue() > mostWins){
                mostWins = entry.getValue();
                winners.clear();
                winners.add(entry.getKey());
            } else if (entry.getValue() == mostWins){
                winners.add(entry.getKey());
            }
        }

        return winners;
    }

    public void endRound() {
        for (Player player : findRoundWinner()){
            incrementPlayerWins(player);
        }
    }

    private ArrayList<Player> findRoundWinner() {
        int winningHand = this.players[0].heldCard();
        ArrayList<Player> winners = new ArrayList<>();
        winners.add(this.players[0]);

        for (int i = 1; i < this.players.length; i++){
            if (this.players[i].heldCard() > winningHand){
                winners.clear();
                winners.add(this.players[i]);
                winningHand = this.players[i].heldCard();
            } else if(this.players[i].heldCard() == winningHand){
                winners.add(this.players[i]);
            }
        }

        if (winners.size() > 1){
            return (findRoundWinnerWhenDraw(winners));
        }

        return winners;
    }

    private ArrayList<Player> findRoundWinnerWhenDraw(ArrayList<Player> drawingWinners) {
        ArrayList<Player> afterDiscardPileComparison = new ArrayList<>();
        afterDiscardPileComparison.add(drawingWinners.get(0));
        int highestDiscardPile = tallyDiscardPile(drawingWinners.get(0));

        for (int i = 1; i < drawingWinners.size(); i++){
            int discardTotal = tallyDiscardPile(drawingWinners.get(i));
            if (discardTotal > highestDiscardPile) {
                afterDiscardPileComparison.clear();
                afterDiscardPileComparison.add(drawingWinners.get(i));
                highestDiscardPile = discardTotal;
            } else if (discardTotal == highestDiscardPile){
                afterDiscardPileComparison.add(drawingWinners.get(i));
            }
        }

        return afterDiscardPileComparison;
    }

    private int tallyDiscardPile(Player player) {
        int total = 0;
        for (int value : player.getDiscardPile()){
            total += value;
        }
        return total;
    }
}
