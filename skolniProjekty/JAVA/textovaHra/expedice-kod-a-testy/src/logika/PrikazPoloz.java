package logika;
/**
 *  Class PrikazPoloz - umoznuje vyndani predmetu z inventare.
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Filip Morschl
 *@version    1.0
 */
public class PrikazPoloz implements  IPrikaz{
    private static final String NAZEV = "poloz";

    private final HerniPlan plan;
    /**
     * Konstruktor třídy PrikazPoloz.
     *
     * @param plan herní plán, ve kterém probíhá hra.
     */
    public PrikazPoloz(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Provádí příkaz "poloz", který slouží k položení předmětu z inventáře do aktuálního prostoru.
     *
     * @param parametry název předmětu, který chce hráč položit
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length ==0){
            return "Co ?";
        }

        if (parametry.length > 1){
            return  "Moc!";
        }

        String nazevVeci = parametry[0];
        Predmet pozadovanaVec = plan.getInventar().vyndejZInventare(nazevVeci);

        if (pozadovanaVec != null){
            plan.getAktualniProstor().vlozPredmet(pozadovanaVec);
            return "Predemt: " + nazevVeci + " jsi polozil do mistnosti.";
        }

        return nazevVeci + " to tam neni";

    }
    /**
     * Metoda vrací název příkazu (slovo, které hráč používá pro jeho vyvolání).
     *
     * @return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
