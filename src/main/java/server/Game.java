package server;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class Game {
    private boolean roundOver;
    private Deck deck;
    private List<Player> players;
    private Map<Player, Integer> wins;
    private Player prevRoundWinner;
    private HashMap<Integer, Integer> prevMovePlayerIdCard;
    private HashMap<Integer, Integer> prevMoveVictimIdGuess;
    private int numberOfRounds;

    public Game(){
        this.deck = new Deck();
        this.players = new ArrayList<>();
        this.wins = new HashMap<>();
        this.roundOver = true;
        this.prevRoundWinner = null;
        this.prevMovePlayerIdCard = new HashMap<>();
        this.prevMoveVictimIdGuess = new HashMap<>();
        this.numberOfRounds = 0;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setPrevMovePlayerIdCard(HashMap<Integer, Integer> prevMovePlayerIdCard) {
        this.prevMovePlayerIdCard = prevMovePlayerIdCard;
    }

    public HashMap<Integer, Integer> getPrevMoveVictimIdGuess() {
        return prevMoveVictimIdGuess;
    }

    public void setPrevMoveVictimIdGuess(HashMap<Integer, Integer> prevMoveVictimIdGuess) {
        this.prevMoveVictimIdGuess = prevMoveVictimIdGuess;
    }

    public void setWins(Map<Player, Integer> wins) {
        this.wins = wins;
    }

    public HashMap<Integer, Integer> getPrevMovePlayerIdCard() {
        return prevMovePlayerIdCard;
    }

    public void setPrevMovePlayer(HashMap<Integer, Integer> prevMovePlayerIdCard) {
        this.prevMovePlayerIdCard = prevMovePlayerIdCard;
    }

    public HashMap<Integer, Integer> getPrevMoveVictim() {
        return prevMoveVictimIdGuess;
    }

    public void setPrevMoveVictim(HashMap<Integer, Integer> prevMoveVictim) {
        this.prevMoveVictimIdGuess = prevMoveVictim;
    }

    public Player getPrevRoundWinner() {
        return prevRoundWinner;
    }

    public void setPrevRoundWinner(Player prevRoundWinner) {
        this.prevRoundWinner = prevRoundWinner;
    }

    public HashMap<Integer, Integer> getPrevMove() {
        return prevMovePlayerIdCard;
    }

    public void setPrevMove(HashMap<Integer, Integer> prevMovePlayer) {
        this.prevMovePlayerIdCard = prevMovePlayer;
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
        assignFirstActor();
        setRoundOver(false);
    }

    private void assignFirstActor() {
//        random first actor or winner of previous round
        if (prevRoundWinner == null){
            Random rand = new Random();
            int value = rand.nextInt(this.players.size());
            this.players.get(value).setActiveTurn(true);
            dealCard(this.players.get(value));
        } else {
            prevRoundWinner.setActiveTurn(true);
            dealCard(prevRoundWinner);
        }
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

    public void playerTakeTurn(int id, int card, int guess, int selectedId) {
        Player turnTaker = getPlayer(id);
        Player selectedPlayer = getPlayer(selectedId);
        turnTaker.playCard(card, selectedPlayer, guess);
        turnTaker.setActiveTurn(false);
        this.prevMovePlayerIdCard.clear();
        this.prevMoveVictimIdGuess.clear();
        this.prevMovePlayerIdCard.put(id, card);
        this.prevMoveVictimIdGuess.put(selectedId, guess);
        boolean isRoundOver = checkRoundOver();

        if (isRoundOver){
            endRound();
        } else {
            int nextPlayerId = this.players.indexOf(turnTaker) + 1;
            assignNextTurn(nextPlayerId);
        }
    }

    private void assignNextTurn(int id) {
        if (id == players.size()){
            players.get(0).setAttackable(true);
            players.get(0).setActiveTurn(true);
            dealCard(players.get(0));
        } else {
            players.get(id).setActiveTurn(true);
            players.get(id).setAttackable(true);
            dealCard(players.get(id));
        }
    }

    private boolean checkRoundOver() {
        if (this.deck.getNumberOfCards() == 0) return true;

        int numberOfRemainingPlayers = 0;

        for (Player player : this.players){
            if (!player.isKnockedOut()){
                numberOfRemainingPlayers ++;
            }
        }

        return numberOfRemainingPlayers == 1;
    }

    public void endRound() {
        this.roundOver = true;
        this.numberOfRounds++;

        Player soleWinningPlayer = new Player("temp");
        int numberOfRemainingPlayers = 0;
        for (Player player : this.players){
            if (!player.isKnockedOut()) numberOfRemainingPlayers ++;
            soleWinningPlayer = player;
        }

        if (numberOfRemainingPlayers == 1) {
            incrementPlayerWins(soleWinningPlayer);
        } else {
            for (Player player : findRoundWinner()){
                incrementPlayerWins(player);
            }
        }
    }
}
