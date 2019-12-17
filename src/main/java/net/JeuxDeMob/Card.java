package net.JeuxDeMob;

import java.io.InputStream;

import javafx.scene.image.*;

public class Card extends Image {
	
	private String url;
	private String name;
	
	
	public Card(InputStream test,String name) {
		super(test);
		 // getClass().getResourceAsStream(url);
		//new Image(test);
		//super(test);
		//this.new Card(test);
		this.setName(name);
		
	}
	
	public String toString() {
		return name;
	}
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

		

}
