package Projet2584_SC2g4;

import java.util.ArrayList;

/**
 *
 * @author quentin
 */
public class Undo {
   
public static class Originator { // il s’agit du créateur à sauvegarder
private String state;
public void set(String state) {
System.out.println("Originator: etat affecte a: "+state);
this.state = state;
}
public Object saveToMemento() {
System.out.println("Originator: sauvegarde dans le memento.");
return new Memento(state);
}
public void restoreFromMemento(Object m) {
if (m instanceof Memento) {
Memento memento = (Memento)m;
state = memento.getSavedState();
System.out.println("Originator: Etat après restauration: "+state);
        }
    } 
}
private static class Memento { // définition d’une classe interne pour la sauvegarde
    private String state;

    public Memento(String stateToSave) {
        state = stateToSave;
    }
    public String getSavedState() {
        return state;
    }
}
public static class Caretaker { // C’est la classe du gardien
    private ArrayList savedStates = new ArrayList();
    public void addMemento(Object m) {
        savedStates.add(m);
    }
public Object getMemento(int index) {
    return savedStates.get(index);
    }
    }


public static void main(String[] args) {
    int x = 0 ;
    Caretaker caretaker = new Caretaker();
    Originator originator = new Originator();
    
while (x<5){
    /*Bouton cliquable
    if bouton cliquable {
    
    }
    */
    x++;
}
    
}
}  