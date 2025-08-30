package logika;

public class PrikazAbrakadabra implements IPrikaz {
    private static final String NAZEV = "abrakadabra";
    private HerniPlan plan;
    private Prostor predchoziProstor;
    private static boolean abrakadabraPocet = false;

    public PrikazAbrakadabra(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {

        if (predchoziProstor == null) {
            predchoziProstor = plan.getAktualniProstor();
        }

        if (abrakadabraPocet == false) {
            Prostor abrakadabraSvet = plan.getAktualniProstor().vratSousedniProstor("abrakadabra");
            plan.setAktualniProstor(abrakadabraSvet);
            abrakadabraPocet = true;
            return "Nyní se nachazis v tajemne mistnosti!\n" + "V mistnosti se take nachazi truhla, skrze klicovou dirku vidis tyto predmety: " +
                    "koste, mec, louc, smetak a magicky predmet.\n\n"
                    + plan.getAktualniProstor().popisPredmetu() ;
        } else {
            abrakadabraPocet = false;
            navratDoPredchozihoProstoru(); // Vrátí hráče do původního prostoru
            return "Opustil jsi tajemnou místnost.";
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }

    // Metoda pro návrat hráče do původního prostoru
    public void navratDoPredchozihoProstoru() {
        if (predchoziProstor != null) {
            plan.setAktualniProstor(predchoziProstor);
            predchoziProstor = null; // Resetování původního prostoru po návratu
        }
    }
}