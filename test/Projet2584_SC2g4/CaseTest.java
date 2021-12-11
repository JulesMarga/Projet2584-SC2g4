package Projet2584_SC2g4;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import javafx.scene.layout.Pane;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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

    @BeforeClass
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

    /**
     * Test of setGrille method, of class Case.
     */
    @Test
    public void testSetGrille() {
        System.out.println("setGrille");
        Grille g = new Grille();
        Case instance = c;
        instance.setGrille(g);
    }

    /**
     * Test of setGuiCase method, of class Case.
     */
    @Test
    public void testSetGuiCase() {
        System.out.println("setGuiCase");
        Pane p = new Pane();
        Case instance = c;
        instance.setGuiCase(p);
    }

    @Test
    public void testGetGuiCase() {
        System.out.println("getGuiCase");
        Case instance = c;
        Pane expResult = new Pane();
        instance.setGuiCase(expResult);
        Pane result = instance.getGuiCase();
        assertEquals(expResult, result);
    }

    /**
     * Test of getX method, of class Case.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Case instance = c;
        int expResult = 1;
        int result = instance.getX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getY method, of class Case.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        Case instance = c;
        int expResult = 2;
        int result = instance.getY();
        assertEquals(expResult, result);
    }

    /**
     * Test of getValeur method, of class Case.
     */
    @Test
    public void testGetValeur() {
        System.out.println("getValeur");
        Case instance = c;
        int expResult = 8;
        int result = instance.getValeur();
        assertEquals(expResult, result);
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
        Case instance = c;
        int expResult = 33;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of suiteFibo method, of class Case.
     */
    @Test
    public void testSuiteFibo() {
        System.out.println("suiteFibo");
        Case instance = c;
        Case c = new Case(0, 0, 5);
        boolean expResult = true;
        boolean result = instance.suiteFibo(c);
        assertEquals(expResult, result);
    }

    /**
     * Test of getVoisinDirect method, of class Case.
     */
    @Test
    public void testGetVoisinDirect() {
        System.out.println("getVoisinDirect");
        int direction = Parametres.HAUT;
        Case instance = c;
        Case expCase = new Case(1, 0, 2);
        Case caseResult = instance.getVoisinDirect(direction);
        boolean expResult = true;
        boolean result = caseResult.equals(expCase);
        assertEquals(expResult, result);
    }

}