import components.Deck;
import components.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDeck {
    private Deck deck;
    
    @Before
    public void setUp() throws Exception {
        deck = new Deck();
        deck.prepStdDeck();
    }

    @Test
    public void shouldStartWithSixteenCardsAndOneBurner() {
        assertEquals(15, deck.getNumberOfCards());
        assertEquals(true, deck.isCardBurned());
    }

    @Test
    public void shouldBeAbleToDealCardToPlayer() {
        Player player1 = new Player("Bob");

        deck.dealCard(player1);
        assertEquals(true, player1.isHoldingCard());
    }

    @Test
    public void shouldBeAbleToDealARoundOfCards() {
        Player player1 = new Player("Bob");
        Player player2 = new Player("Joe");
        Player player3 = new Player("Vic");

        Player[] players = new Player[3];
        players[0] = player1;
        players[1] = player2;
        players[2] = player3;

        deck.dealRound(players);
        assertEquals(true, player1.isHoldingCard());
        assertEquals(true, player2.isHoldingCard());
        assertEquals(true, player3.isHoldingCard());
        assertEquals(12, deck.getNumberOfCards());
    }
}
