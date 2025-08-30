package logika;

import java.util.ArrayList;
import java.util.List;

import static logika.Inventar.klicSebran;

/**
 *  Class Predmet - trida predstavujici predmety, ktere hrac muze pridavat do sveho inventare.
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Filip Morschl
 *@version    1.0
 */
public class Predmet {
    private String nazev;
    private boolean prenositelny;
    private boolean viditelny;
    private boolean objeveno;
    private boolean hadanka;
    private boolean prozkoumano;
    private HerniPlan plan;
    /**
     * Konstruktor
     * @param nazev Nastavuje nazev predmetu.
     * @param viditelny Nastavuje viditelnost predmetu.
     * @param prenositelny Nastavuje prenositelnost predmetu.
     * @param objeveno Nastavuje, zdali byl predmet objeven.
     * @param hadanka Nastavuje, zdali hrac muze pouzit prikaz hadanka.
     * @param prozkoumano Nastavuje, zdali byl predmet jiz prozkouman.
     */
    public Predmet(String nazev, boolean viditelny, boolean prenositelny, boolean objeveno, boolean hadanka, boolean prozkoumano) {
        this.nazev = nazev;
        this.viditelny = viditelny;
        this.prenositelny = prenositelny;
        this.objeveno = objeveno;
        this.hadanka = hadanka;
        this.prozkoumano = prozkoumano;
        this.plan = plan;
    }

    /**
     * Vraci pouze predmety, ktere jsou viditelne.
     * @return Retezec odpovidajici nazvu predmetu nebo nic.
     */
    public String getNazev() {
        if (jeViditelny()) {
            return nazev;
        } else {
            return "";
        }
    }

    /**
     * Vraci nazev vsech predmetu v aktualnim svete.
     * @return Hodnota promenne nazev.
     */
    public String getNazevVse(){
            return nazev + " ";
    }

    /**
     * Vraci prenositelnost predmetu.
     * @return True nebo false podle prenositelnosti predmetu.
     */
    public boolean jePrenositelny(){ return prenositelny;}

    /**
     * Vraci, zdali je predmet viditelny.
     * @return True nebo false podle viditelnosti predmetu.
     */
    public boolean jeViditelny(){ return viditelny;}

    /**
     * Vraci, zdali byl predmet jiz objeven.
     * @return True nebo false podle toto, zda byl predmet jiz objeven.
     */
    public boolean getObjeveno() {
        return objeveno;
    }

    /**
     * Nastavuje viditelnost predmetu.
     * @param viditelny Pramter urcujici viditelnost predmetu.
     */
    public void setViditelny(boolean viditelny) {
        this.viditelny = viditelny;
    }

    /**
     * Vraci, zda hrac jiz muze pouzit prikaz hadanka.
     * @return True nebo false.
     */
    public boolean getHadanka() {
        return hadanka;
    }

    /**
     * Nastavuje, zda hrac muze pouzit prikaz hadanka.
     * @param hadanka True nebo false.
     */
    public void setHadanka(boolean hadanka) {
        this.hadanka = hadanka;
    }

    /**
     * Vraci, zdali byl predmet jiz prozkouman.
     * @return True nebo false.
     */
    public boolean getProzkoumano() {
        return prozkoumano;
    }

    /**
     * Nastavuje, jestli byl predmet prozkouman.
     * @param prozkokoumano Paramter, podle ktereho se nastavi promenna prozkoumano.
     */
    public void setProzkoumano(boolean prozkokoumano) {
        this.prozkoumano = prozkokoumano;
    }

    public void setPrenositelny(boolean prenositelny) {
        this.prenositelny = prenositelny;
    }

    public boolean getPrenositelny() {
        return prenositelny;
    }
}
