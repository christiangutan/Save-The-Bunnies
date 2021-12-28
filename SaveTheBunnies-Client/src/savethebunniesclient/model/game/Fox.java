package savethebunniesclient.model.game;

/** 
* A Fox piece class.
* @author David García Solórzano
* @version 1.0 
*/
public abstract class Fox extends Piece implements Movable{

	/**
	 * The direction the fox is facing. 
	 */
	private FoxDirection direction;
	/**
	 * Reference to the other half of the fox (head or tail).
	 */
	private Fox otherHalf;
	
	/**
	 * Constructor
	 * 
	 * @param coord Coordinate object with the position in which the fox's head is initially.
	 * @param symbol Symbol element that represents an ASCII character/symbol of the bunny.
	 * @param direction The direction the fox is facing.
	 */
	protected Fox(Coordinate coord, Symbol symbol, FoxDirection direction) {
		super(coord, symbol);
		setDirection(direction);		
	}
	
	/**
	 * Getter of the "otherHalf" field.
	 * 
	 * @return Reference/Object to the other half of the fox.
	 */
	public Fox getOtherHalf() {
		return otherHalf;
	}
	
	/**
	 * Setter of the "otherHalf" field.
	 * 
	 * @param otherHalf Object of the other half of the fox.
	 */
	protected void setOtherHalf(Fox otherHalf) {
		this.otherHalf = otherHalf;
	}	
	
	/**
	 * Getter of the "direction" field.
	 * 
	 * @return Value of the "direction" field.
	 */
	public FoxDirection getDirection() {
		return direction;
	}
	
	/**
	 * Setter of the "direction" field.
	 * 
	 * @param direction New value for the "direction" field.
	 */
	private void setDirection(FoxDirection direction) {
		this.direction = direction;
	}
}