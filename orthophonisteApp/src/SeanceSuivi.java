
public class SeanceSuivi extends RendezVous {
    public SeanceSuivi(Patient patient) {
        super(patient);
    }

    @Override
    protected int calculateDuration() {
      
        return 60; 
    }
}
