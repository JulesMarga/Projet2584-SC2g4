package Projet2584_SC2g4;

import application.Controller;
import application.FXMLDocumentController;
import application.GUIController;
import java.util.Scanner;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Partie2584 implements Parametres, java.io.Serializable {

    private Joueur joueur1;
    private Joueur joueur2; //éventuellement null
    private boolean gui; //false si en console, true si avec GUI
    private GUIController guiController;

    //Getters
    public Joueur getJ1() {
        return this.joueur1;
    }

    public Joueur getJ2() {
        return this.joueur2;
    }

    public boolean getGUI() {
        return this.gui;
    }

    public GUIController getGUIController() {
        return guiController;
    }

    //Setters
    public void setJ1(Joueur j) {
        this.joueur1 = j;
    }

    public void setJ2(Joueur j) {
        this.joueur2 = j;
    }

    public void setGUI(boolean b) {
        this.gui = b;
    }

    public void setGUIController(GUIController c) {
        this.guiController = c;
    }

    /**
     * deroulerTour correspond à tout le déroulement du joueur pour un tour et ne renvoie rien 
     * @param j le joueur à qui c'est au tour de jouer
     * @param int entier qui correspond au numéro du joueur (1 ou 2)
     */
    public void deroulerTour(Joueur j, int i) {
        //Lorsqu'on entre dans la méthode on considère que le joueur est capable de jouer: sa grille n'est pas déjà intégralement remplie
        Grille g = j.getGrille();

        if (!this.gui) {
            //affichage du pseudo, du score, de la valeur max par rapport à l'objectif
            System.out.println("\n" + j.getPseudo());
        }

        //ajout d'une nouvelle case
        if (g.getValeurMax() == 0) {
            //Cas particulier: si le jeu est à deux joueurs, les deux doivent commencer avec la même grille
            if (i == 1) {
                g.nouvelleCase(true, this.gui, this.guiController);
                g.nouvelleCase(false, this.gui, this.guiController);
                if (this.joueur2 != null) {
                    //On va créer un clone de la grille pour que le deuxième joueur ait la même par la suite
                    Grille clone = (Grille) g.clone();
                    this.joueur2.setGrille(clone);
                    this.joueur2.getGrille().setValeurMax(g.getValeurMax());
                }
            }
        } else if (i == 2 && this.joueur1.getGrille().getGrille().size() == 2 && this.joueur1.getGrille().getScore() == 0) {
            // Ne rien faire: cas particulier du premier tour du J2
        } else {
            g.nouvelleCase(false, this.gui, this.guiController); //Sinon, 3 chances sur 4 d'avoir un 1, 1 chance sur 4 d'avoir un 2
        }
        
        j.setOldGrille(j.getGrille());//On sauvegarde la nouvelle grille avant déplacement dans l'attribut oldGrille du joueur
        
        //Affichage de la grille
        if (!this.gui) {
            System.out.println("\n" + j.getPseudo());
            System.out.println(g);
            System.out.println("score: " + g.getScore());
            System.out.println("valeur max: " + g.getValeurMax() + ", objectif: " + OBJECTIF);

            //choix du déplacement
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez choisir un déplacement: ");
            String deplacement;
            int direction;
            boolean deplacementEffectue = false;

            while (!deplacementEffectue) {

                if (i == 1) {
                    System.out.println("Déplacer vers la Droite (d), Gauche (q), Haut (z), ou Bas (s) ?");

                    deplacement = sc.nextLine();
                    while (Partie2584.directionZQSD(deplacement) == 0) {
                        System.out.println("Veuillez corriger votre saisie.\n");
                        System.out.println("Déplacer vers la Droite (d), Gauche (q), Haut (z), ou Bas (s) ?");
                        deplacement = sc.nextLine();
                    }

                    direction = Partie2584.directionZQSD(deplacement);
                } else {
                    System.out.println("Déplacer vers la Droite (m), Gauche (k), Haut (o), ou Bas (l) ?");

                    deplacement = sc.nextLine();
                    while (Partie2584.directionOKLM(deplacement) == 0) {
                        System.out.println("Veuillez corriger votre saisie.\n");
                        System.out.println("Déplacer vers la Droite (m), Gauche (k), Haut (o), ou Bas (l) ?");
                        deplacement = sc.nextLine();
                    }

                    direction = Partie2584.directionOKLM(deplacement);
                }

                //On effectue le déplacement demandé
                deplacementEffectue = g.lanceurDeplacerCases(direction, this.guiController);
                
                if (j.getUndo() >= 1){
                    //On présente au joueur la grille obtenu avec son déplacement
                    System.out.println(g);
                    System.out.println("score: " + g.getScore());
                    System.out.println("valeur max: " + g.getValeurMax() + ", objectif: " + OBJECTIF);
                
                    //On propose au joueur d'annuler son dernier mouvement
                    System.out.println("Voulez vous annuler votre dernier déplacement ? \n Il vous reste " + j.getUndo() + " annulation possible pour cette partie\n");
                    System.out.println("Oui (o) ou Non (n) \n");
                    String annuler = sc.nextLine();
                    while (!"o".equals(annuler) && !"n".equals(annuler) ) {
                        System.out.println("Pour annuler votre dernier déplacement, taper (o) sinon taper (n)");
                        annuler = sc.nextLine();
                    }
                    if ("o".equals(annuler)){
                        j.setGrille(j.getOldGrille());//On écrase la grille actuel du joueur par l'ancienne grille, celle qu'il avait avant de se déplacer
                        j.setUndo(j.getUndo()-1);//On retire une posibilité d'annulation au joueur (puisque limité à 5)
                        System.out.println("Dernier deplacement annulé");//On informe le Joueur que son dernier déplacement a été annulé
                        deplacementEffectue = false;//On permet au joueur de se redéplacer (utile principalement dans un jeu à 2 joueurs, pas de perte de tour comparé à l'autre)
                    }
                }
            }
             
            
        }
        else{
            System.out.println("En attente de déplacement");
        }
    }

    /**
     * deroulement appelle dans le bon ordre les joueurs jusqu'à ce que la partie soit finie 
     */         
    public void deroulement() {

        boolean continuer = true; //initialisation

        while (continuer) {

            //On commence par le joueur 1, s'il peut encore jouer
            if (!this.joueur1.getGrille().partieFinie()) {
                this.deroulerTour(this.joueur1, 1);
            }

            continuer = !this.joueur1.getGrille().partieFinie(); //si le joueur 1 a fini, le joueur 2 ne jouera pas son tour

            //Puis c'est au tour du joueur 2, s'il existe et s'il peut encore jouer et que le joueur 1 n'a pas déjà fini à ce tour
            if (this.joueur2 != null && continuer) {
                if (!this.joueur2.getGrille().partieFinie()) {
                    this.deroulerTour(this.joueur2, 2);
                }
            }
            continuer = !this.partieFinie(); //On s'arrête si l'un des joueurs ne peut plus jouer
        }
        messageFin();
    }
    
    /**
     * messageFin affiche un message suivant les conditions pour laquelle la partie s'est terminée  
     */
    public void messageFin() {
        if (this.joueur2 == null) {
            if (this.joueur1.getGrille().getValeurMax() >= OBJECTIF) {
                System.out.println(this.joueur1.getPseudo() + ", vous avez obtenu la tuile " + OBJECTIF + ", félicitations !");
            } else {
                System.out.println(this.joueur1.getPseudo() + ", vous ne pouvez plus déplacer de tuiles, vous avez perdu !");
            }
        } else {

            if (this.joueur1.getGrille().partieFinie()) {
                if (this.joueur1.getGrille().getValeurMax() >= OBJECTIF) {
                    System.out.println(this.joueur1.getPseudo() + " a obtenu la tuile " + OBJECTIF + ", " + this.joueur1.getPseudo() + " a gagné !");
                } else {
                    System.out.println(this.joueur1.getPseudo() + " ne peut plus déplacer de tuiles, " + this.joueur2.getPseudo() + " a gagné !");
                }
            } else {
                if (this.joueur2.getGrille().getValeurMax() >= OBJECTIF) {
                    System.out.println(this.joueur2.getPseudo() + " a obtenu la tuile " + OBJECTIF + ", " + this.joueur2.getPseudo() + " a gagné !");
                } else {
                    System.out.println(this.joueur2.getPseudo() + " ne peut plus déplacer de tuiles, " + this.joueur1.getPseudo() + " a gagné !");
                }
            }
        }
    }

    //Autres méthodes
    
    /**
     * partieFinie permet de savoir si la partie est finie ou non 
     * @return une boolean 
     * @throws NullPointerException 
     */
    public boolean partieFinie() throws NullPointerException {
        //La partie est terminée lorsque l'un des deux joueurs a terminé sa partie
        boolean fin1 = this.joueur1.getGrille().partieFinie();
        if (this.joueur2 == null) {
            return fin1;
        } else {
            return fin1 || this.joueur2.getGrille().partieFinie();
        }
    }
    
    /**
     * directionZQSD permet de renvoyer la direction choisie par le joueur 1 en fonction de sa saisie 
     * @param saisie, string qui correspond à la saisie du clavier 
     * @return un entier 
     */
    public static int directionZQSD(String saisie) {
        saisie = saisie.toLowerCase();
        if (saisie.equals("z")) {
            return HAUT;
        } else if (saisie.equals("q")) {
            return GAUCHE;
        } else if (saisie.equals("s")) {
            return BAS;
        } else if (saisie.equals("d")) {
            return DROITE;
        } else {
            return 0;
        }
    }
    
    /**
     * directionOKLM pemet de renvoyer la direction choisie par le joueur 2 en fonction de sa saisie 
     * @param saisie, string qui correspond à la saisie du clavier 
     * @return un entier correspond à la direction  
     */
    public static int directionOKLM(String saisie) {
        saisie = saisie.toLowerCase();
        if (saisie.equals("o")) {
            return HAUT;
        } else if (saisie.equals("k")) {
            return GAUCHE;
        } else if (saisie.equals("l")) {
            return BAS;
        } else if (saisie.equals("m")) {
            return DROITE;
        } else {
            return 0;
        }
    }

}
