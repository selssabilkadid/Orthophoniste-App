package Classes;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

public class SeanceSuivi extends RendezVous {
    private static final long serialVersionUID = 1L;
    boolean online ;
    private int patientid;
    public SeanceSuivi(LocalDate date, String heur_debut, String heur_fin,String observation ,int id , boolean online) {
        super(date, heur_debut, heur_fin, observation);
        this.online = online;
        patientid = id;
    }
    //objectif map objectif note

}
