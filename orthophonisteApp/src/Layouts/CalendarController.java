package Layouts;

import Classes.*;
import Classes.Calendar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class CalendarController implements Initializable {
    ZonedDateTime dateFocus;
    ZonedDateTime today;

    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private FlowPane calendar;

    private UserAccount Orthophoniste = AccountManager.getCurrentuser();

    private Set<RendezVous> mesRDVs = Orthophoniste.getRendezvous();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();
    }

    @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    private void drawCalendar() {
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(dateFocus.getMonth().toString());

        calendar.getChildren().clear();

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        // List of activities for a given month
        Map<Integer, List<Calendar>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus, mesRDVs);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        // Check for leap year
        if (dateFocus.getYear() % 4 != 0 && monthMaxDate == 29) {
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0, 0, 0, dateFocus.getZone()).getDayOfWeek().getValue();

        // Calculate the number of rows needed to display the entire month
        int totalCells = dateOffset + monthMaxDate;
        int rows = (int) Math.ceil(totalCells / 7.0);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth = (calendarWidth / 7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight / rows) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j + 1) + (7 * i);
                if (calculatedDate > dateOffset) {
                    int currentDate = calculatedDate - dateOffset;
                    if (currentDate <= monthMaxDate) {
                        Text date = new Text(String.valueOf(currentDate));
                        date.wrappingWidthProperty().bind(stackPane.widthProperty().subtract(10));
                        date.setTranslateY(-(rectangleHeight / 2) * 0.75);
                        stackPane.getChildren().add(date);

                        List<Calendar> calendarActivities = calendarActivityMap.get(currentDate);
                        if (calendarActivities != null) {
                            createCalendarActivity(calendarActivities, rectangleHeight, rectangleWidth, stackPane);
                        }

                        // Add mouse click event to show tasks in a pop-up
                        stackPane.setOnMouseClicked(event -> showTasksPopup(currentDate, calendarActivities));

                        if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate) {
                            rectangle.setStroke(Color.BLUE);
                        }
                    }
                }
                calendar.getChildren().add(stackPane);
            }
        }
    }

    private void createCalendarActivity(List<Calendar> calendarActivities, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
        VBox calendarActivityBox = new VBox();

        if (!calendarActivities.isEmpty()) {
            Text text = new Text(calendarActivities.get(0).getClientName() + ", " + calendarActivities.get(0).getDate().toLocalTime());
            text.wrappingWidthProperty().bind(stackPane.widthProperty().subtract(10));

            // Set text color based on RDV type
            String rdvType = calendarActivities.get(0).getClientName();
            switch (rdvType) {
                case "Consultation":
                    text.setFill(Color.web("#FF5363"));
                    break;
                case "Follow Up":
                    text.setFill(Color.web("#24A8FA"));
                    break;
                case "Group Session":
                    text.setFill(Color.web("#7A6EFE"));
                    break;
                default:
                    text.setFill(Color.BLACK);
                    break;
            }

            calendarActivityBox.getChildren().add(text);
            text.setOnMouseClicked(mouseEvent -> {
                // On Text clicked
                System.out.println(text.getText());
            });

            if (calendarActivities.size() > 1) {
                Text moreActivities = new Text("...");
                calendarActivityBox.getChildren().add(moreActivities);
                moreActivities.setOnMouseClicked(mouseEvent -> {
                    // On ... click print all activities for given date
                    System.out.println(calendarActivities);
                });
            }
        }

        calendarActivityBox.setTranslateY((rectangleHeight / 2) * 0.20);
        calendarActivityBox.setMaxWidth(rectangleWidth * 0.8);
        calendarActivityBox.setMaxHeight(rectangleHeight * 0.65);
        calendarActivityBox.setStyle("-fx-background-color:#FFFFFF");
        stackPane.getChildren().add(calendarActivityBox);
    }

    private Map<Integer, List<Calendar>> createCalendarMap(List<Calendar> calendarActivities) {
        Map<Integer, List<Calendar>> calendarActivityMap = new HashMap<>();

        for (Calendar activity : calendarActivities) {
            int activityDate = activity.getDate().getDayOfMonth();
            if (!calendarActivityMap.containsKey(activityDate)) {
                calendarActivityMap.put(activityDate, new ArrayList<>(Collections.singletonList(activity)));
            } else {
                List<Calendar> oldListByDate = calendarActivityMap.get(activityDate);
                List<Calendar> newList = new ArrayList<>(oldListByDate);
                newList.add(activity);
                calendarActivityMap.put(activityDate, newList);
            }
        }
        return calendarActivityMap;
    }

    private Map<Integer, List<Calendar>> getCalendarActivitiesMonth(ZonedDateTime dateFocus, Set<RendezVous> rdvs) {
        List<Calendar> calendarActivities = new ArrayList<>();
        int year = dateFocus.getYear();
        int month = dateFocus.getMonth().getValue();

        int i = 1;
        for (RendezVous rdv : rdvs) {
            // Filter activities to only include those in the current month and year
            if (rdv.getDate().getYear() == year && rdv.getDate().getMonthValue() == month) {
                ZonedDateTime time = ZonedDateTime.of(rdv.getDate().getYear(), rdv.getDate().getMonthValue(), rdv.getDate().getDayOfMonth(), convertStringToTime(rdv.getHeur_debut()).getHour(), convertStringToTime(rdv.getHeur_debut()).getMinute(), 0, 0, dateFocus.getZone());
                calendarActivities.add(new Calendar(time, getRDVType(rdv), i));
                i++;
            }
        }

        return createCalendarMap(calendarActivities);
    }

    private LocalTime convertStringToTime(String timeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            return LocalTime.parse(timeStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format: " + timeStr);
            return null;
        }
    }

    private String getRDVType(RendezVous rdv) {
        if (rdv instanceof Consultation) {
            return "Consultation";
        } else if (rdv instanceof SeanceSuivi) {
            return "Follow Up";
        } else if (rdv instanceof AtelierGrp) {
            return "Group Session";
        } else {
            return "Other";
        }
    }

    private void showTasksPopup(int day, List<Calendar> calendarActivities) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Tasks for " + month.getText() + " " + day + ", " + year.getText());
        alert.setHeaderText(null);

        StringBuilder content = new StringBuilder();
        if (calendarActivities != null) {
            for (Calendar activity : calendarActivities) {
                content.append(activity.getClientName())
                        .append(", ")
                        .append(activity.getDate().toLocalTime())
                        .append("\n");
            }
        } else {
            content.append("No tasks for this day.");
        }

        alert.setContentText(content.toString());
        alert.showAndWait();
    }
}
