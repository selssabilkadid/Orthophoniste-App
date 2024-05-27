package Classes;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Anamnese implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id ;
    private Set<QuestionAnamneseEnfant> QuestionsEnfant = new HashSet<QuestionAnamneseEnfant>();
    private Set<QuestionAnamneseAdult> QuestionsAdult = new HashSet<QuestionAnamneseAdult>();
    public Anamnese(String id){
        this.id = id;
    }
    public void ajouterQuestioEnfant(QuestionAnamneseEnfant question){
        QuestionsEnfant.add(question);
    }
    public void retirerQuestionEnfant(QuestionAnamneseEnfant question){
        QuestionsEnfant.remove(question);
    }
    public void setQuestionsEnfant(Set<QuestionAnamneseEnfant> quest){
        QuestionsEnfant =quest;
    }
    public void setQuestionsAdult(Set<QuestionAnamneseAdult> quest){
        QuestionsAdult = quest;
    }
    public Set<QuestionAnamneseEnfant> getQuestionsEnfant(){
        return QuestionsEnfant;
    }
    public Set<QuestionAnamneseAdult> getQuestionsAdult(){
        return QuestionsAdult;
    }

    public boolean isSelected() {
        return true;
    }

    public String getNom() {
        return id;
    }
}
