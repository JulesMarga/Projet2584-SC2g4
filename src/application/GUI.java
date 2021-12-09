/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import Projet2584_SC2g4.Parametres;
import Projet2584_SC2g4.Partie2584;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author clementinebleuze
 */
public class GUI extends Application implements Parametres {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("GUI.fxml"));
        Parent root = myLoader.load();

        Scene scene = new Scene(root);
        boolean add = scene.getStylesheets().add("application/styles.css");

        stage.setScene(scene);
        stage.show();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent t) {
                System.out.println("Maintenant ça marche !");
                GUIController controller = myLoader.getController();
                Partie2584 p = controller.getPartie();

                if (p.getJ1() != null) {
                    String touche = t.getText();

                    if (touche.compareTo("q") == 0) { //déplacement à gauche
                        p.getJ1().getGrille().lanceurDeplacerCases(GAUCHE, controller);
                    } else if (touche.compareTo("d") == 0) { // déplacement à droite
                        p.getJ1().getGrille().lanceurDeplacerCases(DROITE, controller);
                    } else if (touche.compareTo("s") == 0) {
                        p.getJ1().getGrille().lanceurDeplacerCases(BAS, controller);
                    } else if (touche.compareTo("z") == 0) {
                        p.getJ1().getGrille().lanceurDeplacerCases(HAUT, controller);
                    }
                }
            }

        });
        System.out.println("Lancement du mode graphique du jeu 2584");
    }

}
