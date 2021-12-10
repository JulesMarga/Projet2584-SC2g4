package Projet2584_SC2g4;

import application.GUIController;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GrilleTest implements Parametres {
    
    private static Grille g = new Grille();
    private static Properties prop;
    private static FileInputStream propFile;
    
    public GrilleTest() {
    }
    
    @BeforeAll
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
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of setValeurMax method, of class Grille.
     */
    @Test
    public void testSetValeurMax() {
        System.out.println("setValeurMax");
        int i = 0;
        Grille instance = new Grille();
        instance.setValeurMax(i);
    }

    /**
     * Test of setGuiGrille method, of class Grille.
     */
    @Test
    public void testSetGuiGrille() {
        System.out.println("setGuiGrille");
        GridPane grid = null;
        Grille instance = new Grille();
        instance.setGuiGrille(grid);
    }

    /**
     * Test of getGrille method, of class Grille.
     */
    @Test
    public void testGetGrille() {
        System.out.println("getGrille");
        Grille instance = g;
        HashSet<Case> expResult = g.getGrille();
        HashSet<Case> result = instance.getGrille();
        assertEquals(expResult, result);
    }

    /**
     * Test of getValeurMax method, of class Grille.
     */
    @Test
    public void testGetValeurMax() {
        System.out.println("getValeurMax");
        Grille instance = new Grille();
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
        Grille instance = new Grille();
        int expResult = 0;
        int result = instance.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Grille.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Grille instance = g;
        String expResult = "[0, 2, 0, 4][0, 0, 0, 16][0, 8, 32, 8][4, 16, 4, 2]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of toHTML method, of class Grille.
     */
    @Test
    public void testToHTML() {
        System.out.println("toHTML");
        Grille instance = g;
        String expResult = "<html>[0, 2, 0, 4]<br/>[0, 0, 0, 16]<br/>[0, 8, 32, 8]<br/>[4, 16, 4, 2]<br/></html>";
        String result = instance.toHTML();
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
     * Test of lanceurDeplacerCases method, of class Grille.
     */
    @Test
    public void testLanceurDeplacerCases() {
        System.out.println("lanceurDeplacerCases");
        int direction = 1;
        Grille instance = g;
        GUIController controller = new GUIController();
        boolean expResult = false;
        boolean result = instance.lanceurDeplacerCases(direction, controller);
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
        expResult[3] = new Case(0, 3, 4);
        Case[] result = instance.getCasesExtremites(direction);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
