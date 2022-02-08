package savethebunniesclient.model.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import savethebunniesclient.controller.User;
import savethebunniesclient.util.CreateFile;

/** 
 * Level class.
 * @author christian_gutan
 * 
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
	
	private String name = "";
	
	private int numTotalBunnies = 0;
	private int numTotalFoxes = 0;
	private int numTotalHoles = 0;
		
	private int id;
	private String autor;
	
	private StringProperty nameProperty;
	
	
	/**
	 * Constructor
	 * @param fileName Name of the configuration file which has all the information of the level.
	 * @throws FileNotFoundException When it is impossible to open the configuration file.
	 * @throws IllegalArgumentException When an error of this type happens, e.g. NumberFormatException.
	 * @throws LevelException When there is an error with the information of configuration file, e.g. incorrect size or min moves, no bunnies, no holes, #bunnies&lt;#holes
	 */
	public Level(String fileName) throws FileNotFoundException, IllegalArgumentException, LevelException {
		String line = null;
		char pieceSymbol = ' ';
		Piece piece = null;

		try(Scanner sc = new Scanner(new File(fileName))){
			
			LEVELTYPE = LevelType.valueOf(sc.nextLine().toUpperCase());
			
			SIZE = Integer.parseInt(sc.nextLine());
			
			if(getSize()<3) throw new LevelException(LevelException.ERROR_SIZE);
			
		
			DIFFICULTY = LevelDifficulty.valueOf(sc.nextLine().toUpperCase());
						
			board = new ArrayList<Piece>(SIZE*SIZE);
			
			for (int i = 0; i < SIZE; i++) {
				for(int j = 0; j<SIZE; j++) {
					board.add(new Grass(new Coordinate(i,j)));
				}
			}
			
			name = sc.nextLine().toUpperCase();
			setNameProperty(name);
			
			autor = sc.nextLine().toUpperCase();
			
			//We populate the whole list with Grass pieces.			
			for(int i = 0; i < getSize(); i++) {
				line = sc.nextLine();
				for(int j = 0; j < getSize(); j++) {
					pieceSymbol= line.charAt(j);
					
					switch(pieceSymbol) {
						case '#':
							piece = new Grass(new Coordinate(i, j));
							break;
						case 'H':
							piece = new Hole(new Coordinate(i,j));
							numTotalHoles++;
							break;
						case 'M':
							piece = new Mushroom(new Coordinate(i,j));
							break;
						case 'w':		
							piece = new Bunny(new Coordinate(i,j), Symbol.BUNNY_WHITE);
							numTotalBunnies++;
							break;
						case 'W':
							piece = new Bunny(new Coordinate(i,j), Symbol.BUNNY_WHITE_HOLE);
							numTotalBunnies++;
							numTotalHoles++;
							break;
						case 'b':
							piece = new Bunny(new Coordinate(i,j), Symbol.BUNNY_BROWN);
							numTotalBunnies++;
							break;
						case 'B':
							piece = new Bunny(new Coordinate(i,j), Symbol.BUNNY_BROWN_HOLE);
							numTotalBunnies++;
							numTotalHoles++;
							break;
						case 'g':
							piece = new Bunny(new Coordinate(i,j), Symbol.BUNNY_GRAY);
							numTotalBunnies++;
							break;
						case 'G':
							piece = new Bunny(new Coordinate(i,j), Symbol.BUNNY_GRAY_HOLE);
							numTotalBunnies++;
							numTotalHoles++;
							break;
						case '^':
						case 'V':
						case '<':
						case '>':						
							String direction = Symbol.getName(pieceSymbol).getImageSrc().split("-")[2];
							direction = direction.substring(0,direction.indexOf(".")).toUpperCase();
							FoxHead fox = new FoxHead(new Coordinate(i,j),FoxDirection.valueOf(direction));
							piece = fox;
							board.set((fox.getCoord().getRow()*getSize())+fox.getCoord().getColumn(),fox);
							FoxTail tail = fox.getTail();	
							piece = tail;
							board.set((tail.getCoord().getRow()*getSize())+tail.getCoord().getColumn(),tail);
							//board.set((tail.getCoord().getRow()*getSize())+tail.getCoord().getColumn(),tail);
							numTotalFoxes++;
							break;
						default:
							break;					
					}		
										
					board.set(piece.getCoord().getRow()*getSize()+piece.getCoord().getColumn(),piece);
				}
			}			
			if(LEVELTYPE != LevelType.BUILDERMODE) {
				if(numTotalBunnies==0)		
				 throw new LevelException(LevelException.ERROR_NO_BUNNIES);
				
				if(numTotalHoles==0)
					throw new LevelException(LevelException.ERROR_NO_HOLES);
				
				if(numTotalHoles<numTotalBunnies) throw new LevelException(LevelException.ERROR_MORE_BUNNIES_THAN_HOLES);
			}
		
		}catch(FileNotFoundException e) {
			throw e;
		}
		
	}
	
		
	 public Level(Piece[][] table, LevelType type, int size, LevelDifficulty difficulty, String nameLevel) {
		 LEVELTYPE = type;
		 SIZE = size;
		 DIFFICULTY = difficulty;
		 name = nameLevel;
		 setNameProperty(name);
		 board = new ArrayList<Piece>(SIZE*SIZE);
		 
		for(int i = 0; i < getSize(); i++) {
			for(int j = 0; j < getSize(); j++) {
				board.add(table[i][j]);
			}
		}		
	}

	public Level (Piece[][] table, LevelType type, int size, LevelDifficulty difficulty, String nameLevel, String autor, int id) {
		this(table, type, size, difficulty, nameLevel);
		this.id = id;
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
	 * This method returns a 1-D list with all the pieces which are on the board.  
	 * @return 1-D board in the format of a List. 
	 */
	public List<Piece> getBoard(){
		return board;
	}
	
	/**
	 * This method returns a 2-D array with all the pieces which are on the board.
	 * @return 2-D board in the format of an array [row][column].
	 */
	public Piece[][] getBoard2D(){
		int ind=0;
		Piece[][] boardPieces = new Piece[this.getSize()][this.getSize()];
		for(int i=0; i<this.getSize(); i++) {
			for(int j=0; j< this.getSize(); j++) {
				boardPieces[i][j]=board.get(ind); 
				ind++;
			}
		}
		return boardPieces;
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
	
	public void createFileOfLevel() {

		CreateFile.createFile(this.toString(), this.getId());
	}
	
	public String toString() {
		String level = LEVELTYPE.toString() + "\n" + "5" + "\n" + DIFFICULTY.toString() + "\n" + name + "\n" + autor + "\n";

		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				level += this.getBoard2D()[i][j];	
			}
			level+="\n";
		}
		return level;	
	}
	
	public static void createNewFileOfLevel() {
		String level = LevelType.BUILDERMODE.toString() + "\n" + "5" + "\n" + LevelDifficulty.STARTER.toString() + "\n" + "\n" + User.getUsername() + "\n" ;

		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				level += "#";	
			}
			level+="\n";
		}
		CreateFile.createFile(level, -1);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public StringProperty getNameProperty() {
		return nameProperty;
	}
	
	public void setNameProperty(String nameProperty) {
		this.nameProperty =  new SimpleStringProperty(nameProperty);;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getAutor() {
		return autor;
	}
	
}