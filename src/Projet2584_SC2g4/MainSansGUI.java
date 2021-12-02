package Projet2584_SC2g4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainSansGUI implements Parametres {

    public static void main(String[] args) {

        //Point d'entrée du programme: on choisit de continuer soit en console, soit dans l'interface graphique
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenue dans le jeu 2584. Souhaitez-vous jouer dans la console (c) ou en mode graphique (g) ?");
        String mode = "";

        while (!mode.equals("c") && !mode.equals("g")) {
            try {
                System.out.println("(pressez la touche 'c' ou 'g')");
                mode = sc.nextLine();
            } catch (InputMismatchException ime) {
                System.out.println(ime.getStackTrace());
            }
        }

        if (mode.equals("g")) {
            //On lance l'interface graphique
            application.AccueilGUI.main(args);
        } else {
            //On continue dans la console

            //Création de la partie
            Partie2584 p = new Partie2584();
            p.setGUI(false);

            Scanner sc2 = new Scanner(System.in);
            System.out.println("Souhaitez-vous jouer en mode 1 joueur (1) ou 2 joueurs (2) ?");
            int rep = 0;

            while (rep != 1 && rep != 2) {
                try {
                    rep = sc2.nextInt();
                } catch (InputMismatchException ime) {
                    System.out.println(ime.getStackTrace());
                }
            }

            //Dans tous les cas, il y a forcément au moins un joueur
            System.out.println("\nJoueur n°1, veuillez choisir un pseudonyme: ");
            String pseudo1 = "";

            while (pseudo1.equals("")) {
                try {
                    pseudo1 = sc.nextLine();
                } catch (Exception e) {
                    System.out.println(e.getStackTrace());
                }
            }

            p.setJ1(new Joueur(pseudo1));

            //On répète l'opération s'il y a un deuxième joueur
            if (rep == 2) {
                System.out.println("\nJoueur n°2, veuillez choisir un pseudonyme: ");
                String pseudo2 = "";

                while (pseudo2.equals("")) {
                    try {
                        pseudo2 = sc.nextLine();
                    } catch (Exception e) {
                        System.out.println(e.getStackTrace());
                    }
                }

                p.setJ2(new Joueur(pseudo2));
            }
            p.deroulement();
        }
    }
}
