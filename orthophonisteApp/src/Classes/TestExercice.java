package Classes;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TestExercice extends Test implements Serializable {
    private static final long serialVersionUID = 1L;

    private Set<Exercice> exercices = new HashSet<Exercice>();

    public TestExercice(String id, String capacite, Set<Exercice> exercices) {

        super(id,capacite);
        this.exercices = exercices;
    }
    public Set<Exercice> getExercices() {

        return exercices;
    }


    public void ajouterExercice( Exercice exercice) {

        exercices.add(exercice);

    }
    public void supprimerExercice(Exercice exercice){

        exercices.remove(exercice);
    }



    public int calculerScore() {

        return 0;
    }

}
