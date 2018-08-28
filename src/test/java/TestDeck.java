import components.Deck;
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
        assertEquals(true, deck.isBurner());
    }
}
