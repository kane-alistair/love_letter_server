import components.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCardActions {
    private Player player1;
    private Player player2;


    @Before
    public void setUp() throws Exception {
        player1 = new Player("Bob");
        player2 = new Player("Vicky");
    }

    @Test
    public void shouldBeAbleToGuard() {
        player1.addCard(1);
        player1.playCard(1);
    }
}
