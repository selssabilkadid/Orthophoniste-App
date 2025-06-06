package Classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

public class QuestionQCU extends Question implements Serializable {
    private static final long serialVersionUID = 1L;

    private String bonneReponse;
    private ArrayList<String> propositions;
    public QuestionQCU(String id,String enonce, ArrayList<String> propositions) {
        super(id,enonce);
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
