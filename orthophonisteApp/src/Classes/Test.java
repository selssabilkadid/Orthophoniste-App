public abstract class Test {
    private String nom;
    private String capacite;

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
    
}
