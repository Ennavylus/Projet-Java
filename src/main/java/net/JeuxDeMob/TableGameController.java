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
	@FXML Label labelFinish;
	
	private Game game;
	private ImageView lastCard;
	private ArrayList<ImageView> hand;
	private static boolean yes;
	private static int playerFocus;
	
	private static Player playerReel;
	private static Player playerPnj1;
	private static Player playerPnj2;
	private static Player playerPnj3;
	private static int nbcomputer;
	private static String cardContre;
	
	static Label labelFinishStat;
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
		this.nbcomputer = InterfaceUserController.nbComputerPlay;
		this.setStatic();
		choiceStat.setVisible(false);
		finishStat.setVisible(false);
		this.game = new Game(InterfaceUserController.styleCards, InterfaceUserController.nbComputerPlay);
		this.createCenterTable();
		this.setViewHandCards();
		this.setPnj(nbcomputer);
		playerReel = this.game.getPlayerList().get(0);
		this.game.runTurn();
	}
	

	// ------------------ Show windows to choice game user -----------------
	// show windows for played select card
	public static void phase1() {
		choiceStat.setVisible(true);
		actionToChoiceStat.setText("Selectionner une carte a jouer");
		contrePlayStat.setVisible(false);
		playCardStat.setVisible(true);
		passPlayStat.setVisible(false);
	}
	// show windows if user has possibility of counteract
	public static void phaseContre(Card card, String player) {
		cardContre = card.getName();
		actionToChoiceStat.setText(player+" veut vous prendre la figurine "+cardContre+"\nque faite vous?");
		contrePlayStat.setVisible(true);
		contrePlayStat.setText("Contre");
		passPlayStat.setVisible(true);
		playCardStat.setVisible(false);
		choiceStat.setVisible(true);
	}
	// show windows if adversary counteract card user and if user has possibility played another card
	public static void phaseContreContre(Card card, int playerFo, boolean phase2) {
		cardContre = card.getName();
		yes=phase2;
		playerFocus = playerFo;
		actionToChoiceStat.setText("Vous avez la possibiliter de retenter de prendre la figurine \nque faite vous?");
		contrePlayStat.setVisible(true);
		contrePlayStat.setText("Retentez");
		passPlayStat.setVisible(true);
		playCardStat.setVisible(false);
		choiceStat.setVisible(true);
	}
	// for button "contre", if user counter verify if computer has possibility of counteract  and move figurine if no couteract 
	public void contreCard() throws SQLException {
		choiceStat.setVisible(false);
		if(yes) {
			int posPlayer = this.game.howIsFiguring(cardContre);
			playerReel.addFigurine(this.game.getPlayerList().get(posPlayer).getHandFigurine().get(cardContre));
			this.game.getPlayerList().get(posPlayer).getHandFigurine().remove(cardContre);
			this.game.toPioche(this.game.played);
			this.game.played.getHandCards().remove(this.game.whatNumberCardInHand(cardContre, this.game.played));
			System.out.println("vous avez double contré");
			moveFig(handfigStat ,whatPlayer( playerFocus), cardContre);
			setViewHandCards();
			this.game.nextPlayer();
			yes=false;
			return;
		}
	
		this.game.getPlayerList().get(0).getHandCards().remove(this.game.whatNumberCardInHand(cardContre,playerReel));
		this.game.toPioche(playerReel);
		System.out.println("vous contré");
		setViewHandCards();
		this.game.toPioche(this.game.played);
		if( this.game.whatNumberCardInHand(cardContre, this.game.played)<30) {
			int posPlayer = this.game.howIsFiguring(cardContre);
			System.out.println(this.game.played.getPseudo()+" contre votre contre");
			this.game.played.addFigurine(this.game.getPlayerList().get(posPlayer).getHandFigurine().get(cardContre));
			this.game.getPlayerList().get(posPlayer).getHandFigurine().remove(cardContre);
			this.game.played.getHandCards().remove(this.game.whatNumberCardInHand(cardContre, this.game.played));
			TableGameController.refreshFigPlayer(this.game.playerTurn, posPlayer, cardContre);
			this.game.toPioche(this.game.played);
		}
		this.game.nextPlayer();
	}
	// for button, if user choice is not to counter
	public void passPhase() throws SQLException {
		choiceStat.setVisible(false);
		this.game.played.addFigurine(this.game.getPlayerList().get(0).getHandFigurine().get(cardContre));
		this.game.getPlayerList().get(0).getHandFigurine().remove(cardContre);
		this.game.toPioche(this.game.played);
		moveFig(whatPlayer(this.game.getPlayerTurn()), handfigStat, cardContre);
		setViewHandCards();
		this.game.nextPlayer();
	}
	// for button , play card to selected
	public void playCardButton() throws SQLException {
		if(lastCard==null)return;
		String nameCard = lastCard.getImage().toString();
		choiceStat.setVisible(false);
		
		this.lastCard.setLayoutY(152.0);
		this.lastCard=null;
		if(this.game.toPlayCard(nameCard))this.game.nextPlayer();
		setViewHandCards();
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
	
	// allows to refsh figuring off all;
	public static void moveFig(TilePane gamer, TilePane focusPlayer, String nameFig) {
		int i; 
		for(i =0;i<focusPlayer.getChildren().size();i++) {
			if(nameFig==focusPlayer.getChildren().get(i).toString()) {
				gamer.getChildren().add(focusPlayer.getChildren().get(i));
			}	
		}
	}
	// 	gives the right tilepane according to the number of players
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
				case 2: return figurinePnj3Stat;
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
	// refresh visual if figurine moved
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

	//------------ Creation on view TableGame-----------------
	// place in center table all figurines
	private void createCenterTable() {
		String []listeColor = {"rose","rouge","gris","noir","vert","orange","jaune","blanc","bleu","violet","marron"};
		for(String s : listeColor) {
			centreTable.getChildren().add(this.game.getDeck().getDeckFigurine().get(s));
		}
	}
	// set  visual of hand's card user
	private void setViewHandCards() {
		nbCardsHand = 0;
		viewCard(card0);
		viewCard(card1);
		viewCard(card2);
		viewCard(card3);
		viewCard(card4);
	}
	// set visual card 
	public boolean viewCard(ImageView card) {
		if(nbCardsHand==this.game.getPlayerList().get(0).getHandCards().size()) {
			card.setVisible(false);
			return false;
		}
		card.setImage(null);
		card.setImage(this.game.getPlayerList().get(0).getHandCards().get(nbCardsHand));
		nbCardsHand++;
		return true;
	}
	// set visual skin  for all computer played with the right places in table according to the number of players
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
		if(nbcomputer==3){
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
	// according ellement of view with static variable
	 public void setStatic() {
		 	labelFinishStat = labelFinish;
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
	
	//Getter and setter
	public ArrayList<ImageView> getHand() {
		return hand;
	}
	public void setHand(ArrayList<ImageView> hand) {
		this.hand = hand;
	}
	
	
}
