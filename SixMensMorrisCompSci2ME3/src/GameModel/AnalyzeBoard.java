package GameModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Model for the BoardSetup type game mode, where users can place N pieces
 * anywhere, including on top of one another, the user in any placement can see
 * if a board state is valid. This is an extension to Board as it needs to
 * override the place stone function to allow conflict.
 * 
 * @see Board
 *
 */
public class AnalyzeBoard extends Board {

	private BoardStateError error;
	private List<BoardStateError> errors;

	/**
	 * Construct an Analyze Board Model, constructs an instance based on super().
	 * @see Board
	 */
	public AnalyzeBoard() {
		super();
		errors = new ArrayList<>();
	}

	/**
	 * @param s - Stone instance to be placed
	 * @param position - Position on board stone is being placed
	 * @return - boolean true stone was placed
	 * @see Stone
	 * @see BoardStateError
	 */
	@Override
	public boolean placeStone(Stone s, int position) {
		if (stonesOnBoard[position] == null) {
			stonesOnBoard[position] = s;
		} else {
			stonesOnBoard[position] = s;
			if (!errors.contains(BoardStateError.TO_MANY_STONES))
				errors.add(BoardStateError.TO_MANY_STONES);
		}

		if (s.getTeam().equals(Team.BLUE)) {
			setNumberBlueStones(getNumberBlueStones() + 1);
		} else {
			setNumberRedStones(getNumberRedStones() + 1);
		}

		return true;
	}

	/**
	 * Get all errors on the Board from its current state.
	 * @return - List<BoardStateError>: an ArrayList of errors the board currently possesses.
	 * @see BoardStateError
	 */
	public List<BoardStateError> analyzeBoard() {
		//Error if blue > 6 stones
		if (getNumberBlueStones() > 6 && !errors.contains(BoardStateError.TO_MANY_BLUE_STONES)) {
			errors.add(BoardStateError.TO_MANY_BLUE_STONES);
		}
		//Error if red > 6 stones
		if (getNumberRedStones() > 6 && !errors.contains(BoardStateError.TO_MANY_RED_STONES)) {
			errors.add(BoardStateError.TO_MANY_RED_STONES);
		}
		
		//Error if blue >= 6 stones && red < 2 stones
		if (getNumberBlueStones() >= 6 && getNumberRedStones() < 2 && !errors.contains(BoardStateError.TO_FEW_RED_STONES)) {
			errors.add(BoardStateError.TO_FEW_RED_STONES);
		}
		
		//Error if red >= 6 stones && blue < 2 stones
		if (getNumberRedStones() >= 6 && getNumberBlueStones() < 2 && !errors.contains(BoardStateError.TO_FEW_BLUE_STONES)) {
			errors.add(BoardStateError.TO_FEW_BLUE_STONES);
		}
		
		return errors;
	}

}
