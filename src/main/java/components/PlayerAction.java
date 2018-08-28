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
            case 4: handmaid(actionTaker);
        }
    }

    private static void guard(Player selected, int guess){
        if (!selected.isProtected()){
            if (selected.isHolding(guess)) selected.knockOut();
        }
    }

    private static void priest(Player actionTaker, Player selected){
        if (!selected.isProtected()) actionTaker.addSeenCard(selected);
    }

    private static void baron(Player actionTaker, Player selected) {
        if (!selected.isProtected()) {
            if (actionTaker.getHeldCard() > selected.getHeldCard()) {
                selected.knockOut();
            } else if (selected.getHeldCard() > actionTaker.getHeldCard()) {
                actionTaker.knockOut();
            }
        }
    }

    private static void handmaid(Player actionTaker) {
        actionTaker.protect();
    }
}
