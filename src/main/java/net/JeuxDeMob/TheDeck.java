package net.JeuxDeMob;

import java.util.ArrayList;
import java.util.Collections;
import java.sql.*;

public class TheDeck {
	
	private ArrayList<Card> deck;
	private String[] listUrl;
	private ArrayList<Card> defausse;
	
	TheDeck(String nomDeck) throws SQLException{
		var db = DataBase.getInstance();
		ResultSet res = db.query("Select name, url as url from card ;");
		this.setDeck(new ArrayList<Card>());
		this.setDefausse(new ArrayList<Card>());
		while(res.next()) {
			String url = nomDeck+res.getString("url");
			String name = res.getString("name");
			for(int i =0; i<5; i++) {
				this.getDeck().add(new Card(url, name));
			}
		}
        for(int i =0; i<5;i++){
            Collections.shuffle(this.getDeck());
           }
        
		for(int i =0; i<5; i++) {
			this.getDefausse().add(this.getDeck().get(i));
			this.getDeck().remove(i);
		}
        
	}
	
	public boolean toPioche(Player player) {
		if(player.addCard(this.getDeck().get(0))) {
			this.getDeck().remove(0);
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

}
