package components;

public class PlayerAction {
    public static void handleAction(int card, Player actionTaker, Player selected, int guess){
        switch (card){
            case 1: guard(selected, guess);
                    break;
            case 2: priest(actionTaker, selected);
                    break;
            case 3: baron(actionTaker, selected);
                    break;
        }
    }

    private static void baron(Player actionTaker, Player selected) {
        if (actionTaker.getHeldCard() > selected.getHeldCard()) {
            selected.knockOut();
        } else if (selected.getHeldCard() > actionTaker.getHeldCard()) {
            actionTaker.knockOut();
        }
    }

    private static void guard(Player player, int guess){
        if (player.isHolding(guess)) player.knockOut();
    }

    private static void priest(Player actionTaker, Player selected){
        actionTaker.addSeenCard(selected);
    }


}
