package components;

public class Player {
    private String name;
    private Integer hand[];

    public Player(String name) {
        this.name = name;
        this.hand = new Integer[2];
    }

    public int getHandCount() {
        return this.hand.length;
    }

    public Integer[] getHand() {
        return this.hand.clone();
    }

    public String getName() {
        return name;
    }

    public void addCard(int cardValue){
        this.hand[0] = cardValue;
    }


}
