package Classes;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class BilanO implements Serializable {
     private static final long serialVersionUID = 1L;
     ProjetTh projet ;
     Diagnostic diagnostique;
     Set<Test> tests = new HashSet<>();
     public BilanO(ProjetTh projet, Diagnostic diagnostique, Set<Test> tests) {
         this.projet = projet;
         this.diagnostique = diagnostique;
         this.tests = tests;
     }
     public BilanO() {

     }
     public ProjetTh getProjet() {
         return projet;
     }
     public void setProjet(ProjetTh projet) {
         this.projet = projet;
     }
     public Diagnostic getDiagnostique() {
         return diagnostique;
     }
     public void setDiagnostique(Diagnostic diagnostique) {
         this.diagnostique = diagnostique;
     }
     public Set<Test> getTests() {
         return tests;
     }
     public void setTests(Set<Test> tests) {
         this.tests = tests;
     }


}
