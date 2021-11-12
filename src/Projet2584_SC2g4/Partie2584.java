package Projet2584_SC2g4;

import java.util.Scanner;

public class Partie2584 implements Parametres {

    private Joueur[] joueurs = new Joueur[2];

    //Getters
    public Joueur getJoueur(boolean j) { //retourne le premier joueur si j vaut false et le deuxieme si j vaut true
        if (j) {
            return joueurs[1];
        } else {
            return joueurs[0];
        }
    }

    //Setters
    public void setJoueur(Joueur j) {
        if (this.joueurs[0] == null) {
            this.joueurs[0] = j;
        } else {
            this.joueurs[1] = j;
        }
    }

    public void deroulement() {
        this.joueurs[0].setGrille(new Grille());
        this.joueurs[0].getGrille().nouvelleCase(true);
        this.joueurs[0].getGrille().nouvelleCase(false);
        if (this.joueurs[1] != null) {
            this.joueurs[1].setGrille((Grille) joueurs[0].getGrille().clone());
        }
        Scanner sc = new Scanner(System.in); //plutot temporaire, à part si on veut conserver le fait d'entrer ZQSD et OKLM dans cette classe
        boolean cont = true;
        boolean bon = false;
        boolean j = false; //correspond au joueur a qui c'est le tour
        while (cont) {
            if (cont) {
                System.out.println(getJoueur(j));
                System.out.println("score : " + getJoueur(j).getGrille().getScore());
                System.out.println("valeur max : " + getJoueur(j).getGrille().getValeurMax() + " / objectif : " + OBJECTIF);
                System.out.println("Déplacer vers la Droite (d), Gauche (q), Haut (z), ou Bas (s) ?");
                //surement a cet endroit qu'il faut faire une interaction avec le main pour demander la direction !!
                while (!bon) {
                    String s = sc.nextLine();
                    s.toLowerCase();
                    if (!(s.equals("d") || s.equals("q") || s.equals("z") || s.equals("s"))) {
                        System.out.println("Vous devez écrire d pour Droite, q pour Gauche, z pour Haut ou s pour Bas.");
                    } else {
                        int direction;
                        if (s.equals("d")) {
                            direction = DROITE;
                        } else if (s.equals("q")) {
                            direction = GAUCHE;
                        } else if (s.equals("z")) {
                            direction = HAUT;
                        } else {
                            direction = BAS;
                        }
                        boolean b = getJoueur(j).getGrille().lanceurDeplacerCases(direction);
                        if (b) { //si la grille a été modifiée par le déplacement
                            bon = true;
                            boolean b2 = getJoueur(j).getGrille().nouvelleCase(false);
                            if (!b2) { //si on ne peut pas creer de nouvelle case
                                cont = false;
                            }
                        } else {
                            System.out.println("Vous devez entrer une direction qui modifie la grille.");
                        }
                    }
                }

                bon = false;
                if (getJoueur(j).getGrille().partieFinie() || getJoueur(j).getGrille().getValeurMax() >= OBJECTIF) {
                    cont = false;
                }

                if (joueurs[1] != null) {
                    j = !j;
                }
            }
        }
    }

    public void fin() {
        if (joueurs[1] == null) {
            if (joueurs[0].getGrille().getValeurMax() >= OBJECTIF) {
                System.out.println(joueurs[0].getPseudo() + ", vous avez obtenu la tuile " + OBJECTIF + ", félicitations !");
            } else {
                System.out.println(joueurs[0].getPseudo() + ", vous ne pouvez plus déplacer de tuiles, vous avez perdu !");
            }
        } else {
            if (joueurs[0].getGrille().getValeurMax() >= OBJECTIF) {
                System.out.println(joueurs[0].getPseudo() + " a obtenu la tuile " + OBJECTIF + ", le joueur 1 a gagné !");
            }
            if (joueurs[1].getGrille().getValeurMax() >= OBJECTIF) {
                System.out.println(joueurs[1].getPseudo() + " a obtenu la tuile " + OBJECTIF + ", le joueur 2 a gagné !");
            }
            if (joueurs[0].getGrille().partieFinie()) {
                System.out.println(joueurs[0].getPseudo() + " ne peut plus déplacer de tuiles, le joueur 2 a gagné !");
            }
            if (joueurs[1].getGrille().partieFinie()) {
                System.out.println(joueurs[1].getPseudo() + " ne peut plus déplacer de tuiles, le joueur 1 a gagné !");
            }
        }
    }

    //pourquoi est-ce que y'a un main là dedans ????
    public static void main(String[] args) {
        // TODO code application logic here
    }

}
