package Projet2584_SC2g4;

public class Joueur implements java.io.Serializable {

    private String pseudo;
    private Grille grille;
    private Grille oldGrille;
    private int undo;

    //Constructeur
    public Joueur(String p) {
        this.pseudo = p;
        this.grille = new Grille();
        this.undo = 5;
    }

    //Setters
    public void setGrille(Grille g) {
        this.grille = g;
    }

    public void setPseudo(String p) {
        this.pseudo = p;
    }
    
    public void setOldGrille(Grille g) {
        this.oldGrille = g;
    }
    
     public void setUndo(int u) {
        this.undo = u;
    }

    //Getters
    public String getPseudo() {
        return this.pseudo;
    }

    public Grille getGrille() {
        return this.grille;
    }
    
    public Grille getOldGrille() {
        return this.oldGrille;
    }
    
    public int getUndo() {
        return this.undo;
    }

    //Autres méthodes
    @Override
    public String toString() {
        return (this.pseudo + "\n" + this.grille);
    }

}
