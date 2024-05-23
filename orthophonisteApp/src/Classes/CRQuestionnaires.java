package Classes;

import java.util.Set;

public class CRQuestionnaires extends CompteRendu implements Calculable {
    private static final long serialVersionUID = 1L;
    Set<Integer> notes;
    CRQuestionnaires(int scoretotal, String conclusion, Test test, Set<Integer> notes) {
        super(scoretotal, conclusion, test);
        this.notes = notes;
    }
    public void ajouternote (int note){
        notes.add(note);
    }
    public int calculerScore(){

        return 0;
    }

}
