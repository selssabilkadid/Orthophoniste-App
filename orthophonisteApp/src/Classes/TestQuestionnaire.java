package Classes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TestQuestionnaire extends Test implements Serializable {
    private static final long serialVersionUID = 1L;

    private Set<Question> questions ;

    public TestQuestionnaire(String nom , String capacite ,Set<Question> questions){
        super(nom , capacite);
        this.questions = questions ;
    }
    public void ajouterelement(Question question){

        questions.add(question);

    }
    public void supprimerelement(Question question){
        questions.remove(question);
    }

    public Set<Question> getQuestions(){
        return questions;
    }
    public void afficher() {
        System.out.println("Liste des questions pour le test " + getNom() + ":");
        for (Question question : this.questions) {
            System.out.println(" - " + question.getId() + ": " + question.getenonce());
        }
    }

}
