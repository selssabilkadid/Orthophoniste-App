package Classes;
import java.util.Set;

public class TestExercice extends Test implements Calculable {
     Set<Exercice> exercices;

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
