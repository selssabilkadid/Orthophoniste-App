package Classes;

import java.io.Serializable;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String password;

    private Set<RendezVous> Rendezvous = new HashSet<>();
    private Set<Consultation> Consultations = new HashSet<>();
    private Set<Patient> patients = new HashSet<>();
    private Set<Test> mes_tests = new HashSet<>();
    private Set<TestQuestionnaire> mes_questionnaires = new HashSet<>();
    private Set<Exercice> mes_exercices = new HashSet<>();
    private Set<TestExercice> mes_testexercices = new HashSet<>();
    private Set<Question> mes_questions = new HashSet<>();
    private Map<Integer, Dossier> dossierMap = new HashMap<>();
    private Set<Anamnese> Anamneses = new HashSet<Anamnese>();
    private Set<BilanO> bilans = new HashSet<BilanO>();
    private Set<FicheDeSuiviDone> fichedeSuivis = new HashSet<FicheDeSuiviDone>();


    public UserAccount(String firstName, String lastName, String email, String phoneNumber, String address, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
    }

    // Getter and setter methods

    public void ajouterDossier(Dossier dossier) {

    }
    public void ajouterFichedesuivi(FicheDeSuiviDone fichedeSuivi) {
        fichedeSuivis.add(fichedeSuivi);
    }

    public void ajouternouveaupatient(Patient P) {
        patients.add(P);
    }
    public void ajouterBilan (Dossier folder, BilanO bilan) {
        folder.ajouterBO(bilan);
        if(bilans!= null){
        bilans.add(bilan);
        }
    }

    public void creerdossier(Patient P) {
        Dossier folder = new Dossier(P);
        SecureRandom secureRandom = new SecureRandom();
        int max = 9999;
        int id;

        // Generate a unique ID
        do {
            id = secureRandom.nextInt(max); // You can limit the range if needed
        } while (dossierMap.containsKey(id));
        folder.setId(id);

        dossierMap.put(id, folder);
    }


    public void ajouterRDV(Dossier folder, RendezVous RDV) {
        folder.ajouterRDV(RDV);
        Rendezvous.add(RDV);
    }

    public void ajouter_Consultation(Consultation consulter) {
        Rendezvous.add(consulter);
        Consultations.add(consulter);
    }

    public void ajouterquestion(Question Q) {
        mes_questions.add(Q);
    }

    public Question getQuestionById(String id) {
        for (Question question : mes_questions) {
            if (question.getId().equals(id)) {
                return question;
            }
        }
        return null; // Return null if no question is found with the given ID
    }
    public Dossier getDossierByPatient(Patient patient) {
        for (Dossier dossier : dossierMap.values()) {
            if (dossier.getPatient().equals(patient)) {
                return dossier;
            }
        }
        return null;
    }
    public Set<RendezVous> getRendezvous() {
        return Rendezvous;
    }

    public Exercice getExerciceById(String id) {
        for (Exercice exo : mes_exercices) {
            if (exo.getId().equals(id)) {
                return exo;
            }
        }
        return null; // Return null if no exercise is found with the given ID
    }

    public void ajouterTest(Test t) {
        mes_tests.add(t);
    }

    public Dossier getDossierById(int id) {
        return dossierMap.get(id); // Return the Dossier if found by ID
    }

    public void ajouterExercice(Exercice exo) {
        mes_exercices.add(exo);
    }

    public Set<Test> getMes_tests() {
        return mes_tests;
    }

    public void SupprimerTest(Test test) {
        mes_tests.remove(test);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Set<Question> getQuestions(){
        return mes_questions;
    }
    public Set<Exercice> getExercice(){
        return mes_exercices;
    }
    public Set<RendezVous> getRDVs(){
        return Rendezvous;
    }
    public void SupprimerRDV(RendezVous R){
        Rendezvous.remove(R);
    }

    public Set<TestQuestionnaire> getMes_questionnaires() {
        return mes_questionnaires;
    }

    public void setMes_questionnaires(Set<TestQuestionnaire> mes_questionnaires) {
        this.mes_questionnaires = mes_questionnaires;
    }

    public Set<TestExercice> getMes_testexercices() {
        return mes_testexercices;
    }

    public void setMes_testexercices(Set<TestExercice> mes_testexercices) {
        this.mes_testexercices = mes_testexercices;
    }
    public void ajouterTestQuestionnaire(TestQuestionnaire Q){
        mes_questionnaires.add(Q);
    }
    public void ajouterTestExercice(TestExercice E){
        mes_testexercices.add(E);
    }
    public Test getTestByName(String name) {
        for (Test test : mes_tests) {
            if (test.getNom().equals(name)) {
                return test;
            }
        }
        return null; // Return null if no test is found with the given name
    }
    public TestQuestionnaire getTestQuestionnaireByName(String name) {
        for (TestQuestionnaire test : mes_questionnaires) {
            if (test.getNom().equals(name)) {
                System.out.println("test Questionnaire");
                test.afficher();
                return test;

            }
        }
        return null; // Return null if no test is found with the given name
    }
    public TestExercice getTestExerciceByName(String name) {
        for (TestExercice test : mes_testexercices) {
            if (test.getNom().equals(name)) {
                return test;
            }
        }
        return null; // Return null if no test is found with the given name
    }
    public Set<Consultation> getConsultations(){
        return Consultations;
    }
    public void DeleteConsultation(Consultation C){
        Consultations.remove(C);
        Rendezvous.remove(C);
    }
    public Set<Patient> getPatients(){
        return patients;
    }
    public void ajouterAnamnese(Anamnese A){
        Anamneses.add(A);
    }
    public void supprimerAnamnese(Anamnese A){
        Anamneses.remove(A);
    }
    public void getAnamnesebyID(String ID){

    }
    public Set<Anamnese> getAnamneses(){
        return Anamneses;
    }


    public double Childrenstat() {
        int i= 0;
        if(!patients.isEmpty()) {
            for (Patient p : patients) {
                if(p.getAge()<18){
                    i++;
                }
            }
            return ((double) i /patients.size())*100;
        }
        return i;
    }

    public double AdultsStat() {
        int i= 0;
        if(!patients.isEmpty()) {
            for (Patient p : patients) {
                if(p.getAge() >= 18){
                    i++;
                }
            }
            return ((double) i /patients.size())*100;
        }
        return i;
    }

    public void supprimerPatient(Patient patient) {
        patients.remove(patient);
        Dossier folder = getDossierByPatient(patient);
        dossierMap.remove(folder.getId());
    }
}
