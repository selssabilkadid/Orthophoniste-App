package Classes;
import java.util.ArrayList;
import java.util.Set;

public class QuestionQCM extends Question{
    private ArrayList<String> bonneReponse;
    private ArrayList<String> propositions;
    public QuestionQCM(String id,String enonce, ArrayList<String> bonneReponse, ArrayList<String> propositions) {
        super(id,enonce);
        this.bonneReponse = bonneReponse;
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
