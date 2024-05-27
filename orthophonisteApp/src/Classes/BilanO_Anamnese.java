package Classes;

import java.util.Set;

public class BilanO_Anamnese extends BilanO {
    private static final long serialVersionUID = 1L;
    private CRAnamnese anamnese;
    public BilanO_Anamnese(ProjetTh projet, Diagnostic diagnostique, CRAnamnese CR) {
        this.projet = projet;
        this.diagnostique = diagnostique;
        this.anamnese= CR;
    }

}
