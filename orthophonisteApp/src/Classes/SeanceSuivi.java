package Classes;

import java.sql.Time;
import java.util.Date;

public class SeanceSuivi extends RendezVous {
    private int patientid;
    public SeanceSuivi(Date date, Time heur_debut, Time heur_fin, int id ) {
        super(date, heur_debut, heur_fin);
        patientid = id;
    }
    //objectif map objectif note

}
