package Projet2584_SC2g4.inter;

import java.util.HashSet;
import Projet2584_SC2g4.Case;
import javafx.scene.layout.GridPane;

public interface GrilleInter {
    
    public void setValeurMax(int i);
    public void setGuiGrille(GridPane grid);
    public HashSet<Case> getGrille();
    public int getValeurMax();
    public int getScore();
    @Override
    public String toString();
    @Override
    public Object clone();
    public String toHTML();
    public boolean partieFinie();
    public boolean lanceurDeplacerCases(int direction);
    public Case[] getCasesExtremites(int direction);
    public boolean nouvelleCase();
    
}