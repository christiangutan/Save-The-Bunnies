package savethebunniesclient.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** 
 * Level class.
 * @author David García Solórzano
 * @version 1.0  
 */
public class Level {
	/**
	 * Size of the board. SIZExSIZE.
	 */
	private final int SIZE;
	/**
	 * Level's difficulty
	 */
	private final LevelDifficulty DIFFICULTY;
	/**
	 * Number minimum of moves that are required in order to beat the level/challenge.
	 */
	private final int MIN_MOVES;
	/**
	 * List of pieces that are on the board.	
	 */
	private List<Piece> board;
	
	/**
	 * Constructor
	 * @param fileName Name of the configuration file which has all the information of the level.
	 * @throws FileNotFoundException When it is impossible to open the configuration file.
	 * @throws IllegalArgumentException When an error of this type happens, e.g. NumberFormatException.
	 * @throws LevelException When there is an error with the information of configuration file, e.g. incorrect size or min moves, no bunnies, no holes, #bunnies&lt;#holes
	 */
	public Level(String fileName) throws FileNotFoundException, IllegalArgumentException, LevelException {
		String line = null;
		long numBunnies = 0, numHoles = 0;
		int row = 0, column = 0;
		char pieceSymbol = ' ';
		Piece piece = null;
		
		
		try(Scanner sc = new Scanner(new File(fileName))){
			SIZE = Integer.parseInt(sc.nextLine());
			
			if(getSize()<3)
				throw new LevelException(LevelException.ERROR_SIZE);
			
			DIFFICULTY = LevelDifficulty.valueOf(sc.nextLine().toUpperCase());
			
			MIN_MOVES = Integer.parseInt(sc.nextLine());
			
			if(getMinMoves()<1)
				throw new LevelException(LevelException.ERROR_MIN_MOVES);
			
			board = new ArrayList<Piece>(SIZE*SIZE);
			
			//We populate the whole list with Grass pieces.			
			for(int i = 0; i < getSize(); i++) {
				for(int j = 0; j < getSize(); j++) {
					board.add(new Grass(new Coordinate(i, j)));
				}
			}			
			
			
			while(sc.hasNext()) {
				line = sc.nextLine();
				pieceSymbol= line.charAt(0);
				
				if(pieceSymbol != 'b' && pieceSymbol != 'B'
						&&
						pieceSymbol != 'w' && pieceSymbol != 'W'
						&&
						pieceSymbol != 'g' && pieceSymbol != 'g') { 
					pieceSymbol = Character.toUpperCase(pieceSymbol); 
				}
					
				row = calculateRow(line.toLowerCase().charAt(1)); 
				column = calculateColumn(line.toLowerCase().charAt(2));
				
				switch(Symbol.getName(pieceSymbol)) {
					case HOLE:
						piece = new Hole(new Coordinate(row,column));
						break;
					case MUSHROOM:
						piece = new Mushroom(new Coordinate(row,column));
						break;
					case BUNNY_WHITE:						
					case BUNNY_WHITE_HOLE:
					case BUNNY_BROWN:
					case BUNNY_BROWN_HOLE:
					case BUNNY_GRAY:
					case BUNNY_GRAY_HOLE:
						piece = new Bunny(new Coordinate(row,column),Symbol.getName(pieceSymbol));
						break;
					case FOX_HEAD_UP:
					case FOX_HEAD_DOWN:
					case FOX_HEAD_LEFT:
					case FOX_HEAD_RIGHT:						
						String direction = Symbol.getName(pieceSymbol).getImageSrc().split("-")[2];
						direction = direction.substring(0,direction.indexOf(".")).toUpperCase();
						FoxHead fox = new FoxHead(new Coordinate(row,column),FoxDirection.valueOf(direction));
						piece = fox;
						FoxTail tail = fox.getTail();						
						board.set((tail.getCoord().getRow()*getSize())+tail.getCoord().getColumn(),tail);
						break;
				default:
					break;					
				}			
				
				board.set((row*getSize())+column,piece);
			}
						
			numBunnies = getBoard1D().stream().filter(p -> p instanceof Bunny).count();
			numHoles = getBoard1D().stream().filter(p -> p instanceof Hole || p.getSymbol().getImageSrc().contains("-hole")).count();
			
			if(numBunnies==0)		
			 throw new LevelException(LevelException.ERROR_NO_BUNNIES);
			
			if(numHoles==0)
				throw new LevelException(LevelException.ERROR_NO_HOLES);
			
			if(numHoles<numBunnies) throw new LevelException(LevelException.ERROR_MORE_BUNNIES_THAN_HOLES);
			             
		}catch(FileNotFoundException e) {
			throw e;
		}
		
	}
	
	 /** Getter of SIZE.
	 * @return Value of the field "SIZE".
	 */
	public int getSize() {
		return SIZE;
	}
	
	/** Getter of DIFFICULTY.
	 * @return Value of the field "DIFFICULTY".
	 */
	public LevelDifficulty getDifficulty() {
		return DIFFICULTY;				
	}	
	
	 /** Getter of MIN_MOVES.
	 * @return Value of the field "MIN_MOVES".
	 */
	public int getMinMoves() {
		return MIN_MOVES;
	}
	
	/**
	 * Checks if the cell (row,column) is correct, i.e. it exists on the board. Row in [0,SIZE) and column in [0,SIZE).
	 * 
	 * @param row Index of the row on the board.
	 * @param column Index of the column on the board.
	 * @return True if the cell (row,column) exists on the board. Otherwise, false.
	 */
	private boolean validatePosition(int row, int column) {
	   	 return row >= 0 && column >= 0 && row < SIZE && column < SIZE;        
	}
	
	/**
	 * Given a letter, it returns integer index corresponding to the board.
	 *   
	 * @param letter Letter that is required to convert into an integer. 
	 * @return Row integer index corresponding to the board. The first letter is 'a'.
	 * @throws LevelException When the position does not exist on the board.
	 */
	private int calculateRow(char letter) throws LevelException{
		int row = letter-97;
		if(validatePosition(row,0)) {
			return row;
		}
		
		throw new LevelException(LevelException.ERROR_INCORRECT_ROW);
	}
	
	/**
	 * Given a letter that represents an integer, it returns integer index corresponding to the board.
	 * 
	 * @param columnChar Index in char format that is required to convert into an integer.
	 * @return Column integer index corresponding to the board. The first char is '0' (zero).
	 * @throws LevelException When the position does not exist on the board.
	 */
	private int calculateColumn(char columnChar) throws LevelException{
		int column = columnChar-49;
		if(validatePosition(0,column)) {
			return column;
		}
		
		throw new LevelException(LevelException.ERROR_INCORRECT_COLUMN);
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
        return validatePosition(row,column) && 
        		(board.get((row*getSize())+column) instanceof Bunny || board.get((row*getSize())+column) instanceof Mushroom 
        				|| board.get((row*getSize())+column) instanceof Fox);
    }
       
   	/**
	 * This method returns a 1-D list with all the pieces which are on the board.  
	 * @return 1-D board in the format of a List. 
	 */
	public List<Piece> getBoard1D(){
		return board;
	}
	
	/**
	 * This method returns a 2-D array with all the pieces which are on the board.  
	 * @return 2-D board in the format of an array [row][column]. 
	 */
	public Piece[][] getBoard2D() {
		Piece[][] board2D = new Piece[getSize()][getSize()];
		
		for(var i = 0; i<getSize(); i++) {
			for(var j = 0; j<getSize(); j++) {
				board2D[i][j] = board.get((i*getSize())+j);
			}
		}
		
		return board2D;
	}
	
	/**
	 * Retrieve the piece which is in the cell "coord".
	 * @param coord Coordinate object with the information of the cell (row,col) where the piece is.
	 * @return Piece which is in the cell "coord".
	 * @throws LevelException When the coordinate is invalid for the board.
	 */
	public Piece getPiece(Coordinate coord) throws LevelException{
		if(!validatePosition(coord.getRow(), coord.getColumn())){
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
		if(!validatePosition(row, column)){
			throw new LevelException(LevelException.ERROR_COORDINATE);
		}
		return board.get((row*getSize())+column);
	}
	
	/**
	 * Sets the "piece" in the specific cell indicated by "coord".
	 * @param coord Coordinate object with the information of the cell in which the piece must be.
	 * @param piece Piece to put into the cell indicated by "coord".
	 * @throws LevelException When the coordinate is invalid for the board.
	 */
	public void setPiece(Coordinate coord, Piece piece) throws LevelException{
		if(!validatePosition(coord.getRow(), coord.getColumn())){
			throw new LevelException(LevelException.ERROR_COORDINATE);
		}	    
		board.set((coord.getRow()*getSize())+coord.getColumn(), piece);
	    piece.setCoord(coord);
	}
    
	/**
	 * Indicates if the level is beaten/finished, i.e. all the bunnies are in a hole.
	 * @return True if the level is beaten/finished. Otherwise, false.
	 */
    public boolean isFinished() {
    	return getBoard1D().stream()
    			.filter(piece -> piece instanceof Bunny)
    			.map(piece -> (Bunny)piece)
    			.allMatch(bunny -> bunny.isInHole());    			
    }
    
    /**
	 * Returns a String with board's information.
	 * @return Text-based board.
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();	
		Piece[][] board2D = getBoard2D();
		
		str.append("  ");
		for(int i = 0; i<SIZE; i++)			
			str.append(i+1);
		
		str.append("\n");
			
		for(int row = 0; row<SIZE; row++) {	
			str.append((char)(row+97)+"|");
			for(int column = 0; column<SIZE; column++) {				
				str.append(board2D[row][column]);
			}
			str.append("\n");
		}
		
		return str.toString();		
	}
}