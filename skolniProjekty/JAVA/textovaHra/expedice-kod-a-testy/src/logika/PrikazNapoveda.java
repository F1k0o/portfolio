package logika;

/**
 *  Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček
 *@version    pro školní rok 2016/2017
 *  
 */
public class PrikazNapoveda implements IPrikaz {
    
    private static final String NAZEV = "pomoc";
    private SeznamPrikazu platnePrikazy;

     /**
    *  Konstruktor třídy
    *  
    *  @param platnePrikazy seznam příkazů,
    *                       které je možné ve hře použít,
    *                       aby je nápověda mohla zobrazit uživateli. 
    */    
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }
    
    /**
     *  Vrací základní nápovědu po zadání příkazu "napoveda". Nyní se vypisuje
     *  vcelku primitivní zpráva a seznam dostupných příkazů.
     *
     *  @return napoveda ke hre
     */
    @Override
    public String provedPrikaz(String... parametry) {

        return "Abys probadal cely ostrov je potřeba projít peti svety. Mezi jednotlivymi svety prochazis pomoci prikazu 'jdi' (napr. jdi 2.svet). \n" +
                "Pro odemknuti nekterych svetu je potreba nalezt dosud neobjevene predmety a nasledne je pridat do sveho inventare pomoci prikazu 'seber %nazev predmetu%'.\n"+
                "Pro zjisteni, ktery predmet doposud nebyl objeven, slouzi prikaz 'prozkoumej %nazev predmetu%'. Nektere predmety mohou byt skryte, najdes je pomoci prikazu 'hledej'.\n"+
                "Pokud odpovidas na otazku, odpovidej pomoci prikazu 'odpoved %pismeno odpovedi%'.\n"+
                "Tohle jsou vsechny potrebne informace, ktere potrebujes znat. Hodne stesti!";
    }
    
     /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
      public String getNazev() {
        return NAZEV;
     }

    public SeznamPrikazu getPlatnePrikazy() {
        return platnePrikazy;
    }
}
