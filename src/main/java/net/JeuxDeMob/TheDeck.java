package net.JeuxDeMob;

import java.util.ArrayList;
import java.util.Collections;
import java.sql.*;

public class TheDeck {
	
	private ArrayList<Card> deck;
	private ArrayList<Figurine> figurine;
	private String[] listUrl;
	private ArrayList<Card> defausse;
	private int countCard;
	
	TheDeck(String nomDeck) throws SQLException{
		var db = DataBase.getInstance();
		ResultSet res = db.query("Select name, url from card ;");
		this.setDeck(new ArrayList<Card>());
		this.setDefausse(new ArrayList<Card>());
		this.setFigurine(new ArrayList<Figurine>());
		while(res.next()) {
			String url = nomDeck+"/"+res.getString("url");
			String name = res.getString("name");
			this.getFigurine().add(new Figurine(name,nomDeck+"/f"+res.getString("url")));
			for(int i =0; i<5; i++) {
				this.getDeck().add(new Card(url, name));
				this.countCard++;
			}
		}
        for(int i =0; i<5;i++){
            Collections.shuffle(this.getDeck());
           }
        
		for(int i =0; i<5; i++) {
			this.getDefausse().add(this.getDeck().get(i));
			this.getDeck().remove(i);
			this.countCard--;
		}
        
	}
	
	public boolean toPioche(Player player) {
		if(player.addCard(this.getDeck().get(0))) {
			this.getDeck().remove(0);
			this.countCard--;
			return true;
		}
		return false;
		
	}
	
	
	
	

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}

	public String[] getListUrl() {
		return listUrl;
	}

	public void setListUrl(String[] listUrl) {
		this.listUrl = listUrl;
	}

	public ArrayList<Card> getDefausse() {
		return defausse;
	}
	public void setDefausse(ArrayList<Card> defausse) {
		this.defausse = defausse;
	}

	public int getCountCard() {
		return countCard;
	}

	public void setCountCard(int countCard) {
		this.countCard = countCard;
	}

	public ArrayList<Figurine> getFigurine() {
		return figurine;
	}

	public void setFigurine(ArrayList<Figurine> figurine) {
		this.figurine = figurine;
	}

}
