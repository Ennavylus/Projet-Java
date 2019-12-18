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

			if(partyIsFinish()) {
				
					System.out.println("--------Partie terminer -----------");
					return;
				}

			
			if(played.isUser()) {
				System.out.println("------------------------------------\nplayer = "+played.getPseudo());
				System.out.println("-----main-----");
				for(Card c : played.getHandCards()) {
					System.out.println(c.getName());
				}
				if(!iHaveNoPossibilities()) {
				TableGameController.phase1();
				return;
				}
				nextPlayer();
			
			}
			else {
				System.out.println("------------------------------------\nplayer = "+played.getPseudo());
				System.out.println("-----main-----");
				for(Card c : played.getHandCards()) {
					System.out.println(c.getName());
				}
				if(!iHaveNoPossibilities()) {
					int rand = new Random().nextInt(played.getHandCards().size());
					Card c = played.getHandCards().get(rand);
					while(inHandFigurine(c.getName(),played)) {
						rand = new Random().nextInt(played.getHandCards().size());
						c = played.getHandCards().get(rand);
					}
					if(toPlayCard(c.toString()))nextPlayer();
					
				}
				else {
					System.out.println("ok next player");
					nextPlayer();
				}

			}
		}
	public boolean toPlayCard(String card) {
		int posPlayer = howIsFiguring(card);
		System.out.println("player target "+posPlayer);
		Player focus = null;
		if(posPlayer<30) focus = this.getPlayerList().get(posPlayer);
		
		if(posPlayer <50) {
			if(whatNumberCardInHand(card, focus )<30){
				System.out.println("ok "+played.getPseudo()+" contre  "+focus.getPseudo());
				System.out.println("carte  = "+card);
				if(focus.isUser()) {
					System.out.println("ok is user");
					TableGameController.phaseContre(played.getHandCards().get(whatNumberCardInHand(card, played)));
					return false;
				}
				else {
					contre(focus,card);
					if( whatNumberCardInHand(card, played)<30) {
						System.out.println("double contre");
						TableGameController.refreshFigPlayer(this.playerTurn, posPlayer, card);
						played.getHandCards().remove(whatNumberCardInHand(card, played));
						toPioche(played);
					}
					return true;
				}
			}
			played.addFigurine(this.getPlayerList().get(posPlayer).getHandFigurine().get(card));
			this.getPlayerList().get(posPlayer).getHandFigurine().remove(card);
		}
		else {
			played.addFigurine(this.deck.getDeckFigurine().get(card));
			this.deck.getDeckFigurine().remove(card);

		}
		System.out.println("carte  = "+card);
		System.out.println("carte "+card+"   remove n° = "+whatNumberCardInHand(card, played));
		TableGameController.refreshFigPlayer(this.playerTurn, posPlayer, card);
		
		played.getHandCards().remove(whatNumberCardInHand(card, played));
		toPioche(played);
		return true;
	}
	public void contre(Player focus, String card){
		played.getHandCards().remove(whatNumberCardInHand(card, played));
		System.out.println(played.getPseudo());
		toPioche(played);
		focus.getHandCards().remove(whatNumberCardInHand(card, focus));
		System.out.println(focus.getPseudo());
		toPioche(focus);
	}
	
	// to add card for player P
	public boolean toPioche(Player player) {
		if(this.getDeck().getDeck().size()==0)return false;
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
	public int whatNumberCardInHand(String name, Player p) {
		int pos = 0;
		for(int i = 0; i<p.getHandCards().size();i++) {
			String c = p.getHandCards().get(i).getName();
			if(name == c) {
				return pos;
			}
		pos++;
		}
		return 50;
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
	// to verifie is this player have possibilitie to play 
	public boolean iHaveNoPossibilities() {
		int count = 0; 
		for (Map.Entry mapentry : played.getHandFigurine().entrySet()) {
			for(int i =0; i<played.getHandCards().size();i++) {
				if(played.getHandCards().get(i).getName() == mapentry.getKey().toString()) {
					count++;
					break;
				}

			}
		}
		if(count ==played.getHandCards().size()) {
			played.setNoPossibilities(true);
			return true;
		}
		played.setNoPossibilities(false);
		return false;

//		int firstRand;
//		if(played.getHandCards().size()<1)return true;
//		int rand = new Random().nextInt(played.getHandCards().size());
//		Card c = played.getHandCards().get(cardX(rand));
//		firstRand= rand;
//		while(inHandFigurine(c.getName(),played)) {
//			count++;
//			rand ++;
//			if(rand == played.getHandCards().size()) {
//				rand =0;
//			}
//			System.out.println("rand = "+ rand+"  count = "+count + " nombre de carte main : "+played.getHandCards().size());
//			c = played.getHandCards().get(cardX(rand));
//			if(rand==firstRand)
//		}
//		played.setNoPossibilities(false);
//		System.out.println("possibilitie false");
//		
	}
	// to verified  if i have figuring in my hand
	public boolean inHandFigurine(String card, Player p) {
		int count = 0;
		String[] listHand= new String[5];
		for (Map.Entry mapentry : played.getHandFigurine().entrySet()) {
			if(card == mapentry.getKey().toString()) {
				return true ;
			}
		}
		return false;
	}

	// to verifie is all player havent possibilitie of game
	public boolean partyIsFinish(){
		int count = 0;
		for(int i = 0; i<this.getNbPlayerTotal(); i++) {
			if(this.getPlayerList().get(i).isNoPossibilities()) {
				count++;
			}
		}
		return count==this.getNbPlayerTotal();
		
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
