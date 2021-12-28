package savethebunniesclient.model.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** 
 * Level class.
 * @author Christian Gutiérrez Antolín
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
	
	private final LevelType LEVELTYPE;
	
	private List<Piece> board;
	
	private String name;
	
	private int numTotalBunnies = 0;
	private int numTotalFoxes = 0;
	
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
			
			LEVELTYPE = LevelType.valueOf(sc.nextLine().toUpperCase());
			
			SIZE = Integer.parseInt(sc.nextLine());
			
			if(getSize()<3) throw new LevelException(LevelException.ERROR_SIZE);
			
			DIFFICULTY = LevelDifficulty.valueOf(sc.nextLine().toUpperCase());
			
			board = new ArrayList<Piece>(SIZE*SIZE);
			
			name = "Level " + LEVELTYPE + sc.nextLine().toUpperCase();
			
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
						numTotalBunnies++;
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
						numTotalFoxes++;
						break;
				default:
					break;					
				}			
				
				board.set((row*getSize())+column,piece);
			}
						
			numBunnies = getBoard().stream().filter(p -> p instanceof Bunny).count();
			numHoles = getBoard().stream().filter(p -> p instanceof Hole || p.getSymbol().getImageSrc().contains("-hole")).count();
			
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
	
	public LevelType getLevelType() {
		return LEVELTYPE;
	}
	
	/**
	 * Checks if the cell (row,column) is correct, i.e. it exists on the board. Row in [0,SIZE) and column in [0,SIZE).
	 * 
	 * @param row Index of the row on the board.
	 * @param column Index of the column on the board.
	 * @return True if the cell (row,column) exists on the board. Otherwise, false.
	 */
	public boolean validatePosition(int row, int column) {
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
	 * This method returns a 1-D list with all the pieces which are on the board.  
	 * @return 1-D board in the format of a List. 
	 */
	public List<Piece> getBoard(){
		return board;
	}

	public String getName() {
		return name;
	}

	public int getNumTotalBunnies() {
		return numTotalBunnies;
	}
	public int getNumTotalFoxes() {
		return numTotalFoxes;
	}
	
}