package savethebunniesclient.controller.gui;

import savethebunniesclient.app.GuiApp;
import savethebunniesclient.controller.User;
import savethebunniesclient.model.game.Coordinate;
import savethebunniesclient.model.game.Grass;
import savethebunniesclient.model.game.Hole;
import savethebunniesclient.model.game.LevelException;
import savethebunniesclient.model.game.Movable;
import savethebunniesclient.model.game.Move;
import savethebunniesclient.model.game.Piece;
import savethebunniesclient.model.view.ConfigurationPopUpWindow;
import savethebunniesclient.model.view.DoubleOptionPopUpWindow;
import savethebunniesclient.model.view.InformationPopUpWindow;
import savethebunniesclient.model.view.MenuStoryLevel;
import savethebunniesclient.util.OnActionData;

import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class CreateLevelController {
	
	@FXML
	private RadioButton radioButtonGrass;
	@FXML
	private RadioButton radioButtonBunnie;
	@FXML
	private RadioButton radioButtonBunnieWhite;
	@FXML
	private RadioButton radioButtonBunnieBrown;
	@FXML
	private RadioButton radioButtonBunnieBlack;
	@FXML
	private RadioButton radioButtonFox;
	@FXML
	private RadioButton radioButtonFoxHorizontal;
	@FXML
	private RadioButton radioButtonFoxVertical;
	@FXML
	private RadioButton radioButtonDifficultyStarter;
	@FXML
	private RadioButton radioButtonDifficultyJunior;
	@FXML
	private RadioButton radioButtonDifficultyExpert;
	
	@FXML
	private Label labelLevelDiffuculty;
	@FXML
	private Label labelLevelName;
		
	@FXML 
	private TextField textFieldNameLevel;
	
	@FXML
	private Pane canvas;
	
	private boolean levelPassed = false;
	private Piece [][] table = new Piece[5][5];
	
	@FXML
    public void initialize() {
		
		ToggleGroup groupPiece = new ToggleGroup();
		radioButtonGrass.setToggleGroup(groupPiece);
		radioButtonBunnie.setToggleGroup(groupPiece);
		radioButtonFox.setToggleGroup(groupPiece);
		ToggleGroup groupBunnie = new ToggleGroup();
		radioButtonBunnieWhite.setToggleGroup(groupBunnie);
		radioButtonBunnieBrown.setToggleGroup(groupBunnie);
		radioButtonBunnieBlack.setToggleGroup(groupBunnie);
		ToggleGroup groupFox = new ToggleGroup();		
		radioButtonFoxHorizontal.setToggleGroup(groupFox);
		radioButtonFoxVertical.setToggleGroup(groupFox);
		ToggleGroup groupDifficulty = new ToggleGroup();
		radioButtonDifficultyStarter.setToggleGroup(groupDifficulty);
		radioButtonDifficultyJunior.setToggleGroup(groupDifficulty);
		radioButtonDifficultyExpert.setToggleGroup(groupDifficulty);
		
		radioButtonGrass.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {							
	        		setPiece();
	        	} else {									
	        		radioButtonGrass.setSelected(false);
	        	}	
			}
		});
		
		radioButtonBunnie.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {								
	        		setPiece();								//Bunnie 
	        		radioButtonBunnieWhite.setSelected(true);
	        	} else {									
	        		radioButtonBunnieWhite.setSelected(false);
	        		radioButtonBunnieBrown.setSelected(false);
	        		radioButtonBunnieBlack.setSelected(false);
	        	}	
			}
		});
		
		radioButtonBunnieWhite.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {							
	        		setPiece();
	        	}
			}
		});
		
		radioButtonBunnieBrown.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {							
	        		setPiece();
	        	}
			}
		});
		
		radioButtonBunnieBlack.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {							
	        		setPiece();
	        	}
			}
		});
		
		radioButtonFox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {								//activated
	        		if(checkPosition()) {
	        			setPiece();		//horizontal fox
	        			radioButtonFoxHorizontal.setSelected(true);
	        		} else if(checkPosition()) {
	        			setPiece();		//vertical fox
	        			radioButtonFoxVertical.setSelected(true);
	        		} else {
	        			//mensaje de que no se puede porque no hay espcio suficiente para poner el elemento seleccionado
	        			setPiece();		//grass
	        			radioButtonGrass.setSelected(true);
	        		}
	        	} else {									//no activated
	        		radioButtonFoxHorizontal.setSelected(false);
	        		radioButtonFoxVertical.setSelected(false);
	        	}	
			}
		});
		
		radioButtonDifficultyStarter.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {							
					labelLevelDiffuculty.setText("STARTER");
	        	}
			}
		});
		radioButtonDifficultyJunior.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {							
					labelLevelDiffuculty.setText("JUNIOR");
	        	}
			}
		});
		
		radioButtonDifficultyExpert.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {							
					labelLevelDiffuculty.setText("EXPERT");
	        	}
			}
		});
		
		textFieldNameLevel.textProperty().addListener((observable, oldValue, newValue) -> {
				labelLevelName.setText(newValue);
			});
    }
		
	
	@FXML
	public void resetLevel() {
		//Reset Todo el nivel para que se quede todo grass
	}
	
	@FXML
	public void actionButtonBack() {
		try{
			GuiApp.main.createView("MyLevels.fxml","css-MyLevels.css");
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
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
		
	@FXML
	public void actionButtonInformation() {
		InformationPopUpWindow window = new InformationPopUpWindow();
		window.createView();
	}
	
	private void setPiece(Piece piece, int positionX, int positionY) {
		table[positionX-1][positionY-1] = piece;
	}
	
	private void onClick(Coordinate coord){
    	Optional<Node> nodeOptional = canvas.getChildren().stream().filter(n -> n.getTranslateX()/83.33333333333==coord.getColumn() && n.getTranslateY()/83.33333333333 == coord.getRow()).findFirst(); //83.33333333333 
    	Node node = nodeOptional.get();
    	node.getStyleClass().add("clicked");
    		
		paint();
		
		
    }
	
	private void paint() {
    	ObservableList<Node> nodeList =  FXCollections.observableArrayList();        
    	canvas.getChildren().clear();
    	    	    	
    	for(int i = 0; i < 5; i++) {
    		for(int j = 0; j < 5; j++ ) {

	    		StackPane sprite = new StackPane();    		
	    		ImageView spriteImage = new ImageView(new Image("file:src/savethebunniesclient/util/img/"+table[i][j].getSymbol().getImageSrc()));    	
	    		spriteImage.setFitWidth(80);
	    		spriteImage.setFitHeight(80);
	    		sprite.getChildren().add(spriteImage);
	    		sprite.setTranslateX(83.33333333333*table[i][j].getCoord().getColumn());
	    		sprite.setTranslateY(83.33333333333*table[i][j].getCoord().getRow());
	    		
	    		if(table[i][j] instanceof Movable) {    			
	    			sprite.getStyleClass().add("piece-movable");    			
	    		}
	    		
	    		if(table[i][j] instanceof Grass || table[i][j] instanceof Hole) {    			
	    			sprite.getStyleClass().add("piece-movable-destination");    			
	    		}
	    			    		
	    		nodeList.addAll(sprite);
	    		
	    		Piece piece = table[i][j];
	    		
	    		sprite.setOnMouseClicked(e ->{    			
	    			onClick(piece.getCoord());
	    		}); 	
    		}	
    		
    	}
    	
    	canvas.getChildren().addAll(nodeList);
    	
    }
}




