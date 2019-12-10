package net.JeuxDeMob;

import javafx.scene.image.*;

public class Card extends ImageView {
	
	private String url;
	private String name;
	
	
	public Card(String url) {
		this.setImage(new Image(url));
		
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}

		

}
