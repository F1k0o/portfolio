import logika.Hra;
import logika.IHra;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrikazAbrakadabraTest {

    private IHra hra;

    @BeforeEach
    public void setUp(){
        hra = new Hra();
    }

    @AfterEach
    public void tearDown(){
        hra = null;
    }

    @Test
    public void testAbrakadabra() {
        hra.vratUvitani();
        //1. krok JDI 2.svet
        assertEquals(
                "Nyní se nachazis v tajemne mistnosti!\n" +
                        "V mistnosti se take nachazi truhla, skrze klicovou dirku vidis tyto predmety: koste, mec, louc, smetak a magicky predmet.\n" +
                        "\n" +
                        "predmety k prozkoumani: klic koste mec louc smetak magicky_predmet \n",
                hra.zpracujPrikaz("abrakadabra"));
    }

    @Test
    public void testSeberKlic() {
        hra.vratUvitani();
        hra.zpracujPrikaz("abrakadabra");
        //1. krok JDI 2.svet
        assertEquals(
                "Sebral jsi klic\n",
                hra.zpracujPrikaz("seber klic"));
    }

    @Test
    public void testSeberPredmet() {
        hra.vratUvitani();
        hra.zpracujPrikaz("abrakadabra");
        hra.zpracujPrikaz("seber klic");
        //1. krok JDI 2.svet
        assertEquals(
                "Sebral jsi koste\n",
                hra.zpracujPrikaz("seber koste"));
    }

    @Test
    public void testSeber4Predmet() {
        hra.vratUvitani();
        hra.zpracujPrikaz("abrakadabra");
        hra.zpracujPrikaz("seber klic");
        hra.zpracujPrikaz("seber koste");
        hra.zpracujPrikaz("seber mec");
        hra.zpracujPrikaz("seber smetak");
        //1. krok JDI 2.svet
        assertEquals(
                "Tento predmet nelze sebrat!\n",
                hra.zpracujPrikaz("seber louc"));
    }

    @Test
    public void testSeberMegPred() {
        hra.vratUvitani();
        hra.zpracujPrikaz("abrakadabra");
        hra.zpracujPrikaz("seber klic");
        hra.zpracujPrikaz("seber koste");
        hra.zpracujPrikaz("seber mec");
        hra.zpracujPrikaz("seber smetak");
        hra.zpracujPrikaz("seber louc");
        //1. krok JDI 2.svet
        assertEquals(
                "Sebral jsi magicky_predmet\n",
                hra.zpracujPrikaz("seber magicky_predmet"));
    }

    @Test
    public void testNeniKlic() {
        hra.vratUvitani();
        hra.zpracujPrikaz("abrakadabra");
        //1. krok JDI 2.svet
        assertEquals(
                "Tento predmet je v zamcene truhlici! Nejprve seber klic.\n",
                hra.zpracujPrikaz("seber koste"));
    }


}




