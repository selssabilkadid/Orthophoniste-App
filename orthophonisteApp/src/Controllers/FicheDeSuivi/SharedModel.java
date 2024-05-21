package Controllers.FicheDeSuivi;

import Classes.FicheDeSuiviDone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SharedModel {
    private static final ObservableList<FicheDeSuiviDone> ficheDeSuiviDoneList = FXCollections.observableArrayList();

    public static ObservableList<FicheDeSuiviDone> getFicheDeSuiviDoneList() {
        return ficheDeSuiviDoneList;
    }
}
