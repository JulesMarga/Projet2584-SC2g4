package Projet2584_SC2g4.inter;

import Projet2584_SC2g4.Case;
import Projet2584_SC2g4.Grille;
import javafx.scene.layout.Pane;

public interface CaseInter {

    public void setGrille(Grille g);
    public void setGuiCase(Pane p);
    public void setX(int x);
    public void setY(int y);
    public void setValeur(int valeur);
    public Pane getGuiCase();
    public int getX();
    public int getY();
    public int getValeur();
    @Override
    public boolean equals(Object obj);
    @Override
    public int hashCode();
    public boolean suiteFibo(Case c);
    public Case getVoisinDirect(int direction);
    @Override
    public String toString();
    
}