package net.JeuxDeMob;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Game {
	
	private TheDeck deck;
	private ArrayList<Player> playerList;
	private int nbPlayerTotal;
	private int playerTurn;
	
	
	

	public Game(String style, int nbPlayer) throws SQLException {
		nbPlayerTotal = nbPlayer+1;
		this.setDeck(new TheDeck(style));
		this.setPlayerList(new ArrayList<Player>());
		playerList.add(new Player(true));
		for(int i = 0; i<nbPlayer; i++) {
			playerList.add(new Player(false));
		}
		for(int i = 0; i<this.nbPlayerTotal; i++) {
			while(toPioche(playerList.get(i)));
		}
		this.playerTurn = new Random().nextInt(this.getNbPlayerTotal());
		this.playerTurn = 0;
		
	}
	
	public void runTurn() {
			if(this.getPlayerList().get(this.playerTurn).isUser()) {
				TableGameController.phase1();
				nextPlayer();
				return;
			}

		}


	public boolean toPioche(Player player) {
		if(player.addCard(this.getDeck().getDeck().get(0))) {
			this.getDeck().getDeck().remove(0);
			this.getDeck().setCountCard(this.getDeck().getCountCard()-1);
			return true;
		}
		return false;	
	}
	
	public void nextPlayer() {
		this.getPlayerList().get(this.playerTurn).setYouTurn(false);
		this.playerTurn++;
		if(this.playerTurn==this.getNbPlayerTotal())this.playerTurn=0;
		this.getPlayerList().get(this.playerTurn).setYouTurn(true);
	}

	public boolean inHandFigurine(String card) {
		int count = 0;
		String[] listHand= new String[5];
		for (Map.Entry mapentry : this.getPlayerList().get(0).getHandCards().entrySet()) {
			listHand[count] = mapentry.getKey().toString(); 

			count++;
			}
		count =0 ;
		for(String s : listHand) {
			if(s== card)count++;

		}
		if(count==1) {
			return false ;
		}
		else {
			return true;}
	}
	
	public ArrayList<Player> getPlayerList() {
		return playerList;
	}
	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}
	
	public TheDeck getDeck() {
		return deck;
	}
	public void setDeck(TheDeck deck) {
		this.deck = deck;
	}
	
	public int getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
	}
	
	public int getNbPlayerTotal() {
		return nbPlayerTotal;
	}

}
