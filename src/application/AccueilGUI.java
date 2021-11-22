package application;

import Projet2584_SC2g4.Partie2584;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AccueilGUI extends Application {

    @Override
    public void start(Stage stageAccueil) throws Exception {
        
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
        Parent root = myLoader.load();

        Controller controller = (Controller) myLoader.getController();
        
        controller.setPrevStage(stageAccueil);
        
        Scene scene = new Scene(root);
        boolean add = scene.getStylesheets().add("application/styles.css");

        stageAccueil.setScene(scene);
        stageAccueil.show();

        System.out.println("Lancement du mode graphique du jeu 2584");

    }

    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

}