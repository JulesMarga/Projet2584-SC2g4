/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import Projet2584_SC2g4.Joueur;
import Projet2584_SC2g4.Partie2584;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author clementinebleuze
 */
public class Controller implements Initializable{
    
    Stage prevStage;

    @FXML
    private Pane fond;
    @FXML
    private Button startButton;
    @FXML
    private Button p1Button;
    @FXML
    private Button p2Button;
    @FXML
    private Label labelHello;
    @FXML
    private TextField pseudo1;
    
    public TextField getPseudo1(){
        return this.pseudo1;
    }
    
    @Override
    public void initialize(URL url,ResourceBundle rb){
        System.out.println("Le contrôleur initialise la vue");
    }
    
    public void setPrevStage(Stage s){
        this.prevStage=s;
    }
    
    public void mouseClicked(MouseEvent me) throws IOException{
        System.out.println("cliqué!");
        startButton.setDisable(true);
        
        //création d'une nouvelle stage pour le jeu
        Stage stageJeu = new Stage();
        stageJeu.setTitle("Jeu");
        
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Pane root=myLoader.load();
        
        //On récupère le controller de la nouvelle scene
        FXMLDocumentController controller = (FXMLDocumentController) myLoader.getController();
        
        Partie2584 p = new Partie2584();
        p.setJ1(new Joueur("testpseudo"));
        controller.setPartie(p); //Pourquoi ça ne marche pas ??
        
        
        System.out.println(controller.getPartie().getJ1().getPseudo()); //ici ok: donc il y a bien création de p

        
        Scene scene = new Scene(root);
        stageJeu.setScene(scene);

        this.prevStage.close();

        stageJeu.show();
    }
    
    /** 
     * @param me     
     */
    public void mouseClicked1(MouseEvent me){
        p2Button.setDisable(true);

        Label label = new Label("pseudo:");
        label.setLayoutX(p1Button.getLayoutX());
        label.setLayoutY(p1Button.getLayoutY()+30);
        fond.getChildren().add(label);label.setVisible(true);
        
        TextField tfield = new TextField();
        tfield.setLayoutX(p1Button.getLayoutX()+50);
        tfield.setLayoutY(label.getLayoutY());
        fond.getChildren().add(tfield);tfield.setVisible(true);
 
        p1Button.setDisable(true);
    }
    
     public void mouseClicked2(MouseEvent me){
        p1Button.setDisable(true);
        System.out.println("2j");
        
        Label label1 = new Label("pseudo joueur 1:");
        label1.setLayoutX(p1Button.getLayoutX());
        label1.setLayoutY(p2Button.getLayoutY()+30);
        afficher(label1);
        
        TextField tfield1 = new TextField();
        placerTextField(tfield1,label1);
        afficher(tfield1);
        
        Label label2 = new Label("pseudo joueur 2:");
        label2.setLayoutX(p1Button.getLayoutX());
        label2.setLayoutY(p2Button.getLayoutY()+80);
        afficher(label2);
        
        TextField tfield2 = new TextField();
        placerTextField(tfield2,label2);
        afficher(tfield2);

        p2Button.setDisable(true);
    }
     
     public static void placerTextField(TextField t,Label l){
         t.setLayoutX(l.getLayoutX()+l.getText().length()*7+30);
         t.setLayoutY(l.getLayoutY());
     }
     public void afficher(Node n){
         fond.getChildren().add(n);
         n.setVisible(true);
     }
}
