package net.JeuxDeMob;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class Player {
	private String pseudo;
	private int idPlayer;
	private ArrayList<Card> handCard;
	private boolean user;
	private ResultSet res;

	public Player(boolean user ) {
		if(user) {
			this.setIdPlayer(LogInController.id);
			this.setUser(true);
			res = DataBase.getInstance().query("SELECT pseudo FROM utilisateur WHERE id="+this.getIdPlayer());
				try {
					if(res.next())this.setPseudo(res.getString("pseudo"));
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		else {
			this.setPseudo("Computer");
			this.setUser(false);
		}
		this.setHandCard(new ArrayList<Card>());
	}
	
	
		
	
	public boolean addCard(Card card) {
		if(card!=null&&this.getHandCard().get(4)==null) {
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
	public boolean isUser() {
		return user;
	}
	public void setUser(boolean user) {
		this.user = user;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public int getIdPlayer() {
		return idPlayer;
	}
	public void setIdPlayer(int idPlayer) {
		this.idPlayer = idPlayer;
	}

}
