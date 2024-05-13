package Classes;
public class QuestionAnamneseAdult extends Question {
    private CategorieQuestionAdult categorie;

    public QuestionAnamneseAdult(String enonce, CategorieQuestionAdult categorie) {
        super(enonce);
        this.categorie = categorie;
    }

    public CategorieQuestionAdult getCategorie() {

        return categorie;
    }
    public void setCategorie(CategorieQuestionAdult cat){
        categorie = cat ;
    }

}