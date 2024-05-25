package Classes;
import java.util.ArrayList;
import java.util.Set;

public class TestQuestionnaire extends Test  {
    private static final long serialVersionUID = 1L;

    private Set<Question> questions;

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

    public Set<Question> getQuestions() {
        return questions;
    }
    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }


}
