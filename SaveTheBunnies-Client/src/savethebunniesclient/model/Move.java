package savethebunnies.model;

/**
 * This class represents the move coordinates for the game. Since move will be
 * used in multiple classes (mainly movable pieces) it is easier to represent the start and end points of
 * a move using one object.
 * 
 * @author David García Solórzano
 */
public class Move {
	/**
	 * Coordinate object of cell where the move starts.
	 */
	private Coordinate coordStart;
	/**
	 * Coordinate object of the cell in which the move ends. 
	 */
	private Coordinate coordEnd;
	
	/**
	 * Constructor
	 * 
	 * @param xStart Row's starting cell.
	 * @param yStart Column's starting cell.
	 * @param xEnd Row's ending cell.
	 * @param yEnd Column's ending cell.
	 */
	public Move(int xStart, int yStart, int xEnd, int yEnd) {
		this(new Coordinate(xStart,yStart), new Coordinate(xEnd, yEnd));		
	}
	
	/**
	 * Constructor
	 * 
	 * @param coordStart Starting cell
	 * @param coordEnd Ending cell
	 */
	public Move(Coordinate coordStart, Coordinate coordEnd) {
		this.coordStart = coordStart;
		this.coordEnd = coordEnd;
	}	
	
	/**
	 * Getter of "coordStart".
	 * @return The value of the "coordStart" attribute, i.e. starting coordinate.
	 */
	public Coordinate getStart() {
		return coordStart;
	}
	
	/**
	 *Setter of "coordStart".
	 *@param start The new value of "coordStart" attribute, i.e. starting coordinate.
	 */
	public void setStart(Coordinate start) {
		coordStart = start;
	}
	
	/**
	 * Getter of coordStart's row. 
	 * @return Row of the starting coordinate.
	 */
	public int getRowStart() {
		return coordStart.getRow();
	}

	/**
	 * Setter of coordStart's row. 
	 * @param rowStart New value of the coordStart's row.
	 */
	public void setRowStart(int rowStart) {
		coordStart.setRow(rowStart);
	}

	/**
	 * Getter of coordStart's column. 
	 * @return Column of the starting coordinate.
	 */
	public int getColumnStart() {
		return coordStart.getColumn();
	}

	/**
	 * Setter of coordStart's column. 
	 * @param columnStart New value of the coordStart's column.
	 */
	public void setColumnStart(int columnStart) {
		coordStart.setColumn(columnStart);
	}

	/**
	 * Getter of "coordEnd".
	 * @return The value of the "coordEnd" attribute, i.e. ending coordinate.
	 */
	public Coordinate getEnd() {
		return coordEnd;
	}
	
	/**
	 * Setter of "coordEnd".
	 * @param end The new value of "coordEnd" attribute, i.e. ending coordinate.
	 */
	public void setEnd(Coordinate end) {
		coordEnd = end;
	}

	/**
	 * Getter of coordEnd's row. 
	 * @return Row of the ending coordinate.
	 */
	public int getRowEnd() {
		return coordEnd.getRow();
	}

	/**
	 * Setter of coordEnd's row. 
	 * @param rowEnd New value of the coordEnd's row.
	 */
	public void setRowEnd(int rowEnd) {
		coordEnd.setRow(rowEnd);
	}

	/**
	 * Getter of coordEnd's column. 
	 * @return Column of the ending coordinate.
	 */
	public int getColumnEnd() {
		return coordEnd.getColumn();
	}

	/**
	 * Setter of coordEnd's column. 
	 * @param columnEnd New value of the coordEnd's column.
	 */
	public void setColumnEnd(int columnEnd) {
		coordEnd.setColumn(columnEnd);		
	}

	/**
     * This method is used to compute the direction of movement. Since each piece
     * has move restrictions, this method will be used to initially determine if a
     * move is valid.
     *
     * @return MoveDirection HORIZONTAL, VERTICAL, INVALID.
     */
    public MoveDirection getDirection() {
        if ((coordStart.getRow() == coordEnd.getRow()) && (coordStart.getColumn() != coordEnd.getColumn())) {        	
        	return MoveDirection.HORIZONTAL;        	
        } else if ((coordStart.getRow() != coordEnd.getRow()) && (coordStart.getColumn() == coordEnd.getColumn())) {        	
        	return MoveDirection.VERTICAL;
        }
        
        //Piece has been moved in diagonal or the piece has not been moved.
        return MoveDirection.INVALID;
    }
    
    /**
     * This method calculates the distance that the object needs to move in the x
     * axis (columns). The sign indicates the direction: + is right, - is left.
     *
     * @return the distance in the Horizontal direction.
     */
    public int getHorizontalDistance() {
        return coordEnd.getColumn() - coordStart.getColumn();
    }

    /**
     * This method calculates the distance that the object needs to move in the y
     * axis (rows). The sign indicates the direction: + is down, - is up. 
     *
     * @return the distance in the Vertical direction.
     */
    public int getVerticalDistance() {
        return coordEnd.getRow() - coordStart.getRow();
    }
    
    /**
     * Returns a String representation of the piece.
     * @return String with the following format: (rowStart,columnStart) --&gt; (rowEnd,columnEnd) : Direction
     */
    @Override
    public String toString() {
    	return getStart()+ " --> "+getEnd() +" : "+getDirection();
    }
}