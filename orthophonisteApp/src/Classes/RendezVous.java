package Classes;

import java.io.Serializable;
import java.time.LocalDate;

public abstract  class RendezVous implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalDate date;
    private String heur_debut;
    private String heur_fin;
    private String observation;
    RendezVous(LocalDate date, String heur_debut,String heur_fin,String observation){
        this.date = date;
        this.heur_debut =heur_debut;
        this.heur_fin =heur_fin;
        this.observation =observation;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getHeur_debut() {
        return heur_debut;
    }

    public void setHeur_debut(String heur_debut) {
        this.heur_debut = heur_debut;
    }

    public String getHeur_fin() {
        return heur_fin;
    }

    public void setHeur_fin(String heur_fin) {
        this.heur_fin = heur_fin;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
