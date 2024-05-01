
public class Patient {
    private String nom;
    private String prenom;
    private int age;

    // Constructor
    public Patient(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
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
