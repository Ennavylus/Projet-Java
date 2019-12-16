package net.JeuxDeMob;

import java.io.*;
import java.util.concurrent.TimeUnit;

import javafx.fxml.*;

public class LoadGameControleur {
	
	
	
	
	public void initialize() throws IOException {
			goGame();

			}
	public void goGame() throws IOException {
		App.setRoot("TableGame");
	}
}
