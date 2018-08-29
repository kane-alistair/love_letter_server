import components.Deck;
import components.Player;

import java.lang.reflect.Array;
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
        assignRoundWin();
    }

    private void assignRoundWin() {
        incrementPlayerWins(findRoundWinner());
    }

    private Player findRoundWinner() {
        int winningHand = 0;
        ArrayList<Player> winners = new ArrayList<>();
        winners.add(this.players[0]);

        for (Player player : this.players){
            if (player.getHeldCard() > winningHand){
                winners.clear();
                winners.add(player);
                winningHand = player.getHeldCard();
            }
        }

        return winners.get(0);
//        if (winners.size() > 1){
//            return (calculateDraw())
//        }
    }
}
