package Classes;
import java.util.ArrayList;

public class ObjectifEvalue extends Objectif {
    private int note;
    public ObjectifEvalue(String nom, TypeObjectif typeObjectif, int note){
        super(nom, typeObjectif);
        this.note = note;
    }

   public int getNote() {
        return note;
   }
}
