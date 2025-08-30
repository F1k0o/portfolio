package logika;


import static logika.Inventar.klicSebran;

/**
 *
 *  Class HerniPlan - třída představující mapu a stav adventury.
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 */
public class HerniPlan {
    
    private Prostor aktualniProstor;

    private Prostor vyherniProstor;

    private Inventar inventar;
    private  static final int OMEZENI_INVENTARE = 3;
    public static int level =2;
    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví 1.svět.
     */
    public HerniPlan() {
        zalozProstoryHry();

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví 1.svet.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor svet1 = new Prostor("1.svet","1.svet", true, 1);
        Prostor svet2 = new Prostor("2.svet","2.svet", false, 2);
        Prostor svet3 = new Prostor("3.svet","3.svet", false, 3);
        Prostor svet4 = new Prostor("4.svet","4.svet", false, 4);
        Prostor svet5 = new Prostor("5.svet", "po vstupu vyhraješ", false, 5);
        Prostor abrakadabraSvet = new Prostor("abrakadabra", "Tajemny prostor", false, 0);

        svet1.setVychod(svet2);
        svet2.setVychod(svet1);
        svet2.setVychod(svet3);
        svet3.setVychod(svet2);
        svet3.setVychod(svet4);
        svet4.setVychod(svet3);
        svet4.setVychod(svet5);

        svet1.setVychod(abrakadabraSvet);
        svet2.setVychod(abrakadabraSvet);
        svet3.setVychod(abrakadabraSvet);
        svet4.setVychod(abrakadabraSvet);

        abrakadabraSvet.setVychod(svet1);
        abrakadabraSvet.setVychod(svet2);
        abrakadabraSvet.setVychod(svet3);
        abrakadabraSvet.setVychod(svet4);




        svet2.vlozPredmet(new Predmet("rostlina", true, true, true, true, false));
        svet2.vlozPredmet(new Predmet("houba", true, false, true, false, false));
        svet3.vlozPredmet(new Predmet("podezrela_rostlina", false, false, true, false, false));
        svet3.vlozPredmet(new Predmet("nejaky_sutr", true, false, true, false, false));
        svet3.vlozPredmet(new Predmet("exoticka_houba", false, true, false, true, false));
        svet4.vlozPredmet(new Predmet("vejce", true, false, true, false, false));
        svet4.vlozPredmet(new Predmet("cerna_bobule", false, false, true, false, false));
        svet4.vlozPredmet(new Predmet("vetsi_sutr", false, true, false, true, false));

        abrakadabraSvet.vlozPredmet(new Predmet("klic", true, true, true, false, true));
        abrakadabraSvet.vlozPredmet(new Predmet("koste", true, false, true, false, true));
        abrakadabraSvet.vlozPredmet(new Predmet("mec", true, false, true, false, true));
        abrakadabraSvet.vlozPredmet(new Predmet("louc", true, false, true, false, true));
        abrakadabraSvet.vlozPredmet(new Predmet("smetak", true, false, true, false, true));
        abrakadabraSvet.vlozPredmet(new Predmet("magicky_predmet", true, false, true, false, true));

        aktualniProstor = svet1;  // hra zde zacina
        vyherniProstor = svet5;
        inventar = new Inventar(OMEZENI_INVENTARE);
    }
    
    /**
     * Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     * @return aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }

    public Prostor getVyherniProstor() {
        return vyherniProstor;
    }

    public Inventar getInventar() {
        return inventar;
    }

    public int getLevel() {
        return level;
    }

}
