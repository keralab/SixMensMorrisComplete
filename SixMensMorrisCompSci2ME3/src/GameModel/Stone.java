package GameModel;
/**
 * Object Instance of Stone, the pieces used in 6 Mens Morris
 */
public class Stone {
	private Team team;

	/**
	 * Construct a stone for Team t
	 * @param t - Team stone belongs to
	 * @see Team
	 */
	public Stone(Team t){
		setTeam(t);
	}

	/**
	 * Get stones Team
	 * @return - team stone belongs to
	 * @see Team
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * Set Stones Team
	 * @param t - team stone is being cast too
	 * @see Team
	 */
	public void setTeam(Team t) {
		this.team = t;
	}
	
	
	
}
