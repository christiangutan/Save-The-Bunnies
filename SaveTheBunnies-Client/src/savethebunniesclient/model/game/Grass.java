package savethebunniesclient.model.game;

/** 
* A Grass piece class.
* @author David García Solórzano
* @version 1.0 
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
