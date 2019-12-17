package net.JeuxDeMob;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player {
	private String pseudo;
	private int idPlayer;
	//private ArrayList<Card> handCard;
	private boolean user;
	private boolean youTurn;
	private ResultSet res;
	private int score;
	private ImageView skin;
	private static int[] previousSkin;
	private static int countComputer;
	//private ArrayList<Figurine> handFig;
	private HashMap<String, Figurine> handFigurine;
	private HashMap<String, Card> handCards;
	

	public Player(boolean user ) {
		previousSkin = new int[3];
		if(user) {
			countComputer = 0;
			this.setIdPlayer(LogInController.id);
			previousSkin[0]=100;
			this.setUser(true);
			res = DataBase.getInstance().query("SELECT pseudo FROM utilisateur WHERE id="+this.getIdPlayer());
				try {
					if(res.next())this.setPseudo(res.getString("pseudo"));
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		else {
			
			this.setPseudo("Computer "+countComputer);
			this.setUser(false);
			this.setSkin(new ImageView(new Image(getClass().getResourceAsStream("joueur/"+this.chooseSkin()+".png"))));
		}
		this.setScore(0);
		this.setYouTurn(false);
		//this.setHandCard(new ArrayList<Card>());
		//this.setHandFig(new ArrayList<Figurine>());
		this.setHandFigurine(new HashMap<String, Figurine>());
		this.setHandCards(new HashMap<String, Card>());
	}
	
	
		
	public int chooseSkin() {
		int rand ;
		int count;
		do {
			rand =new Random().nextInt(8);
			count = 0;
			
			for(int i = 0; i<3; i++) {
				if(rand==previousSkin[i])count++;
			}	
		}while(count>0);
		previousSkin[countComputer] = rand;
		countComputer++;
		return rand;
	}
	
	
	public boolean addCard(Card card) {
		if(card!=null&&this.getHandCards().size()<5) {
			this.handCards.put(card.getName(), card);
			return true;
		}
		return false;	
	}
	
	public boolean addFigurine(Figurine figurine) {
		if(figurine!=null) {
			this.handFigurine.put(figurine.getName(), figurine);
			return true;
		}
		return false;	
	}

	
	
	
	
//	public ArrayList<Card> getHandCard() {
//		return handCard;
//	}
//
//	public void setHandCard(ArrayList<Card> handCard) {
//		this.handCard = handCard;
//	}
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

	public boolean isYouTurn() {
		return youTurn;
	}
	public void setYouTurn(boolean youTurn) {
		this.youTurn = youTurn;
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	public ImageView getSkin() {
		return skin;
	}
	public void setSkin(ImageView skin) {
		this.skin = skin;
	}

//	public ArrayList<Figurine> getHandFig() {
//		return handFig;
//	}
//	public void setHandFig(ArrayList<Figurine> handFig) {
//		this.handFig = handFig;
//	}

	public HashMap<String, Figurine> getHandFigurine() {
		return handFigurine;
	}
	public void setHandFigurine(HashMap<String, Figurine> handFigurine) {
		this.handFigurine = handFigurine;
	}

	public HashMap<String, Card> getHandCards() {
		return handCards;
	}
	public void setHandCards(HashMap<String, Card> handCards) {
		this.handCards = handCards;
	}
	
//	public boolean addCardToPlayer(Player player) {
//		if(player.getHandCard().get(0)!=null&&this.getHandCard().get(4)==null) {
//			int rand = new Random().nextInt(5);
//			this.addCard(player.getHandCard().get(rand));
//			player.getHandCard().remove(rand);	
//			return true;
//		}
//		return false;
//		
//	}
}
