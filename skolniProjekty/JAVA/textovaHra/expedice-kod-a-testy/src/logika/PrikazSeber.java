package logika;

import java.util.List;

import static logika.Inventar.klicSebran;

/**
 *  Class PrikazSeber - slouzi pro pridavani predmetu do inventare.
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Filip Morschl
 *@version    1.0
 */
public class PrikazSeber implements IPrikaz{

    private static final String NAZEV = "seber";

    private final HerniPlan plan;
    private boolean a = false;
    /**
     * Konstruktor třídy PrikazSeber.
     *
     * @param plan herní plán, ve kterém probíhá hra.
     */
    public PrikazSeber(HerniPlan plan) {
        this.plan = plan;
    }
    /**
     * Provádí příkaz "seber", který slouží k sebrání předmětu z aktuálního prostoru a vložení ho do inventáře hráče.
     *
     * @param parametry název předmětu, který chce hráč sebrat
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if(parametry.length == 0){
            return "Co mám sebrat? Musíš zadat název předmětu!";
        }

        if(parametry.length > 1){
            return "Chceš toho sebrat nějak moc. Můžeš najednou sebrat jen jednu věc";
        }

        String nazevVeci = parametry[0];

        if (plan.getAktualniProstor().obsahujeVec(nazevVeci)){
           Predmet pozadovanaVec = plan.getAktualniProstor().vyberVec(nazevVeci);
            if(pozadovanaVec == null){
                return "chyba";
            }
            if(!pozadovanaVec.getProzkoumano()){
                return "Tento predmet jeste nemas probadany! Nejdrive ho prozkoumej.";
            }
            if(pozadovanaVec.getHadanka()){
                plan.getAktualniProstor().setHadankaOdemcena(true);
                return "Pro sebrani tohoto predmetu musis spravne odpovedet na otazku.\n"+
                        "Pokud chces pokracovat, napis prikaz: 'hadanka'.";
            }

            boolean povedloSeVlozit = plan.getInventar().vlozDoInventare(pozadovanaVec);

            if(plan.getAktualniProstor().getNazev().equals("abrakadabra") && !klicSebran && !pozadovanaVec.equals("klic")){
                return "Tento predmet je v zamcene truhlici! Nejprve seber klic.";

            } else if(plan.getAktualniProstor().getNazev().equals("abrakadabra")){

                // Získání aktuálního prostoru
                Prostor aktualniProstor = plan.getAktualniProstor();

                // Získání seznamu předmětů v aktuálním prostoru
                List<Predmet> seznamPredmetu = aktualniProstor.getSeznamPredmetu();

                // Projdi všechny předměty v aktuálním prostoru
                for (Predmet predmet : seznamPredmetu) {
                    // Nastav prenositelnost na true
                    predmet.setPrenositelny(true);
                }
                if(!a) {
                    System.out.println("Gratuluji! Odemkli jsi truhlici.");
                    a = true;
                }
            }
            if (povedloSeVlozit){
                return "Sebral jsi " + nazevVeci;
            }

            plan.getAktualniProstor().vlozPredmet(pozadovanaVec);
            return  "Tento predmet nelze sebrat!";
        }
        return nazevVeci + " se nenachází v tomto prostoru.";
    }
    /**
     * Metoda vrací název příkazu.
     *
     * @return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
