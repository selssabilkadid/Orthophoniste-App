package Classes;
public class QuestionAnamneseEnfant extends Question {
    private CategorieQuestionEnfant categorie;

    public QuestionAnamneseEnfant(String enonce, CategorieQuestionEnfant categorie) {
        super(enonce);
        this.categorie = categorie;
    }

    public CategorieQuestionEnfant getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieQuestionEnfant categorie) {
        this.categorie = categorie;
    }
}
