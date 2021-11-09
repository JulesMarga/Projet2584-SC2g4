package Projet2584_SC2g4;

import java.util.InputMismatchException;
import java.util.Scanner;
//Test

public class MainSansGUI implements Parametres {

    public static void main(String[] args) {

        //Création de la partie
        Partie2584 p = new Partie2584();

        //Détermination des paramètres de la partie
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenue dans le jeu 2584. Souhaitez-vous jouer en mode 1 joueur (1) ou 2 joueurs (2) ?");
        int rep = 0;
        while (rep != 1 && rep != 2) {
            try {
                rep = sc.nextInt();
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
        if (rep == 2) {
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

        p.setJ1(j1);
        p.setJ2(j2);

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
            Grille g1 = new Grille();
            boolean b = g1.nouvelleCase(true);
            b = g1.nouvelleCase(false);

            Grille g2 = (Grille) g1.clone();

            boolean cont = true;
            boolean bon = false;
            while (cont) {
                if (cont) {
                    System.out.println("Joueur 1 :");
                    System.out.println(g1);
                    System.out.println("score : " + g1.getScore());
                    System.out.println("valeur max : " + g1.getValeurMax() + " / " + OBJECTIF);
                    System.out.println("Déplacer vers la Droite (d), Gauche (q), Haut (z), ou Bas (s) ?");
                    while (!bon) {
                        String s = sc.nextLine();
                        s.toLowerCase();
                        if (!(s.equals("d") || s.equals("droite")
                                || s.equals("q") || s.equals("gauche")
                                || s.equals("z") || s.equals("haut")
                                || s.equals("s") || s.equals("bas"))) {
                            System.out.println("Vous devez écrire d pour Droite, q pour Gauche, z pour Haut ou s pour Bas");
                        } else {
                            bon = true;
                            int direction;
                            if (s.equals("d") || s.equals("droite")) {
                                direction = DROITE;
                            } else if (s.equals("q") || s.equals("gauche")) {
                                direction = GAUCHE;
                            } else if (s.equals("z") || s.equals("haut")) {
                                direction = HAUT;
                            } else {
                                direction = BAS;
                            }
                            boolean b2 = g1.lanceurDeplacerCases(direction);
                            if (b2) {
                                b = g1.nouvelleCase(false);
                                if (!b) {
                                    cont = false;
                                }
                            }
                        }
                    }
                    bon = false;
                    if (g1.partieFinie() || g1.getValeurMax() >= OBJECTIF) {
                        cont = false;
                    }
                }
                if (cont) {
                    System.out.println("Joueur 2 :");
                    System.out.println(g2);
                    System.out.println("score : " + g2.getScore());
                    System.out.println("valeur max : " + g2.getValeurMax() + " / " + OBJECTIF);
                    System.out.println("Déplacer vers la Droite (m), Gauche (k), Haut (o), ou Bas (l) ?");
                    while (!bon) {
                        String s = sc.nextLine();
                        s.toLowerCase();
                        if (!(s.equals("m") || s.equals("droite")
                                || s.equals("k") || s.equals("gauche")
                                || s.equals("o") || s.equals("haut")
                                || s.equals("l") || s.equals("bas"))) {
                            System.out.println("Vous devez écrire m pour Droite, k pour Gauche, o pour Haut ou l pour Bas");
                        } else {
                            bon = true;
                            int direction;
                            if (s.equals("m") || s.equals("droite")) {
                                direction = DROITE;
                            } else if (s.equals("k") || s.equals("gauche")) {
                                direction = GAUCHE;
                            } else if (s.equals("o") || s.equals("haut")) {
                                direction = HAUT;
                            } else {
                                direction = BAS;
                            }
                            boolean b2 = g2.lanceurDeplacerCases(direction);
                            if (b2) {
                                b = g2.nouvelleCase(false);
                                if (!b) {
                                    cont = false;
                                }
                            }
                        }
                    }
                    bon = false;
                    if (g1.partieFinie() || g2.getValeurMax() >= OBJECTIF) {
                        cont = false;
                    }
                }
            }
            if (g1.getValeurMax() >= OBJECTIF) {
                System.out.println("Le joueur 1 a obtenu la tuile " + OBJECTIF + ", le joueur 1 a gagné !");
            }
            if (g2.getValeurMax() >= OBJECTIF) {
                System.out.println("Le joueur 2 a obtenu la tuile " + OBJECTIF + ", le joueur 2 a gagné !");
            }
            if (g1.partieFinie()) {
                System.out.println("Le joueur 1 ne peut plus déplacer de tuiles, le joueur 2 a gagné !");
            }
            if (g2.partieFinie()) {
                System.out.println("Le joueur 2 ne peut plus déplacer de tuiles, le joueur 1 a gagné !");
            }
        }
    }

}
