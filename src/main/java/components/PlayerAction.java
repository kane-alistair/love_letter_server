package components;

public class PlayerAction {
    public static void handleAction(Player actionTaker, int card, Player selected, int guess){
        switch (card){
            case 1: guard(selected, guess);
                    break;
            case 2: priest(actionTaker, selected);
        }
    }

    private static void guard(Player player, int guess){
        if (player.isHolding(guess)) player.knockOut();
    }

    private static void priest(Player actionTaker, Player selected){
        actionTaker.addSeenCard(selected);
    }
}
