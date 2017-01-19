package Application;

import GameController.Controller;
import View.ApplicationView;
/**
 * This class contains the programs main function, performing the
 * following tasks:
 * <ul>
 * <li> Create the main application view</li>
 * <li> Create the controller for the application and link it to the view</li>
 * </ul>
 * 
 */
public class Application {
	/**
	 * The application's main function instantiates an Application View,
	 * and links it to a Controller.
	 * @param args - Java input arguments, unused
	 * @see ApplicationView
	 * @see Controller
	 */
	public static void main(String args[]){
		ApplicationView view = new ApplicationView();
		Controller controller = new Controller(view);
	}
}
