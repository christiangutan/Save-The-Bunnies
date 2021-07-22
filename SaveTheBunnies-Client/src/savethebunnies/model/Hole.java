package savethebunnies.model;

/** 
* A Hole piece class.
* @author David García Solórzano
* @version 1.0 
*/
public class Hole extends Piece{

	/**
	 * Constructor.
	 * @param coord Coordinate in which the hole piece is. 	
	 */
	public Hole(Coordinate coord) {
		super(coord, Symbol.HOLE);		
	}	

}
