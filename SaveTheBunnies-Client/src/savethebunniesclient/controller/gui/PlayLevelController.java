package savethebunniesclient.controller.gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import savethebunniesclient.app.GuiApp;
import savethebunniesclient.controller.InfoController;
import savethebunniesclient.controller.ToPlay;
import savethebunniesclient.controller.User;
import savethebunniesclient.model.game.Coordinate;
import savethebunniesclient.model.game.Grass;
import savethebunniesclient.model.game.Hole;
import savethebunniesclient.model.game.LevelException;
import savethebunniesclient.model.game.Movable;
import savethebunniesclient.model.game.Move;
import savethebunniesclient.model.view.ConfigurationPopUpWindow;
import savethebunniesclient.model.view.DoubleOptionPopUpWindow;
import savethebunniesclient.util.OnActionData;

public class PlayLevelController {

	public static final int CANVAS_WIDTH = 1024;
	public static final int CANVAS_HEIGHT = 640;
	
	private ToPlay toPlay;
		
	private List<Coordinate> move;
	
	@FXML
	private Pane canvas;
	
	private boolean onlineMode;
	private int idLevel = -1; 
	
	@FXML
	private Text labelLevelName;
	@FXML
	private Text labelLeveDifficulty;
	
	@FXML
    private void initialize() throws FileNotFoundException, LevelException{  
    	move = new ArrayList<Coordinate>();
    	idLevel = InfoController.getCurrentLevelId();
    	
    	onlineMode = idLevel > InfoController.getNumlevelstory();
    	
    	loadLevel();
    	
    	if(onlineMode) {
    		//labelLevel1.setText("LEVEL " + idLevel);
    		//labelLevel2.setText();						//username of who has built the level
    		
    	} else {
    		labelLevelName.setText("LEVEL " + idLevel);
    		labelLeveDifficulty.setText(toPlay.getDifficulty().toString().toUpperCase());
    	}
    }
	
	/**
     * Updates the status of the level (i.e. the flow of the game). It also paints the game in the GUI.
     * @throws FileNotFoundException When there is a problem opening the configuration file.
     * @throws LevelException When there is a level exception/problem.
     */
    private void update() throws FileNotFoundException, LevelException{    	
    	paint();
    	if(toPlay.isFinished()) {   		
    		
    		DoubleOptionPopUpWindow window = new DoubleOptionPopUpWindow("WELL DONE!!!");
    		
    		if(onlineMode) {
    			window.setTextButton1("Reset level");
    			window.setTextButton2("Levels");
    			window.setOnAction1(new OnActionData() {
					@Override
					public void onAction() {
						try {
							GuiApp.main.createView("PlayLevel.fxml", "css-PlayLevel.css");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
    			});
    			window.setOnAction2(new OnActionData() {
					@Override
					public void onAction() {
						try {
							GuiApp.main.createView("LevelsBuiltMode.fxml","cssCorrespondiente.css"); 
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
    			});
    		} else {
    			window.setTextButton1("Next Level");
    			window.setTextButton2("Levels");
    			window.setOnAction1(new OnActionData() {
					@Override
					public void onAction() {
						if(idLevel > User.getLastLevelPassedStory()) {
    						User.setLastLevelPassedStory(idLevel);
    						//actualizar en Servidor el dato
    						//
    						//TODO
    						//
    						//
    						//
    						//
    						//
    						//
    					}
						try {
	    					toPlay = null;
	    					System.gc();
	    					InfoController.setCurrentLevelId(++idLevel);
	    					GuiApp.main.createView("PlayLevel.fxml","css-PlayLevel.css");
							
						} catch (IOException e) {
							e.printStackTrace();
						}					
					}
    			});
    			window.setOnAction2(new OnActionData() {
					@Override
					public void onAction() {
						if(idLevel > User.getLastLevelPassedStory()) {
    						User.setLastLevelPassedStory(idLevel);
    						//TODO
    						//actualizar en Servidor el dato
    					}
						try {
							GuiApp.main.createView("LevelsStory.fxml","css-LevelsStory.css");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
    				
    			});
    			
    			
    		}
    		
    		window.createView();
    	}
    	
    	
    }	
	
	/**
     * Paints the level in the GUI.
     */
    private void paint() {
    	ObservableList<Node> nodeList =  FXCollections.observableArrayList();        
    	canvas.getChildren().clear();
    	    	    	
    	for(var piece : toPlay.getBoard()){
    		StackPane sprite = new StackPane();    		
    		ImageView spriteImage = new ImageView(new Image("file:src/savethebunniesclient/util/img/"+piece.getSymbol().getImageSrc()));    	
    		spriteImage.setFitWidth(120);
    		spriteImage.setFitHeight(120);
    		sprite.getChildren().add(spriteImage);
    		sprite.setTranslateX(125*piece.getCoord().getColumn());
    		sprite.setTranslateY(125*piece.getCoord().getRow());
    		
    		if(piece instanceof Movable) {    			
    			sprite.getStyleClass().add("piece-movable");    			
    		}
    		
    		if(piece instanceof Grass || piece instanceof Hole) {    			
    			sprite.getStyleClass().add("piece-movable-destination");    			
    		}
    			    		
    		nodeList.addAll(sprite);
    		
    		sprite.setOnMouseClicked(e ->{    			
    			onClick(piece.getCoord());
    		}); 		
    		
    	}
    	
    	canvas.getChildren().addAll(nodeList);
    	
    }
    
    /**
     * Manages the click event, when a coordinate is clicked. When 2 coordinates has been clicked, then it tries to update the status of the level by moving the piece.
     * @param coord Coordinate object that corresponds to the cell of the board that has been clicked.
     */
    private void onClick(Coordinate coord){
    	Optional<Node> nodeOptional = canvas.getChildren().stream().filter(n -> n.getTranslateX()/125==coord.getColumn() && n.getTranslateY()/125 == coord.getRow()).findFirst(); //125 
    	Node node = nodeOptional.get();
    	node.getStyleClass().add("clicked");
    		
    	move.add(coord);
    	if(move.size()==2) {    		
    		toPlay.move(new Move(new Coordinate(move.get(0).getRow(), move.get(0).getColumn()),new Coordinate(move.get(1).getRow(), move.get(1).getColumn())));    		
    		try {
				update();
				move.clear();
			} catch (LevelException | FileNotFoundException e) {				
				e.printStackTrace();			
			}
    	}
    }
    
    /**
     * Reloads the current level of the game.
     * @throws FileNotFoundException When there is a problem opening the configuration file.
     * @throws LevelException When there is a level exception/problem.
     */
    @FXML
    private void resetLevel() throws FileNotFoundException, LevelException {    	
    	loadLevel();
    }
    
    @FXML
    private void back(){    	
    	if(onlineMode) {
    	} else {
    		try {
    			GuiApp.main.createView("LevelsStory.fxml","css-LevelsStory.css");
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
    
	@FXML
	public void actionButtonPower() {
		DoubleOptionPopUpWindow window = new DoubleOptionPopUpWindow("ARE YOU SURE?");
		window.setTextButton1("YES");
		window.setTextButton2("NO");
		window.setOnAction1(new OnActionData() {
			@Override
			public void onAction() {
				System.exit(0);
			}
		});
		window.setOnAction2(new OnActionData() {
			@Override
			public void onAction() {
			}
		});
		window.createView();
	}
    
	@FXML
	public void actionButtonConfiguration() {
		ConfigurationPopUpWindow window = new ConfigurationPopUpWindow();
		window.createView();
	}
	
    private void loadLevel() throws FileNotFoundException, LevelException{
    	if(onlineMode) {
    		//TODO
    		//pedir nivel InfoController.currentLevelId
    		//al servidor
    		//cargarlo como cualquier otro
    	} else {
    		InfoController.loadMainInformation();
    		toPlay = new ToPlay(InfoController.getStoryLevels()[idLevel-1]);
    		paint();
    	}    
    }
}
