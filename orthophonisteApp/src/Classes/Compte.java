package Classes;

public class Compte {
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String password;
    Compte(String nom, String prenom, String email, String adresse, String password){
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public String getEmail(){
        return email;
    }

}
