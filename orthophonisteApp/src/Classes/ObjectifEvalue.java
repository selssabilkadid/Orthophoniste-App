package Classes;
import java.util.ArrayList;

public class ObjectifEvalue extends Objectif {
    private ArrayList<Integer> notes;
    ObjectifEvalue(String nom, TypeObjectif typeObjectif, ArrayList<Integer> notes ){
        super(nom, typeObjectif);
        this.notes = notes;
    }
   public void ajouternote(int note){
    if (note < 1 || note > 5) {
        throw new IllegalArgumentException("La note doit être comprise entre 1 et 5.");
    }
     notes.add(note);
   }
}
