import java.text.DateFormat;

public class Patient {
    private String nom;
    private String prenom;
    private int age;
    private DateFormat datenaissance;
    private String lieunaissance;
    private String adresse;

    // Constructor
    public Patient(String nom, String prenom, String lieu , DateFormat date, String adresse) {
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

    public void setprenom(String prenom) {
        this.prenom = prenom;
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
}
