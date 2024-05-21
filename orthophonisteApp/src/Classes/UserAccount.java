package Classes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String password;

    Set<RendezVous> Rendezvous = new HashSet<RendezVous>();
    Set<Patient> patients = new HashSet<Patient>();
    Set<Test> mes_tests = new HashSet<Test>();
    Set<Exercice> mes_exercices = new HashSet<Exercice>();
    Set<Question> mes_questions = new HashSet<Question>();
    HashMap<Integer, Dossier> dossierMap = new HashMap<>();

    public UserAccount(String firstName, String lastName, String email, String phoneNumber, String address, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getAddress() {
        return address;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPassword(String password) {
        this.password =  password;
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

