package Classes;

import java.util.ArrayList;

public class FichedeSuivi {
    private ArrayList<Objectif> liste_objectifs;

    // Constructor to initialize the ArrayList
    public FichedeSuivi() {
        this.liste_objectifs = new ArrayList<>();
    }

    public void ajouterObjectif(Objectif Obj) {
        liste_objectifs.add(Obj);
    }
}
