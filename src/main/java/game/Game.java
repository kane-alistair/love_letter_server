package game;

import player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {
    private Deck deck;
    private ArrayList<Player> players;
    private HashMap<Player, Integer> wins;

    public Game(Deck deck){
        this.deck = deck;
        this.players = new ArrayList<>();
        this.wins = new HashMap<>();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(String name){
        this.players.add(new Player(name));
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

    public int getNumberOfPlayers() { return this.players.size(); }

    public Deck getDeck() {
        return deck;
    }

    public HashMap<Player, Integer> getWins() {
        return this.wins;
    }

    public void incrementPlayerWins(Player player) {
        if (this.wins.containsKey(player)) {
            wins.put(player, this.wins.get(player) + 1);
        } else {
            wins.put(player, 1);
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
        int winningHand = this.players.get(0).heldCard();
        ArrayList<Player> winners = new ArrayList<>();
        winners.add(this.players.get(0));

        for (int i = 1; i < this.players.size(); i++){
            if (this.players.get(i).heldCard() > winningHand){
                winners.clear();
                winners.add(this.players.get(i));
                winningHand = this.players.get(i).heldCard();
            } else if(this.players.get(i).heldCard() == winningHand){
                winners.add(this.players.get(i));
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

    public void removePlayer(int id) {
        Player playerToDelete = new Player("Temp");

        for (Player player : this.players){
            if (player.getExternalId() == id) playerToDelete = player;
        }

        this.players.remove(playerToDelete);
    }

    public void dealCard(Player player) {
        this.deck.dealCard(player);
    }
}
