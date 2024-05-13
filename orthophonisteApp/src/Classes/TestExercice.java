package Classes;
import java.util.Set;

public class TestExercice implements Calculable {
     Set<Exercice> exercices;

    public TestExercice( Set<Exercice> exercices) {

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
