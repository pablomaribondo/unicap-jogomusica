package game.play;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.Transition;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PlayController implements Initializable {

    @FXML
    private ImageView img_map;
    @FXML
    private ImageView img_hint;
    @FXML
    private ImageView img_character;
    @FXML
    private ImageView img_volume;
    @FXML
    private Label description;
    @FXML
    private Label question;
    @FXML
    private Button btn_playMusic;
    @FXML
    private Button btn_showPicture;
    @FXML
    private Button btn_stopMusic;
    @FXML
    private Button btn_nextQuestion;
    @FXML
    private Button btn_menu;
    @FXML
    private Slider volumeSlider;
    @FXML
    private ProgressBar progressBar;

    private boolean hintShown;
    private Media media;
    private MediaPlayer player;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Font.loadFont(this.getClass().getResource("/game/assets/fonts/VCR_OSD_MONO_1.001.ttf").toExternalForm(), 12);
        roundImg();
        String str = "Mussum Ipsum, cacilds vidis litro abertis. "
                + "Leite de capivaris, leite de mula manquis sem cabeça. "
                + "Casamentiss faiz malandris se pirulitá. "
                + "A ordem dos tratores não altera o pão duris. "
                + "Não sou faixa preta cumpadi, sou preto inteiris, inteiris"
                + "Leite de capivaris, leite de mula manquis sem cabeça. "
                + "Casamentiss faiz malandris se pirulitá. "
                + "A ordem dos tratores não altera o pão duris. "
                + "Não sou faixa preta cumpadi, sou preto inteiris, inteiris"
                + "Leite de capivaris, leite de mula manquis sem cabeça. "
                + "Casamentiss faiz malandris se pirulitá. "
                + "A ordem dos tratores não altera o pão duris. "
                + "Não sou faixa preta cumpadi, sou preto inteiris, inteiris"
                + "Leite de capivaris, leite de mula manquis sem cabeça. "
                + "Casamentiss faiz malandris se pirulitá. "
                + "A ordem dos tratores não altera o pão duris. "
                + "Não sou faixa preta cumpadi, sou preto inteiris, inteiris"
                + "Leite de capivaris, leite de mula manquis sem cabeça. "
                + "Casamentiss faiz malandris se pirulitá. "
                + "A ordem dos tratores não altera o pão duris. "
                + "Não sou faixa preta cumpadi, sou preto inteiris, inteiris"
                + "Leite de capivaris, leite de mula manquis sem cabeça. "
                + "Casamentiss faiz malandris se pirulitá. "
                + "A ordem dos tratores não altera o pão duris. "
                + "Não sou faixa preta cumpadi, sou preto inteiris, inteiris";
        description.setWrapText(true);
        animateText(description, str);
        hintShown = false;
        btn_stopMusic.setOnMouseClicked(null);
        media = new Media(this.getClass().getResource("/game/assets/sounds/frevo.mp3").toExternalForm());
        player = new MediaPlayer(media);
        player.setVolume(0.8);
        volumeSlider.setValue(player.getVolume()*100);
        volumeSlider.valueProperty().addListener((Observable observable) -> {
            player.setVolume(volumeSlider.getValue()/100);
            if (player.getVolume() == 0) {
                img_volume.setImage(new Image("/game/assets/images/soundOffWhite.png"));
            } else {
                img_volume.setImage(new Image("/game/assets/images/soundOnWhite.png"));
            }
        });
        btn_nextQuestion.setVisible(false);

    }

    public void roundImg() {
        // set a clip to apply rounded border to the original image.
        Rectangle clip = new Rectangle(img_hint.getFitWidth(), img_hint.getFitHeight());
        clip.setArcWidth(40);
        clip.setArcHeight(40);
        img_hint.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = img_hint.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        img_hint.setClip(null);

        // store the rounded image in the imageView.
        img_hint.setImage(image);
    }

    public void animateText(Label text, String string) {
        String content = string;
        final Animation animation;
        animation = new Transition() {
            {
                setCycleDuration(Duration.millis(3000));
            }

            @Override
            protected void interpolate(double frac) {
                final int length = content.length();
                final int n = Math.round(length * (float) frac);
                text.setText(content.substring(0, n));
            }
        };
        animation.play();
    }

    public static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (InterruptedException exception) {
                System.err.println(exception.getMessage());
            }
        }).start();
    }

    @FXML
    private void moveCharacter(MouseEvent event) throws InterruptedException {
        Path path = new Path();
        path.getElements().add(new MoveTo(img_character.getX(), img_character.getY()));
        path.getElements().add(new LineTo(event.getX() - 200, event.getY() - 190));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(2000));
        pathTransition.setPath(path);
        pathTransition.setNode(img_character);
        pathTransition.play();
        img_character.setX(event.getX() - 200);
        img_character.setY(event.getY() - 190);
        img_map.setOnMouseClicked(null);
        setTimeout(() -> {
            img_map.setOnMouseClicked((MouseEvent e) -> {
                try {
                    moveCharacter(e);
                } catch (InterruptedException exception) {
                    System.err.println(exception.getMessage());
                }
            });
        }, 2000);
    }

    @FXML
    private void showPicture(MouseEvent event) {
        if (!hintShown) {
            img_hint.setImage(new Image("/game/assets/images/frevo.png"));
            roundImg();
            hintShown = true;
        }
    }

    @FXML
    private void playMusic(MouseEvent event) {
        media = new Media(this.getClass().getResource("/game/assets/sounds/frevo.mp3").toExternalForm());
        player = new MediaPlayer(media);
        player.play();

        btn_playMusic.setOnMouseClicked(null);
        btn_stopMusic.setOnMouseClicked((MouseEvent e) -> {
            stopMusic(e);
        });
    }

    @FXML
    private void stopMusic(MouseEvent event) {
        player.stop();

        btn_playMusic.setOnMouseClicked((MouseEvent e) -> {
            playMusic(e);
        });
        btn_stopMusic.setOnMouseClicked(null);
    }

    @FXML
    private void nextQuestion(MouseEvent event) {
        System.out.println("olar");
    }

    @FXML
    private void menu(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/game/menu/Menu.fxml"));

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Menu");

        player.stop();

        stage.setScene(scene);
        stage.show();
    }

}
