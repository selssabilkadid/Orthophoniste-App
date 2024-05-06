public class QuestionAnamneseEnfant extends Question {
    private CategorieQuestionEnfant categorie;

    public QuestionAnamneseEnfant(String enonce, CategorieQuestionEnfant categorie) {
        super(enonce);
        this.categorie = categorie;
    }
}
