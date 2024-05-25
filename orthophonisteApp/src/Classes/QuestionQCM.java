package Classes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

public class QuestionQCM extends Question implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<String> bonneReponse;
    private ArrayList<String> propositions;
    public QuestionQCM(String id,String enonce,  ArrayList<String> propositions) {
        super(id,enonce);
        this.propositions = propositions;
    }
    public ArrayList<String> getBonneReponse() {

        return bonneReponse;
    }
    public void setBonneReponse(ArrayList<String> bonneReponse) {

        this.bonneReponse = bonneReponse;
    }
    public ArrayList<String> getPropositions() {

        return propositions;
    }
    public void setPropositions(ArrayList<String> propositions) {

        this.propositions = propositions;
    }
    public void ajouterproposition (String prop){
        propositions.add(prop);
    }
    public void supprimerproposition(String prop){
        propositions.remove(prop);
    }
    public void ajouterbonnereponse(String rep){
        bonneReponse.add(rep);
    }
    public void supprimerreponse(String rep){
        bonneReponse.remove(rep);
    }
}
