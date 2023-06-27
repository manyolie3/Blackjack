import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameSelectionController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onblackjackButtonPushed() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BlackjackConfigurationView.fxml"));
        Parent root = loader.load();
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(new Scene(root));
        secondaryStage.show();
    }

}
