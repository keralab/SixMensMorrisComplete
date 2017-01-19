
package GameModel;

/**
 * 
 * Enumerations for values that need to be written/opened in a file
 *
 */

public enum Attributes {
	CURRENT_TURN("Current"),
	STONES_BOARD("StonesOnBoard"),
	TURN_COUNT("Turn Counter");
	
	private String att;
	
	/**
	 * Sets the string
	 * @param att - sets the message the enum has
	 */
	Attributes(String att){
		this.att = att;
	}
	
	/**
	 * Gets the string from the Enum
	 * @return
	 */

	public String getAtt(){
		return att;
	}
		
	
}
