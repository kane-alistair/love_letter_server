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
}
