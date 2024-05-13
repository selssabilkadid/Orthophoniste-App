package Classes;
import java.util.Set;

public class Exercice {
    private String consigne;
    private Set<String> materiels;
    public Exercice(String consigne, Set<String> materiels) {
        this.consigne = consigne;
        this.materiels = materiels;
    }
    public String getConsigne() {

        return consigne;
    }
    public void setConsigne(String consigne) {

        this.consigne = consigne;
    }
    public Set<String> getMateriels() {

        return materiels;
    }
    public void ajoutermateriel(String mat){
        materiels.add(mat);
    }
    public void supprimermateriel(String mat){
        materiels.remove(mat);
    }
    public void modifierconsigne(String text){
        consigne = text;
    }
}
