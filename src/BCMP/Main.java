/** Class Name: Main
 *
 * This class launches the application.
 */
package BCMP;

import java.io.FileNotFoundException;


/**
 *
 * @author Lydia Tse
 */
public class Main extends View {


    public Main() throws FileNotFoundException{

    }
    /**
     *
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        launch(args);

        View view = new View();
        Calendar calView = new Calendar(view);
        Controller handler = new Controller(view, calView);

    }

}
