public class QuestionAnamneseAdult extends Question {
    private CategorieQuestionAdult categorie;

    public QuestionAnamneseAdult(String enonce, CategorieQuestionAdult categorie) {
        super(enonce);
        this.categorie = categorie;
    }

    public CategorieQuestionAdult getCategorie() {
        return categorie;
    }

}