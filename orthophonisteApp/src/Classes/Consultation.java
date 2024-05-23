package Classes;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

public class Consultation extends RendezVous {
    private static final long serialVersionUID = 1L;

    private String nom ;
    private String prenom;
    private String age;
    public Consultation(LocalDate date, String heur_debut, String heur_fin,String observation ,String nom, String prenom, String age) {
        super(date, heur_debut, heur_fin,observation);
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
