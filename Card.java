import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Card {

    private Map<String, Integer> wordMap = new HashMap<>();
    protected static Map<Image, Integer> imageIntegerMap = new HashMap<>();
    private String[] folderNames = new String[]{"two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};

    public Map<Image, Integer> createImageIntegerMap() {
        createWordMap();
        for(int i = 0; i < folderNames.length; i++) {
            File imageFolder = new File("src/" + folderNames[i]);
            File[] folderImages = imageFolder.listFiles();
            for(File image : folderImages) {
                if(image.getName().endsWith(".png")) {
                    Image image1 = new Image(String.valueOf(image));
                    imageIntegerMap.put(image1, wordMap.get(folderNames[i]));
                }
            }
        }
        return imageIntegerMap;
    }

    private void createWordMap() {
        int i = 2;
        for (String folderName : folderNames) {
            wordMap.put(folderName, i);
            i++;
        }
    }

    public static int[] determineHandValue(ArrayList<Image> hand) {
        int total = 0;
        int numOfAces = 0;
        for(Image card : hand) {
            if(!(imageIntegerMap.get(card) == 1)) {
                total += imageIntegerMap.get(card);
            } else {
                if(total + 11 > 21) {
                    total += 1;
                } else {
                    total += 11;
                }
            }
        }
        return new int[]{total, numOfAces};
    }
}