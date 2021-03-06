package Projet2584_SC2g4;

import application.GUIController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Grille implements Parametres, Cloneable, java.io.Serializable {

    private HashSet<Case> grille; //Une grille est composée d'un ensemble de cases
    private int valeurMax = 0; //La valeur maximale contenue par la grille à un instant t
    private int score = 0;
    private boolean deplacement;
    private GridPane guiGrille;

    //Constructeur
    public Grille() {
        this.grille = new HashSet<>();
    }

    //Setters
    public void setValeurMax(int i) {
        this.valeurMax = i;
    }

    public void setGuiGrille(GridPane grid) {
        this.guiGrille = grid;
    }

    //Getters
    public HashSet<Case> getGrille() {
        return grille;
    }

    public int getValeurMax() {
        return valeurMax;
    }

    public int getScore() {
        return score;
    }

    //Méthodes redéfinies
    @Override
    public String toString() {
        int[][] tableau = new int[TAILLE][TAILLE];
        for (Case c : this.grille) {
            tableau[c.getY()][c.getX()] = c.getValeur();
        }
        String result = "";
        for (int i = 0; i < tableau.length; i++) {
            result += Arrays.toString(tableau[i]) + "\n";
        }
        return result;
    }

    @Override
    public Object clone() {
        Grille gril = new Grille();
        Case caseClone;
        for (Case c : this.grille) {
            caseClone = new Case(c.getX(), c.getY(), c.getValeur());
            caseClone.setGrille(gril);
            gril.grille.add(caseClone);
        }
        return gril;
    }

    //Autres méthodes
    public String toHTML() {
        int[][] tableau = new int[TAILLE][TAILLE];
        for (Case c : this.grille) {
            tableau[c.getY()][c.getX()] = c.getValeur();
        }
        String result = "<html>";
        for (int i = 0; i < tableau.length; i++) {
            result += Arrays.toString(tableau[i]) + "<br/>";
        }
        result += "</html>";
        return result;
    }
    
    /**
     * partieFinie vérifie si il y a encore un mouvement possible dans la grille 
     * @return boolean, true si la partie est finie, faux sinon 
     */
    public boolean partieFinie() {
        if (this.grille.size() < TAILLE * TAILLE) {
            if (this.valeurMax >= OBJECTIF) {
                return true;
            } else {
                return false;
            }
        } else {
            for (Case c : this.grille) {
                for (int i = 1; i <= 2; i++) {
                    if (c.getVoisinDirect(i) != null) {
                        if (c.suiteFibo(c.getVoisinDirect(i))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    /**
     * lanceurDeplacerCases appelle deplacerCasesRecursif avec les paramètres adéquats  
     * @param direction est un entier qui, suivant sa valeur, donne une direction différente 
     * @param controller : le controller associé à l'interface graphique, si en mode graphique
     * @return deplacement, un boolean 
     */
    public boolean lanceurDeplacerCases(int direction, GUIController controller) {
        Case[] extremites = this.getCasesExtremites(direction);
        deplacement = false; // pour vérifier si on a bougé au moins une case après le déplacement, avant d'en rajouter une nouvelle
        for (int i = 0; i < TAILLE; i++) {
            this.deplacerCasesRecursif(extremites, i, direction, 0, controller);
        }
        return deplacement;
    }
    
    /**
     * fusion fusionne 2 cases sans rien retourner  
     * @param c1 première case 
     * @param c2 deuxièmre case 
     * @param controller : le controller associé à l'interface graphique, si en mode graphique
     */
    private void fusion(Case c1, Case c2, GUIController controller) {
        c1.setValeur(c1.getValeur() + c2.getValeur());
        this.score += c1.getValeur();
        if(this.guiGrille !=null){
            if (this.guiGrille.getId().equals("grille1")){
                controller.setScore1(Integer.toString(this.score));
            }
            else if(this.guiGrille.getId().equals("grille2")){
                controller.setScore2(Integer.toString(this.score));
            }
        }
        if (this.valeurMax < c1.getValeur()) {
            this.valeurMax = c1.getValeur();
        }
        deplacement = true;
    }
    
    /**
     * deplacerCasesRecursif déplace toutes les cases dans une direction et appelle la méthode Fusion si possible 
     * @param extremites tableau de cases aux extrémités de la direction donnée 
     * @param rangee, entier qui correspond à une ligne ou à une colonnne 
     * @param direction, entier qui correspond à la direction 
     * @param compteur, entier qui permet de savoir à quelle case on se trouve 
     * @param controller : le controller associé à l'interface graphique, si en mode graphique
     */
    private void deplacerCasesRecursif(Case[] extremites, int rangee, int direction, int compteur, GUIController controller) {
        if (extremites[rangee] != null) {

            if ((direction == HAUT && extremites[rangee].getY() != compteur)
                    || (direction == BAS && extremites[rangee].getY() != TAILLE - 1 - compteur)
                    || (direction == GAUCHE && extremites[rangee].getX() != compteur)
                    || (direction == DROITE && extremites[rangee].getX() != TAILLE - 1 - compteur)) {
                //On est maintenant sûr que la case n'est pas déjà à sa bonne place

                this.grille.remove(extremites[rangee]); //retrait temporaire de la grille
                //détermination des nouvelles coordonnées
                switch (direction) {
                    case HAUT ->
                        extremites[rangee].setY(compteur);
                    case BAS ->
                        extremites[rangee].setY(TAILLE - 1 - compteur);
                    case GAUCHE ->
                        extremites[rangee].setX(compteur);
                    default ->
                        extremites[rangee].setX(TAILLE - 1 - compteur);
                }
                //Déplacement graphique vers la nouvelle position
                if (this.guiGrille != null) {

                    controller.deplacerTuileRecursif(extremites[rangee].getGuiCase(), this.guiGrille, direction, compteur, false);

                }
            }
            //rajout dans la grille
            this.grille.add(extremites[rangee]);
            deplacement = true;
            //On trouve le voisin direct dans la direction opposée au déplacement
            Case voisin = extremites[rangee].getVoisinDirect(-direction);

            if (voisin != null) {
                //Cas de la fusion
                if (extremites[rangee].suiteFibo(voisin)) {

                    this.fusion(extremites[rangee], voisin, controller);
                    if (this.guiGrille != null) {

                        //On souhaite quand-même que le voisin se déplace jusque la case avec laquelle il fusionne
                        controller.deplacerTuileRecursif(voisin.getGuiCase(), this.guiGrille, direction, compteur, true);

                        voisin.getGuiCase().setVisible(false);
                        this.guiGrille.getChildren().remove(extremites[rangee].getGuiCase());

                        //On modifie graphiquement la valeur de la case résultant de la fusion
                        extremites[rangee].getGuiCase().getChildren().removeAll();
                        Label l = new Label(Integer.toString(extremites[rangee].getValeur()));
                        l.getStyleClass().add("tuile");
                        extremites[rangee].getGuiCase().getChildren().add(l);
                    }

                    extremites[rangee] = voisin.getVoisinDirect(-direction);
                    this.grille.remove(voisin);

                } //Cas sans fusion
                else {
                    extremites[rangee] = voisin;
                    this.deplacerCasesRecursif(extremites, rangee, direction, compteur + 1, controller);

                }

            }
        }

    }

    /*
    * Si direction = HAUT : retourne les 4 cases qui sont le plus en haut (une pour chaque colonne)
    * Si direction = DROITE : retourne les 4 cases qui sont le plus à droite (une pour chaque ligne)
    * Si direction = BAS : retourne les 4 cases qui sont le plus en bas (une pour chaque colonne)
    * Si direction = GAUCHE : retourne les 4 cases qui sont le plus à gauche (une pour chaque ligne)
    * Attention : le tableau retourné peut contenir des null si les lignes/colonnes sont vides
     */
    
    /**
     * getCasesExtremites renvoie les cases aux extrémités 
     * @param direction, entier qui correspond à la direction 
     * @return result, un tableau de cases aux extrémités de la direction donnée  
     */
    public Case[] getCasesExtremites(int direction) {
        Case[] result = new Case[TAILLE];
        for (Case c : this.grille) {
            switch (direction) {
                case HAUT -> {
                    if ((result[c.getX()] == null) || (result[c.getX()].getY() > c.getY())) {
                        result[c.getX()] = c; // si on n'avait pas encore de case pour cette rangée ou si on a trouvé un meilleur candidat
                    }
                }
                case BAS -> {
                    if ((result[c.getX()] == null) || (result[c.getX()].getY() < c.getY())) {
                        result[c.getX()] = c;
                    }
                }
                case GAUCHE -> {
                    if ((result[c.getY()] == null) || (result[c.getY()].getX() > c.getX())) {
                        result[c.getY()] = c;
                    }
                }
                default -> {
                    if ((result[c.getY()] == null) || (result[c.getY()].getX() < c.getX())) {
                        result[c.getY()] = c;
                    }
                }
            }
        }
        return result;
    }

    /**
     * nouvelleCase crée une case, lui attribue une valeur et l'implémente dans la grille    
     * @param b, boolean qui influe la valeur de la case 
     * @param gui, boolean a True si interface graphique, False sinon 
     * @param controller, le controller associé à l'interface graphique, si en mode graphique
     * @return un boolean qui confirme ou non si l'ajout a été effectué
     */
    public boolean nouvelleCase(boolean b, boolean gui, GUIController controller) { //b vaut true si on veut forcer la valeur de la nouvelle case a etre un 1
        //On commence par regarder s'il reste de la place dans la grille pour une nouvelle case
        if (this.grille.size() < TAILLE * TAILLE) {

            //Détermination aléatoire de la valeur de la nouvelle case
            int valeur;
            Random ra = new Random();
            if (b) {
                valeur = 1; //début de partie: on veut seulement avoir un 1
            } else {
                int alea = ra.nextInt(4); //nombre entier aleatoire entre 0 et 3
                if (alea == 0) {
                    valeur = 2; //1 chance sur 4 que le nombre soit 0
                } else {
                    valeur = 1; //3 chance sur 4 que le nombre soit different de 0
                }
                //75% de chances que la nouvelle case soit un 1, 25% de chances que ça soit un 2
            }

            //On va récupérer les cases encore libres
            ArrayList<Case> casesLibres = new ArrayList<>();
            // on crée toutes les cases encore libres
            for (int x = 0; x < TAILLE; x++) {
                for (int y = 0; y < TAILLE; y++) {
                    Case c = new Case(x, y, valeur);
                    if (!this.grille.contains(c)) { // contains utilise la méthode equals dans Case selon laquelle deux cases sont égales lorsqu'elles ont les mêmes coordonnées
                        casesLibres.add(c);
                    }
                }
            }
            // on en choisit une au hasard et on l'ajoute à la grille
            Case nouvelleCase = casesLibres.get(ra.nextInt(casesLibres.size()));
            nouvelleCase.setGrille(this);
            this.grille.add(nouvelleCase);
            if (nouvelleCase.getValeur() > this.valeurMax) { // Mise à jour de la valeur maximale présente dans la grille si c'est la première case ajoutée ou si on ajoute un 2 et que l'ancien max était 1
                this.valeurMax = nouvelleCase.getValeur();
            }

            //ajout en mode graphique
            if (gui) {
                GridPane g = this.guiGrille;

                Pane p = new Pane();
                Label l = new Label(Integer.toString(valeur));
                p.getStyleClass().add("pane");
                l.getStyleClass().add("tuile");

                p.setLayoutX(g.getLayoutX() + nouvelleCase.getX() * g.getWidth() / 4);
                p.setLayoutY(g.getLayoutY() + nouvelleCase.getY() * g.getWidth() / 4);

                nouvelleCase.setGuiCase(p);

                controller.getFondGlobal().getChildren().add(p);
                p.getChildren().add(l);
                p.setVisible(true);
                l.setVisible(true);

                //controller.deplacerTuileRecursif(p, g, BAS, 0);
            }
            return true; //L'ajout a bien été effectué
        } else {
            return false; //L'ajout ne s'est pas effectué: grille déjà remplie
        }
    }

}
