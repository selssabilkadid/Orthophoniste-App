public class Exercice {
    private String consigne;
    private String[] materiels;
    public Exercice(String consigne, String[] materiels) {
        this.consigne = consigne;
        this.materiels = materiels;
    }
    public String getConsigne() {
        return consigne;
    }
    public void setConsigne(String consigne) {
        this.consigne = consigne;
    }
    public String[] getMateriels() {
        return materiels;
    }
    public void setMateriels(String[] materiels) {
        this.materiels = materiels;
    }
}
