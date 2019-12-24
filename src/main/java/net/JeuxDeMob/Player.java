package net.JeuxDeMob;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player {
	
	private String pseudo;
	private int idPlayer;
	private boolean user;
	private boolean youTurn;
	private ResultSet res;
	private int score;
	private ImageView skin;
	private HashMap<String, Figurine> handFigurine;
	private ArrayList<Card> handCards;
	private boolean noPossibilities;
	
	private static int[] previousSkin;
	private static int countComputer;

	public Player(boolean user ) {
		if(user) {
			previousSkin = new int[3];
			countComputer = 0;
			previousSkin[0]=100;			
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
			this.setPseudo("Computer "+(countComputer+1));
			this.setUser(false);
			this.setSkin(new ImageView(new Image(getClass().getResourceAsStream("joueur/"+this.chooseSkin()+".png"))));
		}
		this.setNoPossibilities(false);
		this.setScore(0);
		this.setYouTurn(false);
		this.setHandFigurine(new HashMap<String, Figurine>());
		this.setHandCards(new ArrayList<Card>());
	}
	
	// allows to define Skin of computer 
	public int chooseSkin() {
		int rand ;
		int count;
		do {
			rand =new Random().nextInt(8);
			System.out.println("rand = "+rand);
			count = 0;
			for(int i = 0; i<countComputer; i++) {
				if(rand==previousSkin[i]) {
					count++;
					System.out.println("rand = "+rand+" pevious = "+previousSkin[i]);
				}

			}	
		}while(count>0);
		previousSkin[countComputer] = rand;
		countComputer++;
		return rand;
	}
	
	// allows to add card in hand card after pick in deck
	public boolean addCard(Card card) {
		if(card!=null&&this.getHandCards().size()<5) {
			this.handCards.add(card);
			return true;
		}
		return false;	
	}
	// allows to add figurine in hand figurine after play card
	public boolean addFigurine(Figurine figurine) {
		if(figurine!=null) {
			this.handFigurine.put(figurine.getName(), figurine);
			return true;
		}
		return false;	
	}

	
	// Getter and setter
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

	public HashMap<String, Figurine> getHandFigurine() {
		return handFigurine;
	}
	public void setHandFigurine(HashMap<String, Figurine> handFigurine) {
		this.handFigurine = handFigurine;
	}

	public ArrayList<Card> getHandCards() {
		return handCards;
	}
	public void setHandCards(ArrayList<Card> handCards) {
		this.handCards = handCards;
	}

	public boolean isNoPossibilities() {
		return noPossibilities;
	}
	public void setNoPossibilities(boolean noPossibilities) {
		this.noPossibilities = noPossibilities;
	}
	
}
