package net.JeuxDeMob;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;


public class TableGameController {
	@FXML Button quit;
	@FXML AnchorPane cardList;
	@FXML ImageView card0;
	@FXML ImageView card1;
	@FXML ImageView card2;
	@FXML ImageView card3;
	@FXML ImageView card4;
	@FXML ImageView pnj1;
	@FXML ImageView pnj2;
	@FXML ImageView pnj3;
	
	private Game game;
	private ArrayList<ImageView> hand;
	
	public void initialize() throws SQLException{
		this.game = new Game("pony", 3);
		System.out.println(game.getPlayerList().get(0).getPseudo());
		card0.setImage( this.game.getPlayerList().get(0).getHandCard().get(0).getImage() );
		card1.setImage(this.game.getPlayerList().get(0).getHandCard().get(1).getImage()); 
		card2.setImage(this.game.getPlayerList().get(0).getHandCard().get(2).getImage());
		card3.setImage(this.game.getPlayerList().get(0).getHandCard().get(3).getImage());
		card4.setImage(this.game.getPlayerList().get(0).getHandCard().get(4).getImage());
		
	
		
		
		
		
		
		
	}
	public void onClickCard(MouseEvent e) {
		var card = (ImageView) e.getSource();
		System.out.println(card);
		
		
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
	public ArrayList<ImageView> getHand() {
		return hand;
	}
	public void setHand(ArrayList<ImageView> hand) {
		this.hand = hand;
	}
	
	
}
