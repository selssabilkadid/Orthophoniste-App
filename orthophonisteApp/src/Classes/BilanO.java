package Classes;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class BilanO implements Serializable {
     private static final long serialVersionUID = 1L;
     ProjetTh projet ;
     Diagnostic diagnostique;
     Set<Test> tests = new HashSet<>();

}
