import java.util.ArrayList;
import java.util.Set;

public class QuestionQCU extends Question {
    private String bonneReponse;
    private Set<String> propositions;
    public QuestionQCU(String enonce, String bonneReponse, Set<String> propositions) {
        super(enonce);
        this.bonneReponse = bonneReponse;
        this.propositions = propositions;
    }
    public String getBonnesReponses() {

        return bonneReponse;
    }
    public Set<String> getPropositions() {

        return propositions;
    }

    public void setPropositions(Set<String> propositions) {

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
