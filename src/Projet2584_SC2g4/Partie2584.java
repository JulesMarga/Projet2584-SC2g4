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
public class Partie2584 implements Parametres{
    
    private Joueur joueur1;
    private Joueur joueur2; //éventuellement null
  
    
    //Getters
    public Joueur getJ1(){
        return this.joueur1;
    }
    public Joueur getJ2(){
        return this.joueur2;
    }
    
    //Setters
    public void setJ1(Joueur j){
        this.joueur1=j;
    }
    public void setJ2(Joueur j){
        this.joueur2=j;
    }
    
    //Autres méthodes
    public boolean partieFinie() throws NullPointerException{
        //La partie est terminée lorsque les deux joueurs ont terminé leur partie
        boolean fin1 = this.joueur1.getGrille().partieFinie();
        if(this.joueur2==null){
            return fin1;
        }
        else{
            return fin1 && this.joueur2.getGrille().partieFinie();
        }
    }
    
    public static int directionZQSD(String saisie){
        saisie=saisie.toLowerCase();
        if(saisie.equals("z")){
            return HAUT;
        }
        else if(saisie.equals("q")){
            return GAUCHE;
        }
        else if(saisie.equals("s")){
            return BAS;
        }
        else if(saisie.equals("d")){
            return DROITE;
        }
        else{
            return 0;
        }
    }
    
    public static int directionOKLM(String saisie){
        saisie=saisie.toLowerCase();
        if(saisie.equals("o")){
            return HAUT;
        }
        else if(saisie.equals("k")){
            return GAUCHE;
        }
        else if(saisie.equals("l")){
            return BAS;
        }
        else if(saisie.equals("m")){
            return DROITE;
        }
        else{
            return 0;
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
