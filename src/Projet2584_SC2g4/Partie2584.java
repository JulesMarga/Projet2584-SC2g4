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
    /**
     * @param j le joueur à qui c'est au tour de jouer
     * @param i son numéro (1 ou 2)
     */
    public void deroulerTour(Joueur j,int i){
        //Lorsqu'on entre dans la méthode on considère que le joueur est capable de jouer: sa grille n'est pas déjà intégralement remplie
        Grille g = j.getGrille();
        
        //affichage du pseudo, du score, de la valeur max par rapport à l'objectif
        System.out.println("\n"+j.getPseudo());
        System.out.println("score : " + g.getScore());
        System.out.println("valeur max : " + g.getValeurMax() + " / objectif : " + OBJECTIF);
        
        //ajout d'une nouvelle case
        if(g.getValeurMax()==0){
            /*Cas particulier: si le jeu est à deux joueurs, les deux doivent commencer avec la même grille*/
            if(i==1){
                g.nouvelleCase(true);
                if(this.joueur2!=null){
                    //On va créer un clone de la grille pour que le deuxième joueur ait la même par la suite
                    Grille clone = (Grille) g.clone();
                    this.joueur2.setGrille(clone);
                    this.joueur2.getGrille().setValeurMax(1);
                }  
            }
        }
        else if(i==2 && this.getJ1().getGrille().getGrille().size()==1){
            //ne rien faire
        }
        else{
            g.nouvelleCase(false); //Sinon, 3 chances sur 4 d'avoir un 1, 1 chance sur 4 d'avoir un 2
        }
        
        //Affichage de la grille
        System.out.println(g);
        
        //choix du déplacement
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez choisir un déplacement: ");
        String deplacement; int direction;
        boolean deplacementEffectue=false;
       
        while(!deplacementEffectue){
            
            if(i==1){
                System.out.println("Déplacer vers la Droite (d), Gauche (q), Haut (z), ou Bas (s) ?");
            
                deplacement = sc.nextLine();
                while(Partie2584.directionZQSD(deplacement)==0){
                    System.out.println("Veuillez corriger votre saisie.\n");   
                    System.out.println("Déplacer vers la Droite (d), Gauche (q), Haut (z), ou Bas (s) ?");
                    deplacement = sc.nextLine();
                }
                
                direction = Partie2584.directionZQSD(deplacement);
            }
            else{
                System.out.println("Déplacer vers la Droite (m), Gauche (k), Haut (o), ou Bas (l) ?");
            
                deplacement = sc.nextLine();
                while(Partie2584.directionOKLM(deplacement)==0){
                    System.out.println("Veuillez corriger votre saisie.\n");   
                    System.out.println("Déplacer vers la Droite (m), Gauche (k), Haut (o), ou Bas (l) ?");
                    deplacement = sc.nextLine();
                }
                
                direction = Partie2584.directionOKLM(deplacement);
            }
            
            //On effectue le déplacement demandé
            deplacementEffectue = g.lanceurDeplacerCases(direction);
        }
        
        //On affiche la grille modifiée
        System.out.println(g);

    }

    public void deroulement() {

        boolean continuer = true; //initialisation

        while (continuer) {
            
            //On commence par le joueur 1, s'il peut encore jouer
            if(!this.joueur1.getGrille().partieFinie()){
                this.deroulerTour(this.joueur1,1);
            }
            
            //Puis c'est au tour du joueur 2, s'il existe et s'il peut encore jouer
            if(this.joueur2!=null){
                if(!this.joueur2.getGrille().partieFinie()){
                    this.deroulerTour(this.joueur2,2);
                }
            }
            
            continuer = !this.partieFinie(); //On continue ssi l'un des deux joueurs peut encore jouer
        }
    }

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
