package savethebunniesclient.model.game;

import savethebunniesclient.controller.ToPlay;
import savethebunniesclient.controller.music.Music;
import savethebunniesclient.model.music.SoundType;

/** 
* A Bunny piece class.
* @author Christian_gutan
*/
public class Bunny extends Piece implements Movable{
	
	/**
	 * Constructor. By default, the bunny is brown and out of the hole.
	 * 
	 * @param coord Coordinate object in which the bunny is initially.
	 */
	public Bunny(Coordinate coord) {
		this(coord, Symbol.BUNNY_BROWN);		
	}
	
	/**
	 * Constructor.
	 * 
	 * @param coord Coordinate object in which the bunny is initially.
	 * @param symbol Symbol element that represents an ASCII character/symbol of the bunny.
	 */
	public Bunny(Coordinate coord, Symbol symbol) {
		super(coord, symbol);		
	}
	
	/**
	 * Checks if the bunny is a hole.
	 * @return True if the bunny is in the hole. Otherwise, false.
	 */
	public boolean isInHole() {
		return Character.isUpperCase(getSymbol().getAscii());
	}	
	
	@Override
	/**
	 * {@inheritDoc}
	 * In this case, this method moves the bunny.
	 * @param destination {@inheritDoc}
	 * @param level {@inheritDoc}
	 * @return true if the move was successful, false if parameters are invalid or
     * the move was unsuccessful. It also returns "false" if there is any exception.
	 */
	public boolean move(Coordinate destination, ToPlay toPlay) {
		
		if (getCoord()!= null && destination != null && toPlay != null) {
			if (isValidMove(destination, toPlay)) {
				
				Coordinate start = new Coordinate(this.getCoord().getRow(), this.getCoord().getColumn());																
				Piece endCell;
				
				try {
					Music.playSound(SoundType.BUNNY);
					endCell = toPlay.getPiece(destination);
					toPlay.setPiece(start, this.isInHole()?new Hole(start):new Grass(start));
					if(endCell instanceof Hole) { //Add "_HOLE" if the symbols doesn't have "_HOLE". 
						if(!getSymbol().name().contains("_HOLE")){
							this.setSymbol(Symbol.valueOf(getSymbol().name()+"_HOLE"));
						}
					}else {//Remove "_HOLE"
						this.setSymbol(Symbol.valueOf(getSymbol().name().replace("_HOLE","")));					
					}
					toPlay.setPiece(destination, this);
					
				} catch (LevelException e) {
					return false;
				}								
				
                return true;
            }
		}
	
        return false;		
	}

	/**
     * Validates if the desired move is valid for the object.
     *
     * @param destination Coordinate destination
     * @param level The level on which the move is taking place.
     * @return True if the path for this move is valid for the object (i.e. horizontal or vertical), false otherwise.
     */
	@Override
	public boolean isValidMove(Coordinate destination, ToPlay toPlay) {
		
		if(destination.getRow()<0 || destination.getColumn()>toPlay.getSize()) return false;
		
		Move move = new Move(getCoord(),destination);
		
		switch(move.getDirection()) {
			case HORIZONTAL:
					return isValidHorizontalMove(move,toPlay) ;
			case VERTICAL:
					return isValidVerticalMove(move,toPlay);
			case INVALID: //No-Invalid move (user wants to move the piece in a no-diagonal path)
			default:
					return false;					
		}					         	
	}
	
	/**
	 * Checks if the move is valid horizontally according to the game's rules.
	 * 
	 * @param move Movement that the bunny wants to do.
	 * @param level Level object of the current level.
	 * @return True if the horizontal move is correct. Otherwise, false.
	 */
	private boolean isValidHorizontalMove(Move move, ToPlay toPlay){
		
		int distance = move.getHorizontalDistance();
		
		//Cost O(1) | End no occupied and bunny move two squares, at least.
		if(toPlay.isObstacle(move.getEnd()) || Math.abs(distance) <= 1) return false;
		
		//Go to left from next square; else: go to right from next square		
		int i = distance < 0 ? move.getColumnStart() - 1 : move.getColumnStart() + 1;
		boolean condition = distance < 0 ? i > move.getColumnEnd() : i < move.getColumnEnd();
				
		while(condition) {
			 if (!toPlay.isObstacle(move.getRowStart(),i)) {
                	return false;
             }
			i = distance < 0 ? i-1: i+1;			
			condition = distance < 0 ? i > move.getColumnEnd() : i < move.getColumnEnd();
		}	
		return true;	
	}
	
	/**
	 * Checks if the move is valid vertically according to the game's rules.
	 * 
	 * @param move Movement that the bunny wants to do.
	 * @param level Level object of the current level.
	 * @return True if the vertical move is correct. Otherwise, false.
	 */
	private boolean isValidVerticalMove(Move move, ToPlay toPlay){
		
		int distance = move.getVerticalDistance();
		
		//Cost O(1) | End no occupied and bunny move two squares, at least.
		if(toPlay.isObstacle(move.getEnd()) || Math.abs(distance) <= 1) return false;
		
		//Go to up from next square; else: go to down from next square		
		int i = distance < 0 ? move.getRowStart() - 1 : move.getRowStart() + 1;
		boolean condition = distance < 0 ? i > move.getRowEnd() : i < move.getRowEnd();
				
		while(condition) {
			 if (!toPlay.isObstacle(i,move.getColumnStart())) {				
                	return false;
             }
			i = distance < 0 ? i-1: i+1;			
			condition = distance < 0 ? i > move.getRowEnd() : i < move.getRowEnd();
		}	
		return true;		
	}	
}