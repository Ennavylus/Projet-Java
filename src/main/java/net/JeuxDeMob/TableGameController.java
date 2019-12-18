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
	@FXML AnchorPane main;
	@FXML ImageView finish;
	@FXML ImageView card0;
	@FXML ImageView card1;
	@FXML ImageView card2;
	@FXML ImageView card3;
	@FXML ImageView card4;
	@FXML ImageView pnj1;
	@FXML ImageView pnj2;
	@FXML ImageView pnj3;
	@FXML Label pseudoPnj1;
	@FXML Label pseudoPnj2;
	@FXML Label pseudoPnj3;
	@FXML AnchorPane choice;	
	@FXML TilePane centreTable;
	@FXML TilePane handfig;
	@FXML TilePane figurinePnj1;
	@FXML TilePane figurinePnj2;	
	@FXML TilePane figurinePnj3;
	@FXML Label actionToChoice;
	
	private Game game;
	private ImageView lastCard;
	private ArrayList<ImageView> hand;
	
	private static Player playerReel;
	private static Player playerPnj1;
	private static Player playerPnj2;
	private static Player playerPnj3;
	private static int nbcomputer;
	
	static AnchorPane choiceStat;
	static Button playCardStat;
	static Button passPlayStat;
	static Button contrePlayStat;
	static TilePane centreTableStat;
	static TilePane figurinePnj1Stat;
	static TilePane figurinePnj2Stat;
	static TilePane figurinePnj3Stat;
	static TilePane handfigStat;
	static ImageView finishStat;
	static AnchorPane mainStat;
	static int nbCardsHand;
	static Label actionToChoiceStat;

	
	public void initialize() throws SQLException{
		lastCard=null;
		this.nbcomputer = 3;
		this.setStatic();
		choiceStat.setVisible(false);
		finishStat.setVisible(false);
		
		this.game = new Game("pony", this.nbcomputer);
		this.createCenterTable();
		this.setViewHandCards();
		this.setPnj(nbcomputer);
		playerReel = this.game.getPlayerList().get(0);
		this.game.runTurn();
	}
	

	
	public static void phase1() {
		choiceStat.setVisible(true);
		contrePlayStat.setVisible(false);
		passPlayStat.setVisible(false);
	}
	public static void phaseContre(Card card) {
		lastCard = card;
		contrePlayStat.setVisible(true);
		contrePlayStat.setOpacity(1);
		passPlayStat.setVisible(true);
		playCardStat.setVisible(false);
		choiceStat.setVisible(true);
	}
	
	public void contreCard() {
		
	}
	public void passPhase() {
		choiceStat.setVisible(false);
		this.game.nextPlayer();
	}
	
	public void playCardButton() {
		
		String nameCard = lastCard.getImage().toString();
		choiceStat.setVisible(false);
		
		this.lastCard.setLayoutY(152.0);
		this.lastCard=null;
		if(this.game.toPlayCard(nameCard))this.game.nextPlayer();
		setViewHandCards();
		
		;
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
		playCard.setDisable(this.game.inHandFigurine(lastCard.getImage().toString(),this.game.getPlayerList().get(0)));
		//playCard.setVisible(!this.game.inHandFigurine(lastCard.getImage().toString(),this.game.getPlayerList().get(0)));
	}

	// Interaction On Hover
	public void onLook(MouseEvent e) {
		setViewHandCards();
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
	
//	public static void refreshListFig(Player p, VBox vBox) {
//		String[] listHand = new String[10];
//		int i = 0;
//		for (Map.Entry mapentry : p.getHandFigurine().entrySet()) {
//			listHand[i] = mapentry.getKey().toString() ; 
//			i++;
//		}
//		for(String s:listHand) {
//			vBox.getChildren().add(p.getHandFigurine().get(s));
//		}
//	
//	}
	// allows to refsh figuring off all;
	public static void moveFig(TilePane gamer, TilePane focusPlayer, String nameFig) {
		int i; 
		for(i =0;i<focusPlayer.getChildren().size();i++) {
			if(nameFig==focusPlayer.getChildren().get(i).toString()) {
				gamer.getChildren().add(focusPlayer.getChildren().get(i));
			}	
		}
	}
	public static TilePane whatPlayer(int pos) {
		if(nbcomputer==1) {
			if(pos == 0)return handfigStat;
			if(pos == 1)return figurinePnj2Stat;
			else return null;
		}
		if(nbcomputer==2) {
			switch (pos) {
				case 0 : return handfigStat;
				case 1: return figurinePnj1Stat;
				case 2: return figurinePnj1Stat;
				default: return null;
			}
			
		}
		if(nbcomputer==3) {
			switch (pos) {
			case 0 : return handfigStat;
			case 1: return figurinePnj1Stat;
			case 2: return figurinePnj2Stat;
			case 3: return figurinePnj3Stat;
			default: return null;
			}
		}
		return null;
	}
	public static void refreshFigPlayer(int gamer,int posFocus, String name) {
		if(posFocus<50) {
			moveFig(whatPlayer(gamer), whatPlayer(posFocus), name);
		}
		else {
			int i; 
			for(i =0;i<centreTableStat.getChildren().size();i++) {
				if(name==centreTableStat.getChildren().get(i).toString()) {
					whatPlayer(gamer).getChildren().add(centreTableStat.getChildren().get(i));
				}	
			}
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
		nbCardsHand = 0;
		viewCard(card0);
		viewCard(card1);
		viewCard(card2);
		viewCard(card3);
		viewCard(card4);

	}
	public boolean viewCard(ImageView card) {
		if(nbCardsHand==this.game.getPlayerList().get(0).getHandCards().size()) {
			card.setVisible(false);
			//card.setImage(null);
			return false;
		}
		card.setImage(null);
		card.setImage(this.game.getPlayerList().get(0).getHandCards().get(nbCardsHand));
		nbCardsHand++;
		return true;
		
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
	 public void setStatic() {
			actionToChoiceStat = actionToChoice;
			mainStat = main;
			finishStat  = finish ;
			figurinePnj1Stat = figurinePnj1;
			figurinePnj2Stat = figurinePnj2;
			figurinePnj3Stat = figurinePnj3;
			handfigStat = handfig;
			centreTableStat = centreTable;
			playCardStat = playCard;
			passPlayStat = passPlay;
			contrePlayStat =contrePlay;
			choiceStat = choice;

	 }
	
	//Getteur and setteur
	public ArrayList<ImageView> getHand() {
		return hand;
	}
	public void setHand(ArrayList<ImageView> hand) {
		this.hand = hand;
	}
	
	
}
