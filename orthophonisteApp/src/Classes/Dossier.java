package Classes;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Dossier implements Serializable {
    private static final long serialVersionUID = 1L;
    private int  id;
    private Patient patient;
    private Set<FichedeSuivi> fichedeSuivis = new HashSet<FichedeSuivi>();
    private  Set<RendezVous> rendezvous = new HashSet<RendezVous>();
    private Set<BilanO> Bilans = new HashSet<BilanO>();
    Dossier(Patient patient){
        this.patient = patient;
        id ++;
    }
    public void ajouterRDV (RendezVous RDV){
        rendezvous.add(RDV);
    }
    public void annulerRDV(RendezVous RDV){
        rendezvous.remove(RDV);
    }
    public void ajouterBO(BilanO BO){
        Bilans.add(BO);
    }
    public void supprimerBO (BilanO BO){
        Bilans.remove(BO);
    }
    public void ajouterfichedesuivi(FichedeSuivi fiche){
        fichedeSuivis.add(fiche);
    }
    public void supprimerfiche (FichedeSuivi fiche){
        fichedeSuivis.remove(fiche);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    public String getId() {
        Integer i = id;

        return i.toString();
    }
    public void setId(int id) {
        this.id = id;
    }
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public Set<FichedeSuivi> getFichedeSuivis() {
        return fichedeSuivis;
    }
    public void setFichedeSuivis(Set<FichedeSuivi> fichedeSuivis) {
        this.fichedeSuivis = fichedeSuivis;
    }
    public Set<RendezVous> getRendezvous() {
        return rendezvous;
    }
    public void setRendezvous(Set<RendezVous> rendezvous) {
        this.rendezvous = rendezvous;
    }
    public Set<BilanO> getBilans() {
        return Bilans;
    }
    public void setBilans(Set<BilanO> bilans) {
        Bilans = bilans;
    }

}
