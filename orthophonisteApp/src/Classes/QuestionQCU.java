package Classes;

import java.util.ArrayList;
import java.util.Set;

public class QuestionQCU extends Question {
    private String bonneReponse;
    private ArrayList<String> propositions;
    public QuestionQCU(String id,String enonce, String bonneReponse, ArrayList<String> propositions) {
        super(id,enonce);
        this.bonneReponse = bonneReponse;
        this.propositions = propositions;
    }
    public String getBonnesReponses() {

        return bonneReponse;
    }
    public ArrayList<String> getPropositions() {

        return propositions;
    }

    public void setPropositions(ArrayList<String> propositions) {

        this.propositions = propositions;
    }
    public void ajouterproposition(String prop){
        propositions.add(prop);
    }
    public void supprimerproposition(String prop){
        propositions.remove(prop);
    }
    public void setBonneReponse (String rep){
        bonneReponse = rep;
    }
    
    
}
