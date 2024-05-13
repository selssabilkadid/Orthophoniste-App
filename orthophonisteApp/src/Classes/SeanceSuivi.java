package Classes;
public class SeanceSuivi extends RendezVous {
    //objectif map objectif note
    public SeanceSuivi(Patient patient) {
        super(patient);
    }

    @Override
    protected int calculateDuration() {
      
        return 60; 
    }
}
