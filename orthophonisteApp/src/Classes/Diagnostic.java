package Classes;

import java.io.Serializable;

public class Diagnostic implements Serializable {
    private static final long serialVersionUID = 1L;
    private Trouble[] troubles;

    public Diagnostic(Trouble[] troubles) {
        this.troubles = troubles;
    }
    public Diagnostic( ) {

    }

    public Trouble[] getTroubles() {
        return troubles;
    }
    public void setTroubles(Trouble[] troubles) {
        this.troubles = troubles;
    }

    public void ajouterTrouble(Trouble trouble) {

    }





}
