/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet2584_SC2g4;

/**
 *
 * @author clementinebleuze
 */
public class Joueur {
    
    private String pseudo;
    private Grille grille;
    private int score;


    //Constructeur
    public Joueur(String p){
        this.pseudo=p;
        this.grille=new Grille();
    }
    
    //Setters
    public void setGrille(Grille g){
        this.grille=g;
    }
    public void setScore(int s){
        this.score=s;
    }
    public void setPseudo(String p){
        this.pseudo=p;
    }
    
    //Getters
    public String getPseudo(){
        return this.pseudo;
    }
    public Grille getGrille(){
        return this.grille;
    }
    public int getScore(){
        return this.score;
    }
    
    //Autres méthodes
    @Override
    public String toString(){
        return(this.pseudo+"\n"+this.grille);
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
