public class Player {

    private int betAmount;
    private int balance;
    private String name;
    private String sprite;
    private int handValue;

    public Player(String name, String sprite) {
        this.balance = 2000;
        this.name = name;
        this.betAmount = 0;
        this.sprite = sprite;
        this.handValue = 0;

    }
    
    //GETTERS AND SETTER FOR ALL VARIABLES (IF APPLICABLE)
    public int getBalance() {
        return balance;
    }

    public void setBalance(int amount) {
        balance = amount;
    }

    public String getName() {
        return name;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int newBetAmount) {
        betAmount = newBetAmount;
    }

    public String getSprite() {
        return sprite;
    }

    public int getHandValue() {
        return handValue;
    }

    public void updateBalance(int amount) {
        //create method that decreases the players balance once a bet is made and when a bet is lost
    }

    public void updateBetAmount(int amount) {
        //create method that increases player's balance once a bet is won!
    }
    
}