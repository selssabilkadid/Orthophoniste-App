package Classes;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

public class SeanceSuivi extends RendezVous {
    private int patientid;
    public SeanceSuivi(LocalDate date, String heur_debut, String heur_fin,String observation ,int id ) {
        super(date, heur_debut, heur_fin, observation);
        patientid = id;
    }
    //objectif map objectif note

}
