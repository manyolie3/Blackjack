import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BlackjackConfigurationController implements Initializable {

    @FXML
    private Button enterNameButton;

    @FXML
    private TextField nameField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onStartButtonPushed() throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameView2.fxml"));
        Parent root = loader.load();
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(new Scene(root));
        secondaryStage.show();
    }

    public void onEnterButtonPushed() {
        nameField.setEditable(false);
    }





}

