package savethebunniesclient.model;

/** 
 * Movable interface.
 * @author David García Solórzano
 * @version 1.0  
 */
public interface Movable {
	
	/**
	 * Moves the piece from the move's starting coordinate to the move's ending coordinate according to the game's rules.
	 * 
     * @param destination The object representing the destination coordinate of the movable piece.
     * @param level The level on which this move is taking place.
     * @return true if the move was successful, false if parameters are invalid or
     * the move was unsuccessful. 
     */
	public boolean move(Coordinate destination, Level level);
	
	/**
	 * Checks if the move is valid according to the game's rules.
	 * 
     * @param destination The object representing the destination coordinate of the movable piece.
     * @param level The level on which this move is taking place.
     * @return true if the move is valid, false if parameters are invalid or
     * the move is invalid. 
     */
	public boolean isValidMove(Coordinate destination, Level level) ;
}
