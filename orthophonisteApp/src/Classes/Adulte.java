package Classes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Adulte extends Patient{
    String diplome; 
    String profession ;
    String numtel;
    public Adulte(String nom, String prenom, String lieu , Date date, String adresse, String diplome, String profession, String numtel){
        super(nom, prenom, lieu, date, adresse);
        this.diplome = diplome;
        this.profession =profession;
        this.numtel = numtel;
    }


    public String getAgeGroup() {
        return "Adulte";
    }





    public String getNumTel() {
        return numtel;
    }
    public void setNumTel(String numtel) {
        this.numtel = numtel;
    }
    public String getProfession() {
        return profession;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }


    
}
