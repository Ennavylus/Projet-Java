package net.JeuxDeMob;

import java.io.IOException;
import java.sql.*;
import java.util.Random;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;


public class TableGameController {
	@FXML Button quit;
	@FXML ImageView card1;
	@FXML ImageView card2;
	@FXML ImageView card3;
	@FXML ImageView card4;
	@FXML ImageView card5;
	@FXML ImageView pnj1;
	@FXML ImageView pnj2;
	@FXML ImageView pnj3;
	
	public void initialize(){
		
		
		
		
		
	}
	
	public void onHover(MouseEvent e) {
		var card = (ImageView)e.getSource();
		if(card.getLayoutY()==0.0) card.setLayoutY(152);
		else card.setLayoutY(0);
	
	}
	public void goQuit() {
		try {
			App.setRoot("InterfaceUser");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
