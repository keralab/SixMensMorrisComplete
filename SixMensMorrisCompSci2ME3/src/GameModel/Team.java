package GameModel;

/**
 * Enumeration for Team Color
 *
 */
public enum Team {
	RED("Red"),
	BLUE("Blue");
	
private final String msg;


	/**
	 * Sets the team message
	 * @param msg - message the team has
	 */
	Team(String msg){
		this.msg = msg;
	}
	
	/**
	 * Gets the team message
	 * @return - the message of the team
	 */
	public String getMsg(){
		return this.msg;
	}
	
	/**
	 * Takes the message and returns the team the message belongs to
	 * @param msg - takes the message of the team
	 * @return - returns the team the message belongs to
	 */


	public static Team getTeam(String msg){
		
		if(msg.compareTo(BLUE.msg) == 0){
			return Team.BLUE;
			
		}else { 
			return Team.RED;
		}
		
		
	}
}
