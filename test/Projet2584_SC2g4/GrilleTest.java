package Projet2584_SC2g4;

import application.GUIController;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import javafx.scene.layout.GridPane;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GrilleTest implements Parametres {
    
    private static Grille g = new Grille();
    private static HashSet<Case> expectedGrille = new HashSet<>();
    private static Properties prop;
    private static FileInputStream propFile;
    
    public GrilleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws IOException {
        prop = new Properties();
        propFile = new FileInputStream("test/Projet2584_SC2g4/config.properties");
        prop.load(propFile);
        g = new Grille();  // instancier la classe à tester
        for (int i = 0; i < 4; i++) {
            String valeurs = prop.getProperty("ligne" + i);
            int j = 0;
            for (String s : valeurs.split(" ")) { //pour chaque nombre 
                if (!s.trim().equals("0")) { // si non nul
                    g.getGrille().add(new Case(j, i, Integer.parseInt(s.trim())));  // l'enregistrer en tant que case
                }
                j++;
            }
        }
        expectedGrille = g.getGrille();
    }

    /**
     * Test of setValeurMax method, of class Grille.
     */
    @Test
    public void testSetValeurMax() {
        System.out.println("setValeurMax");
        int i = 0;
        Grille instance = g;
        instance.setValeurMax(i);
    }

    /**
     * Test of setGuiGrille method, of class Grille.
     */
    @Test
    public void testSetGuiGrille() {
        System.out.println("setGuiGrille");
        GridPane grid = new GridPane();
        Grille instance = g;
        instance.setGuiGrille(grid);
    }

    /**
     * Test of getGrille method, of class Grille.
     */
    @Test
    public void testGetGrille() {
        System.out.println("getGrille");
        Grille instance = g;
        HashSet<Case> expResult = expectedGrille;
        HashSet<Case> result = instance.getGrille();
        assertEquals(expResult, result);
    }

    /**
     * Test of getValeurMax method, of class Grille.
     */
    @Test
    public void testGetValeurMax() {
        System.out.println("getValeurMax");
        Grille instance = g;
        int expResult = 0;
        int result = instance.getValeurMax();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScore method, of class Grille.
     */
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        Grille instance = g;
        int expResult = 0;
        int result = instance.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of partieFinie method, of class Grille.
     */
    @Test
    public void testPartieFinie() {
        System.out.println("partieFinie");
        Grille instance = g;
        boolean expResult = false;
        boolean result = instance.partieFinie();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCasesExtremites method, of class Grille.
     */
    @Test
    public void testGetCasesExtremites() {
        System.out.println("getCasesExtremites");
        int direction = 1;
        Grille instance = g;
        Case[] expResult = new Case[4];
        expResult[0] = new Case(0, 3, 4);
        expResult[1] = new Case(1, 0, 2);
        expResult[2] = new Case(2, 2, 32);
        expResult[3] = new Case(3, 0, 4);
        Case[] result = instance.getCasesExtremites(direction);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of nouvelleCase method, of class Grille.
     */
    @Test
    public void testNouvelleCase() {
        System.out.println("nouvelleCase");
        Grille instance = new Grille();
        boolean expResult = true;
        GUIController controller = new GUIController();
        boolean result = instance.nouvelleCase(false, false, controller);
        assertEquals(expResult, result);
    }
    
}
