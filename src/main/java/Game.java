import components.Deck;
import components.Player;

import java.util.HashMap;

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

    public void roundWinner(Player player) {
        if (gameHasPlayer(player)) {
            if (this.wins.containsKey(player)) {
                wins.put(player, this.wins.get(player) + 1);
            } else {
                wins.put(player, 1);
            }
        }
    }

    public Player currentWinner() {
        for ()
    }
}
