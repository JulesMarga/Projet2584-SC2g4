package application; 

import Projet2584_SC2g4.Partie2584;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class FXMLDocumentController implements Initializable{
    
    private Partie2584 partie;
    
    public Partie2584 getPartie(){
        return this.partie;
    }
    public void setPartie(Partie2584 p){
        this.partie=p;
    }
    
    @FXML
    private Label score; // value will be injected by the FXMLLoader
    @FXML
    private GridPane grille;
    @FXML
    private Pane fond; // panneau recouvrant toute la fenêtre
    
    private final Pane pane = new Pane(); // panneau utilisé pour dessiner une tuile "2"
    private final Label c = new Label("2");
    private int x = 24, y = 191;
    private int objectifx = 24, objectify = 191;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("le contrôleur initialise la vue");
        // utilisation de styles pour la grille et la tuile (voir styles.css)
        pane.getStyleClass().add("pane"); 
        c.getStyleClass().add("tuile");
        grille.getStyleClass().add("gridpane");
        GridPane.setHalignment(c, HPos.CENTER);
        fond.getChildren().add(pane);
        pane.getChildren().add(c);

        // on place la tuile en précisant les coordonnées (x,y) du coin supérieur gauche
        pane.setLayoutX(x);
        pane.setLayoutY(y);
        pane.setVisible(true);
        c.setVisible(true);
        
        //Voir comment récupérer des infos depuis la fenêtre précédente
        if(partie==null){
            System.out.println("partie vide");
        }
        else{
            System.out.println("partie créée");
        }
    }
    
    public void keyPressed(KeyEvent ke) {
        System.out.println("Vous avez appuyé sur une touche avec succès, félicitations");
    }
    
}