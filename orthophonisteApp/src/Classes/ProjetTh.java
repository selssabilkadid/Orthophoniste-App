package Classes;

import java.io.Serializable;

public class ProjetTh implements Serializable {
    private static final long serialVersionUID = 1L;
    private String projet;
    
    public ProjetTh(String projet){
        this.projet = projet ;
    }

    public String getProjet() {
        return projet;
    }
    public void setProjet(String projet) {
        this.projet = projet;
    }

}
