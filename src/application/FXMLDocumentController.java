package application;

import Projet2584_SC2g4.Joueur;
import Projet2584_SC2g4.Partie2584;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class FXMLDocumentController implements Initializable {

    private Partie2584 partie;

    public Partie2584 getPartie() {
        return this.partie;
    }

    public void setPartie(Partie2584 p) {
        this.partie = p;
    }

    @FXML
    private Label scoreLabel; // value will be injected by the FXMLLoader
    @FXML
    private Label pseudoLabel;
    @FXML
    private GridPane grille;
    @FXML
    private Pane fond; // panneau recouvrant toute la fenêtre

    private final Pane pane = new Pane(); // panneau utilisé pour dessiner une tuile "1"
    private final Label c = new Label("1");
    private int x = 24, y = 191;
    private int objectifx = 24, objectify = 191;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //initialisation de la partie
        partie = new Partie2584();
        partie.setGUI(true);
        partie.setController(this);
        partie.setJ1(new Joueur("Clémentine"));
        pseudoLabel.setText(partie.getJ1().getPseudo());
        
        
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
        if (partie == null) {
            System.out.println("partie vide");
        } else {
            System.out.println("partie créée");
        }
        
        partie.creerNouvelleCaseGraphique();
    }

    @FXML
    public void keyPressed(KeyEvent ke) {
        String touche = ke.getText();
        if (touche.compareTo("q") == 0) { // utilisateur appuie sur "q" pour envoyer la tuile vers la gauche
            if (objectifx > 24) { // possible uniquement si on est pas dans la colonne la plus à gauche
                objectifx -= (int) 397 / 4; // on définit la position que devra atteindre la tuile en abscisse (modèle). Le thread se chargera de mettre la vue à jour
                scoreLabel.setText(Integer.toString(Integer.parseInt(scoreLabel.getText()) + 1)); // mise à jour du compteur de mouvement
            }
        } else if (touche.compareTo("d") == 0) { // utilisateur appuie sur "d" pour envoyer la tuile vers la droite
            if (objectifx < (int) 445 - 2 * 397 / 4 - 24) { // possible uniquement si on est pas dans la colonne la plus à droite (taille de la fenêtre - 2*taille d'une case - taille entre la grille et le bord de la fenêtre)
                objectifx += (int) 397 / 4;
                scoreLabel.setText(Integer.toString(Integer.parseInt(scoreLabel.getText()) + 1));
            }
        }
        System.out.println("objectifx=" + objectifx);
        Task task = new Task<Void>() { // on définit une tâche parallèle pour mettre à jour la vue
            @Override
            public Void call() throws Exception { // implémentation de la méthode protected abstract V call() dans la classe Task
                while (x != objectifx) { // si la tuile n'est pas à la place qu'on souhaite attendre en abscisse
                    if (x < objectifx) {
                        x += 1; // si on va vers la droite, on modifie la position de la tuile pixel par pixel vers la droite
                    } else {
                        x -= 1; // si on va vers la gauche, idem en décrémentant la valeur de x
                    }
                    // Platform.runLater est nécessaire en JavaFX car la GUI ne peut être modifiée que par le Thread courant, contrairement à Swing où on peut utiliser un autre Thread pour ça
                    Platform.runLater(new Runnable() { // classe anonyme
                        @Override
                        public void run() {
                            //javaFX operations should go here
                            //System.out.println("déplacement en cours");
                            pane.relocate(x, y); // on déplace la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'à atteindre l'objectif
                            pane.setVisible(true);
                        }
                    }
                    );
                    Thread.sleep(2);
                } // end while
                return null; // la méthode call doit obligatoirement retourner un objet. Ici on n'a rien de particulier à retourner. Du coup, on utilise le type Void (avec un V majuscule) : c'est un type spécial en Java auquel on ne peut assigner que la valeur null
            } // end call

        };
        Thread th = new Thread(task); // on crée un contrôleur de Thread
        th.setDaemon(true); // le Thread s'exécutera en arrière-plan (démon informatique)
        th.start(); // et on exécute le Thread pour mettre à jour la vue (déplacement continu de la tuile horizontalement)
    }
    
    public void afficher(Node n){
         fond.getChildren().add(n);
         n.setVisible(true);
     }

}
