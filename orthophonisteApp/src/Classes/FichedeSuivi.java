package Classes;

import java.io.Serializable;
import java.util.ArrayList;

public class FichedeSuivi implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Objectif> liste_objectifs;

    // Constructor to initialize the ArrayList
    public FichedeSuivi() {
        this.liste_objectifs = new ArrayList<>();
    }

    public void ajouterObjectif(Objectif Obj) {
        liste_objectifs.add(Obj);
    }
}
