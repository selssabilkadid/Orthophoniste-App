package Classes;
public class QuestionAnamneseAdult extends Question {
    private CategorieQuestionAdult categorie;

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

}