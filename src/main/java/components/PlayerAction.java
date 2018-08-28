package components;

public class PlayerAction {
    public static void handleAction(int card, Player selected, int guess){
        switch (card){
            case 1: guard(selected, guess);
                    break;
        }
    }

    private static void guard(Player player, int guess){
        if (player.isHolding(guess)) player.knockOut();
    }
}
