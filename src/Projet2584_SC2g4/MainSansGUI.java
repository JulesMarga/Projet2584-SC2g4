package Projet2584_SC2g4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainSansGUI implements Parametres {

    public static void main(String[] args) {

        //Création de la partie
        Partie2584 p = new Partie2584();

        //Détermination des paramètres de la partie
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenue dans le jeu 2584. Souhaitez-vous jouer en mode 1 joueur (1) ou 2 joueurs (2) ?");
        String rep = "";
        while (!"1".equals(rep) && !"2".equals(rep)) {
            try {
                rep = sc.nextLine();
            } catch (InputMismatchException ime) {
            }
        }

        //Initialisation des joueurs
        Joueur j1 = null;
        Joueur j2 = null;

        //joueur1
        System.out.println("Joueur n°1, veuillez choisir un pseudonyme:");
        String p1 = null;
        while (p1 == null) {
            try {
                p1 = sc.nextLine();
                j1 = new Joueur(p1);
            } catch (Exception e) {
                System.out.println("\nVeuillez choisir un pseudonyme:");
            }
        }

        //Eventuellement deuxième joueur
        if ("2".equals(rep)) {
            System.out.println("Joueur n°2, veuillez choisir un pseudonyme:");
            String p2 = null;
            while (p2 == null) {
                try {
                    p2 = sc.nextLine();
                    j2 = new Joueur(p2);
                } catch (Exception e) {
                    System.out.println("\nVeuillez choisir un pseudonyme:");
                }
            }
        }

        p.setJoueur(j1);
        p.setJoueur(j2);

        System.out.println("Souhaitez-vous jouer dans la console (c) ou dans l'interface graphique (g) ?");
        String mode = "";

        while (!mode.equals("c") && !mode.equals("g")) {
            try {
                mode = sc.nextLine();
            } catch (InputMismatchException ime) {

            }
        }

        if (mode.equals("g")) {

            application.Main.main(args);
        } else {
            p.deroulement();
            p.fin();
        }
    }

}
