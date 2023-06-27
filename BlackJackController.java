import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class BlackJackController implements Initializable {

    @FXML
    private GridPane board;

    @FXML
    private Label balance;

    //taken care of in dealButtonPushed()
    @FXML
    private ImageView playerCard1;

    //taken care of in dealButtonPushed()
    @FXML
    private ImageView playerCard2;

    @FXML
    private ImageView playerCard3;

    @FXML
    private ImageView playerCard4;

    @FXML
    private Button hitButton;

    @FXML
    private Button standButton;

    //taken care of in dealButtonPushed()
    @FXML
    private ImageView dealerCard1;

    //taken care of in dealButtonPushed() and must be changed after player hits or stands
    @FXML
    private ImageView dealerCard2;

    @FXML
    private ImageView dealerCard3;

    @FXML
    private ImageView dealerCard4;

    @FXML
    private Button clearButton;

    //taken care of in dealButtonPushed()
    @FXML
    private Button dealButton;

    @FXML
    private Button fiveDollarChip;

    @FXML
    private Button tenDollarChip;

    @FXML
    private Button twentyfiveDollarChip;

    @FXML
    private Button fiftyDollarChip;

    @FXML
    private Button hundredDollarChip;

   //private Deck deck;

    @FXML
    private Label playerHandVal;

    @FXML
    private Label dealerHandVal;

    @FXML
    private Label betAmt;

    private BlackjackGame game;

    @FXML
    private ImageView deckOfCardsImage;

    private int currCol = 1;
    @FXML
    private Pane resultWindow;

    private ImageView newCardSlot;

    @FXML
    private Label gameResultLabel;

    @FXML
    private Button quitGameButton;

    @FXML
    private Button playAgainButton;
    
    @FXML
    private Pane placeYourBetPane;

    /**
     * this method initializes the deck image
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = new BlackjackGame();
        //        deckOfCardsImage.setImage(game.getDeck().getBackOfCard());
        //        playerHandVal.setText("Player Hand: ");
        //        dealerHandVal.setText("Dealer Hand: ");
        balance.setText("Balance: $" + game.getPlayerBalance());
        betAmt.setText("Bet Amount: $" + game.getPlayerBet());
        playAgainButton.setDisable(true);
        placeYourBetPane.setVisible(true);
        hitButton.setDisable(true);
        standButton.setDisable(true);

    }

    /**
     * this method activates the dealBet button that the player will push after placing their bet
     */
    @FXML
    private void dealButtonPushed() {
        placeYourBetPane.setVisible(false);
        game.startGame();
        dealButton.setDisable(true);
        clearButton.setDisable(true);
        fiveDollarChip.setDisable(true);
        tenDollarChip.setDisable(true);
        twentyfiveDollarChip.setDisable(true);
        fiftyDollarChip.setDisable(true);
        hundredDollarChip.setDisable(true);
        hitButton.setDisable(false);
        standButton.setDisable(false);
        Timeline startPause = new Timeline();
        startPause.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, event -> {
                    addImageView(1, 0);
                    newCardSlot.setImage(game.getPlayerCard1());

                }),
                new KeyFrame(Duration.seconds(.75), event -> {
                    addImageView(0, 0);
                    newCardSlot.setImage(game.getDealerCard1());

                }),
                new KeyFrame(Duration.seconds(1.5), event -> {
                    addImageView(1, 1);
                    newCardSlot.setImage(game.getPlayerCard2());
                }),
                new KeyFrame(Duration.seconds(2.25), event -> {
                    addImageView(0, 1);
                    newCardSlot.setImage(new Image("src/images/backOfCard.png"));
                }),
                new KeyFrame(Duration.seconds(3), event -> {
                    dealerHandVal.setText("Dealer Hand: " + (game.getDealerHandValue() - Deck.getImageIntegerMap().get(game.getDealerCard2())));
                    playerHandVal.setText("Player Hand: " + game.getPlayerHandValue());
                }));
        if (game.getPlayerHandValue() == 21 || game.getDealerHandValue() == 21) {
            String result = game.determineWinner();
            balance.setText("Balance: $" + game.getPlayerBalance());
            showGameResult(result);
        }
        PauseTransition initialDelay = new PauseTransition(Duration.seconds(0));
        initialDelay.setOnFinished(event -> startPause.play());
        initialDelay.play();
    }

    private void showGameResult(String result) {
        quitGameButton.setDisable(false);
        dealerHandVal.setText("Dealer Hand: " + game.getDealerHandValue());
//        dealerCard2.setImage(game.getDealerCard2());
        //        resultWindow.setVisible(true);
        switch (result) {
            case "push":
                board.getChildren().clear();
                gameResultLabel.setText("Push! It's a tie!");
                resultWindow.setVisible(true);
                playAgainButton.setDisable(false);
                break;
            case "both bust - push":
                board.getChildren().clear();
                gameResultLabel.setText("You both busted! Dealer and player lose!");
                resultWindow.setVisible(true);
                playAgainButton.setDisable(false);
                break;
            case "dealer bust - player wins":
                board.getChildren().clear();
                gameResultLabel.setText("Congrats! You win!");
                resultWindow.setVisible(true);
                playAgainButton.setDisable(false);
                break;
            case "player bust - dealer wins":
                board.getChildren().clear();
                gameResultLabel.setText("Dealer wins, try again.");
                resultWindow.setVisible(true);
                playAgainButton.setDisable(false);
                break;
            case "blackjack dealer wins":
                playAgainButton.setDisable(false);
                board.getChildren().clear();
                gameResultLabel.setText("Blackjack! Dealer wins!");
                resultWindow.setVisible(true);
                playAgainButton.setDisable(false);
                break;
            case "blackjack player wins":
                board.getChildren().clear();
                gameResultLabel.setText("Blackjack! Player wins!");
                resultWindow.setVisible(true);
                playAgainButton.setDisable(false);
                break;
            case "dealer wins":
                board.getChildren().clear();
                gameResultLabel.setText("Dealer wins, try again.");
                resultWindow.setVisible(true);
                playAgainButton.setDisable(false);
                break;
            case "player wins":
                board.getChildren().clear();
                gameResultLabel.setText("Congrats! You win!");
                resultWindow.setVisible(true);
                playAgainButton.setDisable(false);
                break;

        }
    }

    public void fiveButtonPushed() {
        game.placeBet(5);
        balance.setText("Balance: $" + Integer.toString(game.getPlayerBalance()));
        betAmt.setText("Bet Amount: $" + Integer.toString(game.getPlayerBet()));
    }

    public void tenButtonPushed() {
        game.placeBet(10);
        balance.setText("Balance: $" + Integer.toString(game.getPlayerBalance()));
        betAmt.setText("Bet Amount: $" + Integer.toString(game.getPlayerBet()));
    }

    public void twentyFiveButtonPushed() {
        game.placeBet(25);
        balance.setText("Balance: $" + Integer.toString(game.getPlayerBalance()));
        betAmt.setText("Bet Amount: $" + Integer.toString(game.getPlayerBet()));
    }

    public void fiftyButtonPushed() {
        game.placeBet(50);
        balance.setText("Balance: $" + Integer.toString(game.getPlayerBalance()));
        betAmt.setText("Bet Amount: $" + Integer.toString(game.getPlayerBet()));
    }

    public void hundredButtonPushed() {
        game.placeBet(100);
        balance.setText("Balance: $" + Integer.toString(game.getPlayerBalance()));
        betAmt.setText("Bet Amount: $" + Integer.toString(game.getPlayerBet()));
    }

    public void hitButtonPushed() {
        System.out.println("button pushed");
        if (game.getPlayerHandValue() < 21) {
            addImageView(1, currCol);
            Image newCard = game.playerHit();
            newCardSlot.setImage(newCard);
            playerHandVal.setText("Player Hand: " + Card.determineHandValue(game.getPlayerHand())[0]);
        }
        if (game.getPlayerHandValue() >= 21) {
            hitButton.setDisable(true);
            while (game.getDealerHandValue() < 17) {
                standButton.setDisable(true);
                addImageView(0, currCol);
                Image newCard = game.dealerHit();
                System.out.println(newCard);
                newCardSlot.setImage(newCard);
                dealerHandVal.setText("Dealer Hand: " + game.getDealerHandValue());
                currCol++;
            }
            String result = game.determineWinner();
            balance.setText("Balance: $" + game.getPlayerBalance());
            showGameResult(result);
            System.out.println(result);
        }
        quitGameButton.setDisable(false);
    }

    private void addImageView(int row, int col) {
        newCardSlot = new ImageView();
        newCardSlot.setFitHeight(101);
        newCardSlot.setFitWidth(70);
        if (col == 0 || col == 1) {
            board.add(newCardSlot, col, row);
            currCol++;
        } else {
            board.add(newCardSlot, currCol, row);
            currCol++;
        }
    }

    private void startNewGame() {
        game.startGame();
        dealButton.setDisable(false);
        hitButton.setDisable(true);
        standButton.setDisable(true);
        dealButton.setDisable(false);
        clearButton.setDisable(false);
        fiveDollarChip.setDisable(false);
        tenDollarChip.setDisable(false);
        twentyfiveDollarChip.setDisable(false);
        fiftyDollarChip.setDisable(false);
        hundredDollarChip.setDisable(false);

    }

    private void endGame() {
        currCol = 0;
        resultWindow.setVisible(false);
        hitButton.setDisable(true);
        standButton.setDisable(true);
        dealButton.setDisable(true);
        board.getChildren().clear();
        //game.resetHands();
        game.resetLabels();
        playerHandVal.setText("Player Hand: " + game.getPlayerHandValue());
        dealerHandVal.setText("Dealer Hand: " + game.getDealerHandValue());
        balance.setText("Balance: $" + game.getPlayerBalance());
        betAmt.setText("Bet Amount: $" + game.getPlayerBet());
        startNewGame();
    }

    @FXML
    private void standButtonPushed() {
        int currCol = 2;
        hitButton.setDisable(true);
        standButton.setDisable(true);
        while (game.getDealerHandValue() < 17) {
            addImageView(0, currCol);
            Image newCard = game.dealerHit();
            System.out.println(newCard);
            newCardSlot.setImage(newCard);
            dealerHandVal.setText("Dealer Hand: " + game.getDealerHandValue());
            currCol++;
        }
        String result = game.determineWinner();
        showGameResult(result);
        balance.setText("Balance: $" + game.getPlayerBalance());
        System.out.println(result);
        quitGameButton.setDisable(false);
    }

    //    @FXML
    //    private void standButtonPushed() {
    //        dealerCard2.setImage((game.getDealerHand().get(1).getImage()));
    //        dealerCard3.setImage(deck.getBackofCard());
    //
    //        while (game.getDealerHandValue() < 17) {
    //            game.dealerHit();
    //            if (game.getDealerHand().size() == 3) {
    //                dealerCard3.setImage((game.getPlayerHand().get(game.getPlayerHand().size() - 1)).getImage());
    //                dealerCard4.setImage(deck.getBackofCard());
    //            } else if (game.getDealerHand().size() == 4) {
    //                dealerCard4.setImage((game.getPlayerHand().get(game.getPlayerHand().size() - 1)).getImage());
    //            }
    //        }
    //        updateLabels();
    //        endGame();
    //    }

    @FXML
    public void quitGameButton(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void playAgainButtonPushed() {
        endGame();
        playAgainButton.setDisable(true);
        clearButton.setDisable(false);
        fiveDollarChip.setDisable(false);
        tenDollarChip.setDisable(false);
        twentyfiveDollarChip.setDisable(false);
        fiftyDollarChip.setDisable(false);
        hundredDollarChip.setDisable(false);
    }
    public void clearButtonPushed() {
        game.resetPlayerBet();
        balance.setText("Balance: $" + game.getPlayerBalance());
        betAmt.setText("Bet Amount: $" + game.getPlayerBet());
    }
}
