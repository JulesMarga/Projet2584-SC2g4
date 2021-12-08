/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import Projet2584_SC2g4.Joueur;
import Projet2584_SC2g4.Partie2584;
import static application.Controller.placerTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author clementinebleuze
 */
public class GUIController implements Initializable {
    
    Partie2584 p = new Partie2584();
    @FXML
    private AnchorPane fondGlobal;
    @FXML
    private GridPane grille1;
    @FXML
    private GridPane grille2;
    @FXML
    private Button p1Button;
    @FXML
    private Button p2Button;
    @FXML
    private VBox fond;
    @FXML
    private Label labelPseudo1;
    @FXML
    private Label labelPseudo2;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       p.setGUI(true);
       p.setGUIController(this);
       
    }
    
    public VBox getFond(){
        return this.fond;
    }
    public AnchorPane getFondGlobal(){
        return this.fondGlobal;
    }

    public void mouseClicked1(MouseEvent me) {
        p2Button.setDisable(true);

        Label label = new Label("pseudo:");
        label.setLayoutX(p1Button.getLayoutX());
        label.setLayoutY(p1Button.getLayoutY() + 30);
        fond.getChildren().add(label);
        label.setVisible(true);

        TextField tfield = new TextField();
        tfield.setLayoutX(p1Button.getLayoutX() + 50);
        tfield.setPrefWidth(80);
        tfield.setLayoutY(label.getLayoutY());
        fond.getChildren().add(tfield);
        tfield.setVisible(true);
        
        Button b=new Button("ok");
        placerBoutonValider(b,tfield);
        b.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent e){
               String pseudo = tfield.getText();
               p.setJ1(new Joueur(pseudo));
               labelPseudo1.setText(pseudo);
               label.setVisible(false);
               tfield.setVisible(false);
               b.setVisible(false);
               p.deroulement();
           }
        });
        afficher(b);

        p1Button.setDisable(true);
    }

    public void mouseClicked2(MouseEvent me) {
        p1Button.setDisable(true);
        System.out.println("2j");

        Label label1 = new Label("pseudo joueur 1:");
        label1.setLayoutX(p1Button.getLayoutX());
        label1.setLayoutY(p2Button.getLayoutY() + 30);
        afficher(label1);

        TextField tfield1 = new TextField();
        placerTextField(tfield1, label1);
        tfield1.setPrefWidth(80);
        afficher(tfield1);
        
        Button b1=new Button("ok");
        placerBoutonValider(b1,tfield1);
        b1.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent e){
               String pseudo1 = tfield1.getText();
               p.setJ1(new Joueur(pseudo1));
               labelPseudo1.setText(pseudo1);
               label1.setVisible(false);
               tfield1.setVisible(false);
               b1.setVisible(false);
               
           }
        });
        afficher(b1);

        Label label2 = new Label("pseudo joueur 2:");
        label2.setLayoutX(p1Button.getLayoutX());
        label2.setLayoutY(p2Button.getLayoutY() + 80);
        afficher(label2);

        TextField tfield2 = new TextField();
        placerTextField(tfield2, label2);
        tfield2.setPrefWidth(80);
        afficher(tfield2);
        
        Button b2=new Button("ok");
        placerBoutonValider(b2,tfield2);
        b2.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent e){
               String pseudo2 = tfield2.getText();
               p.setJ2(new Joueur(pseudo2));
               labelPseudo2.setText(pseudo2);
               label2.setVisible(false);
               tfield2.setVisible(false);
               b2.setVisible(false);
               
           }
        });
        afficher(b2);

        p2Button.setDisable(true);
    }

    public void afficher(Node n) {
        fond.getChildren().add(n);
        n.setVisible(true);
    }

    public static void placerTextField(TextField t, Label l) {
        t.setLayoutX(l.getLayoutX() + l.getText().length() * 7 + 30);
        t.setLayoutY(l.getLayoutY());
    }
    
    public static void placerBoutonValider(Button b, TextField t){
        b.setLayoutX(t.getLayoutX()+t.getWidth()+10);
        b.setLayoutY(t.getLayoutY());
    }

}
