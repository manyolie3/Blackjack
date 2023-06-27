import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BlackjackGame {

    private Deck deck;
    private ArrayList<Image> playerHand;
    private int playerHandValue;
    private ArrayList<Image> dealerHand;
    private int dealerHandValue;
    private int playerBalance;
    private int playerBet;
    private Map<Image, Integer> imageIntegerMap;
    private Image playerCard1;
    private Image playerCard2;
    private Image dealerCard1;
    private Image dealerCard2;
     
    @FXML
    private ImageView deckOfCardsImage;

    public BlackjackGame() {
        deck = new Deck();
        imageIntegerMap = Deck.getImageIntegerMap();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        playerHandValue = 0;
        dealerHandValue = 0;
        playerBalance = 2000;
        playerBet = 0;
            
    }

    /**
     * @author Aliyah Crumbley, Marie Andry
     * This method starts a new game, shuffles the deck, and deals the first two cards for the dealer and player
     */
    public void startGame() {
        playerHand.clear();
        dealerHand.clear();
        playerHandValue = 0;
        dealerHandValue = 0;

        playerCard1 = deck.dealTopCard();
        playerHand.add(playerCard1);
        playerHandValue += imageIntegerMap.get(playerCard1);

        dealerCard1 = deck.dealTopCard();
        dealerHand.add(dealerCard1);
        dealerHandValue += imageIntegerMap.get(dealerCard1);

        playerCard2 = deck.dealTopCard();
        playerHand.add(playerCard2);
        playerHandValue += imageIntegerMap.get(playerCard2);

        dealerCard2 = deck.dealTopCard();
        dealerHand.add(dealerCard2);
        dealerHandValue += imageIntegerMap.get(dealerCard2);


    }

    public int getPlayerBalance() {
        return playerBalance;
    }

    public int getPlayerBet() {
        return playerBet;
    }

    public Deck getDeck() {
        return deck;
    }

    public Image playerHit() {
        Image nextCard = deck.dealTopCard();
        playerHand.add(nextCard);
        playerHandValue += imageIntegerMap.get(nextCard);
        return nextCard;
    }

    public void placeBet(int amount) {
        playerBet += amount;
        playerBalance -= amount;
    }

    public Image dealerHit() {
        Image nextCard = deck.dealTopCard();
        dealerHand.add(nextCard);
        dealerHandValue += imageIntegerMap.get(nextCard);
        return nextCard;
    }
    public int getPlayerHandValue() {
        return playerHandValue;
    }

    public int getDealerHandValue() {
        return dealerHandValue;
    }

    public boolean isBust(ArrayList<Image> hand) {
        int currHandValue = Card.determineHandValue(hand)[0];
        int numOfAces = Card.determineHandValue(hand)[1];
        if (numOfAces == 0) {
            return currHandValue > 21;
        } else {
            while (numOfAces > 0) {
                if (currHandValue <= 10) {
                    currHandValue += 11;
                    numOfAces--;
                    continue;
                } else if (currHandValue < 21) {
                    currHandValue += 1;
                    numOfAces--;
                    continue;
                } else {
                    break;
                }
            }
        }
        return currHandValue > 21;
    }

    public boolean isBlackJack(int handValue) {
        return handValue == 21;
    }

    public String determineWinner() {
        String result = null;
        if(dealerHandValue == playerHandValue) {
            result = "push";
            playerBalance += playerBet;
            System.out.println(playerBalance);
        } else if(isBust(dealerHand) || isBust(playerHand)) {
            if(dealerHandValue > 21 && playerHandValue > 21) {
                result = "both bust - push";
                playerBalance += playerBet;
                System.out.println(playerBalance);
            } else if((isBust(dealerHand)) && (playerHandValue <= 21)) {
                result = "dealer bust - player wins";
                playerBalance += (playerBet * 2);
                System.out.println(playerBalance);
            } else if ((isBust(playerHand)) && (dealerHandValue <= 21)){
                result = "player bust - dealer wins";
                System.out.println(playerBalance);
            }
        } else if(isBlackJack(dealerHandValue) || isBlackJack(playerHandValue)) {
            if(dealerHandValue == playerHandValue) {
                result = "blackjack tie - push";
                playerBalance += playerBet;
                System.out.println(playerBalance);
            } else if(isBlackJack(dealerHandValue)) {
                result = "blackjack dealer wins";
            } else {
                result = "blackjack player wins";
                playerBalance += (playerBet * 2);
                System.out.println(playerBalance);
            }
        } else {
            if(dealerHandValue > playerHandValue) {
                result = "dealer wins";
                System.out.println(playerBalance);
            } else {
                result = "player wins";
                playerBalance += (playerBet * 2);
                System.out.println(playerBalance);
            }
        }
        return result;
    }

    public Image getPlayerCard1() {
        return playerCard1;
    }

    public Image getPlayerCard2() {
        return playerCard2;
    }

    public Image getDealerCard1() {
        return dealerCard1;
    }

    public Image getDealerCard2() {
        return dealerCard2;
    }

    public ArrayList<Image> getDealerHand() {
        return dealerHand;
    }

    public ArrayList<Image> getPlayerHand() {
        return playerHand;
    }

    public void resetLabels() {
        playerHandValue = 0;
        dealerHandValue = 0;
        playerBet = 0;
    }

    public void resetPlayerBet() {
        playerBalance += playerBet;
        playerBet = 0;
    }
    public void resetHands() {
        //playerHandValue = 0;
        //dealerHandValue = 0;
    }
}
