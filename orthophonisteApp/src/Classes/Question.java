package Classes;
public class Question {
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
} 
