package Projet2584_SC2g4;

import application.FXMLDocumentController;
import application.GUIController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Grille implements Parametres, Cloneable, java.io.Serializable {

    private HashSet<Case> grille; //Une grille est composée d'un ensemble de cases
    private int valeurMax = 0; //La valeur maximale contenue par la grille à un instant t
    private int score = 0;
    private boolean deplacement;

    //Setters
    public void setValeurMax(int i) {
        this.valeurMax = i;
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

    //Constructeur
    public Grille() {
        this.grille = new HashSet<>();
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

    public boolean lanceurDeplacerCases(int direction) {
        Case[] extremites = this.getCasesExtremites(direction);
        deplacement = false; // pour vérifier si on a bougé au moins une case après le déplacement, avant d'en rajouter une nouvelle
        for (int i = 0; i < TAILLE; i++) {
            switch (direction) {
                case HAUT:
                    this.deplacerCasesRecursif(extremites, i, direction, 0);
                    break;
                case BAS:
                    this.deplacerCasesRecursif(extremites, i, direction, 0);
                    break;
                case GAUCHE:
                    this.deplacerCasesRecursif(extremites, i, direction, 0);
                    break;
                default:
                    this.deplacerCasesRecursif(extremites, i, direction, 0);
                    break;
            }
        }
        return deplacement;
    }

    private void fusion(Case c1, Case c2) {
        c1.setValeur(c1.getValeur() + c2.getValeur());
        this.score += c1.getValeur();
        if (this.valeurMax < c1.getValeur()) {
            this.valeurMax = c1.getValeur();
        }
        deplacement = true;
    }

    private void deplacerCasesRecursif(Case[] extremites, int rangee, int direction, int compteur) {
        if (extremites[rangee] != null) {
            if ((direction == HAUT && extremites[rangee].getY() != compteur)
                    || (direction == BAS && extremites[rangee].getY() != TAILLE - 1 - compteur)
                    || (direction == GAUCHE && extremites[rangee].getX() != compteur)
                    || (direction == DROITE && extremites[rangee].getX() != TAILLE - 1 - compteur)) {
                this.grille.remove(extremites[rangee]);
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
                this.grille.add(extremites[rangee]);
                deplacement = true;
            }
            Case voisin = extremites[rangee].getVoisinDirect(-direction);
            if (voisin != null) {
                if (extremites[rangee].suiteFibo(voisin)) {
                    this.fusion(extremites[rangee], voisin);
                    extremites[rangee] = voisin.getVoisinDirect(-direction);
                    this.grille.remove(voisin);
                    this.deplacerCasesRecursif(extremites, rangee, direction, compteur + 1);
                } else {
                    extremites[rangee] = voisin;
                    this.deplacerCasesRecursif(extremites, rangee, direction, compteur + 1);
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

                //ajout en mode graphique
                if (gui) {
                    Pane p = new Pane();
                    Label l = new Label(Integer.toString(valeur));
                    p.getStyleClass().add("pane");
                    l.getStyleClass().add("tuile");

                    p.setLayoutX(25 + nouvelleCase.getX() * 100);
                    p.setLayoutY(200 + nouvelleCase.getY() * 100);

                    controller.getFondGlobal().getChildren().add(p);
                    p.getChildren().add(l);
                    p.setVisible(true);
                    l.setVisible(true);
                    

                }
            }
            return true; //L'ajout a bien été effectué
        } else {
            return false; //L'ajout ne s'est pas effectué: grille déjà remplie
        }
    }

}
