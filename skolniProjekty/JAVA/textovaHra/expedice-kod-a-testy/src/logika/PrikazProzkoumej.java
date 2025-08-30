package logika;
/**
 *  Class PrikazProzkoumej - prikaz, ktery dovoli hraci zjistit, ktery predmet jiz byl objeven.
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Filip Morschl
 *@version    1.0
 */
public class PrikazProzkoumej implements IPrikaz{
    private static final String NAZEV = "prozkoumej";
    private HerniPlan plan;
    /**
     * Konstruktor třídy PrikazProzkoumej.
     *
     * @param plan herní plán, ve kterém probíhá hra.
     */
    public PrikazProzkoumej(HerniPlan plan) {
        this.plan = plan;
    }
    /**
     * Provádí příkaz "prozkoumej", který slouží k prozkoumání předmětu v aktuálním prostoru.
     *
     * @param parametry název předmětu, který chce hráč prozkoumat
     * @return Zprava, jestli byl predmet jiz objeven nebo ne.
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // Pokud uživatel nezadal žádný předmět k prozkoumání
            return "Co mám prozkoumat? Musíš zadat název předmětu.";
        }

        String nazevPredmetu = parametry[0]; // Název předmětu
        Prostor aktualniProstor = plan.getAktualniProstor(); // Aktuální prostor

        // Získání seznamu předmětů v aktuálním prostoru
        for (Predmet predmet : aktualniProstor.getSeznamPredmetu()) {
            // Pokud název předmětu odpovídá zadanému parametru
            if (predmet.getNazev().equalsIgnoreCase(nazevPredmetu)) {
                // Ověření, zda má předmět proměnnou "objeveno" nastavenou na false
                if(predmet.getObjeveno() && !predmet.getHadanka()){
                    predmet.setProzkoumano(true);
                    return "Tento predmet uz byl objeven. Hledej dal.";
                } else if (predmet.getProzkoumano()) {
                    // Pokud má předmět již "objeveno" nastavené na true
                    return "Předmět " + nazevPredmetu + " už jsi prozkoumal dříve.";
                } else {
                    // Pokud má předmět "objeveno" nastavené na false
                    // Změna hodnoty proměnné "objeveno" na true
                    predmet.setProzkoumano(true);
                    // Vrácení informace o úspěšném prozkoumání předmětu
                    return "Gratuluji! Predmet " + nazevPredmetu + " doposud nebyl objeven.";
                }
            }
        }
        // Pokud předmět nebyl nalezen v aktuálním prostoru
        return "Předmět '" + nazevPredmetu + "' nebyl v aktuálním prostoru nalezen.";
    }
    /**
     * Metoda vrací název příkazu.
     *
     * @return Vraci hodnotu promenne NAZEV.
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
