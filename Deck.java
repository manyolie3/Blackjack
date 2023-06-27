//import javafx.scene.image.Image;
import javafx.scene.image.Image;
import java.util.*;

public class Deck extends Card {
    private ArrayList<Map.Entry<Image, Integer>> deck;
    private Image backOfCard;
//    private Map<String, Integer> wordMap = new HashMap<>();
//    private static Map<Image, Integer> imageIntegerMap = new HashMap<>();

    public static Map<Image, Integer> getImageIntegerMap() {
        return imageIntegerMap;
    }
//
//    public Map<String, Integer> getWordMap() {
//        return wordMap;
//    }


    /**
     * This constructor converts the image-value HashMap into an ArrayList
     */
    public Deck() {
        deck = new ArrayList<>(createImageIntegerMap().entrySet());
        Collections.shuffle(deck);
        backOfCard = new Image("/src/images/backOfCard.png");
    }

    //GETTER FOR BACK OF CARD IMAGE
    public Image getBackOfCard() {
        return backOfCard;
    }

    /**
     * this method will deal cards when 'deal' button is pushed
     * @return card that is dealt and removed from deck
     */
    public Image dealTopCard() {
        if (deck.size() > 0) {
            int newCardValue = deck.get(0).getValue();
            Image newCard = deck.get(0).getKey();
            deck.remove(0);
            return newCard;
        } else {
            return null;
        }
    }

}
