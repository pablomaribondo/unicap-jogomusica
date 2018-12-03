package game.menu;

import game.data.RegisterOnFile;
import game.play.MusicGenre;
import game.play.PlayController;
import game.play.Player;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
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
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MenuController implements Initializable {

    @FXML
    private Slider volumeSlider;
    @FXML
    private ImageView img_volume;

    private String fileName;

    public static ArrayList<Player> players;

    private static boolean read = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            fileName = new File(".").getCanonicalPath() + "/src/game/data/players.dat";
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!read) {
            initFile();
            initMusicGenres();
            read = true;
        }
        if (Menu.player == null) {
            Menu.media = new Media(this.getClass().getResource("/game/assets/sounds/latinfide.mp3").toExternalForm());
            Menu.player = new MediaPlayer(Menu.media);
            Menu.player.setVolume(0.8);
            Menu.player.setOnEndOfMedia(() -> {
                Menu.player.seek(Duration.ZERO);
            });
            Menu.player.play();
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

    private void initMusicGenres() {
        BufferedReader br = null;
        try {
            String line = "";
            String cvsSplitBy = ";";
            String csvFile = new File(".").getCanonicalPath() + "/src/game/data/musicGenres.csv";
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] musicGenre = line.split(cvsSplitBy);
                MusicGenre mg = new MusicGenre(musicGenre[0], musicGenre[1], musicGenre[2], musicGenre[3], musicGenre[4], musicGenre[5], musicGenre[6], Integer.valueOf(musicGenre[7]), Integer.valueOf(musicGenre[8]));
                PlayController.genreList.add(mg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initFile() {
        if (players == null) {
            players = new ArrayList<>();
        }
        File file = new File(fileName);
        Player player;

        if (file.exists()) {
            RegisterOnFile.openToRead(fileName);
            do {
                player = RegisterOnFile.readObjectFromFile();
                if (player != null) {
                    players.add(player);
                }
            } while (player != null);
            RegisterOnFile.closeAfterRead();
        }
    }

    private void closeFile() {
        RegisterOnFile.openToWrite(fileName);
        Collections.sort(players);
        for (Player player : players) {
            System.out.println(player);
            RegisterOnFile.writeObjectOnFile(player);
        }
        RegisterOnFile.closeAfterWrite();
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
        closeFile();
        System.exit(0);
    }

}
