package Classes;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Dossier implements Serializable {
    private static final long serialVersionUID = 1L;
     static int  id;
     Patient patient;
     Set<FichedeSuivi> fichedeSuivis = new HashSet<FichedeSuivi>();
     Set<RendezVous> rendezvous = new HashSet<RendezVous>();
     Set<BilanO> Bilans = new HashSet<BilanO>();
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
    
}
