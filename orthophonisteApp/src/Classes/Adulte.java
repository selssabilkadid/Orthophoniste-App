package Classes;
import java.text.DateFormat;

public class Adulte extends Patient{
    private static final long serialVersionUID = 1L;

    String diplome; 
    String profession ;
    String numtel;
    Adulte(String nom, String prenom, String lieu , DateFormat date, String adresse, String diplome, String profession, String numtel){
        super(nom, prenom, lieu, date, adresse);
        this.diplome = diplome;
        this.profession =profession;
        this.numtel = numtel;
    }
    
}
