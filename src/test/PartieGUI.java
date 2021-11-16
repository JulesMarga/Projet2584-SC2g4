package test;

import Projet2584_SC2g4.Partie2584;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PartieGUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);
        boolean add = scene.getStylesheets().add("application/styles.css");

        stage.setScene(scene);
        stage.show();

        System.out.println("Lancement du mode graphique du jeu 2584");

    }

    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

}
