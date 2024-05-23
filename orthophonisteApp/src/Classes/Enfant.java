package Classes;
import java.text.DateFormat;
import java.util.Date;

public class Enfant extends Patient{
    private String classeEtude;
    private String telpere;
    private String telmere;
    public Enfant(String nom, String prenom, String lieu , Date date, String adresse, String Classe, String telpere, String telmere){
        super(nom,prenom,lieu,date,adresse);
        classeEtude = Classe;
        this.telpere = telpere;
        this.telmere = telmere;
    }
    public String getClasseEtude() {
        return classeEtude;
    }
    public void setClasseEtude(String classeEtude) {
        this.classeEtude = classeEtude;
    }

    public String getTelpere() {
        return telpere;
    }
    public void setTelpere(String telpere) {
        this.telpere = telpere;
    }
    public String getTelmere() {
        return telmere;
    }
    public void setTelmere(String telmere) {
        this.telmere = telmere;
    }
    public String getAgeGroup() {
        return "Enfant";
    }

}
