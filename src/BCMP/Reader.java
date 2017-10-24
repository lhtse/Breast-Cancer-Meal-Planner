/**Class Name: Reader
 *
 * This class reads and parses the respective meal files and stores into map.
 *
 */
package BCMP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import javafx.scene.control.Alert;

/**
 *
 * @author Lydia Tse
 */
public class Reader {

    //Private data fields
    private File breakfast = new File("breakfast.csv");
    private File lunch = new File("lunch.csv");
    private File dinner = new File("dinner.csv");
    private File snack = new File("snack.csv");
    private File chemoBreakfast = new File("chemoBreakfast.csv");
    private File chemoSnacks = new File("chemoSnacks.csv");
    private File chemoLunch = new File("chemoLunch.csv");
    private File chemoDinner = new File("chemoDinner.csv");
    private TreeMap breakfastMap;
    private TreeMap lunchMap;
    private TreeMap dinnerMap;
    private TreeMap snackMap;
    private TreeMap chemoBreakfastMap;
    private TreeMap chemoSnackMap;
    private TreeMap chemoLunchMap;
    private TreeMap chemoDinnerMap;

    public Reader() {

        try {
            //Parsing and storing files into map
            this.breakfastMap = storeRecipes(this.breakfast);
            this.lunchMap = storeRecipes(this.lunch);
            this.dinnerMap = storeRecipes(this.dinner);
            this.snackMap = storeRecipes(this.snack);
            this.chemoBreakfastMap = storeRecipes(this.chemoBreakfast);
            this.chemoSnackMap = storeRecipes(this.chemoSnacks);
            this.chemoLunchMap = storeRecipes(this.chemoLunch);
            this.chemoDinnerMap = storeRecipes(this.chemoDinner);

        } catch (FileNotFoundException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Recipe Error");
            alert.setContentText("Oops! Couldn't find the recipe!");

            alert.showAndWait();
        }

    }

    public TreeMap getBreakfast() {
        return this.breakfastMap;
    }

    public TreeMap getLunch() {
        return this.lunchMap;
    }

    public TreeMap getDinner() {
        return this.dinnerMap;
    }

    public TreeMap getSnack() {
        return this.snackMap;
    }

    public TreeMap getChemoBreakfast() {
        return this.chemoBreakfastMap;
    }

    public TreeMap getChemoSnack() {
        return this.chemoSnackMap;
    }

    public TreeMap getChemoLunch() {
        return this.chemoLunchMap;
    }

    public TreeMap getChemoDinner() {
        return this.chemoDinnerMap;
    }

    /**
     * Definition: this method parses file and stores ingredients and
     * recipes into respective containers.
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public TreeMap storeRecipes(File file) throws FileNotFoundException {

        //Creating scanner to read the file
        Scanner s = new Scanner(file);

        //Creating TreeMap
        TreeMap<String, Recipe> treeMap = new TreeMap<>();

        //Reading and storing recipe names from file
        while (s.hasNext()) {

            //Storing the line from file into a string
            String line = s.nextLine().replaceAll("\"", "");
            String[] strArr = line.split("[$]");

            if (!(strArr[0].matches("Name"))) {

                //Creating ArrayList to store ingredients/instructions
                ArrayList<String> ingredients = new ArrayList<>();
                ArrayList<String> instructions = new ArrayList<>();

                //Storing ingredients and instructions into Recipe object
                for (int j = 1; j < strArr.length; j++) {

                    if (strArr[j].matches("\\d+\\.[^.]*")) {
                        instructions.add(strArr[j]);
                    }
                    else {
                        ingredients.add(strArr[j]);
                    }


                    //Creating recipe object
                    Recipe recipe = new Recipe(ingredients, instructions);

                    //Placing values into thre treeMap
                    treeMap.put(strArr[0], recipe);

                }

            }

        }


        return treeMap;
    }


}
