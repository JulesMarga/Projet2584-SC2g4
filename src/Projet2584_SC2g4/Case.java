package Projet2584_SC2g4;

import javafx.scene.layout.Pane;

public class Case implements Parametres, Cloneable, java.io.Serializable {

    private int x, y, valeur; //La case a des coordonnées ainsi qu'une valeur
    private Grille grille; //La case est liée à une grille (relation d'agrégation)
    private Pane guiCase;

    //Constructeur
    public Case(int abs, int ord, int v) {
        this.x = abs;
        this.y = ord;
        this.valeur = v;
    }

    //Setters
    public void setGrille(Grille g) {
        this.grille = g;
    }

    public void setGuiCase(Pane p) {
        this.guiCase = p;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    //Getters
    public Pane getGuiCase() {
        return this.guiCase;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getValeur() {
        return this.valeur;
    }

    //Autres méthodes
    
    /**
     * equals est utilisée lors de l'ajout d'une case à un ensemble pour vérifier qu'il n'y ait pas de doublons (test parmi tous les candidats qui ont le même hashcode)
     * @param obj est l'objet testé
     * @return boolean 
     */
    @Override
    public boolean equals(Object obj) { // la méthode equals est utilisée lors de l'ajout d'une case à un ensemble pour vérifier qu'il n'y a pas de doublons (teste parmi tous les candidats qui ont le même hashcode)
        if (obj instanceof Case) {
            Case c = (Case) obj;
            //Deux cases sont égales lorsqu'elles ont les mêmes coordonnées
            return (this.x == c.x && this.y == c.y);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() { // détermine le hashcode
        return this.x * 7 + this.y * 13;
    }
    
     /**
     * suiteFibo détermine si deux cases correspondent à des valeurs consécutives de la suite de Fibonacco dont la plus grande est inférieur ou égale à 2584
     * @param c est la case comparée 
     * @return boolean, true si les valeurs se suivent dans la suite, false sinon 
     */
    public boolean suiteFibo(Case c) {

        if (c != null) {

            int max, min; //on determine parmi les deux valeurs laquelle est la plus grande et laquelle est la plus petite

            if (c.getValeur() > this.getValeur()) {
                max = c.getValeur();
                min = this.getValeur();
            } else if (this.getValeur() > c.getValeur()) {
                max = this.getValeur();
                min = c.getValeur();
            } else if (c.getValeur() == 1) {
                return true; //si les deux cases ont la même valeur, c'est bon ssi ce sont des 1
            } else {
                return false; //sinon, ils ne sont pas consécutifs dans la suite de Fibonacci
            }
            //Important: on sait déjà que les valeurs des cases sont contenues dans la suite de Fibonacci (par création des cases)
            //Donc il reste simplement à vérifier le caractère consécutif
            return (max - min <= min);
        }
        return false;
    }
    
    /**
     * getVoisinDirect change la position d'une case d'une unité suivant la valeur de direction 
     * @param direction est un entier qui, suivant sa valeur, donne une direction différente  
     * @return c, une case avec ses nouvelles coordonnées  
     */
    public Case getVoisinDirect(int direction) {
        switch (direction) {
            case HAUT:
                for (int i = this.y - 1; i >= 0; i--) {
                    for (Case c : grille.getGrille()) {
                        if (c.getX() == this.x && c.getY() == i) {
                            return c;
                        }
                    }
                }
                break;
            case BAS:
                for (int i = this.y + 1; i < TAILLE; i++) {
                    for (Case c : grille.getGrille()) {
                        if (c.getX() == this.x && c.getY() == i) {
                            return c;
                        }
                    }
                }
                break;
            case GAUCHE:
                for (int i = this.x - 1; i >= 0; i--) {
                    for (Case c : grille.getGrille()) {
                        if (c.getX() == i && c.getY() == this.y) {
                            return c;
                        }
                    }
                }
                break;
            case DROITE:
                for (int i = this.x + 1; i < TAILLE; i++) {
                    for (Case c : grille.getGrille()) {
                        if (c.getX() == i && c.getY() == this.y) {
                            return c;
                        }
                    }
                }
                break;
            default:
                break;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Case(" + this.x + "," + this.y + "," + this.valeur + ",)";
    }

}
