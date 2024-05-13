package Classes;
public class Consultation extends RendezVous {
    public Consultation(Patient patient) {
        super(patient);
    }

    @Override
    protected int calculateDuration() {
        int age = getPatient().getAge();

        if (age < 18) {
            return 150; 
        } else {
            return 90; 
        }
    }
}
