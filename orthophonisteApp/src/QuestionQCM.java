public class QuestionQCM extends Question{
    private String bonneReponse;
    private String[] propositions;
    public QuestionQCM(String enonce, String bonneReponse, String[] propositions) {
        super(enonce);
        this.bonneReponse = bonneReponse;
        this.propositions = propositions;
    }
    public String getBonneReponse() {
        return bonneReponse;
    }
    public void setBonneReponse(String bonneReponse) {
        this.bonneReponse = bonneReponse;
    }
    public String[] getPropositions() {
        return propositions;
    }
    public void setPropositions(String[] propositions) {
        this.propositions = propositions;
    }
}
