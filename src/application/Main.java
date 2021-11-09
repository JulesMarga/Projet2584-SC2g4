/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author clementinebleuze
 */
public class Main extends Application{

    /**
     * @param args the command line arguments
     */

    @Override
    public void start(Stage stage) throws Exception {
        //System.out.println(getClass().getResource("FXMLDocument.fxml").toString());
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
    
        Scene scene = new Scene(root);
        boolean add = scene.getStylesheets().add("application/styles.css");

        stage.setScene(scene);
        stage.show();
    }
    
        public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

    
}
