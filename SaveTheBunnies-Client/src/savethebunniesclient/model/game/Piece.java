package savethebunniesclient.model.game;

/** 
 * Piece class.
 * @author christian_gutan
 * 
 */
public abstract class Piece implements Cloneable{
	
	/**
	 * Coordinate object in which the piece is. 
	 */
	private Coordinate coord;
	/**
	 * Symbol that represents the ASCII character/symbol of the piece. 
	 */
	private Symbol symbol;
	
	/**
	 * Constructor.
	 * @param coord Coordinate object in which the piece is initially. 
	 * @param symbol Symbol element that represents an ASCII character/symbol of the piece.	 
	 */
	protected Piece(Coordinate coord, Symbol symbol) {
		this.coord = coord;
		setSymbol(symbol);
	}
			
	/**
	 * Getter of coord.
	 * @return The value of the Coordinate object (coord).
	 */
	public Coordinate getCoord() {
		return coord;
	}
	
	/**
	 * Setter of coord.
	 * @param coord Coordinate object to assign to the piece's coord attribute.  
	 */	
	public void setCoord(Coordinate coord) {
		setCoord(coord.getRow(), coord.getColumn());		
	}
	
	/**
	 * Setter of coord.
	 * @param row Index to assign to the pieces's row (attribute of coord).
	 * @param column Index to assign to the pieces's column (attribute of coord). 
	 */	
	public void setCoord(int row, int column) {		
		coord.setRow(row);
		coord.setColumn(column);
	}	
	
	/**
	 * Getter of symbol.
	 * @return Symbol that represents the ASCII character/symbol and the image of the piece.
	 */
	public Symbol getSymbol() {
		return symbol;
	}
	
	/**
	 * Setter of symbol.
	 * @param symbol Symbol that represents the ASCII character/symbol and the image to assign to the piece.
	 */
	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}
	
	/**
	 * Two objects are identical when they have the same symbol and Coordinate object.
	 * @param obj Object to compare.
	 * @return True if the two objects are identical. Otherwise, it returns false. 
	 */
	@Override
	public boolean equals(Object obj) {
		Piece piece = (Piece) obj;
		return this.getSymbol() == piece.getSymbol() && 
				this.getCoord().equals(piece.getCoord());		
	}
	
	@Override
	/**
     * Returns a String representation of the piece.
     * @return It returns the ASCII character/symbol of the piece, e.g. "M" for mushrooms.
     */
    public String toString() {
    	return getSymbol().toString();
    }
	
	public Piece clone() throws CloneNotSupportedException{
		 return (Piece)super.clone();
	}
	
	
	
	
	
}