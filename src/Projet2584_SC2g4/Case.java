package Projet2584_SC2g4;

public class Case implements Parametres, Cloneable {

    private int x, y, valeur;
    private Grille grille;

    public Case(int abs, int ord, int v) {
        this.x = abs;
        this.y = ord;
        this.valeur = v;
    }

    public void setGrille(Grille g) {
        this.grille = g;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    @Override
    public boolean equals(Object obj) { // la méthode equals est utilisée lors de l'ajout d'une case à un ensemble pour vérifier qu'il n'y a pas de doublons (teste parmi tous les candidats qui ont le même hashcode)
        if (obj instanceof Case) {
            Case c = (Case) obj;
            return (this.x == c.x && this.y == c.y);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() { // détermine le hashcode
        return this.x * 7 + this.y * 13;
    }
    
    //détermine si deux valeurs sont consécutives dans la suite de Fibonacci
    public boolean suiteFibo(Case c) {
        if (c != null) {
            int max, min; //on determine parmi les deux valeurs laquelle est la plus grande et laquelle est la plus petite
            if (c.getValeur() > this.getValeur()) {
                max = c.getValeur();
                min = this.getValeur();
            }else if (this.getValeur() > c.getValeur()){
                max = this.getValeur();
                min = c.getValeur();
            }else if(c.getValeur() == 1) return true; //si les deux cases ont la même valeur, c'est bon si c'est des 1
            else return false; //sinon, ils ne sont pas consécutifs dans la suite de Fibonacci
            return max - min <= min; //si la difference entre la plus grande valeur et la plus petite est inférieure à la plus petite valeur, alors ces deux nombres sont consécutifs dans la suite de Fibonacci
        } else {
            return false;
        }
    }

    public Case getVoisinDirect(int direction) {
        if (direction == HAUT) {
            for (int i = this.y - 1; i >= 0; i--) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == this.x && c.getY() == i) {
                        return c;
                    }
                }
            }
        } else if (direction == BAS) {
            for (int i = this.y + 1; i < TAILLE; i++) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == this.x && c.getY() == i) {
                        return c;
                    }
                }
            }
        } else if (direction == GAUCHE) {
            for (int i = this.x - 1; i >= 0; i--) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == i && c.getY() == this.y) {
                        return c;
                    }
                }
            }
        } else if (direction == DROITE) {
            for (int i = this.x + 1; i < TAILLE; i++) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == i && c.getY() == this.y) {
                        return c;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Case(" + this.x + "," + this.y + "," + this.valeur + "," + this.grille + ")";
    }

}
