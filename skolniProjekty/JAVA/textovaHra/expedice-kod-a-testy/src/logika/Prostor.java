package logika;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Filip Morschl
 * @version 1.0
 */
public class Prostor {

    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private List<Predmet> seznamPredmetu;
    private boolean hadankaOdemcena;
    private int level;

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     * @param hadankaOdemcena indikuje, zda je hádanka v tomto prostoru odemčená
     * @param level úroveň, kterou prostor reprezentuje ve hře
     */
    public Prostor(String nazev, String popis, boolean hadankaOdemcena, int level) {
        this.nazev = nazev;
        this.popis = popis;
        this.hadankaOdemcena = hadankaOdemcena;
        this.level = level;
        vychody = new HashSet<>();
        seznamPredmetu = new ArrayList<Predmet>();
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param obj object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
      @Override
    public boolean equals(Object obj) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == obj) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(obj instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) obj;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Nyni se nachazis v lokaci " + popis + "\n"
                + popisPredmetu() + "\n"
                + popisVychodu();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    public String popisVychodu() {
        String vracenyText = "vychody:";
        for (Prostor sousedni : vychody) {
            if(!sousedni.getNazev().equals("abrakadabra")) {
                vracenyText += " " + sousedni.getNazev();
            }
        }
        return vracenyText;
    }

    /**
     * Vrací textový řetězec viditelných předmětů v daném prostoru.
     * @return výpis předmět
     */
    public String popisPredmetu(){
        String vracenyText = "predmety k prozkoumani: ";
        for (Predmet predmet: seznamPredmetu){
            vracenyText = vracenyText + predmet.getNazev() + " ";
        }
        return vracenyText;
    }

    /**
     * Vrací textový řetězec všech předmětů v daném prostoru.
     * @return
     */
    public String vsechnyPredmety() {
        if(HerniPlan.level >= 3) {
            String vracenyText = "Nasel jsi skryty predmet!\n" +
                    "predmety k prozkoumani: ";
            System.out.println("");
            for (Predmet predmet : seznamPredmetu) {
                vracenyText = vracenyText + predmet.getNazevVse();
            }
            return vracenyText;
        } else {
            return "Zde nic neni.";
        }
    }


    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor> hledaneProstory =
                vychody.stream()
                        .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                        .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
    else {
            return hledaneProstory.get(0);
        }
    }




    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

    /**
     * Vkládá předmět do inventáře.
     * @param predmet instance tridy Predmet
     */
    public void vlozPredmet(Predmet predmet){seznamPredmetu.add(predmet);}


    /**
     * Zjišťuje jestli vstup zadaný hráčem odpovídá existujícímu předmětu..
     * @param nazevVeci Řetězec, který hráč zadá.
     * @return false - pokud neexituje v prostoru
     */
    public boolean obsahujeVec(String nazevVeci){
        for (Predmet vec: seznamPredmetu){
            if(vec.getNazev().equals(nazevVeci)){
                return true;
            }
        }
        return false;
    }


    /**
     * Vybírá předmět ze seznamu podle názvu a odstraňuje ho.
     * @param nazevVeci Řetězec, který hráč zadá.
     * @return null - pokud neexituje v prostoru  nebo je nepřenositelná
     */
    public Predmet vyberVec(String nazevVeci){
        Predmet vybranaVec = null;
        for(Predmet vec: seznamPredmetu){
            if(vec.getNazev().equals(nazevVeci)){
                vybranaVec = vec;
            }
        }

        if (vybranaVec != null && vybranaVec.jePrenositelny() && !vybranaVec.getHadanka() && vybranaVec.getProzkoumano()) {
            seznamPredmetu.remove(vybranaVec);
        }


        return vybranaVec;
    }

    /**
     * Nastavuje, zdali hráč může použít příkaz hádanka.
     * @param nazevVeci Řetězec, který hráč zadá.
     */
    public void zmenaHadanky(String nazevVeci){
        Predmet vybranaVec = null;
        for(Predmet vec: seznamPredmetu){
            if(vec.getNazev().equals(nazevVeci)){
                vybranaVec = vec;
            }
        }
        if(vybranaVec != null){
            vybranaVec.setHadanka(false);

        }

    }

    /**
     * Vrací seznam předmětů.
     * @return Řetězec, který vypisuje předměty.
     */
    public List<Predmet> getSeznamPredmetu() {
        return new ArrayList<>(seznamPredmetu);
    }

    /**
     * Nastavuje promennou hadankaOdemcena.
     * @param hadankaOdemcena Hodnota, která nastavuje proměnnou hadankaOdemcena.
     */
    public void setHadankaOdemcena(boolean hadankaOdemcena) {
        this.hadankaOdemcena = hadankaOdemcena;
    }

    /**
     * Vrací hodnotu hadankaOdemcena.
     * @return Hodnota proměnné hadankaOdemcena.
     */
    public boolean getHadankaOdemcena() {
        return hadankaOdemcena;
    }

    /**
     * Vrací hodnotu promenne level.
     * @return Vraci hodnotu promenne level.
     */
    public int getLevel() {
        return level;
    }
}
