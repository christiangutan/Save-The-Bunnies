package savethebunniesclient.model.game;

/** 
* A Grass piece class.
* @author christian_gutan
*
*/
public class Grass extends Piece{
	
	/**
	 * Constructor.
	 * @param coord Coordinate in which the grass piece is. 	
	 */
	public Grass(Coordinate coord) {
		super(coord, Symbol.GRASS);		
	}	
}
