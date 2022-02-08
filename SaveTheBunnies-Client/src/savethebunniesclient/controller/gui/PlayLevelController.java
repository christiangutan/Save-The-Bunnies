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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import savethebunniesclient.app.GuiApp;
import savethebunniesclient.controller.ConnectionServer;
import savethebunniesclient.controller.InfoController;
import savethebunniesclient.controller.ToPlay;
import savethebunniesclient.controller.User;
import savethebunniesclient.controller.music.Music;
import savethebunniesclient.model.game.Coordinate;
import savethebunniesclient.model.game.Grass;
import savethebunniesclient.model.game.Hole;
import savethebunniesclient.model.game.Level;
import savethebunniesclient.model.game.LevelException;
import savethebunniesclient.model.game.LevelType;
import savethebunniesclient.model.game.Movable;
import savethebunniesclient.model.game.Move;
import savethebunniesclient.model.music.SoundType;
import savethebunniesclient.model.view.ConfigurationPopUpWindow;
import savethebunniesclient.model.view.DoubleOptionPopUpWindow;
import savethebunniesclient.util.OnActionData;

/**
 * Controller of the view where you can play not also the story levels, and your levels but also the levels of the others players 
 * @author christian_gutan
 *
 */
public class PlayLevelController {

	public static final int CANVAS_WIDTH = 1024;
	public static final int CANVAS_HEIGHT = 640;
	
	private ToPlay toPlay;
		
	private List<Coordinate> move;
	
	@FXML
	private Pane canvas;
	
	private boolean onlineMode = false;
	private boolean testMode = false;
	private boolean storyMode = false;
	private int idLevel; 
	
	@FXML
	private Text labelLevelName;
	@FXML
	private Text labelLevelDifficulty;
	
	@FXML
	private Circle circleImageProfile;
	
	@FXML
    private void initialize() throws FileNotFoundException, LevelException{  
		GuiApp.setPlaying(true);
		
		move = new ArrayList<Coordinate>();
    	idLevel = InfoController.getCurrentLevelId();
    	
    	if(InfoController.isTesting()) {
    		testMode = true;
    	} else {
        	onlineMode = idLevel > InfoController.getNumlevelstory();
        	storyMode = idLevel > -1 && idLevel <= InfoController.getNumlevelstory(); 
        	testMode = idLevel == -1;
    	}
    	
    	loadLevel();
    	
    	if(onlineMode) {
			labelLevelName.setText(InfoController.getCurrentLevel().getName());
			labelLevelDifficulty.setText(InfoController.getCurrentLevel().getDifficulty().toString().toUpperCase());						
    	} else if (storyMode){
        	labelLevelName.setText("LEVEL " + idLevel);
    		labelLevelDifficulty.setText(toPlay.getDifficulty().toString().toUpperCase());
    	} else if (testMode) {
    		labelLevelName.setText(InfoController.getCurrentLevel().getName().toUpperCase());
    		labelLevelDifficulty.setText(InfoController.getCurrentLevel().getDifficulty().toString().toUpperCase());
    	}
    	
    	circleImageProfile.setStroke(Color.SEAGREEN);
        Image im = User.getImageProfile();
        circleImageProfile.setFill(new ImagePattern(im));
        circleImageProfile.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
   
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
				    	Music.playSound(SoundType.BUTTON);
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
					    	Music.playSound(SoundType.BUTTON);
							Music.playSound(SoundType.MUSICMENU);

							GuiApp.main.createView("LevelsOnline.fxml","css-LevelsOnline.css"); 
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
    			});
    		} else if (storyMode){
    			window.setTextButton1("Next Level");
    			window.setTextButton2("Levels");
    			window.setOnAction1(new OnActionData() {
					@Override
					public void onAction() {
				    	Music.playSound(SoundType.BUTTON);
						if(idLevel > User.getLastLevelPassedStory()) {
    						User.setLastLevelPassedStory(idLevel);
    						ConnectionServer.updateStoryLevel(User.getUsername(), User.getLastLevelPassedStory());
    					}
						try {
	    					toPlay = null;
	    					System.gc();
	    					idLevel++;
	    					if(idLevel <= InfoController.getNumlevelstory()) {
	    						InfoController.setCurrentLevelId(idLevel);
	    						GuiApp.main.createView("PlayLevel.fxml","css-PlayLevel.css");
	    					} else {
	    						GuiApp.main.createView("Congratulations.fxml","css-Congratulations.css");
	    						
	    					}
						} catch (IOException e) {
							e.printStackTrace();
						}					
					}
    			});
    			window.setOnAction2(new OnActionData() {
					@Override
					public void onAction() {
				    	Music.playSound(SoundType.BUTTON);
						Music.playSound(SoundType.MUSICMENU);
						if(idLevel > User.getLastLevelPassedStory()) {
    						User.setLastLevelPassedStory(idLevel);
    						ConnectionServer.updateStoryLevel(User.getUsername(), User.getLastLevelPassedStory());
    					}
						try {
							GuiApp.main.createView("LevelsStory.fxml","css-LevelsStory.css");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
    				
    			});
    			
    		} else if(testMode) {
    			InfoController.setTestedLevel(true);
    			window.setTextButton1("Reset level");
    			window.setTextButton2("Back");
    			window.setOnAction1(new OnActionData() {
					@Override
					public void onAction() {
				    	Music.playSound(SoundType.BUTTON);
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
				    	Music.playSound(SoundType.BUTTON);
						try {
							GuiApp.main.createView("CreateLevel.fxml","css-CreateLevel.css"); 
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

    	Music.playSound(SoundType.BUTTON);
    	if(onlineMode) {
    		Music.playSound(SoundType.MUSICMENU);
    		try {
    			GuiApp.main.createView("LevelsOnline.fxml","css-LevelsOnline.css");
			} catch (IOException e) {
				e.printStackTrace();
			}
    	} else if (storyMode) {
    		Music.playSound(SoundType.MUSICMENU);
    		try {
    			GuiApp.main.createView("LevelsStory.fxml","css-LevelsStory.css");
			} catch (IOException e) {
				e.printStackTrace();
			}
    	} else if (testMode) {
    		try {
    			GuiApp.main.createView("CreateLevel.fxml","css-CreateLevel.css");
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
    
	@FXML
	public void actionButtonPower() {
		Music.playSound(SoundType.BUTTON);
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
		Music.playSound(SoundType.BUTTON);
		ConfigurationPopUpWindow window = new ConfigurationPopUpWindow();
		window.createView();
	}
	
    private void loadLevel() throws FileNotFoundException, LevelException {
    	if(onlineMode) {
    		toPlay = new ToPlay(InfoController.getCurrentLevel());
    		paint();
    	} else if(storyMode) {
    		InfoController.loadMainInformation();
    		toPlay = new ToPlay(InfoController.getStoryLevels()[idLevel-1]);
    		paint();
    	} else if(testMode) {
    		toPlay = new ToPlay(InfoController.getCurrentLevel());
    		paint();
    	}
    }
}
