package Classes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FicheDeSuiviDone implements Serializable {

    private static final long serialVersionUID = 1L;
    private LocalDateTime savedDate;
    private ArrayList<ObjectifEvalue> objectifs_atteints;


    public void setSavedDate(LocalDateTime savedDate) {
        this.savedDate = savedDate;
    }
    public LocalDateTime getSavedDate() {
        return savedDate;
    }

    public void setObjectifsAtteints(ArrayList<ObjectifEvalue> objectifsAtteints) {
        this.objectifs_atteints = objectifsAtteints;
    }
    public ArrayList<ObjectifEvalue> getObjectifsAtteints() {
        return objectifs_atteints;
    }

}
