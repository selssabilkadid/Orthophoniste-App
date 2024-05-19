package Classes;

import java.sql.Time;
import java.util.Date;
import java.util.Set;

public class AtelierGrp extends RendezVous {
    private Set<Integer> patientlist;

    public AtelierGrp(Date date, Time heur_debut, Time heur_fin,Set<Integer> patientlist) {
        super(date, heur_debut, heur_fin);
        this.patientlist=patientlist;
    }

    public Set<Integer> getPatientlist() {
        return patientlist;
    }

    public void setPatientlist(Set<Integer> patientlist) {
        this.patientlist = patientlist;
    }
}
