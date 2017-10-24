/**Class Name: Controller
 *
 * This class serves as the handler to all of the "View" files associated
 * with this application.
 *
 */
package BCMP;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;
/**
 *
 * @author Lydia Tse
 */
public class Controller implements EventHandler<Event>,
        ChangeListener<String> {

    //Private datafields
    private View view;
    private Calendar calView;

    /**
     * Constructor for Controller.
     * @param view
     * @param calView
     */
    public Controller(View view, Calendar calView) {
        this.view = view;
        this.calView = calView;
    }

    /**
     * Definition of handleKeyEvent: custom handler for all KeyEvents.
     * @param event
     */
    public void handleKeyEvent(KeyEvent event) {

        //Creating object to store event handler source
        Object caller = ((Event)event).getSource();

        //Handler for pressing enter on textfield
        if (view.nameText.equals(caller)) {
            if (event.getCode() == KeyCode.ENTER) {
                view.weekPicker.requestFocus();
            }
        }

        //Handler for datepicker
        if (view.weekPicker.equals(caller)) {
            if (event.getCode() == KeyCode.ENTER) {
                view.noRB.requestFocus();
            }
        }

        //Handler for yes radiobutton
        if (view.yesRB.equals(caller)) {
            if (event.getCode() == KeyCode.ENTER) {
                view.chemoDay.setDisable(false);
                view.chemoDay.requestFocus();
            }

        }
    }

    /**
     *Definition of handleActionEvent: custom handler for all ActionEvents.
     * @param event
     */
    public void handleActionEvent(ActionEvent event) {

        //Creating object to store event handler source
        Object caller = event.getSource();

        //Handler for no radiobutton
        if (view.noRB.equals(caller)) {
            if (view.noRB.isSelected()) {

                view.chemoDay.setDisable(true);

            }
        }

        //Handler for yes radiobutton
        if (view.yesRB.equals(caller)) {
            if (view.yesRB.isSelected()) {

                view.chemoDay.setDisable(false);
                view.chemoDay.requestFocus();
            }
        }

        //Handler for submit button
        if (view.submitBtn.equals(caller)) {

            //Validating text field
            if (view.nameText.getText().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Name Error");
                alert.setContentText("Oops! Don't forget to enter your name!");

                alert.showAndWait();
            }
            //Validating week picker
            else if (view.weekPicker.getValue() == null) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Date Error");
                alert.setContentText("Oops! Don't forget to pick a date!");

                alert.showAndWait();
            }
            //Validating combobox
            else if (view.yesRB.isSelected() &&
                    view.chemoDay.getSelectionModel().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Day Selection Error");
                alert.setContentText("Oops! Please select a chemo day!");

                alert.showAndWait();
            }
            else {
                //this.calView = new Calendar(view);
                calView.showCalView();
                calView.calStage.show();
            }


        }


        //Handler for monday breakfast button
        if ((calView.mon[0]).equals(caller)) {

            TreeMap map;

            if ("Monday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoBreakfastMap();
            else {
                map = calView.monMeals.getBreakfastMap();
            }

            Recipe recipe = (Recipe)map.get(calView.mon[0].getText());
            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.mon[0].getText(), ingredients, directions);
        }
        //Handler for monday snack button
        if ((calView.mon[1]).equals(caller) || (calView.mon[3]).equals(caller)) {

            TreeMap map;
            Recipe recipe;

            if ("Monday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoSnackMap();
            else {
                map = calView.monMeals.getSnackMap();
            }

            if ((calView.mon[1]).equals(caller))
                recipe = (Recipe)map.get(calView.mon[1].getText());
            else
                recipe = (Recipe)map.get(calView.mon[3].getText());

            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.mon[1].getText(), ingredients, directions);
        }

        //Handler for monday lunch button
        if ((calView.mon[2]).equals(caller)) {
            TreeMap map;

            if ("Monday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoLunchMap();
            else {
                map = calView.monMeals.getLunchMap();
            }

            Recipe recipe = (Recipe)map.get(calView.mon[2].getText());
            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.mon[2].getText(), ingredients, directions);
        }
        //Handler for monday dinner button
        if ((calView.mon[4]).equals(caller)) {
            TreeMap map;

            if ("Monday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoDinnerMap();
            else {
                map = calView.monMeals.getDinnerMap();
            }

            Recipe recipe = (Recipe)map.get(calView.mon[4].getText());
            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.mon[4].getText(), ingredients, directions);
        }

        //Handler for tuesday breakfast button
        if ((calView.tues[0]).equals(caller)) {
            TreeMap map;

            if ("Tuesday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoBreakfastMap();
            else
                map = calView.tuesMeals.getBreakfastMap();

            Recipe recipe = (Recipe)map.get(calView.tues[0].getText());
            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.tues[0].getText(), ingredients, directions);
        }

        //Handler for tuesday snack button
        if ((calView.tues[1]).equals(caller) || (calView.tues[3]).equals(caller)) {
            TreeMap map;
            Recipe recipe;

            if ("Tuesday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoSnackMap();
            else
                map = calView.tuesMeals.getSnackMap();

            if ((calView.tues[1]).equals(caller))
                recipe = (Recipe)map.get(calView.tues[1].getText());
            else
                recipe = (Recipe)map.get(calView.tues[3].getText());


            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.tues[1].getText(), ingredients, directions);
        }

        //Handler for tuesday lunch button
        if ((calView.tues[2]).equals(caller)) {
            TreeMap map;

            if ("Tuesday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoLunchMap();
            else
                map = calView.tuesMeals.getLunchMap();

            Recipe recipe = (Recipe)map.get(calView.tues[2].getText());
            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.tues[2].getText(), ingredients, directions);
        }

        //Handler for tuesday dinner button
        if ((calView.tues[4]).equals(caller)) {
            TreeMap map;

            if ("Tuesday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoDinnerMap();
            else
                map = calView.tuesMeals.getDinnerMap();

            Recipe recipe = (Recipe)map.get(calView.tues[4].getText());
            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.tues[4].getText(), ingredients, directions);
        }

        //Handler for wednesday breakfast button
        if ((calView.wed[0]).equals(caller)) {
            TreeMap map;

            if ("Wednesday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoBreakfastMap();
            else
                map = calView.wedMeals.getBreakfastMap();

            Recipe recipe = (Recipe)map.get(calView.wed[0].getText());
            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.wed[0].getText(), ingredients, directions);
        }

        //Handler for wednesday  snack button
        if ((calView.wed[1]).equals(caller) || (calView.wed[3]).equals(caller)) {
            TreeMap map;
            Recipe recipe;

            if ("Wednesday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoSnackMap();
            else
                map = calView.wedMeals.getSnackMap();

            if ((calView.wed[1]).equals(caller))
                recipe = (Recipe)map.get(calView.wed[1].getText());
            else
                recipe = (Recipe)map.get(calView.wed[3].getText());


            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.wed[1].getText(), ingredients, directions);
        }

        //Handler for wednesday lunch button
        if ((calView.wed[2]).equals(caller)) {
            TreeMap map;

            if ("Wednesday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoLunchMap();
            else
                map = calView.wedMeals.getLunchMap();

            Recipe recipe = (Recipe)map.get(calView.wed[2].getText());
            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.wed[2].getText(), ingredients, directions);
        }

        //Handler for wednesday  dinner button
        if ((calView.wed[4]).equals(caller)) {
            TreeMap map;

            if ("Wednesday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoDinnerMap();
            else
                map = calView.wedMeals.getDinnerMap();

            Recipe recipe = (Recipe)map.get(calView.wed[4].getText());
            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.wed[4].getText(), ingredients, directions);
        }

        //Handler for thursday breakfast button
        if ((calView.thurs[0]).equals(caller)) {
            TreeMap map;

            if ("Thursday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoBreakfastMap();
            else
                map = calView.thursMeals.getBreakfastMap();

            Recipe recipe = (Recipe)map.get(calView.thurs[0].getText());
            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.thurs[0].getText(), ingredients, directions);
        }
        //Handler for thursday  snack button
        if ((calView.thurs[1]).equals(caller) || (calView.thurs[3]).equals(caller)) {

            TreeMap map;
            Recipe recipe;
            if ("Thursday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoSnackMap();
            else
                map = calView.thursMeals.getSnackMap();

            if ((calView.thurs[1]).equals(caller))
                recipe = (Recipe)map.get(calView.thurs[1].getText());
            else
                recipe = (Recipe)map.get(calView.thurs[3].getText());

            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.thurs[1].getText(), ingredients, directions);
        }

        //Handler for thursday  lunch button
        if ((calView.thurs[2]).equals(caller)) {
            TreeMap map;

            if ("Thursday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoLunchMap();
            else
                map = calView.thursMeals.getLunchMap();

            Recipe recipe = (Recipe)map.get(calView.thurs[2].getText());
            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.thurs[2].getText(), ingredients, directions);
        }
        //Handler for thursday dinner button
        if ((calView.thurs[4]).equals(caller)) {
            TreeMap map;

            if ("Thursday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoDinnerMap();
            else
                map = calView.thursMeals.getDinnerMap();

            Recipe recipe = (Recipe)map.get(calView.thurs[4].getText());
            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.thurs[4].getText(), ingredients, directions);
        }

        //Handler for friday breakfast button
        if ((calView.fri[0]).equals(caller)) {
            TreeMap map;

            if ("Friday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoBreakfastMap();
            else
                map = calView.thursMeals.getBreakfastMap();

            Recipe recipe = (Recipe)map.get(calView.fri[0].getText());
            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.fri[0].getText(), ingredients, directions);
        }

        //Handler for friday  snack button
        if ((calView.fri[1]).equals(caller) || (calView.fri[3]).equals(caller)) {
            TreeMap map;
            Recipe recipe;

            if ("Friday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoSnackMap();
            else
                map = calView.thursMeals.getSnackMap();

            if ((calView.fri[1]).equals(caller))
                recipe = (Recipe)map.get(calView.fri[1].getText());
            else
                recipe = (Recipe)map.get(calView.fri[3].getText());

            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.fri[1].getText(), ingredients, directions);
        }

        //Handler for friday  lunch button
        if ((calView.fri[2]).equals(caller)) {
            TreeMap map;

            if ("Friday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoLunchMap();
            else
                map = calView.thursMeals.getLunchMap();

            Recipe recipe = (Recipe)map.get(calView.fri[2].getText());
            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.fri[2].getText(), ingredients, directions);
        }

        //Handler for friday dinner button
        if ((calView.fri[4]).equals(caller)) {
            TreeMap map;

            if ("Friday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoDinnerMap();
            else
                map = calView.thursMeals.getDinnerMap();

            Recipe recipe = (Recipe)map.get(calView.fri[4].getText());
            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.fri[4].getText(), ingredients, directions);
        }

        //Handler for saturday breakfast button
        if ((calView.sat[0]).equals(caller)) {
            TreeMap map;

            if ("Saturday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoBreakfastMap();
            else
                map = calView.thursMeals.getBreakfastMap();

            Recipe recipe = (Recipe)map.get(calView.sat[0].getText());
            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.sat[0].getText(), ingredients, directions);
        }

        //Handler for saturday  snack button
        if ((calView.sat[1]).equals(caller) || (calView.sat[3]).equals(caller)) {
            TreeMap map;
            Recipe recipe;

            if ("Saturday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoSnackMap();
            else
                map = calView.thursMeals.getSnackMap();

            if ((calView.sat[1]).equals(caller))
                recipe = (Recipe)map.get(calView.sat[1].getText());
            else
                recipe = (Recipe)map.get(calView.sat[3].getText());

            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.sat[1].getText(), ingredients, directions);
        }
        //Handler for saturday lunch button
        if ((calView.sat[2]).equals(caller)) {

            TreeMap map;

            if ("Saturday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoLunchMap();
            else
                map = calView.thursMeals.getLunchMap();

            Recipe recipe = (Recipe)map.get(calView.sat[2].getText());
            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.sat[2].getText(), ingredients, directions);
        }

        //Handler for saturday dinner button
        if ((calView.sat[4]).equals(caller)) {
            TreeMap map;

            if ("Saturday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoDinnerMap();
            else
                map = calView.thursMeals.getDinnerMap();

            Recipe recipe = (Recipe)map.get(calView.sat[4].getText());
            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.sat[4].getText(), ingredients, directions);
        }

        //Handler for sunday breakfast button
        if ((calView.sun[0]).equals(caller)) {
            TreeMap map;

            if ("Sunday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoBreakfastMap();
            else
                map = calView.thursMeals.getBreakfastMap();

            Recipe recipe = (Recipe)map.get(calView.sun[0].getText());
            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.sun[0].getText(), ingredients, directions);
        }

        //Handler for sunday snack button
        if ((calView.sun[1]).equals(caller) || (calView.sun[3]).equals(caller)) {
            TreeMap map;
            Recipe recipe;

            if ("Sunday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoSnackMap();
            else
                map = calView.thursMeals.getSnackMap();

            if ((calView.sun[1]).equals(caller))
                recipe = (Recipe)map.get(calView.sun[1].getText());
            else
                recipe = (Recipe)map.get(calView.sun[3].getText());

            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.sun[1].getText(), ingredients, directions);
        }

        //Handler for sunday lunch button
        if ((calView.sun[2]).equals(caller)) {
            TreeMap map;

            if ("Sunday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoLunchMap();
            else
                map = calView.thursMeals.getLunchMap();

            Recipe recipe = (Recipe)map.get(calView.sun[2].getText());
            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.sun[2].getText(), ingredients, directions);
        }

        //Handler for sunday dinner button
        if ((calView.sun[4]).equals(caller)) {
            TreeMap map;

            if ("Sunday".equals(view.chemoDay.getValue()))
                map = calView.chemoMeals.getChemoDinnerMap();
            else
                map = calView.thursMeals.getDinnerMap();

            Recipe recipe = (Recipe)map.get(calView.sun[4].getText());
            ArrayList ingredients = recipe.getIngredients();
            ArrayList directions = recipe.getDirections();

            calView.showRecipe(calView.sun[4].getText(), ingredients, directions);
        }
    }

    //Handles if chemo day is selected from combobox
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {


        if ("Monday".equals(view.chemoDay.getValue())) {
            calView.mon = calView.chemoMeals();

            for (int i = 0; i < calView.mon.length; i++) {
                //Customizing size of buttons
                calView.mon[i].setMaxWidth(Double.MAX_VALUE);
                calView.mon[i].setWrapText(true);
                calView.mon[i].getStyleClass().add("chemoButton");

            }
        }

        if ("Tuesday".equals(view.chemoDay.getValue())) {
            calView.tues = calView.chemoMeals();

            for (int i = 0; i < calView.mon.length; i++) {
                //Customizing size of buttons
                calView.tues[i].setMaxWidth(Double.MAX_VALUE);
                calView.tues[i].setWrapText(true);
                calView.tues[i].getStyleClass().add("chemoButton");

            }
        }

        if ("Wednesday".equals(view.chemoDay.getValue())) {
            calView.wed = calView.chemoMeals();

            for (int i = 0; i < calView.mon.length; i++) {
                //Customizing size of buttons
                calView.wed[i].setMaxWidth(Double.MAX_VALUE);
                calView.wed[i].setWrapText(true);
                calView.wed[i].getStyleClass().add("chemoButton");

            }
        }

        if ("Thursday".equals(view.chemoDay.getValue())) {
            calView.thurs = calView.chemoMeals();

            for (int i = 0; i < calView.mon.length; i++) {
                //Customizing size of buttons
                calView.thurs[i].setMaxWidth(Double.MAX_VALUE);
                calView.thurs[i].setWrapText(true);
                calView.thurs[i].getStyleClass().add("chemoButton");

            }
        }
        if ("Friday".equals(view.chemoDay.getValue())) {
            calView.fri = calView.chemoMeals();

            for (int i = 0; i < calView.mon.length; i++) {
                //Customizing size of buttons
                calView.fri[i].setMaxWidth(Double.MAX_VALUE);
                calView.fri[i].setWrapText(true);
                calView.fri[i].getStyleClass().add("chemoButton");

            }
        }

        if ("Saturday".equals(view.chemoDay.getValue())) {
            calView.sat = calView.chemoMeals();

            for (int i = 0; i < calView.mon.length; i++) {
                //Customizing size of buttons
                calView.sat[i].setMaxWidth(Double.MAX_VALUE);
                calView.sat[i].setWrapText(true);
                calView.sat[i].getStyleClass().add("chemoButton");

            }
        }

        if ("Sunday".equals(view.chemoDay.getValue())) {
            calView.sun = calView.chemoMeals();

            for (int i = 0; i < calView.mon.length; i++) {
                //Customizing size of buttons
                calView.sun[i].setMaxWidth(Double.MAX_VALUE);
                calView.sun[i].setWrapText(true);
                calView.sun[i].getStyleClass().add("chemoButton");

            }
        }

    }

    //Overriden handler is not used
    @Override
    public void handle(Event event) {



    }

    //Customies date picker popup calendar
    public Callback<DatePicker, DateCell> getDayCellFactory() {

        final Callback<DatePicker, DateCell> dayCellFactory =
                new Callback<DatePicker, DateCell>() {

                    @Override
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);

                                //Disabling all days except Sunday
                                if (item.getDayOfWeek() == DayOfWeek.MONDAY
                                        || item.getDayOfWeek() == DayOfWeek.TUESDAY
                                        || item.getDayOfWeek() == DayOfWeek.WEDNESDAY
                                        || item.getDayOfWeek() == DayOfWeek.THURSDAY
                                        || item.getDayOfWeek() == DayOfWeek.FRIDAY
                                        || item.getDayOfWeek() == DayOfWeek.SATURDAY
                                        ) {

                                    setDisable(true);
                                    setStyle("-fx-background-color: #606163");
                                }
                            }
                        };
                    }
                };

        return dayCellFactory;

    }

    //Customizes the text wrapping abilities of listview
    public Callback<ListView<String>, ListCell<String>> getListCellFactory() {

        final Callback<ListView<String>, ListCell<String>> listCellFactory =
                new Callback<ListView<String>, ListCell<String>>() {

                    @Override
                    public ListCell<String> call(final ListView<String> list) {
                        return new ListCell<String>() {
                            {
                                Text text = new Text();
                                text.wrappingWidthProperty().bind(list.widthProperty().subtract(20));
                                text.textProperty().bind(itemProperty());

                                setPrefWidth(0);
                                setGraphic(text);
                            }
                        };
                    }
                };



        return listCellFactory;
    }






}
