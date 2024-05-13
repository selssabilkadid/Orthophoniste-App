package Classes;
import java.text.DateFormat;

public class Enfant extends Patient{
    private String classeEtude;
    private String telpere;
    private String telmere;
    Enfant(String nom, String prenom, String lieu , DateFormat date, String adresse, String Classe, String telpere,String telmere){
        super(nom,prenom,lieu,date,adresse);
        classeEtude = Classe;
        this.telpere = telpere;
        this.telmere = telmere;
    }
}
