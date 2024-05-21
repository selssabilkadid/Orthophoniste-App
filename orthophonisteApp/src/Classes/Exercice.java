package Classes;
import java.util.Set;

public class Exercice {
    private String id;
    private String consigne;
    private Set<String> materiels;
    public Exercice(String id,String consigne, Set<String> materiels) {
        this.id = id;
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

    public String getId() {
        return id;
    }
}
