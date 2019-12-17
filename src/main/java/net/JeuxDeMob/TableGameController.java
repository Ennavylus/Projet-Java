package net.JeuxDeMob;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;


public class TableGameController {
	@FXML Button quit;
	@FXML  Button playCard;
	@FXML  Button passPlay;
	@FXML  Button contrePlay;
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
	@FXML AnchorPane choice;
	@FXML TilePane handfig;
	
	
	private Game game;
	private ArrayList<ImageView> hand;
	private Player playerPnj1;
	private Player playerPnj2;
	private Player playerPnj3;
	private int nbcomputer;
	private ImageView lastCard;
	static AnchorPane choiceStat;
	static Button playCardStat;
	static Button passPlayStat;
	static Button contrePlayStat;

	
	
	
	
	public void initialize() throws SQLException{
		lastCard=null;
		this.nbcomputer = 3;
		
		playCardStat = playCard;
		passPlayStat = passPlay;
		contrePlayStat =contrePlay;
		choiceStat = choice;
		choiceStat.setVisible(false);
		
		this.game = new Game("pony", this.nbcomputer);
		this.createCenterTable();
		this.setViewHandCards();
		this.setPnj(nbcomputer);
		game.runTurn();
	}
	

	
	public static void phase1() {
		choiceStat.setVisible(true);
		contrePlayStat.setVisible(false);
		passPlayStat.setVisible(false);
	}
	
	public void playCardButton() {
		
		String nameCard = lastCard.getImage().toString();
		
		if(isFigInOnTable(nameCard)<50) {
			int pos = isFigInOnTable(nameCard);
			this.handfig.getChildren().add(centreTable.getChildren().get(pos));

//			for( i = 0;i<4;i++) {
//				var card2 = this.game.getPlayerList().get(this.game.getPlayerTurn()).getHandCards().get(i);
//				if(nameCard.equals(card2.toString())) {
//					count++;
//					break;
//					}
//			}
		
			choiceStat.setVisible(false);
			
			this.game.getPlayerList().get(0).getHandCards().remove(nameCard);
			this.game.toPioche(this.game.getPlayerList().get(0));
			
			System.out.println("\napres pioche =");
			for (Map.Entry mapentry : this.game.getPlayerList().get(0).getHandCards().entrySet()) {
				System.out.println(mapentry.getKey()) ; }
			
			setViewHandCards();
			//this.lastCard.setImage(this.game.getPlayerList().get(this.game.getPlayerTurn()).getHandCards().get(4));
			this.lastCard.setLayoutY(152.0);
			this.lastCard=null;
			}
		else {
			
		}
			
		}

	public int isFigInOnTable(String nameCard) {
		for(int i = 0;i<centreTable.getChildren().size();i++) {
			var figurine = centreTable.getChildren().get(i);
			if(nameCard.equals(figurine.toString())) {
				return i;
			}
		}
		
		return 99;
	}
	
	// Interaction if click on card
	public void onClickCard(MouseEvent e) {
		var card = (ImageView) e.getSource();
		// allows to move in the initial position last Cards clicked
		if(lastCard!=null) {
			lastCard.setLayoutY(152);
			if(lastCard == card)	{
				card.setLayoutY(152);
				lastCard=null;
				return;
			}
		}
		else {
			card.setLayoutY(0);
		}
		lastCard =card;

		playCard.setDisable(this.game.inHandFigurine(lastCard.getImage().toString()));
	}
	
	

	// Interaction On Hover
	public void onLook(MouseEvent e) {
		var card = (ImageView)e.getSource();
		card.setLayoutY(0);
	}
	public void onExit(MouseEvent e) {
		var card = (ImageView)e.getSource();
		if(card.getLayoutY()==0.0&&card!=lastCard) 
			card.setLayoutY(152);
	}

	// Button Quit the Game
	public void goQuit() {
		try {
			App.setRoot("InterfaceUser");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// Creation on view TableGame
	private void createCenterTable() {
		String []listeColor = {"rose","rouge","gris","noir","vert","orange","jaune","blanc","bleu","violet","marron"};
		for(String s : listeColor) {
			centreTable.getChildren().add(this.game.getDeck().getDeckFigurine().get(s));
		}
	}

	private void setViewHandCards() {
		Object[] listHand = new Object[5];
		int i = 0;
		
		for (Map.Entry mapentry : this.game.getPlayerList().get(0).getHandCards().entrySet()) {
			listHand[i] = mapentry.getKey() ; 
			i++;
	        }
		card0.setImage(this.game.getPlayerList().get(0).getHandCards().get(listHand[0]));
		card1.setImage(this.game.getPlayerList().get(0).getHandCards().get(listHand[1]));
		card2.setImage(this.game.getPlayerList().get(0).getHandCards().get(listHand[2]));
		card3.setImage(this.game.getPlayerList().get(0).getHandCards().get(listHand[3]));
		card4.setImage(this.game.getPlayerList().get(0).getHandCards().get(listHand[4]));
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
			pseudoPnj3.setText(this.playerPnj3.getPseudo());
		
		}
		
	}
	
	
	//Getteur and setteur
	public ArrayList<ImageView> getHand() {
		return hand;
	}
	public void setHand(ArrayList<ImageView> hand) {
		this.hand = hand;
	}
	
	
}
