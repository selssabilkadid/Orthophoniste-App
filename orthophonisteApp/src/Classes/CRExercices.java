package Classes;
import java.util.ArrayList;
import java.util.Set;

public class CRExercices extends CompteRendu implements Calculable {
    ArrayList<Integer> scoreExo;
    CRExercices(int scoretotal, String conclusion, Test test , ArrayList<Integer> notes) {
        super(scoretotal, conclusion, test);

    }
    public void ajouternote(int note){

        scoreExo.add(note);
    }
    public int calculerScore(){
        return 0;
    }
}
