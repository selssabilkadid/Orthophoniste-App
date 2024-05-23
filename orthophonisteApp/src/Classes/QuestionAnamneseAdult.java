package Classes;
public class QuestionAnamneseAdult extends Question {
    private static final long serialVersionUID = 1L;

    private CategorieQuestionAdult categorie;
    private String reponse;

    public QuestionAnamneseAdult(String id,String enonce, CategorieQuestionAdult categorie) {
        super(id,enonce);
        this.categorie = categorie;
    }

    public CategorieQuestionAdult getCategorie() {

        return categorie;
    }
    public void setCategorie(CategorieQuestionAdult cat){

        categorie = cat ;
    }
    public void ajouterReponse(String reponse){
        this.reponse = reponse;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
}