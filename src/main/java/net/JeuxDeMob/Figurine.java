package net.JeuxDeMob;

import javafx.scene.image.*;

public class Figurine extends ImageView{
	private String name;
	
	
	Figurine(String name, String url){
		this.name = name;
		this.setImage(new Image(getClass().getResourceAsStream(url)));
		this.setPreserveRatio(true);
		this.setFitHeight(60);
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
