package Projet2584_SC2g4;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

public class CaseTest implements Parametres {

    private static Case c;
    private static HashSet<Case> casesGrille = new HashSet<>();
    private static Properties prop;
    private static FileInputStream propFile;
    private static int expectedX;
    private static int expectedY;
    private static int expectedValeur;

    public CaseTest() {
    }

    @BeforeAll
    public static void setUpClass() throws IOException {
        prop = new Properties();
        propFile = new FileInputStream("test/Projet2584_SC2g4/config.properties");
        prop.load(propFile);
        expectedX = Integer.parseInt(prop.getProperty("x"));
        expectedY = Integer.parseInt(prop.getProperty("y"));
        expectedValeur = Integer.parseInt(prop.getProperty("valeur"));
        Grille grilleMock = Mockito.mock(Grille.class);
        for (int i = 0; i < 4; i++) {
            String valeurs = prop.getProperty("ligne" + i);
            int j = 0;
            for (String s : valeurs.split(" ")) { //pour chaque nombre 
                if (!s.trim().equals("0")) { // si non nul
                    casesGrille.add(new Case(j, i, Integer.parseInt(s.trim())));  // l'enregistrer en tant que case
                }
                j++;
            }
        }
        Mockito.when(grilleMock.getGrille()).thenReturn(casesGrille);
        c = new Case(expectedX, expectedY, expectedValeur);  // instancier la classe à tester
        c.setGrille(grilleMock);

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
     * Test of setGrille method, of class Case.
     */
    @Test
    public void testSetGrille() {
        System.out.println("setGrille");
        Grille g = null;
        Case instance = null;
        instance.setGrille(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGuiCase method, of class Case.
     */
    @Test
    public void testSetGuiCase() {
        System.out.println("setGuiCase");
        Pane p = null;
        Case instance = null;
        instance.setGuiCase(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setX method, of class Case.
     */
    @Test
    public void testSetX() {
        System.out.println("setX");
        int x = 0;
        Case instance = null;
        instance.setX(x);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setY method, of class Case.
     */
    @Test
    public void testSetY() {
        System.out.println("setY");
        int y = 0;
        Case instance = null;
        instance.setY(y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValeur method, of class Case.
     */
    @Test
    public void testSetValeur() {
        System.out.println("setValeur");
        int valeur = 0;
        Case instance = null;
        instance.setValeur(valeur);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGuiCase method, of class Case.
     */
    @Test
    public void testGetGuiCase() {
        System.out.println("getGuiCase");
        Case instance = null;
        Pane expResult = null;
        Pane result = instance.getGuiCase();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getX method, of class Case.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Case instance = new Case(0, 0, 1);
        int expResult = 0;
        int result = instance.getX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getY method, of class Case.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        Case instance = null;
        int expResult = 0;
        int result = instance.getY();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValeur method, of class Case.
     */
    @Test
    public void testGetValeur() {
        System.out.println("getValeur");
        Case instance = null;
        int expResult = 0;
        int result = instance.getValeur();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Case.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Case(1, 2, 8);
        Case instance = c;
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        obj = new Case(1, 0, 2);
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Case.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Case instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of suiteFibo method, of class Case.
     */
    @Test
    public void testSuiteFibo() {
        System.out.println("suiteFibo");
        Case c = null;
        Case instance = null;
        boolean expResult = false;
        boolean result = instance.suiteFibo(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVoisinDirect method, of class Case.
     */
    @Test
    public void testGetVoisinDirect() {
        System.out.println("getVoisinDirect");
        int direction = 0;
        Case instance = null;
        Case expResult = null;
        Case result = instance.getVoisinDirect(direction);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Case.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Case instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
