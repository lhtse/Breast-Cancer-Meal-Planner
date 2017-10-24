/**Class Name: View
 *
 * This class serves as the main view for the application.
 */
package BCMP;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author Lydia Tse
 */
public class View extends Application{

    //Datafields
    private BorderPane mainPane = new BorderPane();
    private Calendar calView = new Calendar(this);
    private Controller handler = new Controller(this, calView);
    TextField nameText = new TextField();
    DatePicker weekPicker = new DatePicker();
    RadioButton noRB = new RadioButton("No");
    RadioButton yesRB = new RadioButton("Yes");
    ComboBox chemoDay = new ComboBox();
    Button submitBtn = new Button("Submit");

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Adding labels
        Label nameLbl = new Label("Name: ");
        Label weekLbl = new Label("Select Meal Plan Week: ");
        weekLbl.setPadding(new Insets(20, 0, 0, 0));
        Label chemoLbl = new Label("Any Chemo This Week? ");
        chemoLbl.setPadding(new Insets(20, 0, 5, 0));

        //Creating ToggleGroup for chemo
        ToggleGroup chemoToggle = new ToggleGroup();
        noRB.setSelected(true);
        noRB.setToggleGroup(chemoToggle);
        yesRB.setToggleGroup(chemoToggle);

        //Adding days to combobox
        Label chemoDayLbl = new Label("Select a Day: ");
        chemoDayLbl.setPadding(new Insets(20, 0, 0, 0));
        chemoDay.getItems().addAll("Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday", "Sunday");
        chemoDay.setDisable(true);

        //Adding handler to combobox
        chemoDay.getSelectionModel().selectedItemProperty().addListener(new Controller(this, calView));

        //Creating hbox for buttons
        HBox btnBox = new HBox(10);
        btnBox.setPadding(new Insets(20, 0, 0, 0));
        btnBox.getChildren().addAll(submitBtn);

        //Creating VBox to hold elements
        VBox infoBox = new VBox(5);
        infoBox.setPadding(new Insets(30, 30, 30, 30));
        infoBox.getChildren().addAll(nameLbl, nameText, weekLbl, weekPicker,
                chemoLbl, noRB, yesRB, chemoDayLbl, chemoDay, btnBox);

        //Handling nodes
        nameText.addEventHandler(KeyEvent.KEY_PRESSED, k -> handler.handleKeyEvent(k));
        weekPicker.addEventHandler(KeyEvent.KEY_PRESSED, k -> handler.handleKeyEvent(k));
        noRB.addEventHandler(ActionEvent.ACTION, a -> handler.handleActionEvent(a));
        yesRB.addEventHandler(ActionEvent.ACTION, a -> handler.handleActionEvent(a));
        yesRB.addEventHandler(KeyEvent.KEY_PRESSED, k -> handler.handleKeyEvent(k));

        //Handling weekpicker
        Callback<DatePicker, DateCell> cf = handler.getDayCellFactory();
        weekPicker.setDayCellFactory(cf);
        weekPicker.setEditable(false);

        //Handling submit button
        submitBtn.addEventHandler(ActionEvent.ACTION, a-> handler.handleActionEvent(a));

        //Adding nodes to borderpane
        mainPane.setCenter(infoBox);
        mainPane.setPadding(new Insets(0, 20, 0, 20));

        //Setting scene
        Scene mainScene = new Scene(mainPane, 300, 550);
        mainScene.getStylesheets().add("mainCSS.css");

        //Setting the stage
        primaryStage.setTitle("Breast Cancer Meal Planner");
        primaryStage.getIcons().add(new Image("bcribbon.png"));
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }



}
