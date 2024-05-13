package Classes;
public class Objectif {
    private String nom;
    private TypeObjectif typeObjectif;
    

    // Constructeur
    public Objectif(String nom, TypeObjectif typeObjectif) {
        this.nom = nom;
        this.typeObjectif = typeObjectif;
        // Vérification de la note
        
    }

    // Méthodes getters
    public String getNom() {
        return nom;
    }

    public TypeObjectif getTypeObjectif() {
        return typeObjectif;
    }
}
