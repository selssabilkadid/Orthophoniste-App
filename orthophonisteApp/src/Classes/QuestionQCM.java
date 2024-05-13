package Classes;
import java.util.Set;

public class QuestionQCM extends Question{
    private Set<String> bonneReponse;
    private Set<String> propositions;
    public QuestionQCM(String enonce, Set<String> bonneReponse, Set<String> propositions) {
        super(enonce);
        this.bonneReponse = bonneReponse;
        this.propositions = propositions;
    }
    public Set<String> getBonneReponse() {

        return bonneReponse;
    }
    public void setBonneReponse(Set<String> bonneReponse) {

        this.bonneReponse = bonneReponse;
    }
    public Set<String> getPropositions() {

        return propositions;
    }
    public void setPropositions(Set<String> propositions) {

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
