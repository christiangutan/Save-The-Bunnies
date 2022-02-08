package savethebunniesclient.model.game;

/** 
* A Hole piece class.
* @author christian_gutan
* 
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
