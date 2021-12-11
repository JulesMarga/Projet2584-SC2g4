package Projet2584_SC2g4;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author etudiant
 */
public class JoueurTest {
    
    private static Joueur j;
    private static Properties prop;
    private static FileInputStream propFile;
    private static String expectedPseudo;
    private static Grille expectedGrille;
    
    public JoueurTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws IOException {
        prop = new Properties();
        propFile = new FileInputStream("test/Projet2584_SC2g4/config.properties");
        prop.load(propFile);
        expectedPseudo = prop.getProperty("pseudo");
        j = new Joueur(expectedPseudo);
        expectedGrille = j.getGrille();
    }

    /**
     * Test of setGrille method, of class Joueur.
     */
    @Test
    public void testSetGrille() {
        System.out.println("setGrille");
        Grille g = new Grille();
        Joueur instance = j;
        instance.setGrille(g);
    }

    /**
     * Test of setPseudo method, of class Joueur.
     */
    @Test
    public void testSetPseudo() {
        System.out.println("setPseudo");
        String p = expectedPseudo;
        Joueur instance = j;
        instance.setPseudo(p);
    }

    /**
     * Test of getPseudo method, of class Joueur.
     */
    @Test
    public void testGetPseudo() {
        System.out.println("getPseudo");
        Joueur instance = j;
        String expResult = expectedPseudo;
        String result = instance.getPseudo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGrille method, of class Joueur.
     */
    @Test
    public void testGetGrille() {
        System.out.println("getGrille");
        Joueur instance = j;
        Grille expResult = expectedGrille;
        Grille result = instance.getGrille();
        assertEquals(expResult, result);
    }
    
}
