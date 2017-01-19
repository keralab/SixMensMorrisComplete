package GameModel;

/**
 * Possible Board Error States are Defined Here.
 *
 */
public enum BoardStateError {

	TO_MANY_STONES("There is more than one stone on a Node."),
	TO_MANY_BLUE_STONES("More than 6 blue stones are on the Board"),
	TO_MANY_RED_STONES("More than 6 red stones are on the Board"),
	TO_FEW_BLUE_STONES("6+ Red stones and < 2 blue stones are on the board "),
	TO_FEW_RED_STONES("6+ Blue stones and < 2 red stones are on the board");
	private final String msg;
	
	/**
	 * Enumeration constructor, each error holds a respective message
	 * @param msg string message error corresponds to
	 */
	BoardStateError(String msg){
		this.msg = msg;
	}
	
	/**
	 * Getter for Errors' message
	 * @return String GameStateError's message
	 */
	public String getMsg(){
		return this.msg;
	}
}
