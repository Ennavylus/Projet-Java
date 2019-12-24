package net.JeuxDeMob;

import javafx.scene.image.*;

/**
 * This Class is a  figurine for the game, 
 * this extends Class ImageView
 * 
 * @author Ennavylus
 *
 */
public class Figurine extends ImageView{
	private String name;
	
	/**
	 * Constructor for Figurine
	 * @param name this name for this figurine
	 * @param url this url for upload this figurine
	 */
	Figurine(String name, String url){
		this.name = name;
		this.setImage(new Image(getClass().getResourceAsStream(url)));
		this.setPreserveRatio(true);
		this.setFitHeight(60);
	}

	//	Redefine to string methode
	public String toString() {
		return name;
	}
	//getter and setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
