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

    @Test
    public void shouldBeAbleToKnockOutPlayerIfGuessIsCorrectWhenGuard() {
        player1.addCard(1);
        player2.addCard(8);
        player1.playCard(1, player2, 8);
        assertEquals(true, player2.isKnockedOut());
    }

    @Test
    public void shouldNotBeKnockedOutIfGuessIsIncorrectWhenGuard() {
        player1.addCard(1);
        player2.addCard(8);
        player1.playCard(1, player2, 5);
        assertEquals(false, player2.isKnockedOut());
    }

    @Test
    public void shouldNotBeAbleToAffectSelfWhenGuard() {
        player1.addCard(1);
        player1.addCard(3);
        player1.playCard(1, player1, 3);
        assertEquals(false, player1.isKnockedOut());
    }

    @Test
    public void shouldAddToSeenPileWhenPriest() {
        player1.addCard(2);
        player2.addCard(5);
        player1.playCard(2, player2, 0);
        assertEquals(1, player1.getSeenPileLength());
    }

    @Test
    public void shouldKnockOutPlayerWithLowerCardWhenBaron() {
        player1.addCard(3);
        player1.addCard(8);
        player2.addCard(1);
        player1.playCard(3, player2, 0);
        assertEquals(true, player2.isKnockedOut());
    }

    @Test
    public void shouldKnockOutActionTakerWithLowerCardWhenBaron() {
        player1.addCard(3);
        player1.addCard(1);
        player2.addCard(8);
        player1.playCard(3, player2, 0);
        assertEquals(true, player1.isKnockedOut());
    }

    @Test
    public void shouldHaveNoEffectIfDrawWhenBaron() {
        player1.addCard(3);
        player1.addCard(5);
        player2.addCard(5);
        player1.playCard(3, player2, 0);
        assertEquals(false, player1.isKnockedOut());
        assertEquals(false, player2.isKnockedOut());
    }

    @Test
    public void shouldNotBeAffectedByAttackWhenHandMaid() {
        player1.addCard(4);
        player1.addCard(3);
        player2.addCard(1);

        player1.playCard(4, player1, 0);
        player2.playCard(1, player1, 3);
        assertEquals(false, player1.isKnockedOut());
    }

    @Test
    public void shouldDiscardAndDrawWhenPrince() {
        player1.addCard(5);
        player2.addCard(3);
        player1.playCard(5, player2, 0);
        assertEquals(1, player2.getHeldCard());
    }

    @Test
    public void shouldSwapHandsWhenKing() {
        player1.addCard(6);
        player1.addCard(7);
        player2.addCard(3);

        player1.playCard(6, player2, 0);
        assertEquals(3, player1.getHeldCard());
        assertEquals(7, player2.getHeldCard());
    }
}
