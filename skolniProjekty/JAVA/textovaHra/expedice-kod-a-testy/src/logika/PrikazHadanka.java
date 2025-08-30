package logika;
/**
 *  Class PrikazHadanka - slouzi pro vypsani hadanky na zaklade aktualniho svetu.
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Filip Morschl
 *@version    1.0
 */
public class PrikazHadanka implements IPrikaz{
    private static final String NAZEV = "hadanka";
    private final HerniPlan plan;

    /**
     * Konstruktor
     * @param plan Instance tridy HerniPlan.
     */
    public PrikazHadanka(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Vypisuje obsah hadanek na zaklade aktualniho svetu.
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return Vrací textový řetězec hadanky.
     */

    @Override
    public String provedPrikaz(String... parametry) {
        Prostor prostor = plan.getAktualniProstor();
        if (prostor.getHadankaOdemcena()) {
            if (prostor.getNazev().equals("2.svet")) {
                return "Otazka: Jakou roli hraje rostlinny hormon ethylen v fyziologickych procesech rostlin?\n" +
                        "a) Stimuluje kliceni semen\n" +
                        "b) Reguluje tvorbu kvetu a plodu\n"+
                        "c) Zpusobuje odpadavani listu";
            } else if (prostor.getNazev().equals("3.svet")) {
                return "Otazka: Ktera cast houby je zodpovedna za prijimani zivin z okoli?\n" +
                        "a) Mycelium\n" +
                        "b) Hyfy\n"+
                        "c) Plodnice";
            } else if (prostor.getNazev().equals("4.svet")) {
                return "Otazka: Ktery proces vede ke vzniku puklin a rozpinani hornin?\n" +
                        "a) Eroze\n" +
                        "b) Meteorizace\n"+
                        "c) Litifikace";
            }
        }
        return "Nemůžeš odpovídat, dokud ti nepolozim otazku! >:(";

    }

    /**
     * Vraci promennou NAZEV.
     * @return Hodnota promenne NAZEV.
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
