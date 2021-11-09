/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import Projet2584_SC2g4.Partie2584;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author clementinebleuze
 */
public class Main extends Application {

    /**
     * @param args the command line arguments
     */
    private Partie2584 p;

    public void setPartie(Partie2584 p) {
        this.p = p;
    }

    @Override
    public void start(Stage stage) throws Exception {
        //System.out.println(getClass().getResource("FXMLDocument.fxml").toString());
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
