package Projet2584_SC2g4;

public class Joueur implements java.io.Serializable {

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

    public void setPseudo(String p) {
        this.pseudo = p;
    }

    //Getters
    public String getPseudo() {
        return this.pseudo;
    }

    public Grille getGrille() {
        return this.grille;
    }

    //Autres méthodes
    @Override
    public String toString() {
        return (this.pseudo + "\n" + this.grille);
    }

}
