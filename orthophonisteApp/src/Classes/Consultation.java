package Classes;

import java.sql.Time;
import java.util.Date;

public class Consultation extends RendezVous {
    private Patient patient;
    public Consultation(Date date, Time heur_debut, Time heur_fin, Patient patient) {
        super(date, heur_debut, heur_fin);
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
