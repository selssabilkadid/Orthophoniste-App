package Classes;
public class Question {
    private String enonce;

    public Question(String enonce) {
        this.enonce = enonce;
    }
    public String getenonce() {
        return enonce;
    }
    public void modifierenonce(String newenonce){
           enonce = newenonce;
    }
} 
