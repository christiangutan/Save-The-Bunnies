package savethebunniesclient.controller;

import java.util.List;

import savethebunniesclient.model.Bunny;
import savethebunniesclient.model.Coordinate;
import savethebunniesclient.model.Fox;
import savethebunniesclient.model.Level;
import savethebunniesclient.model.LevelDifficulty;
import savethebunniesclient.model.LevelException;
import savethebunniesclient.model.Movable;
import savethebunniesclient.model.Move;
import savethebunniesclient.model.Mushroom;
import savethebunniesclient.model.Piece;

public class ToPlay {
	private Level level;
	private int size;
	//private int numNumberLevelStory = -1;
	
	/**
	 * List of pieces that are on the board.	
	 */
	private List<Piece> board;
	
	public ToPlay(Level level) {
		this.level = level;
		board = level.getBoard();
		this.size = level.getSize();		
		
		//controlar despues de cada movimiento si se ha acabado el juego
		//en caso de que sea así pasar al siguiente nivel (si existe) en modo historia
		//y si es en modo multiplayer volver a la pantalla de niveles.
		//todo esto con sus correspondientes mensajes
		
		
	}
	
	/**
     * Determines if the specified position (coord) is an obstacle that can be jumped over, i.e. bunny, mushroom or fox.
     *
     * @param coord Coordinate object that represents an object of the board.  
     * @return True if the position is occupied, false otherwise.
     */
    public boolean isObstacle(Coordinate coord) {
        return isObstacle(coord.getRow(), coord.getColumn());
    }
    
    /**
     * Determines if the specified position (coord) is an obstacle that can be jumped over, i.e. bunny, mushroom or fox.
     * 
     * @param row The y-coordinate (row) of the position.
     * @param column The x-coordinate (column) of the position.
     * @return True if the position is occupied, false otherwise.
     */
    public boolean isObstacle(int row, int column) {
        return level.validatePosition(row,column) && 
        		(board.get((row*level.getSize())+column) instanceof Bunny || board.get((row*level.getSize())+column) instanceof Mushroom 
        				|| board.get((row*level.getSize())+column) instanceof Fox);
    }
	
	/**
	 * Retrieve the piece which is in the cell "coord".
	 * @param coord Coordinate object with the information of the cell (row,col) where the piece is.
	 * @return Piece which is in the cell "coord".
	 * @throws LevelException When the coordinate is invalid for the board.
	 */
	public Piece getPiece(Coordinate coord) throws LevelException{
		if(!level.validatePosition(coord.getRow(), coord.getColumn())){
			throw new LevelException(LevelException.ERROR_COORDINATE);
		}
		return getPiece(coord.getRow(), coord.getColumn());
	}
	
	/**
	 * Retrieve the piece which is in the cell (row,column).
	 * @param row Index of the row in which the piece is.
	 * @param column Index of the column in which the piece is.
	 * @return Piece which is in the cell (row,column).
	 * @throws LevelException When the coordinate/cell (row,column) is invalid for the board.
	 */
	public Piece getPiece(int row, int column) throws LevelException{
		if(!level.validatePosition(row, column)){
			throw new LevelException(LevelException.ERROR_COORDINATE);
		}
		return board.get((row*level.getSize())+column);
	}
	
	/**
	 * Sets the "piece" in the specific cell indicated by "coord".
	 * @param coord Coordinate object with the information of the cell in which the piece must be.
	 * @param piece Piece to put into the cell indicated by "coord".
	 * @throws LevelException When the coordinate is invalid for the board.
	 */
	public void setPiece(Coordinate coord, Piece piece) throws LevelException{
		if(!level.validatePosition(coord.getRow(), coord.getColumn())){
			throw new LevelException(LevelException.ERROR_COORDINATE);
		}	    
		board.set((coord.getRow()*level.getSize())+coord.getColumn(), piece);
	    piece.setCoord(coord);
	}
	
	public boolean move(Move move) {
        if (move == null) return false;
        Piece piece;
		try {
			piece = getPiece(move.getStart());			
		} catch (LevelException e) {
			return false;
		}
		
		if (piece instanceof Movable && ((Movable) piece).move(move.getEnd(), this)) {
			return true;
		}
		
        return false;
    }
    
	/**
	 * Indicates if the level is beaten/finished, i.e. all the bunnies are in a hole.
	 * @return True if the level is beaten/finished. Otherwise, false.
	 */
    public boolean isFinished() {
    	return getBoard().stream()
    			.filter(piece -> piece instanceof Bunny)
    			.map(piece -> (Bunny)piece)
    			.allMatch(bunny -> bunny.isInHole());    			
    }
    
    private void reload() {
    	//board = level.getBoard();
    }
    
    public List<Piece> getBoard(){
		return board;
	}
    
    public int getSize() {
    	return size;
    }
    public LevelDifficulty getDifficulty() {
    	return level.getDifficulty();
    }
    
    
}
