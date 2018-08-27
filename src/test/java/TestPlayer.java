import Player.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPlayer {
    Player player1;
    @Before
    public void setUp(){
       player1 = new Player("Bob");
    }

    @Test
    public void shouldStartWithHandArray() {
        assertEquals(2, player1.getHandCount());
    }

}
