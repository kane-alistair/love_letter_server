import server.game.Deck;
import server.game.Game;
import server.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TestGame {
    private Game game1;
    private Deck deck1;
    private Player player1;
    private Player player2;
    private Player player3;

    @Before
    public void setUp() {
        deck1 = new Deck();
        deck1.prepStdDeck();
        game1 = new Game(deck1);
        game1.addPlayer("Bob");
        game1.addPlayer("Jan");
        game1.addPlayer("Mop");
        player1 = game1.getPlayers().get(0);
        player2 = game1.getPlayers().get(1);
        player3 = game1.getPlayers().get(2);
    }

    @Test
    public void shouldStartWithThreePlayers() {
        assertEquals(3, game1.getNumberOfPlayers());
    }

    @Test
    public void shouldBeAbleToAddPlayers() {
        game1.addPlayer("Hat");
        assertEquals(4, game1.getNumberOfPlayers());
    }

    @Test
    public void shouldStartWithDeck() {
        assertEquals(deck1, game1.getDeck());
    }

    @Test
    public void shouldBeAbleToAssignARoundWin() {
        game1.incrementPlayerWins(player1);
        HashMap<Player, Integer> expected = new HashMap<>();
        expected.put(game1.getPlayers().get(0), 1);
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
    public void shouldBeAbleToAssignWinToWinnerAtEndOfRound() {
        player1.addCard(8);
        player2.addCard(2);
        player3.addCard(1);
        game1.endRound();
        assertEquals(1, game1.currentWinner().size());
        assertEquals(player1, game1.currentWinner().get(0));
    }

    @Test
    public void shouldTallyDiscardPileWhenDrawToFindWinner() {
//        player1 discard pile is larger than others, so should be sole winner of round
        player1.addCard(1);
        player1.discard(1);
        player1.addCard(5);
        player2.addCard(5);
        player3.addCard(5);
        game1.endRound();
        assertEquals(1, game1.currentWinner().size());
        assertEquals(player1, game1.currentWinner().get(0));
    }

    @Test
    public void shouldAssignMultipleWinsToOverallDraw() {
//          players discard pile totals are the same, so should all win round
        player1.addCard(5);
        player2.addCard(5);
        player3.addCard(5);
        game1.endRound();
        assertEquals(3, game1.currentWinner().size());
    }
}
