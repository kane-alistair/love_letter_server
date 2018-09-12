package server;

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
            case 6: king(actionTaker, selected);
                    break;
        }
    }

    private static void guard(Player actionTaker, Player selected, int guess){
        if (actionTaker != selected){
            if (selected.isAttackable()){
                if (selected.isHolding(guess)) selected.knockOut();
            }
        }
    }

    private static void priest(Player actionTaker, Player selected){
        if (selected.isAttackable()) actionTaker.addSeenCard(selected);
    }

    private static void baron(Player actionTaker, Player selected) {
        if (selected.isAttackable()) {
            if (actionTaker.heldCard() > selected.heldCard()) {
                selected.knockOut();
            } else if (selected.heldCard() > actionTaker.heldCard()) {
                actionTaker.knockOut();
            }
        }
    }

    private static void handmaid(Player actionTaker) {
        actionTaker.protect();
    }

    private static void prince(Player selected) {
        if (selected.isAttackable()) selected.discardAndDraw(1);
    }

    private static void king(Player actionTaker, Player selected) {
        int actionTakerCard = actionTaker.removeHeldCard();
        int selectedCard = selected.removeHeldCard();

        actionTaker.addCard(selectedCard);
        selected.addCard(actionTakerCard);
    }
}
