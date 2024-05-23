package Classes;

import java.io.Serializable;

public class Objectif implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nom;
    private TypeObjectif typeObjectif;

    // Constructeur
    public Objectif(String nom, TypeObjectif typeObjectif) {
        this.nom = nom;
        this.typeObjectif = typeObjectif;
        
    }

    // Méthodes getters
    public String getNom() {
        return nom;
    }
    

    public TypeObjectif getTypeObjectif() {
        return typeObjectif;
    }
}
