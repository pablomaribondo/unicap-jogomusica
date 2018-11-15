package pratica.jogo.play;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
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
    private ImageView img_style;
    @FXML
    private ImageView img_character;
    @FXML
    private Label txt;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label question;
    @FXML
    private Button btn_playmusic;
    @FXML
    private Button btn_showpicture;

    private boolean tipShown;
    
    private Media media;
    
    private MediaPlayer player;
    @FXML
    private Button btn_stopmusic;
    @FXML
    private Button btn_menu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Font.loadFont(this.getClass().getResource("/pratica/jogo/fonts/pixelart.ttf").toExternalForm(), 12);
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
        txt.setWrapText(true);
        animateText(txt, str);
        tipShown = false;

        
    }

    public void roundImg() {
        // set a clip to apply rounded border to the original image.
        Rectangle clip = new Rectangle(img_style.getFitWidth(), img_style.getFitHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        img_style.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = img_style.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        img_style.setClip(null);

        // apply a shadow effect.
        img_style.setEffect(new DropShadow(20, Color.BLACK));

        // store the rounded image in the imageView.
        img_style.setImage(image);
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
    private void move(MouseEvent event) throws InterruptedException {

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
                    move(e);
                } catch (InterruptedException exception) {
                    System.err.println(exception.getMessage());
                }
            });
        }, 2000);
    }

    @FXML
    private void showpicture(MouseEvent event) {
        if (!tipShown) {
            img_style.setImage(new Image("/pratica/jogo/images/frevo.png"));
            roundImg();
            tipShown = true;
        }
    }

    @FXML
    private void playMusic(MouseEvent event) {
        media = new Media(this.getClass().getResource("/pratica/jogo/sounds/frevo.mp3").toExternalForm());
        player = new MediaPlayer(media);
        player.play();
    }

    @FXML
    private void stopMusic(MouseEvent event) {
        player.stop();
    }

    @FXML
    private void menu(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/pratica/jogo/menu/Menu.fxml"));

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Menu");

        stage.setScene(scene);
        stage.show();
    }

}
