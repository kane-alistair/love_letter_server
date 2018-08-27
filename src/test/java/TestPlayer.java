import components.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestPlayer {
    private Player player1;

    @Before
    public void setUp(){
       player1 = new Player("Bob");
    }

    @Test
    public void shouldStartWithAName() {
        assertEquals("Bob", player1.getName());
    }

    @Test
    public void shouldStartWithHandArrayOfLengthTwo() {
        assertEquals(2, player1.getHandCount());
    }

    @Test
    public void shouldBeAbleToAddCardToHand() {
        player1.addCard(1);
        Integer[] expected = new Integer[2];
        expected[0] = 1;
        assertArrayEquals(expected, player1.getHand());
    }

    @Test
    public void shouldBeAbleToRemoveCardFromHand() {
        player1.addCard(5);
        player1.removeCard(5);
        Integer[] expected = new Integer[2];
        assertArrayEquals(expected, player1.getHand());
    }

    @Test
    public void shouldBeAbleToAddTwoCardsToHand() {
        player1.addCard(1);
        player1.addCard(1);
        Integer[] expected = new Integer[2];
        expected[0] = 1;
        expected[1] = 1;
        assertArrayEquals(expected, player1.getHand());
    }

    @Test
    public void shouldNotBeAbleToAddThreeCardsToHand() {
        player1.addCard(1);
        player1.addCard(1);
        player1.addCard(5);
        Integer[] expected = new Integer[2];
        expected[0] = 1;
        expected[1] = 1;
        assertArrayEquals(expected, player1.getHand());
    }

    @Test
    public void shouldBeAbleToDiscard() {
        player1.addCard(1);
        player1.discard(0);
        Integer[] expected = new Integer[2];
        assertArrayEquals(expected, player1.getHand());
    }

    @Test
    public void shouldBeAbleToDiscardByCardValue() { }

    @Test
    public void shouldStartWithAnEmptyDiscardPile() {
        assertEquals(0, player1.getDiscardPileLength());
    }

    @Test
    public void shouldAddCardToDiscardPileWhenCardIsDiscarded() {
        player1.addCard(5);
        player1.discard(0);
        assertEquals(1, player1.getDiscardPileLength());
    }

    @Test
    public void shouldAddCardToDiscardPileWhenCardIsPlayed() {
        player1.addCard(5);
        player1.playCard(5);
        assertEquals(1, player1.getDiscardPileLength());
    }

//    @Test
//    public void shouldRemoveCardFromHandWhenPlayed() {
//        player1.addCard(5);
//        player1.playCard(5);
//        Integer[] expected = new Integer[2];
//        assertArrayEquals(expected, player1.getHand());
//    }
}
