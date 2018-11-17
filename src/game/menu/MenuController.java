package game.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MenuController implements Initializable {

    @FXML
    private Button btn_play;
    @FXML
    private Button btn_instructions;
    @FXML
    private Button btn_pontuation;
    @FXML
    private Button btn_exit;
    @FXML
    private Slider volumeSlider;
    @FXML
    private ImageView img_volume;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (!Menu.musicPlaying) {
            Menu.media = new Media(this.getClass().getResource("/game/assets/sounds/frevo.mp3").toExternalForm());
            Menu.player = new MediaPlayer(Menu.media);
            Menu.player.setVolume(0.8);
            Menu.player.setOnEndOfMedia(() -> {
                Menu.player.seek(Duration.ZERO);
            });
            Menu.player.play();
            Menu.musicPlaying = true;
        }
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

    }

    @FXML
    private void play(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/game/play/Play.fxml"));

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Play");

        Menu.player.stop();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void instructions(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/game/instructions/Instructions.fxml"));

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Instructions");

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void pontuation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/game/pontuation/Pontuation.fxml"));

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Pontuation");

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

}
