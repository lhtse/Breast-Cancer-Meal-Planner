/**Class Name: Calendar
 *
 * This class creates the calendar view for the application.
 */
package BCMP;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Lydia Tse
 */
public class Calendar {

    //Data fields
    private View view;
    private Controller handler;
    Meals monMeals, tuesMeals, wedMeals, thursMeals, friMeals, satMeals,
            sunMeals, chemoMeals;
    Button[] mon, tues, wed, thurs, fri, sat, sun;
    VBox[] vbArr = new VBox[] {
            new VBox(10), new VBox(10), new VBox(10), new VBox(10),
            new VBox(10), new VBox(10), new VBox(10)
    };
    Stage calStage;

    //Constructor

    /**
     * Constructor for the Calendar. Takes a View
     * object as argument.
     * @param view
     */
    public Calendar(View view) {

        this.view = view;
        this.calStage = new Stage();
        try {
            //Creating handler object
            this.handler = new Controller(view, this);

            //Creating new meal objects
            this.monMeals = new Meals();
            this.tuesMeals = new Meals();
            this.wedMeals = new Meals();
            this.thursMeals = new Meals();
            this.friMeals = new Meals();
            this.satMeals = new Meals();
            this.sunMeals = new Meals();
            this.chemoMeals = new Meals();

            //Creating button arrays
            this.mon = new Button[] {
                    new Button(monMeals.getBreakfast()),
                    new Button(monMeals.getSnack1()),
                    new Button(monMeals.getLunch()),
                    new Button(monMeals.getSnack2()),
                    new Button(monMeals.getDinner()),
            };

            this.tues = new Button[] {
                    new Button(tuesMeals.getBreakfast()),
                    new Button(tuesMeals.getSnack1()),
                    new Button(tuesMeals.getLunch()),
                    new Button(tuesMeals.getSnack2()),
                    new Button(tuesMeals.getDinner()),
            };

            this.wed = new Button[] {
                    new Button(wedMeals.getBreakfast()),
                    new Button(wedMeals.getSnack1()),
                    new Button(wedMeals.getLunch()),
                    new Button(wedMeals.getSnack2()),
                    new Button(wedMeals.getDinner()),
            };

            this.thurs = new Button[] {
                    new Button(thursMeals.getBreakfast()),
                    new Button(thursMeals.getSnack1()),
                    new Button(thursMeals.getLunch()),
                    new Button(thursMeals.getSnack2()),
                    new Button(thursMeals.getDinner()),
            };

            this.fri = new Button[] {
                    new Button(friMeals.getBreakfast()),
                    new Button(friMeals.getSnack1()),
                    new Button(friMeals.getLunch()),
                    new Button(friMeals.getSnack2()),
                    new Button(friMeals.getDinner()),
            };

            this.sat = new Button[] {
                    new Button(satMeals.getBreakfast()),
                    new Button(satMeals.getSnack1()),
                    new Button(satMeals.getLunch()),
                    new Button(satMeals.getSnack2()),
                    new Button(satMeals.getDinner()),
            };

            this.sun = new Button[] {
                    new Button(sunMeals.getBreakfast()),
                    new Button(sunMeals.getSnack1()),
                    new Button(sunMeals.getLunch()),
                    new Button(sunMeals.getSnack2()),
                    new Button(sunMeals.getDinner()),
            };


        } catch (FileNotFoundException ex) {

            //Displaying alert for exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Recipe Error");
            alert.setContentText("Oops! Couldn't find the recipe!");

            alert.showAndWait();
        }
    }

    /**
     * Definition of chemoMeals(): This method creates and
     * returns an array of Buttons.
     * @return
     */
    public Button[] chemoMeals() {

        //Creating new button array for chemo meals
        Button[] arr = new Button[] {
                new Button(chemoMeals.getChemoBreakfast()),
                new Button(chemoMeals.getChemoSnack1()),
                new Button(chemoMeals.getChemoLunch()),
                new Button(chemoMeals.getChemoSnack2()),
                new Button(chemoMeals.getChemoDinner()),
        };

        return arr;

    }

    /**
     *Definition of setHandlers(): This method sets the handlers for all of
     * the buttons associated with the calendar view in the application.
     */
    public void setHandlers() {

        //Setting Handlers
        for (int i = 0; i < 5; i++) {
            mon[i].addEventHandler(ActionEvent.ACTION, e->handler.handleActionEvent(e));
            tues[i].addEventHandler(ActionEvent.ACTION, e->handler.handleActionEvent(e));
            wed[i].addEventHandler(ActionEvent.ACTION, e->handler.handleActionEvent(e));
            thurs[i].addEventHandler(ActionEvent.ACTION, e->handler.handleActionEvent(e));
            fri[i].addEventHandler(ActionEvent.ACTION, e->handler.handleActionEvent(e));
            sat[i].addEventHandler(ActionEvent.ACTION, e->handler.handleActionEvent(e));
            sun[i].addEventHandler(ActionEvent.ACTION, e->handler.handleActionEvent(e));
        }


    }

    /**
     *Definition of showCalView(): This method displays the calendar view
     * in a new window.
     */
    public void showCalView() {
        //Creating new stage
        GridPane calendar = new GridPane();
        calendar.setPadding(new Insets(10, 20, 10, 20));

        //Getting values from datepicker
        LocalDate date = view.weekPicker.getValue();

        //Creating calendar view header
        String[] daysArr = new String[] {
                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
                "Saturday", "Sunday"
        };

        //Placing headers into respective VBox
        for (int i = 0; i < vbArr.length; i++ ) {
            //Adding proper dates to the header
            vbArr[i].getChildren().add(new Label((date.plusDays(i + 1)).toString() +
                    "\n" + daysArr[i]));
            //Formatting vboxes properly on pane
            calendar.add(vbArr[i], 2*i, 0);
        }

        //Adding Vertical Separators
        Separator[] sepArr = new Separator[] {
                new Separator(Orientation.VERTICAL),
                new Separator(Orientation.VERTICAL),
                new Separator(Orientation.VERTICAL),
                new Separator(Orientation.VERTICAL),
                new Separator(Orientation.VERTICAL),
                new Separator(Orientation.VERTICAL)
        };

        //Customizing separators
        for (int i = 0; i < sepArr.length; i++) {
            sepArr[i].setPrefHeight(900);
            sepArr[i].setPadding(new Insets(10, 20, 0, 20));
            calendar.add(sepArr[i], (2*i)+1, 0);
        }

        //Customizing meal buttons
        for (int i = 0; i < 5; i++) {
            //Customizing size of buttons
            mon[i].setMaxWidth(Double.MAX_VALUE);
            mon[i].setWrapText(true);
            mon[i].getStyleClass().add("mwfButton");

            tues[i].setMaxWidth(Double.MAX_VALUE);
            tues[i].setWrapText(true);
            tues[i].getStyleClass().add("ttButton");

            wed[i].setMaxWidth(Double.MAX_VALUE);
            wed[i].setWrapText(true);
            wed[i].getStyleClass().add("mwfButton");

            thurs[i].setMaxWidth(Double.MAX_VALUE);
            thurs[i].setWrapText(true);
            thurs[i].getStyleClass().add("ttButton");

            fri[i].setMaxWidth(Double.MAX_VALUE);
            fri[i].setWrapText(true);
            fri[i].getStyleClass().add("mwfButton");

            sat[i].setMaxWidth(Double.MAX_VALUE);
            sat[i].setWrapText(true);
            sat[i].getStyleClass().add("ttButton");

            sun[i].setMaxWidth(Double.MAX_VALUE);
            sun[i].setWrapText(true);
            sun[i].getStyleClass().add("mwfButton");

            //Adding meal buttons to vboxes
            vbArr[0].getChildren().add(mon[i]);
            vbArr[1].getChildren().add(tues[i]);
            vbArr[2].getChildren().add(wed[i]);
            vbArr[3].getChildren().add(thurs[i]);
            vbArr[4].getChildren().add(fri[i]);
            vbArr[5].getChildren().add(sat[i]);
            vbArr[6].getChildren().add(sun[i]);

        }

        //Setting the handlers for the buttons
        setHandlers();


        //Setting Scene
        Scene calScene = new Scene(calendar, 1500, 600);
        calScene.getStylesheets().add("mainCSS.css");

        //Setting Stage
        calStage.setTitle(view.nameText.getText() + "'s Meal Schedule");
        calStage.setScene(calScene);
        calStage.getIcons().add(new Image("bcribbon.png"));
        calStage.show();

    }

    /**
     * Definition of showRecipe: This method takes a String, ArrayList, and
     * ArrayList as arguments to display a new window containing the corresponding
     * recipe.
     * @param name
     * @param ingredients
     * @param directions
     */
    public void showRecipe(String name, ArrayList ingredients, ArrayList directions) {

        //Setting recipe stage
        Stage recipeStage = new Stage();

        //Creating Observable lists
        ObservableList<String> obsIngred = FXCollections.observableArrayList(ingredients);
        ObservableList<String> obsDirect = FXCollections.observableArrayList(directions);

        //BorderPane
        BorderPane recipePane = new BorderPane();
        recipePane.setPadding(new Insets(10, 10, 10, 20));

        //Setting ingredients list and adding to vbox
        VBox ingBox = new VBox(10);
        ingBox.setPadding(new Insets(20, 20, 20, 20));
        Label ingLbl = new Label("Ingredients: ");

        ListView<String> ingList = new ListView<>(obsIngred);
        ingList.setCellFactory(handler.getListCellFactory());
        ingList.setMinWidth(200);

        ingBox.getChildren().addAll(ingLbl, ingList);

        //Setting directions list and adding to vbox
        VBox dirBox = new VBox(10);
        dirBox.setPadding(new Insets(20, 20, 20, 20));
        Label dirLbl = new Label("Instructions: ");

        ListView<String> dirList = new ListView<>(obsDirect);
        dirList.setCellFactory(handler.getListCellFactory());
        dirList.setMinWidth(450);

        dirBox.getChildren().addAll(dirLbl, dirList);

        //Placing ingredients and directions boxes to border pane
        recipePane.setLeft(ingBox);
        recipePane.setCenter(dirBox);

        //Setting scene
        Scene recipeScene = new Scene(recipePane, 850, 450);
        recipeScene.getStylesheets().add("mainCSS.css");

        //Displaying recipe window
        recipeStage.setTitle(name + " Recipe");
        recipeStage.setScene(recipeScene);
        recipeStage.getIcons().add(new Image("bcribbon.png"));
        recipeStage.show();

    }

}
