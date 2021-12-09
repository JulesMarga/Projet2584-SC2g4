package Projet2584_SC2g4;

import application.GUIController;
import java.util.HashSet;
import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GrilleTest implements Parametres {
    
    public GrilleTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
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
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGrille method, of class Grille.
     */
    @Test
    public void testGetGrille() {
        System.out.println("getGrille");
        Grille instance = new Grille();
        HashSet<Case> expResult = null;
        HashSet<Case> result = instance.getGrille();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Grille.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Grille instance = new Grille();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class Grille.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        Grille instance = new Grille();
        Object expResult = null;
        Object result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toHTML method, of class Grille.
     */
    @Test
    public void testToHTML() {
        System.out.println("toHTML");
        Grille instance = new Grille();
        String expResult = "";
        String result = instance.toHTML();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of partieFinie method, of class Grille.
     */
    @Test
    public void testPartieFinie() {
        System.out.println("partieFinie");
        Grille instance = new Grille();
        boolean expResult = false;
        boolean result = instance.partieFinie();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of lanceurDeplacerCases method, of class Grille.
     */
    @Test
    public void testLanceurDeplacerCases() {
        System.out.println("lanceurDeplacerCases");
        int direction = 0;
        GUIController controller = null;
        Grille instance = new Grille();
        boolean expResult = false;
        boolean result = instance.lanceurDeplacerCases(direction, controller);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCasesExtremites method, of class Grille.
     */
    @Test
    public void testGetCasesExtremites() {
        System.out.println("getCasesExtremites");
        int direction = 0;
        Grille instance = new Grille();
        Case[] expResult = null;
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
        boolean b = false;
        boolean gui = false;
        GUIController controller = null;
        Grille instance = new Grille();
        boolean expResult = false;
        boolean result = instance.nouvelleCase(b, gui, controller);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
