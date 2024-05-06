import java.util.ArrayList;
import java.util.HashMap;

public class Orthophoniste {
    private String username;
    private String password;
    private ArrayList<RendezVous> Rendezvous;
    private ArrayList<Patient> patients;
   // private ArrayList<Test> mestests;
    private HashMap<Integer, Dossier> dossierMap = new HashMap<>(); 
    Orthophoniste(String username, String password){
        this.username = username;
        this.password = password;
    }
    public boolean seconnecter(String username, String password){
          if (this.username == username & this.password == password){
            return true;
          }
          else return false;
    }
    public void ajouterDossier(Dossier dossier){
           
    }
    public void ajouterconsultation (Consultation consultation){
        Rendezvous.add(consultation);
    }
}

