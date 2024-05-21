package Classes;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class FicheDeSuiviDone {
    private ArrayList<ObjectifEvalue> objectifs_atteints;
    private LocalDateTime savedDate;

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
