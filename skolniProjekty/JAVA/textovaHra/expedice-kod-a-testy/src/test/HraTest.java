import logika.IHra;
import logika.Hra;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HraTest {

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
    public void testUkolUvitani() {

        ///////////////////////// 1.svet

        //Uvítání - zapnutí hry
        assertEquals("Vitej na dobrodruzne expedici!\n" +
                        "Tvym ukolem je prozkoumat tajuplny ostrov a najit doposud neobjevene predmety. Pokud nevis, jak se hra hraje, napis prikaz: 'pomoc'.\n" +
                        "\n" +
                        "Nyni se nachazis v lokaci 1.svet\n" +
                        "predmety k prozkoumani: \n" +
                        "vychody: 2.svet\n" +
                        "obsah inventare: ",
                hra.vratUvitani());
    }
    @Test
    public void testUkol1() {
        ///////////////////// 2.svet
        hra.vratUvitani();
        //1. krok JDI 2.svet
        assertEquals(
                "Nyni se nachazis v lokaci 2.svet\n" +
                        "predmety k prozkoumani: rostlina houba \n" +
                        "vychody: 1.svet 3.svet\n" +
                        "obsah inventare: \n",
                hra.zpracujPrikaz("jdi 2.svet"));
    }
    @Test
    public void testUkol2(){

        //2. krok PROZKOUMEJ rostlina
        hra.vratUvitani();
        hra.zpracujPrikaz("jdi 2.svet");
        assertEquals(
                "Gratuluji! Predmet rostlina doposud nebyl objeven.\n",
                hra.zpracujPrikaz("prozkoumej rostlina"));
        }
    @Test
    public void testUkol3(){
        //3. krok SEBER rostlina
        hra.vratUvitani();
        hra.zpracujPrikaz("jdi 2.svet");
        hra.zpracujPrikaz("prozkoumej rostlina");
        assertEquals(
                "Pro sebrani tohoto predmetu musis spravne odpovedet na otazku.\n"+
                "Pokud chces pokracovat, napis prikaz: 'hadanka'.\n",
                hra.zpracujPrikaz("seber rostlina"));
    }
    @Test
    public void testUkol4(){
        //4. krok HADANKA
        hra.vratUvitani();
        hra.zpracujPrikaz("jdi 2.svet");
        hra.zpracujPrikaz("prozkoumej rostlina");
        hra.zpracujPrikaz("seber rostlina");
        assertEquals(
                "Otazka: Jakou roli hraje rostlinny hormon ethylen v fyziologickych procesech rostlin?\n" +
                        "a) Stimuluje kliceni semen\n" +
                        "b) Reguluje tvorbu kvetu a plodu\n"+
                        "c) Zpusobuje odpadavani listu\n",
                hra.zpracujPrikaz("hadanka"));
    }
    @Test
    public void testUkol5(){
        //5. ODPOVED_JE
        hra.vratUvitani();
        hra.zpracujPrikaz("jdi 2.svet");
        hra.zpracujPrikaz("prozkoumej rostlina");
        hra.zpracujPrikaz("seber rostlina");
        hra.zpracujPrikaz("hadanka");
        assertEquals(
                "Dobra prace! Uspesne jsi pridal predmet rostlina do sveho inventare.\n"+
                        "Nyni muzes pokracovat do dalsiho sveta!\n",
                hra.zpracujPrikaz("odpoved c"));

        ////////////////////// 3.svet
    }
    @Test
    public void testUkol6(){
        //6. krok JDI 3.svet
        hra.vratUvitani();
        hra.zpracujPrikaz("jdi 2.svet");
        hra.zpracujPrikaz("prozkoumej rostlina");
        hra.zpracujPrikaz("seber rostlina");
        hra.zpracujPrikaz("hadanka");
        hra.zpracujPrikaz("odpoved c");
        assertEquals(
                "Nyni se nachazis v lokaci 3.svet\n" +
                        "predmety k prozkoumani:  nejaky_sutr  \n" +
                        "vychody: 4.svet 2.svet\n" +
                        "obsah inventare: rostlina \n",
                hra.zpracujPrikaz("jdi 3.svet"));
    }
    @Test
    public void testUkol7(){
        //7. krok HLEDEJ
        hra.vratUvitani();
        hra.zpracujPrikaz("jdi 2.svet");
        hra.zpracujPrikaz("prozkoumej rostlina");
        hra.zpracujPrikaz("seber rostlina");
        hra.zpracujPrikaz("hadanka");
        hra.zpracujPrikaz("odpoved c");
        hra.zpracujPrikaz("jdi 3.svet");
        assertEquals(
                "Nasel jsi skryty predmet!\n"+
                "predmety k prozkoumani: podezrela_rostlina nejaky_sutr exoticka_houba \n",
                hra.zpracujPrikaz("hledej"));
    }
    @Test
    public void testUkol8(){
        //8. krok PROZKOUMEJ exoticka_houba
        hra.vratUvitani();
        hra.zpracujPrikaz("jdi 2.svet");
        hra.zpracujPrikaz("prozkoumej rostlina");
        hra.zpracujPrikaz("seber rostlina");
        hra.zpracujPrikaz("hadanka");
        hra.zpracujPrikaz("odpoved c");
        hra.zpracujPrikaz("jdi 3.svet");
        hra.zpracujPrikaz("hledej");
        assertEquals(
                "Gratuluji! Predmet exoticka_houba doposud nebyl objeven.\n",
                hra.zpracujPrikaz("prozkoumej exoticka_houba"));
    }
    @Test
    public void testUkol9(){
        //9. krok SEBER rostlina
        hra.vratUvitani();
        hra.zpracujPrikaz("jdi 2.svet");
        hra.zpracujPrikaz("prozkoumej rostlina");
        hra.zpracujPrikaz("seber rostlina");
        hra.zpracujPrikaz("hadanka");
        hra.zpracujPrikaz("odpoved c");
        hra.zpracujPrikaz("jdi 3.svet");
        hra.zpracujPrikaz("hledej");
        hra.zpracujPrikaz("prozkoumej exoticka_houba");
        assertEquals(
                "Pro sebrani tohoto predmetu musis spravne odpovedet na otazku.\n"+
                        "Pokud chces pokracovat, napis prikaz: 'hadanka'.\n",
                hra.zpracujPrikaz("seber exoticka_houba"));
    }
    @Test
    public void testUkol10(){
        //10. krok HADANKA
        hra.vratUvitani();
        hra.zpracujPrikaz("jdi 2.svet");
        hra.zpracujPrikaz("prozkoumej rostlina");
        hra.zpracujPrikaz("seber rostlina");
        hra.zpracujPrikaz("hadanka");
        hra.zpracujPrikaz("odpoved c");
        hra.zpracujPrikaz("jdi 3.svet");
        hra.zpracujPrikaz("hledej");
        hra.zpracujPrikaz("prozkoumej exoticka_houba");
        hra.zpracujPrikaz("seber exoticka_houba");
        assertEquals(
                "Otazka: Ktera cast houby je zodpovedna za prijimani zivin z okoli?\n" +
                        "a) Mycelium\n" +
                        "b) Hyfy\n"+
                        "c) Plodnice\n",
                hra.zpracujPrikaz("hadanka"));
    }
    @Test
    public void testUkol11(){
        //11. ODPOVED_JE
        hra.vratUvitani();
        hra.zpracujPrikaz("jdi 2.svet");
        hra.zpracujPrikaz("prozkoumej rostlina");
        hra.zpracujPrikaz("seber rostlina");
        hra.zpracujPrikaz("hadanka");
        hra.zpracujPrikaz("odpoved c");
        hra.zpracujPrikaz("jdi 3.svet");
        hra.zpracujPrikaz("hledej");
        hra.zpracujPrikaz("prozkoumej exoticka_houba");
        hra.zpracujPrikaz("seber exoticka_houba");
        hra.zpracujPrikaz("hadanka");
        assertEquals(
                "Dobra prace! Uspesne jsi pridal predmet exoticka_houba do sveho inventare.\n"+
                        "Nyni muzes pokracovat do dalsiho sveta!\n",
                hra.zpracujPrikaz("odpoved b"));

        //////////////////////// 4.svet
    }
    @Test
    public void testUkol12(){
        //12. krok JDI 3.svet
        hra.vratUvitani();
        hra.zpracujPrikaz("jdi 2.svet");
        hra.zpracujPrikaz("prozkoumej rostlina");
        hra.zpracujPrikaz("seber rostlina");
        hra.zpracujPrikaz("hadanka");
        hra.zpracujPrikaz("odpoved c");
        hra.zpracujPrikaz("jdi 3.svet");
        hra.zpracujPrikaz("hledej");
        hra.zpracujPrikaz("prozkoumej exoticka_houba");
        hra.zpracujPrikaz("seber exoticka_houba");
        hra.zpracujPrikaz("hadanka");
        hra.zpracujPrikaz("odpoved b");
        assertEquals(
                "Nyni se nachazis v lokaci 4.svet\n" +
                        "predmety k prozkoumani: vejce   \n" +
                        "vychody: 5.svet 3.svet\n" +
                        "obsah inventare: exoticka_houba rostlina \n",
                hra.zpracujPrikaz("jdi 4.svet"));
    }
    @Test
    public void testUkol13(){
        //13. krok HLEDEJ
        hra.vratUvitani();
        hra.zpracujPrikaz("jdi 2.svet");
        hra.zpracujPrikaz("prozkoumej rostlina");
        hra.zpracujPrikaz("seber rostlina");
        hra.zpracujPrikaz("hadanka");
        hra.zpracujPrikaz("odpoved c");
        hra.zpracujPrikaz("jdi 3.svet");
        hra.zpracujPrikaz("hledej");
        hra.zpracujPrikaz("prozkoumej exoticka_houba");
        hra.zpracujPrikaz("seber exoticka_houba");
        hra.zpracujPrikaz("hadanka");
        hra.zpracujPrikaz("odpoved b");
        hra.zpracujPrikaz("jdi 4.svet");
        assertEquals(
                "Nasel jsi skryty predmet!\n"+
                        "predmety k prozkoumani: vejce cerna_bobule vetsi_sutr \n",
                hra.zpracujPrikaz("hledej"));
    }
    @Test
    public void testUkol14(){
        //14. krok PROZKOUMEJ vetsi_sutr
        hra.vratUvitani();
        hra.zpracujPrikaz("jdi 2.svet");
        hra.zpracujPrikaz("prozkoumej rostlina");
        hra.zpracujPrikaz("seber rostlina");
        hra.zpracujPrikaz("hadanka");
        hra.zpracujPrikaz("odpoved c");
        hra.zpracujPrikaz("jdi 3.svet");
        hra.zpracujPrikaz("hledej");
        hra.zpracujPrikaz("prozkoumej exoticka_houba");
        hra.zpracujPrikaz("seber exoticka_houba");
        hra.zpracujPrikaz("hadanka");
        hra.zpracujPrikaz("odpoved b");
        hra.zpracujPrikaz("jdi 4.svet");
        hra.zpracujPrikaz("hledej");
        assertEquals(
                "Gratuluji! Predmet vetsi_sutr doposud nebyl objeven.\n",
                hra.zpracujPrikaz("prozkoumej vetsi_sutr"));
    }
    @Test
    public void testUkol15(){
        //15. krok SEBER rostlina
        hra.vratUvitani();
        hra.zpracujPrikaz("jdi 2.svet");
        hra.zpracujPrikaz("prozkoumej rostlina");
        hra.zpracujPrikaz("seber rostlina");
        hra.zpracujPrikaz("hadanka");
        hra.zpracujPrikaz("odpoved c");
        hra.zpracujPrikaz("jdi 3.svet");
        hra.zpracujPrikaz("hledej");
        hra.zpracujPrikaz("prozkoumej exoticka_houba");
        hra.zpracujPrikaz("seber exoticka_houba");
        hra.zpracujPrikaz("hadanka");
        hra.zpracujPrikaz("odpoved b");
        hra.zpracujPrikaz("jdi 4.svet");
        hra.zpracujPrikaz("hledej");
        hra.zpracujPrikaz("prozkoumej vetsi_sutr");
        assertEquals(
                "Pro sebrani tohoto predmetu musis spravne odpovedet na otazku.\n"+
                        "Pokud chces pokracovat, napis prikaz: 'hadanka'.\n",
                hra.zpracujPrikaz("seber vetsi_sutr"));
    }
    @Test
    public void testUkol16(){
        //16. krok HADANKA
        hra.vratUvitani();
        hra.zpracujPrikaz("jdi 2.svet");
        hra.zpracujPrikaz("prozkoumej rostlina");
        hra.zpracujPrikaz("seber rostlina");
        hra.zpracujPrikaz("hadanka");
        hra.zpracujPrikaz("odpoved c");
        hra.zpracujPrikaz("jdi 3.svet");
        hra.zpracujPrikaz("hledej");
        hra.zpracujPrikaz("prozkoumej exoticka_houba");
        hra.zpracujPrikaz("seber exoticka_houba");
        hra.zpracujPrikaz("hadanka");
        hra.zpracujPrikaz("odpoved b");
        hra.zpracujPrikaz("jdi 4.svet");
        hra.zpracujPrikaz("hledej");
        hra.zpracujPrikaz("prozkoumej vetsi_sutr");
        hra.zpracujPrikaz("seber vetsi_sutr");
        assertEquals(
                "Otazka: Ktery proces vede ke vzniku puklin a rozpinani hornin?\n" +
                        "a) Eroze\n" +
                        "b) Meteorizace\n"+
                        "c) Litifikace\n",
                hra.zpracujPrikaz("hadanka"));
    }
    @Test
    public void testUkol17(){
        //17. ODPOVED_JE
        hra.vratUvitani();
        hra.zpracujPrikaz("jdi 2.svet");
        hra.zpracujPrikaz("prozkoumej rostlina");
        hra.zpracujPrikaz("seber rostlina");
        hra.zpracujPrikaz("hadanka");
        hra.zpracujPrikaz("odpoved c");
        hra.zpracujPrikaz("jdi 3.svet");
        hra.zpracujPrikaz("hledej");
        hra.zpracujPrikaz("prozkoumej exoticka_houba");
        hra.zpracujPrikaz("seber exoticka_houba");
        hra.zpracujPrikaz("hadanka");
        hra.zpracujPrikaz("odpoved b");
        hra.zpracujPrikaz("jdi 4.svet");
        hra.zpracujPrikaz("hledej");
        hra.zpracujPrikaz("prozkoumej vetsi_sutr");
        hra.zpracujPrikaz("seber vetsi_sutr");
        hra.zpracujPrikaz("hadanka");
        assertEquals(
                "Dobra prace! Uspesne jsi pridal predmet vetsi_sutr do sveho inventare.\n"+
                        "Nyni muzes pokracovat do dalsiho sveta!\n",
                hra.zpracujPrikaz("odpoved b"));

        ///////////////////// 5.svet

    }
    @Test
    public void testUkol18(){
        //18. krok JDI 3.svet
        hra.vratUvitani();
        hra.zpracujPrikaz("jdi 2.svet");
        hra.zpracujPrikaz("prozkoumej rostlina");
        hra.zpracujPrikaz("seber rostlina");
        hra.zpracujPrikaz("hadanka");
        hra.zpracujPrikaz("odpoved c");
        hra.zpracujPrikaz("jdi 3.svet");
        hra.zpracujPrikaz("hledej");
        hra.zpracujPrikaz("prozkoumej exoticka_houba");
        hra.zpracujPrikaz("seber exoticka_houba");
        hra.zpracujPrikaz("hadanka");
        hra.zpracujPrikaz("odpoved b");
        hra.zpracujPrikaz("jdi 4.svet");
        hra.zpracujPrikaz("hledej");
        hra.zpracujPrikaz("prozkoumej vetsi_sutr");
        hra.zpracujPrikaz("seber vetsi_sutr");
        hra.zpracujPrikaz("hadanka");
        hra.zpracujPrikaz("odpoved b");
        hra.zpracujPrikaz("jdi 5.svet");
        assertTrue(hra.konecHry());
        assertEquals("Gratuluji! Prozkoumal jsi cely ostrov!\n"+
                        "Hra timto konci.\n"+
                        "Sbohem!",
                hra.vratEpilog());

    }

    @Test
    public void testSebraniVeci(){
        hra.vratUvitani();
        hra.zpracujPrikaz("jdi 2.svet");
        hra.zpracujPrikaz("prozkoumej houba");
        //hra.zpracujPrikaz("seber houba");
        assertEquals(
                "Tento predmet nelze sebrat!\n",
                hra.zpracujPrikaz("seber houba"));
    }
    @Test
    public void testPomoc(){

        assertEquals(
                "Abys probadal cely ostrov je potřeba projít peti svety. Mezi jednotlivymi svety prochazis pomoci prikazu 'jdi' (napr. jdi 2.svet). \n" +
                        "Pro odemknuti nekterych svetu je potreba nalezt dosud neobjevene predmety a nasledne je pridat do sveho inventare pomoci prikazu 'seber %nazev predmetu%'.\n" +
                        "Pro zjisteni, ktery predmet doposud nebyl objeven, slouzi prikaz 'prozkoumej %nazev predmetu%'. Nektere predmety mohou byt skryte, najdes je pomoci prikazu 'hledej'.\n" +
                        "Pokud odpovidas na otazku, odpovidej pomoci prikazu 'odpoved %pismeno odpovedi%'.\n" +
                        "Tohle jsou vsechny potrebne informace, ktere potrebujes znat. Hodne stesti!",
                hra.zpracujPrikaz("pomoc"));
    }
    @Test
    public void testKonec(){
        assertEquals(
                "hra ukončena příkazem konec\n",
                hra.zpracujPrikaz("konec"));
    }
    @Test
    public void testPoloz(){
        hra.vratUvitani();
        hra.zpracujPrikaz("jdi 2.svet");
        hra.zpracujPrikaz("prozkoumej rostlina");
        hra.zpracujPrikaz("seber rostlina");
        hra.zpracujPrikaz("hadanka");
        hra.zpracujPrikaz("odpoved c");
        assertEquals(
                "Predemt: rostlina jsi polozil do mistnosti.\n",
                hra.zpracujPrikaz("poloz rostlina"));
    }

}
