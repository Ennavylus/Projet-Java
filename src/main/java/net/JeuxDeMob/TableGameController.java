package net.JeuxDeMob;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;


import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;


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
	@FXML HBox centreTable;
	@FXML VBox figurinePnj1;
	@FXML VBox figurinePnj2;	
	@FXML VBox figurinePnj3;
	@FXML Label pseudoPnj1;
	@FXML Label pseudoPnj2;
	@FXML Label pseudoPnj3;
	
	
	private Game game;
	private ArrayList<ImageView> hand;
	private Player playerPnj1;
	private Player playerPnj2;
	private Player playerPnj3;
	private int nbcomputer;
	
	
	
	
	public void initialize() throws SQLException{
		this.nbcomputer = 3;
		this.game = new Game("pony", this.nbcomputer);
		card0.setImage( this.game.getPlayerList().get(0).getHandCard().get(0).getImage());
		card1.setImage(this.game.getPlayerList().get(0).getHandCard().get(1).getImage()); 
		card2.setImage(this.game.getPlayerList().get(0).getHandCard().get(2).getImage());
		card3.setImage(this.game.getPlayerList().get(0).getHandCard().get(3).getImage());
		card4.setImage(this.game.getPlayerList().get(0).getHandCard().get(4).getImage());
		this.setPnj(nbcomputer);
		
		for(int i = 0; i<11; i++) {
			centreTable.getChildren().add(this.game.getDeck().getFigurine().get(i));
		}
		
		
		
		
		
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
	
	public void setPnj(int nbcomputer) {
		if(nbcomputer==1) {
			playerPnj1 = this.game.getPlayerList().get(1);
			pnj2.setImage(this.playerPnj1.getSkin().getImage());
			pseudoPnj2.setText(this.playerPnj1.getPseudo());
			pnj1.setImage(null);
			pnj3.setImage(null);
		}
		if(nbcomputer==2) {
			playerPnj1 = this.game.getPlayerList().get(1);
			pnj1.setImage(this.playerPnj1.getSkin().getImage());
			pseudoPnj1.setText(this.playerPnj1.getPseudo());
			
			playerPnj2 = this.game.getPlayerList().get(2);
			pnj3.setImage(this.playerPnj2.getSkin().getImage());
			pseudoPnj3.setText(this.playerPnj2.getPseudo());
			
			pnj2.setImage(null);
		}
		else {
			playerPnj1 = this.game.getPlayerList().get(1);
			pnj1.setImage(this.playerPnj1.getSkin().getImage());
			pseudoPnj1.setText(this.playerPnj1.getPseudo());
			
			playerPnj2 = this.game.getPlayerList().get(2);
			pnj2.setImage(this.playerPnj2.getSkin().getImage());
			pseudoPnj2.setText(this.playerPnj2.getPseudo());
			
			playerPnj3 = this.game.getPlayerList().get(3);
			pnj3.setImage(this.playerPnj3.getSkin().getImage());
			pseudoPnj3.setText(this.playerPnj2.getPseudo());
		
		}
		
	}
	public ArrayList<ImageView> getHand() {
		return hand;
	}
	public void setHand(ArrayList<ImageView> hand) {
		this.hand = hand;
	}
	
	
}
