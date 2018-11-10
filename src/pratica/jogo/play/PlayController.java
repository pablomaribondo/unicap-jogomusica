package pratica.jogo.play;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class PlayController implements Initializable {

    @FXML
    private ImageView img_map;
    @FXML
    private ImageView img_style;
    @FXML
    private ImageView img_character;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
        path.getElements().add(new LineTo(event.getX() - 365, event.getY() - 185));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(2000));
        pathTransition.setPath(path);
        pathTransition.setNode(img_character);
        pathTransition.play();
        img_character.setX(event.getX() - 365);
        img_character.setY(event.getY() - 185);
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

}
