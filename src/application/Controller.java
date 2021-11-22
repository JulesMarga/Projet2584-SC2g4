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
    private Button buttonStart;
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
        
        //création d'une nouvelle stage pour le jeu
        Stage stageJeu = new Stage();
        stageJeu.setTitle("Jeu");
        
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Pane root=myLoader.load();

        FXMLDocumentController controller = (FXMLDocumentController) myLoader.getController();
        
        Partie2584 p = new Partie2584();
        p.setJ1(new Joueur("testpeudo"));
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
        System.out.println("1j");
        
        Label label = new Label("pseudo:");
        TextField tfield = new TextField();
        fond.getChildren().add(label); fond.getChildren().add(tfield);
        
        tfield.setVisible(true);
        label.setLayoutX(30.0);
        label.setLayoutY(100.0);
        label.setVisible(true);
    }
    
     public void mouseClicked2(MouseEvent me){
        p1Button.setDisable(true);
        System.out.println("2j");
        
        Label label1 = new Label("pseudo joueur 1:");
        TextField tfield1 = new TextField();
        
        Label label2=new Label("pseudo joueur 2:");
        TextField tfield2=new TextField();
        
        tfield1.setVisible(true);
        label1.setVisible(true);
        tfield2.setVisible(true);
        label2.setVisible(true);
    }
}
