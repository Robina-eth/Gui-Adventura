

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková
 * @author    Robina Bangová
 * @version  pro školní rok 2016/2017
 */
public class HraTest {
    private Hra hra;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * 
     */
    @Test
    public void testVyhry() 
    {
        hra.zpracujPrikaz("seber mačeta");
        hra.zpracujPrikaz("jdi Louka");
        hra.zpracujPrikaz("seber zelený_diamand");
        hra.zpracujPrikaz("jdi Jeskyně");
        hra.zpracujPrikaz("seber červený_diamand");
        hra.zpracujPrikaz("jdi Osada");
        // test že máme v batohu 2 věci
        assertEquals(3, hra.getHerniPlan().getBatoh().vratObsahBatohu().size());
        hra.zpracujPrikaz("kup mapu"); // hra.zpracujPrikaz("kup mapa");
        // test že máme v batohu mapu a mačetu
        assertEquals(2, hra.getHerniPlan().getBatoh().vratObsahBatohu().size());
        hra.zpracujPrikaz("jdi Prales");
        assertEquals(true, hra.konecHry());
    }
    @Test
    public void testProhry() // Srabácký úťek ze hry
    {
        hra.zpracujPrikaz("jdi Loď");
        assertEquals(true, hra.konecHry());
    }
    @Test
    public void testProhry2() // Pád do studny
    {
        hra.zpracujPrikaz("jdi Louka");
        hra.zpracujPrikaz("jdi Studna");
        assertEquals(true, hra.konecHry());
    }
    @Test
    public void testProhry3() // Vchod do Pralesa bez věcí
    {
        hra.zpracujPrikaz("jdi Louka");
        hra.zpracujPrikaz("jdi Prales");
        assertEquals(true, hra.konecHry());
    }
    @Test
    public void testBatohu() // Test batohu - sbírání / vyhazov / sbírání / kapacita
    {
        hra.zpracujPrikaz("seber mačeta");
        assertEquals(1, hra.getHerniPlan().getBatoh().vratObsahBatohu().size());
        hra.zpracujPrikaz("vyhod mačeta");
        assertEquals(0, hra.getHerniPlan().getBatoh().vratObsahBatohu().size());
        hra.zpracujPrikaz("seber mačeta");
        assertEquals(1, hra.getHerniPlan().getBatoh().vratObsahBatohu().size());
        hra.zpracujPrikaz("seber boty");
        hra.zpracujPrikaz("jdi Louka");
         hra.zpracujPrikaz("seber zelený_diamand");
        hra.zpracujPrikaz("jdi Jeskyně");
        hra.zpracujPrikaz("seber červený_diamand");
        // Test že po sebrání 4 věcí máme v batohu jen 3 - kapacita
        assertEquals(3, hra.getHerniPlan().getBatoh().vratObsahBatohu().size());
    }
    
}
