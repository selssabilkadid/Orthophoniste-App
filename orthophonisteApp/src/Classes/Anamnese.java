package Classes;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Anamnese implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id ;
    private Set<Question> questions = new HashSet<Question>();
    Anamnese(Set<Question> questions){
        this.questions = questions;
    }
    public void ajouterQuestio(Question question){
        questions.add(question);
    }
    public void retirerQuestion(Question question){
        questions.remove(question);
    }

}
