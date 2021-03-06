package game.pontuation;

import game.menu.Menu;
import game.menu.MenuController;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PontuationController implements Initializable {

    @FXML
    private Slider volumeSlider;
    @FXML
    private ImageView img_volume;
    @FXML
    private GridPane grid;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        volumeSlider.setValue(Menu.player.getVolume() * 100);
        if (Menu.player.getVolume() == 0) {
            img_volume.setImage(new Image("/game/assets/images/soundOffWhite.png"));
        }
        volumeSlider.valueProperty().addListener((Observable observable) -> {
            Menu.player.setVolume(volumeSlider.getValue() / 100);
            if (Menu.player.getVolume() == 0) {
                img_volume.setImage(new Image("/game/assets/images/soundOffWhite.png"));
            } else {
                img_volume.setImage(new Image("/game/assets/images/soundOnWhite.png"));
            }
        });
        
        Collections.sort(MenuController.players);
        if (!MenuController.players.isEmpty()) {
            for (int i = 0; i < Math.min(MenuController.players.size(), 10); i++) {
                grid.add(new Label(MenuController.players.get(i).getName()), 0, i);
                grid.add(new Label(Integer.toString(MenuController.players.get(i).getScore())), 1, i);
            }
            
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/game/menu/Menu.fxml"));

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Menu");

        stage.setScene(scene);
        stage.show();
    }

}
