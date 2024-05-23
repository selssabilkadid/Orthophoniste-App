package Classes;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class Patient implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nom;
    private String prenom;
    private int age;
    private ArrayList<Trouble> troubles ;
    private Date datenaissance;
    private String lieunaissance;
    private String adresse;

    // Constructor
    public Patient(String nom, String prenom, String lieu , Date date, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        lieunaissance =lieu;
        datenaissance = date;
        this.adresse = adresse ;
    }

    // Getter and setter methods
    public String getnom() {
        return nom;
    }

    public void setnom(String nom) {
        this.nom = nom;
    }

    public String getprenom() {
        return prenom;
    }

//    public DateFormat getDatenaissance() {
//        return datenaissance;
//    }
    public String getDateNaissanceString() {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(datenaissance);
    }

    public void setprenom(String prenom) {
        this.prenom = prenom;
    }
    public String getFullname() {
        return getnom() + " " + getprenom();
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ",  age=" + age +
                '}';
    }
    public void ajouterTrouble( Trouble t){
        troubles.add(t);
    }
    public String getLieuNaissance() {
        return lieunaissance;
    }
    public void setLieuNaissance(String lieunaissance) {
        this.lieunaissance = lieunaissance;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }
    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }
    public ArrayList<Trouble> getTroubles() {
        return troubles;
    }
    public void setTroubles(ArrayList<Trouble> troubles) {
        this.troubles = troubles;
    }


}
