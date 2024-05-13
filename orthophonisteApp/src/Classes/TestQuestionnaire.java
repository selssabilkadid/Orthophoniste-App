package Classes;
import java.util.ArrayList;
import java.util.Set;

public class TestQuestionnaire extends Test  {
    private Set<Question> questions;

    TestQuestionnaire(String nom , String capacite ,Set<Question> questions){
        super(nom , capacite);
        this.questions = questions ;
    }
    public void ajouterelement(Question question){

        questions.add(question);

    }
    public void supprimerelement(Question question){
        questions.remove(question);
    }



}
