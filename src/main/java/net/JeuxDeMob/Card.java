package net.JeuxDeMob;

import javafx.scene.image.*;

public class Card extends ImageView {
	
	private String url;
	private String name;
	
	
	public Card(String url, String name) {
		this.setImage(new Image(getClass().getResourceAsStream(url)));
		this.setName(name);
		
	}



	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

		

}
