package net.JeuxDeMob;

import java.io.InputStream;

import javafx.scene.image.*;

/**
 * this Class is for visual carte for the game , 
 * this extends Class Image
 * @author Ennavylus
 *
 */
public class Card extends Image {
	
	private String name;
	
	
	public Card(InputStream test,String name) {
		super(test);
		this.setName(name);
		
	}
	// Redefine method to String  
	public String toString() {
		return name;
	}
	// Getter and setter

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

		

}
