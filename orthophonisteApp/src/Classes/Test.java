package Classes;

import java.io.Serializable;

public abstract class Test implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nom;
    private String capacite;
    private boolean selected ;

    Test(String nom, String capacite){
        this.nom = nom;
        this.capacite= capacite;
    }

    public void modifiernom(String name){
        nom = name;
    }
    public void modifiercapacite(String capacite){
        this.capacite = capacite;
    }
    public String getNom(){
        return nom;
    }
    public String getCapacite(){
        return capacite;
    }
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
}
