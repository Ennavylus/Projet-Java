package net.JeuxDeMob;

import java.sql.SQLException;
import java.util.ArrayList;

public class Game {
	
	private TheDeck deck;
	private ArrayList<Player> playerList;
	
	
	
	public Game(String style, int nbPlayer) throws SQLException {
		this.setDeck(new TheDeck(style));
		this.setPlayerList(new ArrayList<Player>());
		playerList.add(new Player(true));
		for(int i = 0; i<nbPlayer; i++) {
			playerList.add(new Player(false));
		}
		for(int i = 0; i<nbPlayer+1; i++) {
			while(deck.toPioche(playerList.get(i)));
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
}
