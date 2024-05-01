
public abstract class RendezVous {
    private int duration;
    private String observation;
    private Patient patient;

    // Constructor
    public RendezVous(Patient patient) {
        this.patient = patient;
        this.duration = calculateDuration();
    }

    // Abstract method to be implemented by subclasses
    protected abstract int calculateDuration();

    // Getters and setters
 

    public int getDuration() {
        return duration;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
