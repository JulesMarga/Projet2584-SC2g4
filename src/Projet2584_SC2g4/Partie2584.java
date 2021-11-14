package Projet2584_SC2g4;

import java.util.Scanner;

public class Partie2584 implements Parametres {
    
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
  

//    public void deroulement() {
//        this.joueurs[0].setGrille(new Grille());
//        this.joueurs[0].getGrille().nouvelleCase(true);
//        this.joueurs[0].getGrille().nouvelleCase(false);
//        if (this.joueurs[1] != null) {
//            this.joueurs[1].setGrille((Grille) joueurs[0].getGrille().clone());
//        }
//        Scanner sc = new Scanner(System.in); //plutot temporaire, à part si on veut conserver le fait d'entrer ZQSD et OKLM dans cette classe
//        boolean cont = true;
//        boolean bon = false;
//        boolean j = false; //correspond au joueur a qui c'est le tour
//        while (cont) {
//            if (cont) {
//                System.out.println(getJoueur(j));
//                System.out.println("score : " + getJoueur(j).getGrille().getScore());
//                System.out.println("valeur max : " + getJoueur(j).getGrille().getValeurMax() + " / objectif : " + OBJECTIF);
//                System.out.println("Déplacer vers la Droite (d), Gauche (q), Haut (z), ou Bas (s) ?");
//                //surement a cet endroit qu'il faut faire une interaction avec le main pour demander la direction !!
//                while (!bon) {
//                    String s = sc.nextLine();
//                    s.toLowerCase();
//                    if (!(s.equals("d") || s.equals("q") || s.equals("z") || s.equals("s"))) {
//                        System.out.println("Vous devez écrire d pour Droite, q pour Gauche, z pour Haut ou s pour Bas.");
//                    } else {
//                        int direction;
//                        if (s.equals("d")) {
//                            direction = DROITE;
//                        } else if (s.equals("q")) {
//                            direction = GAUCHE;
//                        } else if (s.equals("z")) {
//                            direction = HAUT;
//                        } else {
//                            direction = BAS;
//                        }
//                        boolean b = getJoueur(j).getGrille().lanceurDeplacerCases(direction);
//                        if (b) { //si la grille a été modifiée par le déplacement
//                            bon = true;
//                            boolean b2 = getJoueur(j).getGrille().nouvelleCase(false);
//                            if (!b2) { //si on ne peut pas creer de nouvelle case
//                                cont = false;
//                            }
//                        } else {
//                            System.out.println("Vous devez entrer une direction qui modifie la grille.");
//                        }
//                    }
//                }
//
//                bon = false;
//                if (getJoueur(j).getGrille().partieFinie() || getJoueur(j).getGrille().getValeurMax() >= OBJECTIF) {
//                    cont = false;
//                }
//
//                if (joueurs[1] != null) {
//                    j = !j;
//                }
//            }
//        }
//    }

    public void messageFin() {
        if (this.joueur2 == null) {
            if (this.joueur1.getGrille().getValeurMax()>= OBJECTIF) {
                System.out.println(this.joueur1.getPseudo() + ", vous avez obtenu la tuile " + OBJECTIF + ", félicitations !");
            } else {
                System.out.println(this.joueur1.getPseudo() + ", vous ne pouvez plus déplacer de tuiles, vous avez perdu !");
            }
        } else {
            //Joueur 1
            if (this.joueur1.getGrille().getValeurMax() >= OBJECTIF) {
                System.out.println(this.joueur1.getPseudo() + " a obtenu la tuile " + OBJECTIF + ", le joueur 1 a gagné !");
            }
            else{
                System.out.println(this.joueur1.getPseudo() + " ne peut plus déplacer de tuiles, le joueur 2 a gagné !");

            }
            
            //Joueur 2
            if (this.joueur2.getGrille().getValeurMax() >= OBJECTIF) {
                System.out.println(this.joueur2.getPseudo() + " a obtenu la tuile " + OBJECTIF + ", le joueur 2 a gagné !");
            }
            else{
                System.out.println(this.joueur2.getPseudo() + " ne peut plus déplacer de tuiles, le joueur 1 a gagné !");

            }
        }
    }

    //pourquoi est-ce que y'a un main là dedans ????
    public static void main(String[] args) {
        // TODO code application logic here
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

    
}
