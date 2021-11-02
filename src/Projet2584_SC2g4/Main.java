package Projet2584_SC2g4;

import java.util.Scanner;
//Test

public class Main implements Parametres {

    public static void main(String[] args) {
        Grille g1 = new Grille();
        boolean b = g1.nouvelleCase(true);
        b = g1.nouvelleCase(false);
        Scanner sc = new Scanner(System.in);
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
                if(g1.partieFinie() || g1.getValeurMax() >= OBJECTIF) cont = false;
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
                if(g1.partieFinie() || g2.getValeurMax() >= OBJECTIF) cont = false;
            }
        }
        if (g1.getValeurMax() >= OBJECTIF) System.out.println("Le joueur 1 a obtenu la tuile " + OBJECTIF + ", le joueur 1 a gagné !");
        if (g2.getValeurMax() >= OBJECTIF) System.out.println("Le joueur 2 a obtenu la tuile " + OBJECTIF + ", le joueur 2 a gagné !");
        if (g1.partieFinie()) System.out.println("Le joueur 1 ne peut plus déplacer de tuiles, le joueur 2 a gagné !");
        if (g2.partieFinie()) System.out.println("Le joueur 2 ne peut plus déplacer de tuiles, le joueur 1 a gagné !");
    }

}
