package Projet2584_SC2g4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Grille implements Parametres, Cloneable {

    private HashSet<Case> grille; //Une grille est composée d'un ensemble de cases
    private int valeurMax = 0; //La valeur maximale contenue par la grille à un instant t
    private int score = 0;
    private boolean deplacement;

    //Setters
    //Getters
    public HashSet<Case> getGrille() {
        return grille;
    }

    public int getValeurMax() {
        return valeurMax;
    }

    public String getScore() {
        return String.valueOf(score);
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
            return false;
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

    public void victory() {
        System.out.println("Bravo ! Vous avez atteint " + this.valeurMax);
        System.exit(0);
    }

    public void gameOver() {
        System.out.println("La partie est finie. Votre score est " + this.valeurMax);
        System.exit(1);
    }

    public boolean nouvelleCase(boolean b) { //b vaut true si on veut forcer la valeur de la nouvelle case a etre un 1
        if (this.grille.size() < TAILLE * TAILLE) {
            ArrayList<Case> casesLibres = new ArrayList<>();
            //valeur a 75% de chance d'etre 1 et 25% de chance d'etre 2
            int valeur;
            Random ra = new Random();
            if (b) {
                valeur = 1;
            } else {
                int val = ra.nextInt(4); //nombre entier aleatoire entre 0 et 3
                if (val == 0) {
                    valeur = 2; //1 chance sur 4 que le nombre soit 0
                } else {
                    valeur = 1; //3 chance sur 4 que le nombre soit different de 0
                }
            }
            // on crée toutes les cases encore libres
            for (int x = 0; x < TAILLE; x++) {
                for (int y = 0; y < TAILLE; y++) {
                    Case c = new Case(x, y, valeur);
                    if (!this.grille.contains(c)) { // contains utilise la méthode equals dans Case
                        casesLibres.add(c);
                    }
                }
            }
            // on en choisit une au hasard et on l'ajoute à la grille
            Case ajout = casesLibres.get(ra.nextInt(casesLibres.size()));
            ajout.setGrille(this);
            this.grille.add(ajout);
            if (ajout.getValeur() > this.valeurMax) { // Mise à jour de la valeur maximale présente dans la grille si c'est la première case ajoutée ou si on ajoute un 2 et que l'ancien max était 1
                this.valeurMax = ajout.getValeur();
            }
            return true;
        } else {
            return false;
        }
    }

}
