package savethebunniesclient.model.game;

/** 
* A Mushroom piece class.
* @author David García Solórzano
* @version 1.0 
*/
public class Mushroom extends Piece{

	/**
	 * Constructor.
	 * @param coord Coordinate in which the mushroom piece is. 	
	 */
	public Mushroom(Coordinate coord) {
		super(coord,Symbol.MUSHROOM);
	}
}