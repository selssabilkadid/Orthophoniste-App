package Classes;

import java.io.Serializable;
import java.util.Arrays;

public class Diagnostic implements Serializable {
    private static final long serialVersionUID = 1L;
    private Trouble[] troubles;

    public Diagnostic(Trouble[] troubles) {
        this.troubles = troubles != null ? troubles : new Trouble[0];
    }

    public Diagnostic() {
        this.troubles = new Trouble[0];
    }

    public Trouble[] getTroubles() {
        return troubles;
    }

    public void setTroubles(Trouble[] troubles) {
        this.troubles = troubles;
    }

    public void ajouterTrouble(Trouble trouble) {
        if (trouble != null) {
            troubles = Arrays.copyOf(troubles, troubles.length + 1);
            troubles[troubles.length - 1] = trouble;
        }
    }
}
