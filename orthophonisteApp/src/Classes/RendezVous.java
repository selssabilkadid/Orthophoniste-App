package Classes;


import java.sql.Time;
import java.util.Date;

public abstract class RendezVous {
    Date date;
    Time heur_debut;
    Time heur_fin;
    String observation;

    // Constructor
    public RendezVous(Date date,Time heur_debut, Time heur_fin) {
        this.date= date;
        this.heur_debut=heur_debut;
        this.heur_fin = heur_fin;
    }

    public String getObservation() {

        return observation;
    }

    public void setObservation(String observation) {

        this.observation = observation;
    }

}
