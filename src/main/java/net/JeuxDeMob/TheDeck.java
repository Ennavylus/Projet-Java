package net.JeuxDeMob;

import java.util.ArrayList;
import java.util.Collections;

public class TheDeck {
	
	private ArrayList<Card> deck;
	private String[] listUrl;
	private ArrayList<Card> defausse;
	
	TheDeck(String[] listUrl){
		this.setDeck(new ArrayList<Card>());
		this.setDefausse(new ArrayList<Card>());
		this.setListUrl(new String[] {"/happy/blanc.png",
				"/happy/noir.png","/happy/violet.png",
				"/happy/gris.png", "/happy/bleu.png",
				"/happy/rose.png", "/happy/vert.png",
				"/happy/jaune.png", "/happy/marron.png",
				"/happy/rouge.png", "/happy/orange.png"} );
		for(String url : listUrl) {
			for(int i =0; i<5; i++) {
				this.getDeck().add(new Card(url));
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
