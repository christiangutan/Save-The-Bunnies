package savethebunniesclient.model.game;

import savethebunniesclient.controller.ToPlay;
import savethebunniesclient.controller.music.Music;
import savethebunniesclient.model.music.SoundType;

/** 
* A FoxHead piece class.
* @author David García Solórzano
* @version 1.0 
*/
public class FoxHead extends Fox{
	
	/**
	 * Constructor which also creates its related tail.
	 * 
	 * @param coord Coordinate object with the position in which the fox's head is initially.
	 * @param direction The direction the fox is facing. 
	 */
	public FoxHead(Coordinate coord, FoxDirection direction) {
		super(coord,Symbol.valueOf("FOX_HEAD_"+direction.toString()),direction);
		setOtherHalf(new FoxTail(this));
	}	
	
	/**
	 * Returns FoxTail object related to this head.
	 * @return Reference to the tail object, like a FoxTail, no Fox.
	 */
	public FoxTail getTail() {
		return (FoxTail) getOtherHalf();
	}

	@Override
	/**
	 * In this case, this method moves the head and the tail according to the game's rules.
	 * @param destination {@inheritDoc}
	 * @param level {@inheritDoc}
	 * @return true if the move was successful, false if parameters are invalid or
     * the move was unsuccessful. It also returns "false" if there doesn't exist a reference to the tail.
	 */
	public boolean move(Coordinate destination, ToPlay toPlay) {
				
		if (getCoord()!= null && destination != null && toPlay != null) {
			if (isValidMove(destination, toPlay)) {
				
				Coordinate headCoordinate = new Coordinate(getCoord().getRow(),getCoord().getColumn());
				Coordinate tailCoordinate = new Coordinate(getOtherHalf().getCoord().getRow(),getOtherHalf().getCoord().getColumn());
								
				try{
					Music.playSound(SoundType.FOX);
					
					//We put grass in the fox's head
					toPlay.setPiece(headCoordinate, new Grass(headCoordinate));
					//We put grass in the fox's tail
					toPlay.setPiece(tailCoordinate, new Grass(tailCoordinate));
					
					//We put fox's head in the new position
					toPlay.setPiece(destination, this);
					
					//We put fox's tail in the new position
					toPlay.setPiece(((FoxTail)getOtherHalf()).calculateCoord(destination), getOtherHalf());	
					
					
					
				}catch(Exception e) {
					return false;
				}
										
                return true;
            }
		}
	
        return false;	
	}
	
	/**
	 * In this case, this method  moves the head and tail of the fox.
	 * @param destination {@inheritDoc}
	 * @param level {@inheritDoc}
	 * @return true if the move is valid, false if parameters are invalid or
     * the move is invalid. It also returns "false" if there doesn't exist a reference to the head.
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
			case INVALID: //Invalid move (user wants to move the piece in a no-diagonal path)
			default:
					return false;					
		}	
	}
	
	/**
	 * Checks if the move is valid horizontally according to the game's rules.
	 * @param move Movement that the fox's head wants to do.
	 * @param level Level object of the current level.
	 * @return True if the horizontal move is correct. Otherwise, false.
	 */
	private boolean isValidHorizontalMove(Move move, ToPlay toPlay){
		FoxDirection direction = getDirection();
		//Cost O(1) | Fox is not orientated horizontally
		if(direction!=FoxDirection.LEFT && direction!=FoxDirection.RIGHT) return false;
		
		int distance = move.getHorizontalDistance();		
		//Cost O(1) | End occupied or no move
			try {
				if(
					(toPlay.isObstacle(move.getEnd())
							&& 
							!(toPlay.getPiece(move.getEnd()) instanceof Fox)
					) 
					|| Math.abs(distance) == 0
					) return false;
			} catch (LevelException e) {
				return false;
			}
		
		//Cost O(1) | We check limits taking the tail into account
		if((direction==FoxDirection.RIGHT && move.getColumnEnd()<=0) || (direction==FoxDirection.LEFT && move.getColumnEnd()>=toPlay.getSize()-1)){
			return false;
		}
		
		//Go to left from next square; else: go to right from next square		
		int i = distance < 0 ? move.getColumnStart() - 1 : move.getColumnStart() + 1;
		boolean condition = calculateHorizontalCondition(distance,i,move.getColumnEnd());
				
		while(condition) {
			//No occupied and different from its tail 
			 try {
				if (toPlay.isObstacle(move.getRowStart(),i) && toPlay.getPiece(move.getRowStart(),i)!=getOtherHalf()) {
				    	return false;
				 }
			} catch (LevelException e) {
				return false;
			}
			i = distance < 0 ? i-1: i+1;			
			condition = calculateHorizontalCondition(distance,i,move.getColumnEnd());
		}	
		return true;	
	}
	
	/**
	 * Helper method for "isValidHorizontalMove". 
	 * @param distance Distance/Subtraction between move's ending column coordinate and move's starting column coordinate.
	 * @param i Current index of the loop.
	 * @param end Ending index of the loop.
	 * @return True if it's possible to move the fox to "i" cell. Otherwise, false.
	 */
	private boolean calculateHorizontalCondition(int distance, int i, int end) {
		if(getDirection()==FoxDirection.RIGHT){
			if(distance < 0) {				
				return i >= end-1;
			}else {
				return i <= end; 
			}
		}else {
			if(distance < 0) {
				return i >= end;
			}else {
				return i <= end+1; 
			}
		}	
	}
	
	/**
	 * Checks if the move is valid vertically according to the game's rules.
	 * @param move Movement that the fox's head wants to do.
	 * @param level Level object of the current level.
	 * @return True if the vertical move is correct. Otherwise, false.
	 */
	private boolean isValidVerticalMove(Move move, ToPlay toPlay){
		FoxDirection direction = getDirection();
		//Cost O(1) | Fox is not orientated vertically
		if(direction!=FoxDirection.UP && direction!=FoxDirection.DOWN) return false;
		
		int distance = move.getVerticalDistance();
		
		//Cost O(1) | End occupied or no move
		try {
			if(
					(toPlay.isObstacle(move.getEnd())
							&& 
							!(toPlay.getPiece(move.getEnd()) instanceof Fox)
					) 
					|| Math.abs(distance) == 0
					) return false;
		} catch (LevelException e) {
			return false;
		}
		
		//Cost O(1) | We check limits taking the tail into account
		if((direction==FoxDirection.DOWN && move.getRowEnd()<=0) || (direction==FoxDirection.UP && move.getRowEnd()>=toPlay.getSize()-1)){
			return false;
		}
		
		//Go to up from next square; else: go to down from next square	
							   //Go up; else: Go down
		int i = distance < 0 ? move.getRowStart() - 1 : move.getRowStart() + 1;
		boolean condition = calculateVerticalCondition(distance, i, move.getRowEnd());
						
		while(condition) {
			//No occupied and different from its tail			
			 try {
				if (toPlay.isObstacle(i,move.getColumnStart()) && toPlay.getPiece(i,move.getColumnStart())!=getOtherHalf()) {				 
				    	return false;
				 }
			} catch (LevelException e) {
				return false;
			}
			i = distance < 0 ? i-1: i+1;			
			condition = calculateVerticalCondition(distance, i, move.getRowEnd());
		}	
		return true;		
	}	
	
	/**
	 * Helper method for "isValidVerticalMove". 
	 * @param distance Distance/Subtraction between move's ending row coordinate and move's starting row coordinate.
	 * @param i Current index of the loop.
	 * @param end Ending index of the loop.
	 * @return True if it's possible to move the fox to "i" cell. Otherwise, false.
	 */
	private boolean calculateVerticalCondition(int distance, int i, int end) {
		if(getDirection()==FoxDirection.DOWN){
			if(distance < 0) {				
				return i >= end-1;
			}else {
				return i <= end; 
			}
		}else {
			if(distance < 0) {
				return i >= end;
			}else {
				return i <= end+1; 
			}
		}	
	}	
}