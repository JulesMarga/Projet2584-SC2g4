/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import Projet2584_SC2g4.Joueur;
import Projet2584_SC2g4.Parametres;
import Projet2584_SC2g4.Partie2584;
import static application.GUIController.placerTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author clementinebleuze
 */
public class GUIController implements Initializable, Parametres {

    Partie2584 p = new Partie2584();

    //Elements graphiques
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
    @FXML
    private Label labelScore1;
    @FXML
    private Label labelScore2;
    @FXML
    private VBox vbox1;
    @FXML
    private VBox vbox2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        p.setGUI(true);
        p.setGUIController(this);

    }
    
    public Partie2584 getPartie(){
        return this.p;
    }

    public VBox getFond() {
        return this.fond;
    }

    public AnchorPane getFondGlobal() {
        return this.fondGlobal;
    }
    public void setScore1(String s){
        labelScore1.setText("score: " + s);
    }
    public void setScore2(String s){
        labelScore2.setText("score: " + s);
    }
    
    /**
     Evenement d??clench?? suite au choix du mode 1 joueur
     * @param me l'??v??nement clic
     */
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

        Button b = new Button("ok");
        placerBoutonValider(b, tfield);
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String pseudo = tfield.getText();
                p.setJ1(new Joueur(pseudo));
                p.getJ1().getGrille().setGuiGrille(grille1);
                labelPseudo1.setText(pseudo);
                vbox1.setVisible(true);
                grille1.setVisible(true);
                fond.getChildren().remove(label);
                fond.getChildren().remove(tfield);
                fond.getChildren().remove(b);
                p.getJ1().getGrille().nouvelleCase(true, true, p.getGUIController());
                p.getJ1().getGrille().nouvelleCase(false, true, p.getGUIController());

            }
        });
        afficher(b);

        p1Button.setDisable(true);
    }

    public void mouseClicked2(MouseEvent me) {
        p1Button.setDisable(true);

        Label label1 = new Label("pseudo joueur 1:");
        label1.setLayoutX(p1Button.getLayoutX());
        label1.setLayoutY(p2Button.getLayoutY() + 30);
        afficher(label1);

        TextField tfield1 = new TextField();
        placerTextField(tfield1, label1);
        tfield1.setPrefWidth(80);
        afficher(tfield1);

        Button b1 = new Button("ok");
        placerBoutonValider(b1, tfield1);
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String pseudo1 = tfield1.getText();
                p.setJ1(new Joueur(pseudo1));
                p.getJ1().getGrille().setGuiGrille(grille1);
                labelPseudo1.setText(pseudo1);
                vbox1.setVisible(true);
                grille1.setVisible(true);
                fond.getChildren().remove(label1);
                fond.getChildren().remove(tfield1);
                fond.getChildren().remove(b1);

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

        Button b2 = new Button("ok");
        placerBoutonValider(b2, tfield2);
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String pseudo2 = tfield2.getText();
                p.setJ2(new Joueur(pseudo2));
                p.getJ2().getGrille().setGuiGrille(grille2);
                labelPseudo2.setText(pseudo2);
                vbox2.setVisible(true);
                grille2.setVisible(true);
                fond.getChildren().remove(label2);
                fond.getChildren().remove(tfield2);
                fond.getChildren().remove(b2);

                p.getJ1().getGrille().nouvelleCase(true, true, p.getGUIController());
                p.getJ2().getGrille().nouvelleCase(true, true, p.getGUIController());
                //p.getJ1().getGrille().nouvelleCase(false,true,p.getGUIController());
                //p.getJ2().getGrille().nouvelleCase(false,true,p.getGUIController());

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

    public static void placerBoutonValider(Button b, TextField t) {
        b.setLayoutX(t.getLayoutX() + t.getWidth() + 10);
        b.setLayoutY(t.getLayoutY());
    }

    public void deplacerTuileRecursif(Pane p, GridPane g, int direction, int compteur, boolean fusion) {

        if (direction == DROITE) {
            Task task = new Task<Void>() { // on d??finit une t??che parall??le pour mettre ?? jour la vue
                double objectifx = g.getLayoutX() + 100 * (TAILLE - compteur - 1);
                double x = p.getLayoutX();
                double y = p.getLayoutY();

                @Override
                public Void call() throws Exception { // impl??mentation de la m??thode protected abstract V call() dans la classe Task
                    while (x != objectifx) { // si la tuile n'est pas ?? la place qu'on souhaite attendre en abscisse
                        x += 1;
                        // Platform.runLater est n??cessaire en JavaFX car la GUI ne peut ??tre modifi??e que par le Thread courant, contrairement ?? Swing o?? on peut utiliser un autre Thread pour ??a
                        Platform.runLater(new Runnable() { // classe anonyme
                            @Override
                            public void run() {
                                //javaFX operations should go here
                                //System.out.println("d??placement en cours");
                                p.relocate(x, y); // on d??place la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'?? atteindre l'objectif
                                p.setVisible(true);
                            }
                        }
                        );
                        Thread.sleep(1);
                    } // end while
                    if(fusion){
                        p.setVisible(false);
                    }
                    return null; // la m??thode call doit obligatoirement retourner un objet. Ici on n'a rien de particulier ?? retourner. Du coup, on utilise le type Void (avec un V majuscule) : c'est un type sp??cial en Java auquel on ne peut assigner que la valeur null
                } // end call

            };
            Thread th = new Thread(task); // on cr??e un contr??leur de Thread
            th.setDaemon(true); // le Thread s'ex??cutera en arri??re-plan (d??mon informatique)
            th.start(); // et on ex??cute le Thread pour mettre ?? jour la vue (d??placement continu de la tuile horizontalement)

        } else if (direction == GAUCHE) {
            Task task = new Task<Void>() { // on d??finit une t??che parall??le pour mettre ?? jour la vue
                double objectifx = g.getLayoutX() + compteur * 100;
                double x = p.getLayoutX();
                double y = p.getLayoutY();

                @Override
                public Void call() throws Exception { // impl??mentation de la m??thode protected abstract V call() dans la classe Task
                    while (x != objectifx) { // si la tuile n'est pas ?? la place qu'on souhaite attendre en abscisse
                        x -= 1;
                        // Platform.runLater est n??cessaire en JavaFX car la GUI ne peut ??tre modifi??e que par le Thread courant, contrairement ?? Swing o?? on peut utiliser un autre Thread pour ??a
                        Platform.runLater(new Runnable() { // classe anonyme
                            @Override
                            public void run() {
                                //javaFX operations should go here
                                //System.out.println("d??placement en cours");
                                p.relocate(x, y); // on d??place la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'?? atteindre l'objectif
                                p.setVisible(true);
                            }
                        }
                        );
                        Thread.sleep(1);
                    } // end while
                    if(fusion){
                        p.setVisible(false);
                    }
                    return null; // la m??thode call doit obligatoirement retourner un objet. Ici on n'a rien de particulier ?? retourner. Du coup, on utilise le type Void (avec un V majuscule) : c'est un type sp??cial en Java auquel on ne peut assigner que la valeur null
                } // end call

            };
            Thread th = new Thread(task); // on cr??e un contr??leur de Thread
            th.setDaemon(true); // le Thread s'ex??cutera en arri??re-plan (d??mon informatique)
            th.start(); // et on ex??cute le Thread pour mettre ?? jour la vue (d??placement continu de la tuile horizontalement)

        } else if (direction == HAUT) {
            Task task = new Task<Void>() { // on d??finit une t??che parall??le pour mettre ?? jour la vue
                double objectify = g.getLayoutY() + compteur * 100;
                double x = p.getLayoutX();
                double y = p.getLayoutY();

                @Override
                public Void call() throws Exception { // impl??mentation de la m??thode protected abstract V call() dans la classe Task
                    while (y != objectify) { // si la tuile n'est pas ?? la place qu'on souhaite attendre en abscisse
                        y -= 1;
                        // Platform.runLater est n??cessaire en JavaFX car la GUI ne peut ??tre modifi??e que par le Thread courant, contrairement ?? Swing o?? on peut utiliser un autre Thread pour ??a
                        Platform.runLater(new Runnable() { // classe anonyme
                            @Override
                            public void run() {
                                //javaFX operations should go here
                                //System.out.println("d??placement en cours");
                                p.relocate(x, y); // on d??place la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'?? atteindre l'objectif
                                p.setVisible(true);
                            }
                        }
                        );
                        Thread.sleep(1);
                    } // end while
                    if(fusion){
                        p.setVisible(false);
                    }
                    return null; // la m??thode call doit obligatoirement retourner un objet. Ici on n'a rien de particulier ?? retourner. Du coup, on utilise le type Void (avec un V majuscule) : c'est un type sp??cial en Java auquel on ne peut assigner que la valeur null
                } // end call

            };
            Thread th = new Thread(task); // on cr??e un contr??leur de Thread
            th.setDaemon(true); // le Thread s'ex??cutera en arri??re-plan (d??mon informatique)
            th.start(); // et on ex??cute le Thread pour mettre ?? jour la vue (d??placement continu de la tuile horizontalement)

        } else {
            Task task = new Task<Void>() { // on d??finit une t??che parall??le pour mettre ?? jour la vue
                double objectify = g.getLayoutX() + (TAILLE - compteur - 1) * 100;
                double x = p.getLayoutX();
                double y = p.getLayoutY();

                @Override
                public Void call() throws Exception { // impl??mentation de la m??thode protected abstract V call() dans la classe Task
                    while (y != objectify) { // si la tuile n'est pas ?? la place qu'on souhaite attendre en abscisse
                        y += 1;
                        // Platform.runLater est n??cessaire en JavaFX car la GUI ne peut ??tre modifi??e que par le Thread courant, contrairement ?? Swing o?? on peut utiliser un autre Thread pour ??a
                        Platform.runLater(new Runnable() { // classe anonyme
                            @Override
                            public void run() {
                                //javaFX operations should go here
                                //System.out.println("d??placement en cours");
                                p.relocate(x, y); // on d??place la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'?? atteindre l'objectif
                                p.setVisible(true);
                            }
                        }
                        );
                        Thread.sleep(1);
                    } // end while
                    if(fusion){
                        p.setVisible(false);
                    }
                    return null; // la m??thode call doit obligatoirement retourner un objet. Ici on n'a rien de particulier ?? retourner. Du coup, on utilise le type Void (avec un V majuscule) : c'est un type sp??cial en Java auquel on ne peut assigner que la valeur null
                } // end call

            };
            Thread th = new Thread(task); // on cr??e un contr??leur de Thread
            th.setDaemon(true); // le Thread s'ex??cutera en arri??re-plan (d??mon informatique)
            th.start(); // et on ex??cute le Thread pour mettre ?? jour la vue (d??placement continu de la tuile horizontalement)

        }
    }

    @FXML
    public void keyPressed(KeyEvent ke) {
        if (p.getJ1() != null) {
            String touche = ke.getText();

            if (touche.compareTo("q") == 0) { //d??placement ?? gauche

            } else if (touche.compareTo("d") == 0) { // d??placement ?? droite
                p.getJ1().getGrille().lanceurDeplacerCases(DROITE, this);
            }
        }

    }
    
}
