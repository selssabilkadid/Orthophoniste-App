package Classes;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
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
        this.age = calculateAge(date);

    }
    public String getAgeGroup() {
        if (age <= 18) {
            return "Child";
        } else if (age <= 60) {
            return "Adult";
        } else {
            return "Senior";
        }
    }
    // Method to calculate age based on date of birth
    private int calculateAge(Date birthDate) {
        if (birthDate == null) {
            return 0;
        }
        LocalDate birthLocalDate = convertToLocalDateViaInstant(birthDate);
        LocalDate currentDate = LocalDate.now();
        if (birthLocalDate != null && !birthLocalDate.isAfter(currentDate)) {
            return Period.between(birthLocalDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    // Helper method to convert java.util.Date to java.time.LocalDate
    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
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
