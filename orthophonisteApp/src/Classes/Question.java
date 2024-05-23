package Classes;

import java.io.Serializable;

public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String enonce;

    public Question(String id,String enonce) {
        this.enonce = enonce;
        this.id = id;
    }
    public String getenonce() {
        return enonce;
    }
    public void modifierenonce(String newenonce){

        enonce = newenonce;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
