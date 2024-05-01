
public class AtelierGrp
        extends RendezVous {
    public AtelierGrp(Patient patient) {
        super(patient);
    }

    @Override
    protected int calculateDuration() {

        return 60;
    }
}
