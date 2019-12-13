package net.JeuxDeMob;

import java.util.ArrayList;
import java.util.Random;

public class Player {
	private String pseudo;
	private int idPlayer;
	private ArrayList<Card> handCard;


	public Player(int idPayer, String pseudo ) {
		
		
	}
	
	
		
	
	public boolean addCard(Card card) {
		if(card!=null) {
			this.handCard.add(card);
			return true;
		}
		return false;	
	}
	
	public boolean addCardToPlayer(Player player) {
		if(player.getHandCard().get(0)!=null&&this.getHandCard().get(4)==null) {
			int rand = new Random().nextInt(5);
			this.addCard(player.getHandCard().get(rand));
			player.getHandCard().remove(rand);	
			return true;
		}
		return false;
		
	}
	
	
	public ArrayList<Card> getHandCard() {
		return handCard;
	}

	public void setHandCard(ArrayList<Card> handCard) {
		this.handCard = handCard;
	}



}
