package savethebunniesclient.model.game;

import savethebunniesclient.controller.ToPlay;

/** 
* A FoxTail piece class.
* @author Christian Gutiérrez Antolín
* @version 1.0 
*/
public class FoxTail extends Fox{
	
	/**
	 * Constructor
	 * 
	 * @param foxHead Reference to the FoxHead object which the tail is related to.
	 */
	public FoxTail(FoxHead foxHead) {		
		super(new Coordinate(0,0),Symbol.valueOf("FOX_TAIL_"+foxHead.getDirection().toString()),foxHead.getDirection());
		setOtherHalf(foxHead);
		setCoord(calculateCoord(getOtherHalf().getCoord()));
	}
	
	/**
	 * Calculates the coordinate in which the fox's tail must be in relation to the coordinate of the fox's head. 
	 * @param coordHead Fox Head's coordinate.
	 * @return The coordinate in which the fox's tail must be. 
	 */
	public Coordinate calculateCoord(Coordinate coordHead) {
		return switch(getOtherHalf().getDirection()) {
			case LEFT ->{
				yield new Coordinate(coordHead.getRow(),coordHead.getColumn()+1);
			}
			case RIGHT ->{
				yield new Coordinate(coordHead.getRow(),coordHead.getColumn()-1);
			}
			case UP->{
				yield new Coordinate(coordHead.getRow()+1,coordHead.getColumn());
			}
			case DOWN ->{
				yield new Coordinate(coordHead.getRow()-1,coordHead.getColumn());
			}
		};
	}
	
	/**
	 * 
	 * 
	 * @param tailDestination Coordinate of the destination of the fox's tail 
	 * @param sizeBoard How big the board is. 
	 * @return Coordinate object of the destination of the head that is related to the fox's tail.
	 */
	private Coordinate getHeadEndCoordinate(Coordinate tailDestination, int sizeBoard) {
			
		return switch(getDirection()) {
			case LEFT:
				if(tailDestination.getColumn()<=0) {
					yield null;
				}
				yield new Coordinate(tailDestination.getRow(),
						tailDestination.getColumn()-1);
			
			case RIGHT:
				if(tailDestination.getColumn()>=sizeBoard-1) {
					yield null;
				}
				yield new Coordinate(tailDestination.getRow(),
						tailDestination.getColumn()+1);					
			case UP:
				if(tailDestination.getRow()<=0) {
					yield null;
				}
				
				yield new Coordinate(tailDestination.getRow()-1,
						tailDestination.getColumn());	
			case DOWN:
				if(tailDestination.getRow()>=sizeBoard-1) {
					yield null;
				}
				
				yield new Coordinate(tailDestination.getRow()+1,
						tailDestination.getColumn());
		};
	}
	
	
	@Override
	/**
	 * In this case, the validation is delegated to the head reference.
	 * @param coordinate {@inheritDoc}
	 * @param level {@inheritDoc} 
	 * @return {@inheritDoc}
	 */
	public boolean isValidMove(Coordinate destination, ToPlay toPlay) {
		Coordinate headDestination = getHeadEndCoordinate(destination,toPlay.getSize());
		if(headDestination==null) return false;
		return getOtherHalf().isValidMove(headDestination, toPlay);
		
	}
	
	/**
	 * {@inheritDoc}. In this case, this method really moves the head, which will move the tail.
	 * @param destination {@inheritDoc}
	 * @param level {@inheritDoc}
	 * @return {@inheritDoc} It also returns "false" if there doesn't exist a reference to the head.
	 */
	@Override
	public boolean move(Coordinate destination, ToPlay toPlay) {		
		if(isValidMove(destination,toPlay)) {
			Coordinate headDestination  = getHeadEndCoordinate(destination,toPlay.getSize());
			return getOtherHalf().move(headDestination, toPlay);
		}else {
			return false;
		}
	}
}