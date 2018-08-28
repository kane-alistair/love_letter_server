package components;

public class PlayerAction {
    public static void handleAction(int card, Player actionTaker, Player selected, int guess){
        switch (card){
            case 1: guard(actionTaker, selected, guess);
                    break;
            case 2: priest(actionTaker, selected);
                    break;
            case 3: baron(actionTaker, selected);
                    break;
            case 4: handmaid(actionTaker);
                    break;
            case 5: prince(selected);
                    break;
        }
    }

    private static void guard(Player actionTaker, Player selected, int guess){
        if (actionTaker != selected){
            if (!selected.isProtected()){
                if (selected.isHolding(guess)) selected.knockOut();
            }
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

    private static void prince(Player selected) {
        selected.discardAndDraw(1);
    }
}
