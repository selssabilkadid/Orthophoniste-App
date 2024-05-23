package Classes;

import java.io.Serializable;
import java.util.ArrayList;

public class FicheDeSuiviDone implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<ObjectifEvalue> objectifs_atteints;



    public void setObjectifsAtteints(ArrayList<ObjectifEvalue> objectifsAtteints) {
        this.objectifs_atteints = objectifsAtteints;
    }
}
