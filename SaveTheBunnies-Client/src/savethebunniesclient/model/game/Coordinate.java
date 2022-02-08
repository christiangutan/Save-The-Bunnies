package savethebunniesclient.model.game;

/** 
* A Coordinate class.
* @author Christian_gutan
* @version 1.0 
*/
public class Coordinate implements Comparable<Coordinate>{

	/**
	 * Index of the row with respect to the board.
	 */
	private int row;
	/**
	 * Index of the column with respect to the board.
	 */
	private int column;
	
	/**
	 * Constructor
	 * 
	 * @param row Index of the row with respect to the board.
	 * @param column Index of the column with respect to the board.
	 */
	public Coordinate(int row, int column) {
		setRow(row);
		setColumn(column);
	}
	
	/**
	 * Getter of row.
	 * @return The value of the row.
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Setter of row.
	 * @param row New index to assign to the row attribute.  
	 */	
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * Getter of column.
	 * @return The value of the column.
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * Setter of column.
	 * @param column New index to assign to the column attribute.  
	 */	
	public void setColumn(int column) {
		this.column = column;
	}
	
	/**
	 * Two objects are identical when they have the same row and column.
	 * @param obj Object to compare.
	 * @return True if the two objects are identical. Otherwise, it returns false. 
	 */
	@Override
	public boolean equals(Object obj) {
		Coordinate coord = (Coordinate) obj;
		return this.getRow() == coord.getRow() && this.getColumn() == coord.getColumn(); 
	}
	
	/**In this case, this method first compares rows and second columns. 
	 * @param other Coordinate object to compare 
	 * @return {@inheritDoc}
	 */
	@Override
	public int compareTo(Coordinate other) {
		int result = this.getRow() - other.getRow();
		return result == 0? (this.getColumn()-other.getColumn()) : result;		
	}	
	
	/**
     * Returns a String representation of the coordinate.
     * @return It returns coordinate as a text such as "(row,column)".
     */
	@Override
	public String toString() {
		return "("+getRow()+","+getColumn()+")";
	}	
}