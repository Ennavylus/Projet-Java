package net.JeuxDeMob;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Game {
	
	private TheDeck deck;
	private ArrayList<Player> playerList;
	private int playerTurn;
	private static int nbPlayerTotal;
	
	
	
	public Game(String style, int nbPlayer) throws SQLException {
		nbPlayerTotal = nbPlayer+1;
		this.setDeck(new TheDeck(style));
		this.setPlayerList(new ArrayList<Player>());
		playerList.add(new Player(true));
		for(int i = 0; i<nbPlayer; i++) {
			playerList.add(new Player(false));
		}
		for(int i = 0; i<this.nbPlayerTotal; i++) {
			while(deck.toPioche(playerList.get(i)));
		}
		this.setPlayerTurn(new Random().nextInt(this.nbPlayerTotal));
		
	}
	
	public void run() {
		while(this.getDeck().getCountCard()!=0) {
			this.getPlayerList().get(this.playerTurn).setYouTurn(true);
			
			
			
			
			
			this.getPlayerList().get(this.playerTurn).setYouTurn(false);
			this.playerTurn++;
			if(this.playerTurn==this.nbPlayerTotal)this.playerTurn=0;
		}
		
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
