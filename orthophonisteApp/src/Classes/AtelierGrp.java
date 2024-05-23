package Classes;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public class AtelierGrp extends RendezVous {
    private static final long serialVersionUID = 1L;

    private String thematique;
    private Set<Integer> patientlist;

    public AtelierGrp(LocalDate date, String heur_debut, String heur_fin, String observation,Set<Integer> patientlist,String thematique) {
        super(date, heur_debut, heur_fin,observation);
        this.patientlist=patientlist;
        this.thematique = thematique;
    }

    public Set<Integer> getPatientlist() {
        return patientlist;
    }

    public void setPatientlist(Set<Integer> patientlist) {
        this.patientlist = patientlist;
    }
}
