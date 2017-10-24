/**Class Name: Meals
 *
 * This stores and generates random meals from respective maps.
 */
package BCMP;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

/**
 *
 * @author Lydia Tse
 */
public class Meals {

    //Data fields
    private Reader reader;
    private TreeMap breakfastMap;
    private TreeMap lunchMap;
    private TreeMap snackMap;
    private TreeMap dinnerMap;
    private TreeMap chemoBreakfastMap;
    private TreeMap chemoSnackMap;
    private TreeMap chemoLunchMap;
    private TreeMap chemoDinnerMap;
    private String breakfast;
    private String snack1;
    private String snack2;
    private String lunch;
    private String dinner;
    private String chemoBreakfast;
    private String chemoSnack1;
    private String chemoSnack2;
    private String chemoSnack3;
    private String chemoLunch;
    private String chemoDinner;

    /**
     * Constructor for Meals object
     * @throws FileNotFoundException
     */
    public Meals() throws FileNotFoundException {

        this.reader = new Reader();

        //Retrieving respective maps
        this.breakfastMap = reader.getBreakfast();
        this.lunchMap = reader.getLunch();
        this.snackMap = reader.getSnack();
        this.dinnerMap = reader.getDinner();
        this.chemoBreakfastMap = reader.getChemoBreakfast();
        this.chemoSnackMap = reader.getChemoSnack();
        this.chemoLunchMap = reader.getChemoLunch();
        this.chemoDinnerMap = reader.getChemoDinner();

        //Generating random meals
        this.breakfast = randomMeal(this.breakfastMap);
        this.snack1 = randomMeal(this.snackMap);
        this.snack2 = randomMeal(this.snackMap);
        this.lunch = randomMeal(this.lunchMap);
        this.dinner = randomMeal(this.dinnerMap);
        this.chemoBreakfast = randomMeal(this.chemoBreakfastMap);
        this.chemoSnack1= randomMeal(this.chemoSnackMap);
        this.chemoSnack2= randomMeal(this.chemoSnackMap);
        this.chemoSnack3= randomMeal(this.chemoSnackMap);
        this.chemoLunch= randomMeal(this.chemoLunchMap);
        this.chemoDinner= randomMeal(this.chemoDinnerMap);

    }

    /**
     * Definition of randomMeal method: generates random meals and
     * returns that meal as a String.
     * @param map
     * @return
     */
    public String randomMeal(TreeMap map) {

        Random rand = new Random();
        ArrayList<String> keys = new ArrayList<>(map.keySet());
        String randomKey = keys.get( rand.nextInt(keys.size()));

        return randomKey;
    }

    public String getBreakfast() {
        return this.breakfast;
    }

    public String getSnack1() {
        return this.snack1;
    }

    public String getLunch() {
        return this.lunch;
    }

    public String getSnack2() {
        return this.snack2;
    }

    public String getDinner() {
        return this.dinner;
    }

    public String getChemoBreakfast() {
        return this.chemoBreakfast;
    }

    public String getChemoSnack1() {
        return this.chemoSnack1;
    }

    public String getChemoLunch() {
        return this.chemoLunch;
    }

    public String getChemoSnack2() {
        return this.chemoSnack2;
    }

    public String getChemoSnack3() {
        return this.chemoSnack3;
    }

    public String getChemoDinner() {
        return this.chemoDinner;
    }

    public TreeMap getBreakfastMap() {
        return this.breakfastMap;
    }

    public TreeMap getSnackMap() {
        return this.snackMap;
    }

    public TreeMap getLunchMap() {
        return this.lunchMap;
    }

    public TreeMap getDinnerMap() {
        return this.dinnerMap;
    }

    public TreeMap getChemoBreakfastMap() {
        return this.chemoBreakfastMap;
    }

    public TreeMap getChemoSnackMap() {
        return this.chemoSnackMap;
    }

    public TreeMap getChemoLunchMap() {
        return this.chemoLunchMap;
    }
    public TreeMap getChemoDinnerMap() {
        return this.chemoDinnerMap;
    }

}
