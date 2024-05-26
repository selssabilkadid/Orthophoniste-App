package Classes;

import java.io.Serializable;

public class QuestionAnamneseEnfant extends Question implements Serializable {
    private static final long serialVersionUID = 1L;

    private CategorieQuestionEnfant categorie;
    private String reponse;

    public QuestionAnamneseEnfant(String id,String enonce, CategorieQuestionEnfant categorie) {
        super(id,enonce);
        this.categorie = categorie;
    }

    public CategorieQuestionEnfant getCategorie() {

        return categorie;
    }

    public void setCategorie(CategorieQuestionEnfant categorie) {
        this.categorie = categorie;
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

