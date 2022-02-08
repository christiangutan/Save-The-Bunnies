package savethebunniesclient.controller.gui;

import savethebunniesclient.app.GuiApp;
import savethebunniesclient.controller.ConnectionServer;
import savethebunniesclient.controller.InfoController;
import savethebunniesclient.controller.User;
import savethebunniesclient.controller.music.Music;
import savethebunniesclient.model.game.Bunny;
import savethebunniesclient.model.game.Coordinate;
import savethebunniesclient.model.game.Fox;
import savethebunniesclient.model.game.FoxDirection;
import savethebunniesclient.model.game.FoxHead;
import savethebunniesclient.model.game.FoxTail;
import savethebunniesclient.model.game.Grass;
import savethebunniesclient.model.game.Hole;
import savethebunniesclient.model.game.Level;
import savethebunniesclient.model.game.LevelDifficulty;
import savethebunniesclient.model.game.LevelException;
import savethebunniesclient.model.game.LevelType;
import savethebunniesclient.model.game.Mushroom;
import savethebunniesclient.model.game.Piece;
import savethebunniesclient.model.game.Symbol;
import savethebunniesclient.model.music.SoundType;
import savethebunniesclient.model.view.ConfigurationPopUpWindow;
import savethebunniesclient.model.view.DoubleOptionPopUpWindow;
import savethebunniesclient.model.view.InformationPopUpWindow;
import savethebunniesclient.util.OnActionData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * Controller of the view where you can create new own levels
 * @author christian_gutan
 *
 */
public class CreateLevelController {
	
	@FXML
	private RadioButton radioButtonGrass;
	@FXML
	private RadioButton radioButtonMushroom;
	@FXML
	private RadioButton radioButtonHole;
	@FXML
	private RadioButton radioButtonBunny;
	@FXML
	private RadioButton radioButtonBunnyWhite;
	@FXML
	private RadioButton radioButtonBunnyBrown;
	@FXML
	private RadioButton radioButtonBunnyGray;
	@FXML
	private RadioButton radioButtonFox;
	@FXML
	private RadioButton radioButtonFoxHorizontal;
	@FXML
	private RadioButton radioButtonFoxHorizontalRight;
	@FXML
	private RadioButton radioButtonFoxHorizontalLeft;
	@FXML
	private RadioButton radioButtonFoxVertical;
	@FXML
	private RadioButton radioButtonFoxVerticalUp;
	@FXML
	private RadioButton radioButtonFoxVerticalDown;
	@FXML
	private RadioButton radioButtonDifficultyStarter;
	@FXML
	private RadioButton radioButtonDifficultyJunior;
	@FXML
	private RadioButton radioButtonDifficultyExpert;
	
	@FXML
	private Text labelLevelDifficulty;
	@FXML
	private Text labelLevelName;
		
	@FXML 
	private TextField textFieldNameLevel;
	
	@FXML
	private Pane canvas;
	
	@FXML
	private Button buttonPlay;
	@FXML
	private Button buttonSave;
	
	private Piece [][] table = new Piece[5][5];
	private Piece pieceSelected = null;
	private Coordinate coordinateSelected = null;
	
	@FXML
	private Circle circleImageProfile;
	
	@FXML
    public void initialize() {
		
		circleImageProfile.setStroke(Color.SEAGREEN);
	    Image im = User.getImageProfile();
	    circleImageProfile.setFill(new ImagePattern(im));
	    circleImageProfile.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
	    		
		ToggleGroup groupPiece = new ToggleGroup();
		radioButtonGrass.setToggleGroup(groupPiece);
		radioButtonMushroom.setToggleGroup(groupPiece);
		radioButtonHole.setToggleGroup(groupPiece);
		radioButtonBunny.setToggleGroup(groupPiece);
		radioButtonFox.setToggleGroup(groupPiece);
		ToggleGroup groupBunny = new ToggleGroup();
		radioButtonBunnyWhite.setToggleGroup(groupBunny);
		radioButtonBunnyBrown.setToggleGroup(groupBunny);
		radioButtonBunnyGray.setToggleGroup(groupBunny);
		ToggleGroup groupFox = new ToggleGroup();		
		radioButtonFoxHorizontal.setToggleGroup(groupFox);
		radioButtonFoxVertical.setToggleGroup(groupFox);
		ToggleGroup groupFoxHorizontalVertical = new ToggleGroup();
		radioButtonFoxHorizontalRight.setToggleGroup(groupFoxHorizontalVertical);
		radioButtonFoxHorizontalLeft.setToggleGroup(groupFoxHorizontalVertical);
		radioButtonFoxVerticalUp.setToggleGroup(groupFoxHorizontalVertical);
		radioButtonFoxVerticalDown.setToggleGroup(groupFoxHorizontalVertical);
		ToggleGroup groupDifficulty = new ToggleGroup();
		radioButtonDifficultyStarter.setToggleGroup(groupDifficulty);
		radioButtonDifficultyJunior.setToggleGroup(groupDifficulty);
		radioButtonDifficultyExpert.setToggleGroup(groupDifficulty);
		
		radioButtonGrass.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {			
					if(table[coordinateSelected.getRow()][coordinateSelected.getColumn()] instanceof Fox) deleteFox();
	        		setPiece(new Grass(coordinateSelected), coordinateSelected.getRow(), coordinateSelected.getColumn());
	        		paintCanvaWithoutClicked(coordinateSelected);
	        	} else {									
	        		radioButtonGrass.setSelected(false);
	        	}	
			}
		});
		
		radioButtonMushroom.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {		
					if(table[coordinateSelected.getRow()][coordinateSelected.getColumn()] instanceof Fox) deleteFox();
					setPiece(new Mushroom(coordinateSelected), coordinateSelected.getRow(), coordinateSelected.getColumn());
	        		paintCanvaWithoutClicked(coordinateSelected);
	        	}
			}
		});
		
		radioButtonHole.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {		
					if(table[coordinateSelected.getRow()][coordinateSelected.getColumn()] instanceof Fox) deleteFox();
					setPiece(new Hole(coordinateSelected), coordinateSelected.getRow(), coordinateSelected.getColumn());
	        		paintCanvaWithoutClicked(coordinateSelected);
	        	}
			}
		});
		
		radioButtonBunny.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {								
	        		paintCanvaWithoutClicked(coordinateSelected);
	        	} else {									
	        		radioButtonBunnyWhite.setSelected(false);
	        		radioButtonBunnyBrown.setSelected(false);
	        		radioButtonBunnyGray.setSelected(false);
	        	}	

			}
		});
		
		radioButtonBunnyWhite.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					if(table[coordinateSelected.getRow()][coordinateSelected.getColumn()] instanceof Fox) deleteFox();
					setPiece(new Bunny(coordinateSelected, Symbol.BUNNY_WHITE), coordinateSelected.getRow(), coordinateSelected.getColumn());
					radioButtonBunnyWhite.setSelected(true);
	        		radioButtonBunnyBrown.setSelected(false);
	        		radioButtonBunnyGray.setSelected(false);
	        		paintCanvaWithoutClicked(coordinateSelected);
	        	}

			}
		});
		
		radioButtonBunnyBrown.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {					
					if(table[coordinateSelected.getRow()][coordinateSelected.getColumn()] instanceof Fox) deleteFox();
					setPiece(new Bunny(coordinateSelected, Symbol.BUNNY_BROWN), coordinateSelected.getRow(), coordinateSelected.getColumn());
					radioButtonBunnyWhite.setSelected(false);
	        		radioButtonBunnyBrown.setSelected(true);
	        		radioButtonBunnyGray.setSelected(false);
	        		radioButtonBunny.setSelected(true);
	        		paintCanvaWithoutClicked(coordinateSelected);
	        	}
			}
		});
		
		radioButtonBunnyGray.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {			
					if(table[coordinateSelected.getRow()][coordinateSelected.getColumn()] instanceof Fox) {
						for (int i = 0; i < 5; i++) {
							for(int j = 0; j < 5; j++) {
								System.out.print(table[i][j] + ";");
							}
							System.out.println();
						}
						System.out.println();
						deleteFox();
					}
					setPiece(new Bunny(coordinateSelected, Symbol.BUNNY_GRAY), coordinateSelected.getRow(), coordinateSelected.getColumn());
					radioButtonBunnyWhite.setSelected(false);
	        		radioButtonBunnyBrown.setSelected(false);
	        		radioButtonBunnyGray.setSelected(true);
	        		radioButtonBunny.setSelected(true);
	        		paintCanvaWithoutClicked(coordinateSelected);
	        	}
			}
		});		
		
		radioButtonFox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {								//activated
				
	        	} else {									//no activated
	        		radioButtonFoxHorizontal.setSelected(false);
	        		radioButtonFoxVertical.setSelected(false);
	        	}	
			}
		});
		
		radioButtonFoxHorizontal.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {								//activated
					radioButtonFox.setSelected(true);
	        	} else {									//no activated
	        		radioButtonFoxHorizontal.setSelected(false);
	        		radioButtonFoxVertical.setSelected(false);
	        		radioButtonFoxHorizontalRight.setSelected(false);
	        		radioButtonFoxHorizontalLeft.setSelected(false);
	        		radioButtonFoxVertical.setSelected(false);
	        		radioButtonFoxVertical.setSelected(false);
	        	}
			}
		});
		
		radioButtonFoxHorizontalRight.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {								//activated
					if(table[coordinateSelected.getRow()][coordinateSelected.getColumn()] instanceof Fox) deleteFox();
					
					radioButtonFoxHorizontal.setSelected(true);
						
					FoxHead foxHead= new FoxHead(coordinateSelected, FoxDirection.RIGHT);
					setPiece(foxHead, coordinateSelected.getRow(), coordinateSelected.getColumn());
					setPiece(new FoxTail(foxHead), coordinateSelected.getRow(), coordinateSelected.getColumn() - 1);
					paintCanvaWithoutClicked(coordinateSelected);
				} else {									//no activated
	        		radioButtonFoxHorizontal.setSelected(false);
	        	}
			}
		});
		
		radioButtonFoxHorizontalLeft.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {								//activated
					if(table[coordinateSelected.getRow()][coordinateSelected.getColumn()] instanceof Fox) deleteFox();

					radioButtonFoxHorizontal.setSelected(true);
					
					FoxHead foxHead= new FoxHead(coordinateSelected, FoxDirection.LEFT);
					setPiece(foxHead, coordinateSelected.getRow(), coordinateSelected.getColumn());
					setPiece(new FoxTail(foxHead), coordinateSelected.getRow(), coordinateSelected.getColumn() + 1);
					paintCanvaWithoutClicked(coordinateSelected);

	        	} else {									//no activated
	        		radioButtonFoxHorizontal.setSelected(false);
	        	}
			}
		});
		
		radioButtonFoxVertical.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {								//activated
					radioButtonFox.setSelected(true);
	        	} else {									//no activated
	        		radioButtonFoxHorizontal.setSelected(false);
	        		radioButtonFoxVertical.setSelected(false);
	        		radioButtonFoxHorizontalRight.setSelected(false);
	        		radioButtonFoxHorizontalLeft.setSelected(false);
	        		radioButtonFoxVertical.setSelected(false);
	        		radioButtonFoxVertical.setSelected(false);
	        	}
			}
		});
		
		radioButtonFoxVerticalUp.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {								//activated
					if(table[coordinateSelected.getRow()][coordinateSelected.getColumn()] instanceof Fox) deleteFox();

					radioButtonFoxVertical.setSelected(true);
						
					FoxHead foxHead= new FoxHead(coordinateSelected, FoxDirection.UP);
					setPiece(foxHead, coordinateSelected.getRow(), coordinateSelected.getColumn());
					setPiece(new FoxTail(foxHead), coordinateSelected.getRow() + 1, coordinateSelected.getColumn());
					paintCanvaWithoutClicked(coordinateSelected);
		
	        	} else {									//no activated
	        		radioButtonFoxVertical.setSelected(false);
	        	}
			}
		});
		
		radioButtonFoxVerticalDown.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {								//activated
					if(table[coordinateSelected.getRow()][coordinateSelected.getColumn()] instanceof Fox) deleteFox();

					radioButtonFoxVertical.setSelected(true);
						
					FoxHead foxHead= new FoxHead(coordinateSelected, FoxDirection.DOWN);
					setPiece(foxHead, coordinateSelected.getRow(), coordinateSelected.getColumn());
					setPiece(new FoxTail(foxHead), coordinateSelected.getRow() - 1, coordinateSelected.getColumn());
					paintCanvaWithoutClicked(coordinateSelected);
				} else {									//no activated
	        		radioButtonFoxVertical.setSelected(false);
	        	}
			}
		});
		
		
		radioButtonDifficultyStarter.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {							
					labelLevelDifficulty.setText("STARTER");
	        	}
			}
		});
		
		radioButtonDifficultyJunior.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {							
					labelLevelDifficulty.setText("JUNIOR");
	        	}
			}
		});
		
		radioButtonDifficultyExpert.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {							
					labelLevelDifficulty.setText("EXPERT");
	        	}
			}
		});
		
		textFieldNameLevel.textProperty().addListener((observable, oldValue, newValue) -> {
				labelLevelName.setText(newValue.toUpperCase());
		});
		
		GuiApp.setPlaying(true);
		
		radioButtonDifficultyStarter.setSelected(true);
		
		try {
			textFieldNameLevel.setText(InfoController.getCurrentLevel().getName());
		} catch (FileNotFoundException | IllegalArgumentException | LevelException e1) {
			e1.printStackTrace();
		}
		resetLevel();
				
		Level lev = null;
		try {
			lev = InfoController.getCurrentLevel();
			if(lev.getDifficulty() == LevelDifficulty.STARTER) radioButtonDifficultyStarter.setSelected(true);
			if(lev.getDifficulty() == LevelDifficulty.JUNIOR) radioButtonDifficultyJunior.setSelected(true);
			if(lev.getDifficulty() == LevelDifficulty.EXPERT) radioButtonDifficultyExpert.setSelected(true);
			
		} catch (FileNotFoundException | IllegalArgumentException | LevelException e) {
			e.printStackTrace();
		}
		
		table = lev.getBoard2D();
		
		if(InfoController.isTestedLevel()) buttonSave.setDisable(false);
		else buttonSave.setDisable(true);
		
		paint();	
    }
	
	@FXML
	public void resetLevel() {
		Music.playSound(SoundType.BUTTON);
		for(int i = 0; i < 5; i++) {
    		for(int j = 0; j < 5; j++ ) {
    			table[i][j]=null;
    		}
		}
		resetButtonsPieces();
    	
		paint();
	}
	
	@FXML
	public void actionButtonBack() {
		Music.playSound(SoundType.BUTTON);
		try{
			GuiApp.main.createView("MyLevels.fxml","css-MyLevels.css");
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
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
		
	@FXML
	public void actionButtonInformation() {
		Music.playSound(SoundType.BUTTON);
		InformationPopUpWindow window = new InformationPopUpWindow();
		window.createView();
	}
	
	private void setPiece(Piece piece, int positionX, int positionY) {
		table[positionX][positionY] = piece;
		paint();
	}
	
	private void onClick(Coordinate coord){
    	Optional<Node> nodeOptional = canvas.getChildren().stream().filter(n -> n.getTranslateX()/83.33333333333==coord.getColumn() && n.getTranslateY()/83.33333333333 == coord.getRow()).findFirst(); //83.33333333333 
    	Node node = nodeOptional.get();

    	node.getStyleClass().add("piece-selectable-clicked");
    	
    	radioButtonGrass.setSelected(false);
    	radioButtonMushroom.setSelected(false);
    	radioButtonHole.setSelected(false);
    	radioButtonBunny.setSelected(false);
    	radioButtonBunnyBrown.setSelected(false);
    	radioButtonBunnyWhite.setSelected(false);
    	radioButtonBunnyGray.setSelected(false);
    	radioButtonFox.setSelected(false);
    	radioButtonFoxHorizontal.setSelected(false);
    	radioButtonFoxVertical.setSelected(false);
    	radioButtonFoxHorizontalLeft.setSelected(false);
    	radioButtonFoxHorizontalRight.setSelected(false);
    	radioButtonFoxVerticalUp.setSelected(false);
    	radioButtonFoxVerticalDown.setSelected(false);
    	
		radioButtonGrass.setDisable(false);
		radioButtonMushroom.setDisable(false);
		radioButtonHole.setDisable(false);
    	radioButtonBunny.setDisable(false);
    	radioButtonBunnyBrown.setDisable(false);
    	radioButtonBunnyWhite.setDisable(false);
    	radioButtonBunnyGray.setDisable(false);
    	radioButtonFox.setDisable(false);
    	radioButtonFoxHorizontal.setDisable(false);
    	radioButtonFoxVertical.setDisable(false);
    	radioButtonFoxHorizontalLeft.setDisable(false);
    	radioButtonFoxHorizontalRight.setDisable(false);
    	radioButtonFoxVerticalUp.setDisable(false);
    	radioButtonFoxVerticalDown.setDisable(false);
    	
    	if(coordinateSelected.getRow() == 0) {
    		radioButtonFoxVerticalDown.setDisable(true);
    	}
    	
    	if(coordinateSelected.getRow() == 4) {
    		radioButtonFoxVerticalUp.setDisable(true);
    	}
    	
    	if(coordinateSelected.getColumn() == 0) {
    		radioButtonFoxHorizontalRight.setDisable(true);
    	}
    	
    	if(coordinateSelected.getColumn() == 4) {
    		radioButtonFoxHorizontalLeft.setDisable(true);
    	}
    	
    	if(coordinateSelected.getRow() >= 0 && coordinateSelected.getRow() <= 4 && coordinateSelected.getColumn() - 1 >= 0 && coordinateSelected.getColumn() - 1 <= 4) {
			if(!(table[coordinateSelected.getRow()][coordinateSelected.getColumn() - 1] instanceof Grass))  {
				radioButtonFoxHorizontalRight.setDisable(true);
			}
    	}
    	
    	if(coordinateSelected.getRow() >= 0 && coordinateSelected.getRow() <= 4 && coordinateSelected.getColumn() + 1 >= 0 && coordinateSelected.getColumn() + 1 <= 4) {
    		if(!(table[coordinateSelected.getRow()][coordinateSelected.getColumn() + 1] instanceof Grass))  {
    			radioButtonFoxHorizontalLeft.setDisable(true);
    		}
    	}
    	
    	if(coordinateSelected.getRow() + 1 >= 0 && coordinateSelected.getRow() + 1 <= 4 && coordinateSelected.getColumn() >= 0 && coordinateSelected.getColumn() <= 4) {
    		if(!(table[coordinateSelected.getRow() + 1][coordinateSelected.getColumn()] instanceof Grass)) {
    			radioButtonFoxVerticalUp.setDisable(true);
    		}
    	}
    	
    	if(coordinateSelected.getRow() - 1 >= 0 && coordinateSelected.getRow() - 1 <= 4 && coordinateSelected.getColumn() >= 0 && coordinateSelected.getColumn() <= 4) {
    		if(!(table[coordinateSelected.getRow() - 1][coordinateSelected.getColumn()] instanceof Grass)) {
    			radioButtonFoxVerticalDown.setDisable(true);
    		}
    	}
    	
    	if(pieceSelected instanceof Grass) {
    		radioButtonGrass.setSelected(true);
    	} else if(pieceSelected instanceof Mushroom) {
    		radioButtonMushroom.setSelected(true);
    	} else if(pieceSelected instanceof Hole) {
    		radioButtonHole.setSelected(true);
    	} else if(pieceSelected instanceof Bunny) {
    		radioButtonBunny.setSelected(true);
    		if(((Bunny)pieceSelected).getSymbol().toString().contains("b")) {
    			radioButtonBunnyBrown.setSelected(true);
    		} else if(((Bunny)pieceSelected).getSymbol().toString().contains("g")) {
    			radioButtonBunnyGray.setSelected(true);
    		} else if(((Bunny)pieceSelected).getSymbol().toString().contains("w")) {
    			radioButtonBunnyWhite.setSelected(true);
    		}
    	} else if(pieceSelected instanceof Fox) {
    		if(((Fox)pieceSelected).getSymbol().toString().contains("<")) {
    			radioButtonFoxHorizontal.setSelected(true);
    			radioButtonFoxHorizontalLeft.setSelected(true);
    		} else if (((Fox)pieceSelected).getSymbol().toString().contains("⊣")){
    			pieceSelected = ((FoxTail)table[coord.getRow()][coord.getColumn()]).getOtherHalf();
    			coordinateSelected = pieceSelected.getCoord();
    			radioButtonFoxHorizontal.setSelected(true);
    			radioButtonFoxHorizontalLeft.setSelected(true);
    		} else if (((Fox)pieceSelected).getSymbol().toString().contains(">")) {
    			radioButtonFoxHorizontal.setSelected(true);
    			radioButtonFoxHorizontalRight.setSelected(true);
    		} else if (((Fox)pieceSelected).getSymbol().toString().contains("⊢")) {
    			pieceSelected = ((FoxTail)table[coord.getRow()][coord.getColumn()]).getOtherHalf();
    			coordinateSelected = pieceSelected.getCoord();
    			radioButtonFoxHorizontal.setSelected(true);
    			radioButtonFoxHorizontalRight.setSelected(true);
    		} else if (((Fox)pieceSelected).getSymbol().toString().contains("^")) {
    			radioButtonFoxVertical.setSelected(true);
    			radioButtonFoxVerticalUp.setSelected(true);
    		} else if (((Fox)pieceSelected).getSymbol().toString().contains("⊥")) {
    			pieceSelected = ((FoxTail)table[coord.getRow()][coord.getColumn()]).getOtherHalf();
    			coordinateSelected = pieceSelected.getCoord();
    			radioButtonFoxVertical.setSelected(true);
    			radioButtonFoxVerticalUp.setSelected(true);
    		} else if (((Fox)pieceSelected).getSymbol().toString().contains("V")) {
    			radioButtonFoxVertical.setSelected(true);
    			radioButtonFoxVerticalDown.setSelected(true);
    		} else if (((Fox)pieceSelected).getSymbol().toString().contains("T")) {
    			pieceSelected = ((FoxTail)table[coord.getRow()][coord.getColumn()]).getOtherHalf();
    			coordinateSelected = pieceSelected.getCoord();
    			radioButtonFoxVertical.setSelected(true);
    			radioButtonFoxVerticalDown.setSelected(true);
    		}
    	}
    	
    	InfoController.setTestedLevel(false);
    	buttonSave.setDisable(true);
    	
    	paintCanvaWithoutClicked(coordinateSelected);
    }
	
	private void paint() {
    	ObservableList<Node> nodeList =  FXCollections.observableArrayList();        
    	canvas.getChildren().clear();
    	    	    	
    	for(int i = 0; i < 5; i++) {
    		for(int j = 0; j < 5; j++ ) {
    			StackPane sprite = new StackPane();    	
    			ImageView spriteImage;

    			if(table[i][j] == null) {
    				spriteImage = new ImageView(new Image("file:src/savethebunniesclient/util/img/"+"grass.png"));   
    				table[i][j] = new Grass(new Coordinate(i, j));
    			} else {
    				spriteImage = new ImageView(new Image("file:src/savethebunniesclient/util/img/"+table[i][j].getSymbol().getImageSrc()));    	
    			}
	    		
	    		spriteImage.setFitWidth(80);
	    		spriteImage.setFitHeight(80);
	    		sprite.getChildren().add(spriteImage);
	    		sprite.setTranslateX(83.33333333333*table[i][j].getCoord().getColumn());
	    		sprite.setTranslateY(83.33333333333*table[i][j].getCoord().getRow());

	    		sprite.getStyleClass().add("piece-selectable");
	    			    		
	    		nodeList.addAll(sprite);
	    		
	    		Piece piece = table[i][j];
	    		
	    		sprite.setOnMouseClicked(e ->{    	
	    			pieceSelected = piece;
		    		coordinateSelected = piece.getCoord();
	    			onClick(piece.getCoord());
	    		}); 	
    		}	
    		
    	}
    	
    	canvas.getChildren().addAll(nodeList);
	}
	
	private void paintCanvaWithoutClicked(Coordinate coordinate) {
		int coordinate1 = coordinate.getRow();
		int coordinate2 = coordinate.getColumn();
		
		ObservableList<Node> nodeList =  FXCollections.observableArrayList();        
    	canvas.getChildren().clear();
    	    	    	
    	for(int i = 0; i < 5; i++) {
    		for(int j = 0; j < 5; j++ ) {    			
    			StackPane sprite = new StackPane();    	
    			ImageView spriteImage;
    			if(table[i][j] == null) {
    				spriteImage = new ImageView(new Image("file:src/savethebunniesclient/util/img/"+"grass.png"));   
    				table[i][j] = new Grass(new Coordinate(i, j));
    			} else {
    				spriteImage = new ImageView(new Image("file:src/savethebunniesclient/util/img/"+table[i][j].getSymbol().getImageSrc()));    	
    			}
	
		    	spriteImage.setFitWidth(80);
		    	spriteImage.setFitHeight(80);
		    	sprite.getChildren().add(spriteImage);
		    	sprite.setTranslateX(83.33333333333*table[i][j].getCoord().getColumn());
		    	sprite.setTranslateY(83.33333333333*table[i][j].getCoord().getRow());
		    	
		    	if(!(i==coordinate1 && j==coordinate2)) {
		    		sprite.getStyleClass().add("piece-selectable");
		    	}else {
		    		sprite.getStyleClass().add("piece-selectable-clicked");
		    	}
		    	
		    	nodeList.addAll(sprite);
		    		
		    	Piece piece = table[i][j];
		    		
		    	sprite.setOnMouseClicked(e ->{    	
		    		pieceSelected = piece;
		    		coordinateSelected = piece.getCoord();
		    		onClick(piece.getCoord());
		    	}); 	

    		}	
    		
    	}
    	canvas.getChildren().addAll(nodeList);
	}
	
	private void deleteFox() {
		pieceSelected = table[coordinateSelected.getRow()][coordinateSelected.getColumn()];
		
		setPiece(new Grass(((FoxHead)pieceSelected).getOtherHalf().getCoord()), ((FoxHead)pieceSelected).getOtherHalf().getCoord().getRow(), ((FoxHead)pieceSelected).getOtherHalf().getCoord().getColumn());
		setPiece(new Grass(coordinateSelected), coordinateSelected.getRow(), coordinateSelected.getColumn());

	}
	
	private void resetButtonsPieces() {
		radioButtonGrass.setSelected(false);
		radioButtonMushroom.setSelected(false);
		radioButtonHole.setSelected(false);
    	radioButtonBunny.setSelected(false);
    	radioButtonBunnyBrown.setSelected(false);
    	radioButtonBunnyWhite.setSelected(false);
    	radioButtonBunnyGray.setSelected(false);
    	radioButtonFox.setSelected(false);
    	radioButtonFoxHorizontal.setSelected(false);
    	radioButtonFoxVertical.setSelected(false);
    	radioButtonFoxHorizontalLeft.setDisable(false);
    	radioButtonFoxHorizontalRight.setDisable(false);
    	radioButtonFoxVerticalUp.setDisable(false);
    	radioButtonFoxVerticalDown.setDisable(false);
    	
		radioButtonGrass.setDisable(true);
		radioButtonMushroom.setDisable(true);
		radioButtonHole.setDisable(true);
    	radioButtonBunny.setDisable(true);
    	radioButtonBunnyBrown.setDisable(true);
    	radioButtonBunnyWhite.setDisable(true);
    	radioButtonBunnyGray.setDisable(true);
    	radioButtonFox.setDisable(true);
    	radioButtonFoxHorizontal.setDisable(true);
    	radioButtonFoxVertical.setDisable(true);
    	radioButtonFoxHorizontalLeft.setDisable(true);
    	radioButtonFoxHorizontalRight.setDisable(true);
    	radioButtonFoxVerticalUp.setDisable(true);
    	radioButtonFoxVerticalDown.setDisable(true);
	}
	
	@FXML
	private void actionButtonPlay() {
		Music.playSound(SoundType.BUTTON);
		LevelDifficulty difficulty = null;
		if(radioButtonDifficultyStarter.isSelected()) difficulty = LevelDifficulty.STARTER;
		if(radioButtonDifficultyJunior.isSelected())  difficulty = LevelDifficulty.JUNIOR;
		if(radioButtonDifficultyExpert.isSelected())  difficulty = LevelDifficulty.EXPERT;
		
		String nameLevel = labelLevelName.getText();
		try {
			Level level = new Level(table, LevelType.BUILDERMODE, 5,  difficulty, nameLevel,  User.getUsername(), InfoController.getCurrentLevelId());
			level.createFileOfLevel();
			if(level.getId() != -1) InfoController.setTesting(true);
			GuiApp.main.createView("PlayLevel.fxml", "css-PlayLevel.css");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void actionButtonSave() {
		Music.playSound(SoundType.BUTTON);
		LevelDifficulty difficulty = null;
		if(radioButtonDifficultyStarter.isSelected()) difficulty = LevelDifficulty.STARTER;
		if(radioButtonDifficultyJunior.isSelected())  difficulty = LevelDifficulty.JUNIOR;
		if(radioButtonDifficultyExpert.isSelected())  difficulty = LevelDifficulty.EXPERT;
		
		String nameLevel = labelLevelName.getText();
		Level level = new Level(table, LevelType.BUILDERMODE, 5,  difficulty, nameLevel);
		level.setAutor(User.getUsername());
		if(InfoController.getCurrentLevelId() == -1) ConnectionServer.addNewLevel(User.getUsername(), level.toString());
		else ConnectionServer.modifyLevel(User.getUsername(), level.toString(), InfoController.getCurrentLevelId());
		try {
			GuiApp.main.createView("MyLevels.fxml", "css-MyLevels.css");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}