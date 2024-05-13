import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;

public class Objectif {
    private String nom;
    private TypeObjectif typeObjectif;
    private int note;

    // Constructeur
    public Objectif(String nom, TypeObjectif typeObjectif, int note) {
        this.nom = nom;
        this.typeObjectif = typeObjectif;
        // Vérification de la note
        if (note < 1 || note > 5) {
            throw new IllegalArgumentException("La note doit être comprise entre 1 et 5.");
        }
        this.note = note;
    }

    // Méthodes getters
    public String getNom() {
        return nom;
    }

    public TypeObjectif getTypeObjectif() {
        return typeObjectif;
    }

    public int getNote() {
        return note;
    }
    
     public ObservableValue<String> getNomObservable() {
        return new ReadOnlyStringWrapper(nom);
    }

    public ObservableValue<TypeObjectif> getTypeObjectifObservable() {
        return new ReadOnlyObjectWrapper<>(typeObjectif);
    }

    public ObservableValue<Integer> getNoteObservable() {
        return new ReadOnlyObjectWrapper<>(note);
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTypeObjectif(TypeObjectif typeObjectif) {
        this.typeObjectif = typeObjectif;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
