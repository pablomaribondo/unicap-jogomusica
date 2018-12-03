package game.instructions;

import game.menu.Menu;
import game.play.PlayController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class InstructionsController implements Initializable {

    @FXML
    private Button btn_back;
    @FXML
    private Slider volumeSlider;
    @FXML
    private ImageView img_volume;
    @FXML
    private Label instructionsDescription;

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
        instructionsDescription.setWrapText(true);
        String instructions = "\tO jogo é formado por questões relativas à estilos musicais. "
                + "São 6 questões, cada uma com um áudio do estilo musical, uma foto referente "
                + "a este e alguma dica sobre a resposta correta (instrumentos utilizados, "
                + "dança relacionada, entre outras). As alternativas aparecerão em um mapa mundi, "
                + "referenciando o principal local de manifestação daquele estilo musical e o nome "
                + "deste. O usuário deverá mover seu personagem para o local/estilo musical em que "
                + "acredita ser a resposta correta através do clique. Assim que a assertiva correta "
                + "for escolhida, aparecerá um texto com a história do estilo musical e sua influência "
                + "sociológica, outrossim o personagem receberá uma modificação gráfica, com acessórios "
                + "que representem aquele local ou estilo musical. \n\tObtém-se pontuação ao escolher a "
                + "assertiva correta, caso essa seja escolhida na primeira tentativa, o jogador receberá "
                + "a maior pontuação. Caso contrário, receberá uma quantidade menor por cada escolha errada. "
                + "O objetivo do jogo é obter a maior pontuação possível, enquanto descobre a incrível "
                + "variedade musical dispostas em várias culturas de nossa sociedade.\n\tPor fim, a pontuação "
                + "total do usuário será gravada em uma tabela de pontuação geral, que conterá as 10 "
                + "maiores pontuações já registradas pelo jogo. O jogo, se valerá de recursos sonoros "
                + "e gráficos, assim como o uso de teclado e mouse.";
        PlayController.animateText(instructionsDescription, instructions);

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
