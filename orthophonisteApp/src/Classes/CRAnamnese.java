package Classes;

import java.io.Serializable;
import java.util.Set;

public class CRAnamnese implements Serializable {
    private static final long serialVersionUID = 1L;
     private Anamnese anamnese;
     private Consultation consultation;
     private Set<String> reponses;

     public CRAnamnese(Anamnese anamnese, Consultation consultation,Set<String> reponses){
         this.anamnese = anamnese;
         this.consultation = consultation;
         this.reponses = reponses;
     }

    public Anamnese getAnamnese() {
        return anamnese;
    }

    public void setAnamnese(Anamnese anamnese) {
        this.anamnese = anamnese;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }
    public void SetReponses (Set<String> reponses){
         this.reponses =reponses;
    }

    public Set<String> getReponses() {
        return reponses;
    }

    public void setReponses(Set<String> reponses) {
        this.reponses = reponses;
    }
}
