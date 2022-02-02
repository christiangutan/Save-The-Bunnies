package savethebunniesclient.model.game;

/** 
* Level Exception class.
* @author Christian_gutan
* @version 1.0 
*/
public class LevelException extends Exception{
	
	/**
	 * Error message when the size of the board is smaller than 3.
	 */
	public static final String ERROR_SIZE = "[ERROR] Board's size must be greater than 2!!";
	/**
	 * Error message when the number of minimum of moves is 0 or smaller.
	 */
	public static final String ERROR_MIN_MOVES = "[ERROR] Minimum number of moves must be at least 1!!";
	/**
	 * Error message when there are no bunnies on the board.
	 */
	public static final String ERROR_NO_BUNNIES = "[ERROR] This level does not have any bunny!!";
	/**
	 * Error message when there are no holes on the board.
	 */
	public static final String ERROR_NO_HOLES = "[ERROR] This level does not have any hole!!";
	/**
	 * Error message when there are more bunnies than holes on the board.
	 */
	public static final String ERROR_MORE_BUNNIES_THAN_HOLES = "[ERROR] This level has more bunnies than holes!!";
	/**
	 * Error message when the row index is incorrect.
	 */
	public static final String ERROR_INCORRECT_ROW = "[ERROR] This row does not exist!!";
	/**
	 * Error message when the column index is incorrect.
	 */
	public static final String ERROR_INCORRECT_COLUMN = "[ERROR] This column does not exist!!";
	/**
	 * Error message when the coordinate is incorrect.
	 */
	public static final String ERROR_COORDINATE = "[ERROR] This coordinate is incorrect!!";
	
	/**
	 * Default constructor.
	 */
	public LevelException() {
		super();
	}
	
	/**
	 * Parameterized constructor.
	 * @param msg Message which must be displayed.
	 */
	public LevelException(String msg) {
		super(msg);
	}

}