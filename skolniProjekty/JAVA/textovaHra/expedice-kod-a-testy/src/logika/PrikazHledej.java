package logika;
import java.util.List;
/**
 *  Class PrikazHledej - slouzi k nalezeni skrytych predmetu.
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Filip Morschl
 *@version    1.0
 */
public class PrikazHledej implements IPrikaz{

    private static final String NAZEV = "hledej";
    private HerniPlan plan;

    /**
     * Konstruktor třídy PrikazHledej.
     *
     * @param plan herní plán, ve kterém se bude vyhledávat.
     */
    public PrikazHledej(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Provádí příkaz "hledej". Změní viditelnost všech předmětů v aktuálním prostoru na true.
     *
     * @param parametry nepoužívá se žádný parametr.
     * @return zpráva, kterou vypíše hra hráči
     */

    @Override
    public String provedPrikaz(String... parametry) {
        // Získání aktuálního prostoru
        Prostor aktualniProstor = plan.getAktualniProstor();

        // Získání seznamu předmětů v aktuálním prostoru
        List<Predmet> seznamPredmetu = aktualniProstor.getSeznamPredmetu();

        // Projdi všechny předměty v aktuálním prostoru
        for (Predmet predmet : seznamPredmetu) {
            // Nastav viditelnost na true
            predmet.setViditelny(true);
        }

        //Vypise vsechny predmety, vcetne tech skrytych.

        return plan.getAktualniProstor().vsechnyPredmety();
    }
    /**
     * Metoda vrací název příkazu (slovo, které používá hráč pro jeho vyvolání).
     *
     * @return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
