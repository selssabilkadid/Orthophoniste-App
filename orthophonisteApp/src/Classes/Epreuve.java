package Classes;
import java.io.Serializable;
import java.util.Set;

public class Epreuve implements Serializable {
    private static final long serialVersionUID = 1L;
    private String Observation;
    private Set<Test> tests ;
}
