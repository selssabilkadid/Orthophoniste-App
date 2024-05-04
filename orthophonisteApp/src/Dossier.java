import java.util.ArrayList;

public class Dossier {
    private int id;
    private Patient info_personnel;
    private ArrayList<FichedeSuivi> fichedeSuivis;
    private ArrayList<RendezVous> rendezvous;
    //private ArrayList<BilanO> Bilans;
    Dossier(int id, Patient patient){
        this.id = id;
        info_personnel = patient;
    }
}
