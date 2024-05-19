package Classes;

import java.util.HashMap;
import java.util.Set;

public class  Orthophoniste {
    private Compte moncompte;
    private Set<RendezVous> Rendezvous;
    private Set<Patient> patients;
     static Set<Test> mes_tests;
     static Set<Exercice> mes_exercices;
     static Set<Question> mes_questions;
    private HashMap<Integer, Dossier> dossierMap = new HashMap<>(); 
   
    public boolean seconnecter(String password, String email){
          if (moncompte.getPassword().equals(password) || moncompte.getEmail().equals(email)){
            return true;
          }
          else return false;
    }
    public void ajouterDossier(Dossier dossier){
           
    }
    public void ajouterconsultation (Consultation consultation){

        Rendezvous.add(consultation);
    }
    public void ajouternouveaupatient(Patient P){

        patients.add(P);
    }
    public void creerdossier(Patient P){
        Dossier folder = new Dossier(P);
        dossierMap.put(folder.id,folder);
    }
    public void ajouterRDV (Dossier folder, RendezVous RDV){
        folder.ajouterRDV(RDV);
        Rendezvous.add(RDV);
    }
    public static void ajouterquestion(Question Q){
        mes_questions.add(Q);
    }

}

