package Projet2584_SC2g4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.*;

public class SerializerPartie {

    //créer un document .ser contenant toutes les informations caractérisants la partie passée en paramètres
    public void serialPartie(Partie2584 partie) {
        ObjectOutputStream oos = null;
        try {
            final FileOutputStream fichier = new FileOutputStream("partie.ser");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(partie);
            oos.flush();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    //régénère et retourne la partie associée au document .ser
    public Partie2584 deserialPartie() {
        Partie2584 partie;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            final FileInputStream fichierIn = new FileInputStream("partie.ser");
            ois = new ObjectInputStream(fichierIn);
            partie = (Partie2584) ois.readObject();
            return partie;
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                    //suppression du document après l'avoir lu
                    Files.deleteIfExists(Paths.get("partie.ser"));
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    //renvoie vrai si le fichier .ser existe, non sinon
    public boolean partieExist() {
        return Files.exists(Paths.get("partie.ser"));
    }
}
