package Projet2584_SC2g4;

public class Joueur {

    private String pseudo;
    private Grille grille;

    //Constructeur
    public Joueur(String p) {
        this.pseudo = p;
        this.grille = new Grille();
    }

    //Setters
    public void setGrille(Grille g) {
        this.grille = g;
    }

    //Getters
    public String getPseudo() {
        return this.pseudo;
    }

    public Grille getGrille() {
        return this.grille;
    }

    public static void main(String[] args) {
        // TODO code application logic here
    }

}
