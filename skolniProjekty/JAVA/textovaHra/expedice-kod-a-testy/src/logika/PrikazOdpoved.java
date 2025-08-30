package logika;
/**
 *  Class PrikazOdpoved - umoznuje hraci odpovedet na otazku.
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Filip Morschl
 *@version    1.0
 */
public class PrikazOdpoved implements IPrikaz{
    private String nazev = "odpoved";
    HerniPlan plan;
    /**
     * Konstruktor třídy PrikazOdpoved.
     *
     * @param plan herní plán, ve kterém probíhá hra.
     */

    public PrikazOdpoved(HerniPlan plan) {
        this.plan = plan;
    }
    /**
     * Provádí příkaz odpoved, který slouží k odpovídání na otázky.
     *
     * @param parametry pole parametrů zadaných při zadání příkazu
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if(parametry.length == 0){
            return "Musis napsat pismeno spravne odpovedi!";
        }
        if(parametry.length > 1){
            return "Zadal jsi moc znaku! Musis zadat pouze pismeno odpovei.";
        }
        String odpoved = parametry[0];
        PrikazSeber prikazSeber = new PrikazSeber(plan);
        Prostor prostor = plan.getAktualniProstor();

        if (prostor.getHadankaOdemcena()) {
            if (odpoved.equalsIgnoreCase("C") && plan.getAktualniProstor().getNazev().equals("2.svet")) {
                HerniPlan.level++;
                prostor.zmenaHadanky("rostlina");
                prikazSeber.provedPrikaz("rostlina");
                return "Dobra prace! Uspesne jsi pridal predmet rostlina do sveho inventare.\n" + "Nyni muzes pokracovat do dalsiho sveta!";
            } else if (odpoved.equalsIgnoreCase("B") && plan.getAktualniProstor().getNazev().equals("3.svet")) {
                HerniPlan.level++;
                prostor.zmenaHadanky("exoticka_houba");
                prikazSeber.provedPrikaz("exoticka_houba");
                return "Dobra prace! Uspesne jsi pridal predmet exoticka_houba do sveho inventare.\n" + "Nyni muzes pokracovat do dalsiho sveta!";
            } else if (odpoved.equalsIgnoreCase("B") && plan.getAktualniProstor().getNazev().equals("4.svet")) {
                HerniPlan.level++;
                prostor.zmenaHadanky("vetsi_sutr");
                prikazSeber.provedPrikaz("vetsi_sutr");
                return "Dobra prace! Uspesne jsi pridal predmet vetsi_sutr do sveho inventare.\n" + "Nyni muzes pokracovat do dalsiho sveta!";
            } else {
                return "Nespravna odpoved. Zkus to znovu.";
            }
        } else {
            return "Nemůžeš odpovídat, dokud ti nepolozim otazku! >:(";
        }

    }

    /**
     * Vraci nazev prikazu.
     * @return Vraci hodnotu promenne nazev.
     */
    @Override
    public String getNazev() {
        return nazev;
    }
}
