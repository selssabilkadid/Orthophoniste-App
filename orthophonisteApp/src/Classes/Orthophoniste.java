import java.util.ArrayList;
import java.util.HashMap;

public class Orthophoniste {
    private final String password = "1230orth";
    private ArrayList<RendezVous> Rendezvous;
    private ArrayList<Patient> patients;
   // private ArrayList<Test> mestests;
   // set questions
   //set exercices
   // set tests
    private HashMap<Integer, Dossier> dossierMap = new HashMap<>(); 
   
    public boolean seconnecter(String username, String password){
          if (this.password == password){
            return true;
          }
          else return false;
    }
    public void ajouterDossier(Dossier dossier){
           
    }
    public void ajouterconsultation (Consultation consultation){
        Rendezvous.add(consultation);
    }
    public void ajouterpatient(Patient P){
        patients.add(P);
    }

}

