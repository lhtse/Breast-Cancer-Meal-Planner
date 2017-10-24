/**Class Name: Recipe
 *
 * This class holds the ingredients and instructions for meals read from
 * Reader class.
 */
package BCMP;

import java.util.ArrayList;

/**
 *
 * @author Lydia Tse
 */
public class Recipe {

    //Private Data fields
    private ArrayList<String> ingredients = new ArrayList<>();
    private ArrayList<String> instructions = new ArrayList<>();

    /**
     * Default Constructor for Recipe object
     */
    public Recipe() {

        this.ingredients = null;
        this.instructions = null;
    }

    /**
     * Constructor for Recipe object
     * @param i
     * @param d
     */
    public Recipe(ArrayList i, ArrayList d) {

        this.ingredients = i;
        this.instructions = d;

    }

    //Getter for ingredients list
    public ArrayList<String> getIngredients() {

        return this.ingredients;
    }

    //Getter for directions list
    public ArrayList<String> getDirections() {

        return this.instructions;
    }
}
