public class QuestionQCU extends Question {
    private String[] bonnesReponses;
    private String[] propositions;
    public QuestionQCU(String enonce, String[] bonnesReponses, String[] propositions) {
        super(enonce);
        this.bonnesReponses = bonnesReponses;
        this.propositions = propositions;
    }
    public String[] getBonnesReponses() {
        return bonnesReponses;
    }
    public String[] getPropositions() {
        return propositions;
    }
    public void setBonnesReponses(String[] bonnesReponses) {
        this.bonnesReponses = bonnesReponses;
    }
    public void setPropositions(String[] propositions) {
        this.propositions = propositions;
    }
    
    
}
