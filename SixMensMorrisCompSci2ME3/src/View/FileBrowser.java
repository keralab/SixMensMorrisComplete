package View;


import java.io.File;
import javax.swing.JFileChooser;


/**
 * 
 * Sets up the file browser so the user can save or open a file at a specific location 
 *
 */

public class FileBrowser extends JFileChooser {
	private int userSelection;
	private JFileChooser chooseLocation;
	private File file;
	private String userDataFile;
	
	/**
	 * 
	 * @param choice - determines the button name as  "Save" or "Open"
	 * @return - userDataFile, the location at which the user would like to save or open a file for
	 * 							their game progress
	 */
	public String FileBox(String userChoice) {
		// opens up a dialog box so the user can find the correct place they would like to
		// save or open up the file
		// returns the location so it can write or read accordingly in the model class
		chooseLocation = new JFileChooser();

		chooseLocation.setDialogTitle("File Browser");
		userSelection = chooseLocation.showDialog(chooseLocation, userChoice);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			file = chooseLocation.getSelectedFile();
			userDataFile = file.getAbsolutePath();
			
		}
		return userDataFile;
	}
}






