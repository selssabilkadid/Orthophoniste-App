package Classes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class  Orthophoniste {
      UserAccount compte ;
      Set<RendezVous> Rendezvous = new HashSet<RendezVous>();
      Set<Patient> patients = new HashSet<Patient>();
      Set<Test> mes_tests = new HashSet<Test>();
      Set<Exercice> mes_exercices = new HashSet<Exercice>();
      Set<Question> mes_questions = new HashSet<Question>();
      HashMap<Integer, Dossier> dossierMap = new HashMap<>();
   
     public Orthophoniste(UserAccount compte){
         this.compte = compte;
     }
    public void ajouterDossier(Dossier dossier){
           
    }

    public void ajouternouveaupatient(Patient P){
        patients.add(P);
    }
    public void creerdossier(Patient P){
        Dossier folder = new Dossier(P);
        dossierMap.put(folder.id,folder);
    }
    public  void ajouterRDV (Dossier folder, RendezVous RDV){
        folder.ajouterRDV(RDV);
        Rendezvous.add(RDV);
    }
    public void ajouter_Consultation(Consultation consulter){
        Rendezvous.add(consulter);
    }
    public void ajouterquestion(Question Q){
        mes_questions.add(Q);
    }
    // Method to get a question by ID
    public Question getQuestionById(String id) {
        for (Question question : mes_questions) {
            if (question.getId().equals(id)) {
                return question;
            }
        }
        return null; // Return null if no exercice is found with the given ID
    }
    public Set<RendezVous> getRendezvous(){
        return Rendezvous;
    }
    public Exercice getExerciceById(String id) {
        for (Exercice exo : mes_exercices) {
            if (exo.getId().equals(id)) {
                return exo;
            }
        }
        return null; // Return null if no exercice is found with the given ID
    }
     public void ajouterTest(Test t){
        mes_tests.add(t);
     }
     public Dossier getDossierById(int id) {
        for (Map.Entry<Integer, Dossier> entry : dossierMap.entrySet()) {
            int key = entry.getKey();
            Dossier value = entry.getValue();
            if (key == id) {
                return value;
            }
        }
        return null; // Return null if no Dossier is found with the given ID
    }



}

