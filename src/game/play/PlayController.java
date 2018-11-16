package game.play;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private ImageView img_hint;
    @FXML
    private ImageView img_character;
    @FXML
    private ImageView img_volume;
    @FXML
    private ImageView img_spot1;
    @FXML
    private ImageView img_spot2;
    @FXML
    private ImageView img_spot3;
    @FXML
    private ImageView img_spot4;
    @FXML
    private Label description;
    @FXML
    private Label lbl_spot1;
    @FXML
    private Label lbl_spot2;
    @FXML
    private Label lbl_spot3;
    @FXML
    private Label lbl_spot4;
    @FXML
    private Label lbl_questionNumber;
    @FXML
    private Label lbl_points;
    @FXML
    private Button btn_playMusic;
    @FXML
    private Button btn_stopMusic;
    @FXML
    private Button btn_confirmQuestion;
    @FXML
    private Button btn_nextQuestion;
    @FXML
    private Slider volumeSlider;
    @FXML
    private ProgressBar progressBar;

    private int questionNumber;
    private int points;
    private boolean hintShown;
    private boolean arrived;
    private Media media;
    private MediaPlayer player;
    private ArrayList<MusicGenre> genreList = new ArrayList();
    private ArrayList<ImageView> alternatives = new ArrayList();
    private ImageView characterTarget;
    private ImageView characterSource;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Font.loadFont(this.getClass().getResource("/game/assets/fonts/VCR_OSD_MONO_1.001.ttf").toExternalForm(), 12);

        description.setWrapText(true);
        hintShown = false;
        arrived = false;
        btn_stopMusic.setOnMouseClicked(null);
        btn_nextQuestion.setVisible(false);
        btn_confirmQuestion.setVisible(false);

        roundImg();

        questionNumber = 1;
        points = 0;
        lbl_questionNumber.setText("Questão: " + String.format("%02d", questionNumber));
        lbl_points.setText("Pontos: " + String.format("%05d", points));

        // tem que fazer uma função pra carregar esses dados do arquivo
        genreList.add(new MusicGenre("nome1", "desc1", "imgurl1", "soundurl", "spotname1", 321, 532));
        genreList.add(new MusicGenre("nome2", "desc2", "imgurl2", "soundur2", "spotname2", 507, 564));
        genreList.add(new MusicGenre("nome3", "desc3", "imgurl3", "soundur3", "spotname3", 662, 403));
        genreList.add(new MusicGenre("nome4", "desc4", "imgurl4", "soundur4", "spotname4", 801, 508));

        // função pra carregar as alternativas
        alternatives.add(img_spot1);
        alternatives.add(img_spot2);
        alternatives.add(img_spot3);
        alternatives.add(img_spot4);

        // Tem que ir pra outra função
        img_character.setX(genreList.get(0).getSpotX());
        img_character.setY(genreList.get(0).getSpotY());
        img_spot1.setX(genreList.get(0).getSpotX());
        img_spot1.setY(genreList.get(0).getSpotY());
        lbl_spot1.setTranslateX(img_spot1.getX() - 40);
        lbl_spot1.setTranslateY(img_spot1.getY() - 16);
        lbl_spot1.setText(genreList.get(0).getSpotName());
        img_spot2.setX(genreList.get(1).getSpotX());
        img_spot2.setY(genreList.get(1).getSpotY());
        lbl_spot2.setTranslateX(img_spot2.getX() - 40);
        lbl_spot2.setTranslateY(img_spot2.getY() - 16);
        lbl_spot2.setText(genreList.get(1).getSpotName());
        img_spot3.setX(genreList.get(2).getSpotX());
        img_spot3.setY(genreList.get(2).getSpotY());
        lbl_spot3.setTranslateX(img_spot3.getX() - 40);
        lbl_spot3.setTranslateY(img_spot3.getY() - 16);
        lbl_spot3.setText(genreList.get(2).getSpotName());
        img_spot4.setX(genreList.get(3).getSpotX());
        img_spot4.setY(genreList.get(3).getSpotY());
        lbl_spot4.setTranslateX(img_spot4.getX() - 40);
        lbl_spot4.setTranslateY(img_spot4.getY() - 16);
        lbl_spot4.setText(genreList.get(3).getSpotName());

        // Tem que rolar quando o brother acertar a questão
        String str = "Mussum Ipsum, cacilds vidis litro abertis.";
        animateText(description, str);

        media = new Media(this.getClass().getResource("/game/assets/sounds/frevo.mp3").toExternalForm());
        player = new MediaPlayer(media);
        player.setVolume(0.8);

        volumeSlider.setValue(player.getVolume() * 100);
        volumeSlider.valueProperty().addListener((Observable observable) -> {
            player.setVolume(volumeSlider.getValue() / 100);
            if (player.getVolume() == 0) {
                img_volume.setImage(new Image("/game/assets/images/soundOffWhite.png"));
            } else {
                img_volume.setImage(new Image("/game/assets/images/soundOnWhite.png"));
            }
        });

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
        arrived = false;
        characterSource = characterTarget;
        characterTarget = (ImageView) event.getTarget();
        Path path = new Path();
        path.getElements().add(new MoveTo(img_character.getX(), img_character.getY()));
        path.getElements().add(new LineTo(event.getX(), event.getY()));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(2000));
        pathTransition.setPath(path);
        pathTransition.setNode(img_character);
        pathTransition.play();
        img_character.setX(event.getX());
        img_character.setY(event.getY());
        alternatives.forEach((alternative) -> {
            alternative.setOnMouseClicked(null);
        });
        alternatives.remove(characterTarget);
        setTimeout(() -> {
            alternatives.forEach((alternative) -> {
                alternative.setOnMouseClicked((MouseEvent e) -> {
                    try {
                        moveCharacter(e);
                    } catch (InterruptedException exception) {
                        System.err.println(exception.getMessage());
                    }
                });
            });
            alternatives.add(characterTarget);
            arrived = true;
        }, 2000);
        if (characterSource != null) {
            characterSource.setImage(new Image("/game/assets/images/mapPin.png"));
        }
        characterTarget.setImage(new Image("/game/assets/images/mapPinChosen.png"));
        btn_confirmQuestion.setVisible(true);
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
    private void confirmQuestion(MouseEvent event) {
        if (arrived) {
            characterTarget.setImage(new Image("/game/assets/images/mapPinDisabled.png"));
            characterTarget.setOnMouseClicked(null);
            alternatives.remove(characterTarget);
            characterTarget = null;
        }
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
