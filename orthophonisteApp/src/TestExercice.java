public class TestExercice {
    private Exercice[] exercices;

    public TestExercice( Exercice[] exercices) {
        this.exercices = exercices;
    }
    public Exercice[] getExercices() {
        return exercices;
    }
    public void setExercices(Exercice[] exercices) {
        this.exercices = exercices;
    }
    public Exercice getExercice(int i) {
        return exercices[i];
    }
    public void setExercice(int i, Exercice exercice) {
        exercices[i] = exercice;
    }
    
}
