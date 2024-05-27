package Classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Diagnostic implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Trouble> troubles;

    public Diagnostic(ArrayList<Trouble> troubles) {
        this.troubles = troubles ;
    }

    public Diagnostic() {

    }

    public ArrayList<Trouble> getTroubles() {
        return troubles;
    }

    public void setTroubles(ArrayList<Trouble> troubles) {
        this.troubles = troubles;
    }

    public void ajouterTrouble(Trouble trouble) {
        troubles.add(trouble);
    }
}
