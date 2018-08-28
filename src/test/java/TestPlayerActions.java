import components.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPlayerActions {
    private Player player1;
    private Player player2;

    @Before
    public void setUp() throws Exception {
        player1 = new Player("Bob");
        player2 = new Player("Vicky");
    }
//    guard
    @Test
    public void shouldBeAbleToKnockOutPlayerWithGuardIfGuessIsCorrect() {
        player1.addCard(1);
        player2.addCard(8);
        player1.playCard(1, player2, 8);
        assertEquals(true, player2.isKnockedOut());
    }
}
