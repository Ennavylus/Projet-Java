package net.JeuxDeMob;

import java.util.ArrayList;

public class Player {
	private String pseudo;
	private int idPlayer;
	private ArrayList<Card> handCard;
	private ArrayList<Figurine> handFigurine;

	public Player(int idPayer, String pseudo ) {
		
		
	}
	
	
	
	
	
	
	
	public ArrayList<Card> getHandCard() {
		return handCard;
	}

	public void setHandCard(ArrayList<Card> handCard) {
		this.handCard = handCard;
	}

	public ArrayList<Figurine> getHandFigurine() {
		return handFigurine;
	}

	public void setHandFigurine(ArrayList<Figurine> handFigurine) {
		this.handFigurine = handFigurine;
	}

}
