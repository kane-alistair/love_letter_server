package server;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Game {
    private boolean roundOver;
    private Deck deck;
    private List<Player> players;
    private Map<Player, Integer> wins;

    public Game(){
        this.deck = new Deck();
        this.players = new ArrayList<>();
        this.wins = new HashMap<>();
        this.roundOver = true;
    }

    public boolean isRoundOver() {
        return roundOver;
    }

    @PostConstruct
    public void setNewDeck(){
        this.deck = new Deck();
    }

    @PostConstruct
    public void setEmptyPlayerList(){
        this.players = new ArrayList<>();
    }

    @PostConstruct
    public void setEmptyWinsMap() {
        this.wins = new HashMap<>();
    }

    public void setRoundOver(boolean state){
        this.roundOver = state;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void add(Player player){
        this.players.add(player);
    }

    public void prepNewRound(){
        this.deck.prepStdDeck();
        prepPlayers();
        openDeal();
        setRoundOver(false);
    }

    private void openDeal(){
        this.deck.dealRound(this.players);
    }

    private void prepPlayers() {
        for (Player player: this.players){
            player.roundRestart();
        }
    }

    public int cardsRemaining(){
        return this.deck.getNumberOfCards();
    }

    public int getNumberOfPlayers() { return this.players.size(); }

    public Deck getDeck() {
        return deck;
    }

    public Map<Player, Integer> getWins() {
        return this.wins;
    }

    public void incrementPlayerWins(Player player) {
        if (this.wins.containsKey(player)) {
            wins.put(player, this.wins.get(player) + 1);
        } else {
            wins.put(player, 1);
        }
    }

    public List<Player> currentWinner() {
        List<Player> winners = new ArrayList<>();
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

    private List<Player> findRoundWinner() {
        int winningHand = this.players.get(0).heldCard();
        List<Player> winners = new ArrayList<>();
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

    private List<Player> findRoundWinnerWhenDraw(List<Player> drawingWinners) {
        List<Player> afterDiscardPileComparison = new ArrayList<>();
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

    public Player getPlayer(int id) {
        for (Player player : this.players){
            if (player.getExternalId() == id) return player;
        }
        return null;
    }

    public void playerTakeTurn(int id, int card, int guess, int selected) {
        Player turnTaker = getPlayer(id);
        Player selectedPlayer = getPlayer(selected);
        turnTaker.playCard(card, selectedPlayer, guess);
    }
}
