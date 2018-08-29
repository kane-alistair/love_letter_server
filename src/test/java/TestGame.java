import components.Deck;
import components.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TestGame {
    private Game game1;
    private Player player1;
    private Player player2;
    private Player player3;
    private Player[] players;
    private Deck deck1;

    @Before
    public void setUp() throws Exception {
        player1 = new Player("Bob");
        player2 = new Player("Vic");
        player3 = new Player("Joe");
        players = new Player[3];
        players[0] = player1;
        players[1] = player2;
        players[2] = player3;

        deck1 = new Deck();
        deck1.prepStdDeck();
        game1 = new Game(deck1, players);
    }

    @Test
    public void shouldStartWithThreePlayers() {
        assertEquals(3, game1.getNumberOfPlayers());
    }

    @Test
    public void shouldStartWithDeck() {
        assertEquals(deck1, game1.getDeck());
    }

    @Test
    public void shouldBeAbleToAssignARoundWin() {
        game1.incrementPlayerWins(player1);
        HashMap<Player, Integer> expected = new HashMap<>();
        expected.put(player1, 1);
        assertEquals(expected, game1.getWins());
    }

    @Test
    public void shouldBeAbleToTallyMultipleRoundWins() {
        game1.incrementPlayerWins(player1);
        game1.incrementPlayerWins(player1);
        HashMap<Player, Integer> expected = new HashMap<>();
        expected.put(player1, 2);
        assertEquals(expected, game1.getWins());
    }

    @Test
    public void shouldKeepTrackOfCurrentWinningPlayer() {
        game1.incrementPlayerWins(player1);
        assertEquals(player1, game1.currentWinner().get(0));
    }

    @Test
    public void shouldKeepTrackOfMultipleCurrentWinningPlayers() {
        game1.incrementPlayerWins(player1);
        game1.incrementPlayerWins(player2);
        game1.incrementPlayerWins(player3);
        assertEquals(3, game1.currentWinner().size());
    }

    @Test
    public void shouldBeAbleToFindWinnerAtEndOfRound() {
        player1.addCard(3);
        player2.addCard(5);
        player3.addCard(8);
        game1.endRound();
        assertEquals(1, game1.currentWinner().size());
        assertEquals(player3, game1.currentWinner().get(0));
    }

    @Test
    public void shouldEndRoundWhenOutOfCards() { }

    @Test
    public void shouldClearPlayersHandsAtEndOfRound() { }
}
