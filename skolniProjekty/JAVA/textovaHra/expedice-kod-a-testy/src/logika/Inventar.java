package logika;

import java.util.HashSet;
import java.util.Set;


public class Inventar {
    private final int omezeniInventare;
    private static Set<Predmet> obsahInventare;
    public static boolean klicSebran = false;


    public Inventar(int omezeniInventare) {
        this.omezeniInventare = omezeniInventare;
        obsahInventare = new HashSet<Predmet>();
    }

    public Predmet vyndejZInventare(String nazevVeci){
        for (Predmet neco:obsahInventare){
            if (neco.getNazev().equals(nazevVeci)){
                obsahInventare.remove(neco);
                return neco;
            }
        }
        return  null;
    }

    public boolean vlozDoInventare(Predmet vec){

        if(obsahInventare.size()< omezeniInventare && vec.jePrenositelny() || vec.getNazev().equals("magicky_predmet")){
            if(!vec.getNazev().equals("klic")) {
                obsahInventare.add(vec);
            } else {
                klicSebran = true;
            }
            return true;
        }
        return false;
    }


    public String dlouhyPopis(){
        String vypis = "obsah inventare: ";
        for (Predmet vec: obsahInventare){
            vypis += vec.getNazev() + " ";
        }
        return vypis;
    }

    public static Set<Predmet> getObsahInventare() {
        return obsahInventare;
    }
}
