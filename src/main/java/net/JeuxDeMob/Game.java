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
	private static Player played;
	
	
	

	public Game(String style, int nbPlayer) throws SQLException {
		nbPlayerTotal = nbPlayer+1;
		this.setDeck(new TheDeck(style));
		this.setPlayerList(new ArrayList<Player>());
		playerList.add(new Player(true));
		for(int i = 0; i<nbPlayer; i++) {
			playerList.add(new Player(false));
		}
		for(int i = 0; i<this.nbPlayerTotal; i++) {
			while(toPioche(playerList.get(i)));;
			
		}
		this.playerTurn = new Random().nextInt(this.getNbPlayerTotal());
		this.playerTurn = 0;
		
	}
	
	public void runTurn() {
		
			played = this.getPlayerList().get(getPlayerTurn());
			if(played.isUser()) {
				System.out.println("ok player = "+played.getPseudo());
				TableGameController.phase1();
				
				return;
			}
			else {
				System.out.println("ok player = "+played.getPseudo());
				int rand = new Random().nextInt(played.getHandCards().size());
				Card c = played.getHandCards().get(cardX(rand));
				System.out.println("carte n° = "+rand);
				while(inHandFigurine(c.getName(),played)) {
					rand = new Random().nextInt(played.getHandCards().size());
					c = played.getHandCards().get(cardX(rand));
				}
			toPlayCard(c.toString());
			}
		}
	public void toPlayCard(String card) {
		int posPlayer = howIsFiguring(card);
		if(posPlayer <50) {
			if(this.getPlayerList().get(posPlayer).getHandCards().get(card)!=null) {
//				if(this.getPlayerList().get(this.playerTurn).isUser()) {
//					TableGameController.phaseContre();
//				}
			}
			played.addFigurine(this.getPlayerList().get(posPlayer).getHandFigurine().get(card));
			this.getPlayerList().get(posPlayer).getHandFigurine().remove(card);
			
		}
		else {
			played.addFigurine(this.deck.getDeckFigurine().get(card));
			this.deck.getDeckFigurine().remove(card);

		}
		TableGameController.refreshFigPlayer(this.playerTurn, posPlayer, card);
		nextPlayer();
		
	}
	// to add card for player P
	public boolean toPioche(Player player) {
		if(player.addCard(this.getDeck().getDeck().get(0))) {
			this.getDeck().getDeck().remove(0);
			this.getDeck().setCountCard(this.getDeck().getCountCard()-1);
			return true;
		}
		return false;	
	}
	//to allows to switch next players
	public void nextPlayer() {
		this.getPlayerList().get(this.playerTurn).setYouTurn(false);
		this.playerTurn++;
		if(this.playerTurn==this.getNbPlayerTotal())this.playerTurn=0;
		this.getPlayerList().get(this.playerTurn).setYouTurn(true);
		runTurn();
	}
	// give key card in index X
	public String cardX(int nb) {
		String[] handCards = new String[5];
		int count =0;
		for (Map.Entry mapentry : played.getHandCards().entrySet()) {
			handCards[count] = mapentry.getKey().toString(); 			
			count++;
			
			}
		return handCards[nb];
	}
	// to verified how is figuring
	public int howIsFiguring(String nameCard) {
		int count;
		for(int i = 0; i<nbPlayerTotal;i++) {
			Player p = this.playerList.get(i);
			String[] HandCards = new String[5];
			count =0;
			for (Map.Entry mapentry : p.getHandFigurine().entrySet()) {
				HandCards[count] = mapentry.getKey().toString(); 
				if(nameCard == mapentry.getKey().toString()) {
					count++;
					break;
				}
			}
			if(count ==1) return i;	
			}
			
		return 99;
		}
	// to verified  if i have figuring in my hand
	public boolean inHandFigurine(String card, Player p) {
		int count = 0;
		String[] listHand= new String[5];
		for (Map.Entry mapentry : played.getHandCards().entrySet()) {
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
