/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pratica.jogo.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author pablo
 */
public class MenuController implements Initializable {

    @FXML
    private Button btn_play;
    @FXML
    private Button btn_instructions;
    @FXML
    private Button btn_pontuation;
    @FXML
    private Button btn_exit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void play(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/pratica/jogo/play/Play.fxml"));

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Play");

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void instructions(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/pratica/jogo/instructions/Instructions.fxml"));

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Instructions");

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void pontuation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/pratica/jogo/pontuation/Pontuation.fxml"));

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
