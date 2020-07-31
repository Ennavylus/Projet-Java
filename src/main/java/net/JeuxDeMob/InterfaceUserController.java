package net.JeuxDeMob;


import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.sql.*;
import java.util.Random;

import javafx.beans.property.*;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;

public class InterfaceUserController {
	private ResultSet res;
	protected IntegerProperty nbPlay;
	protected IntegerProperty nbW;
	protected IntegerProperty nbL;
	public static int nbComputerPlay;
	public static String styleCards;
	
	@FXML Button clear;
	@FXML Button modify;
	@FXML Button disconnect;
	@FXML Button goAdmin;
	@FXML Label pseudo;
	@FXML Label nbPlayed;
	@FXML Label nbWin;
	@FXML Label nbLoose;
	@FXML ImageView faceProfil;
	@FXML ChoiceBox gameMod;
	@FXML ChoiceBox nbPlayers;
	@FXML AnchorPane main;
	

	
	public void initialize(){
		var db= DataBase.getInstance();
		res = db.query("SELECT pseudo, nbPartie,Victoire,defaite,urlProfil,admin FROM utilisateur as U LEFT JOIN historique as H ON U.id = H.id_utilisateur where U.id="+LogInController.id+";");
		try {
			res.next();
			this.nbPlay = new SimpleIntegerProperty(res.getInt("nbPartie"));
			this.nbW = new SimpleIntegerProperty(res.getInt("victoire"));
			this.nbL = new SimpleIntegerProperty(res.getInt("defaite"));
			if(nbPlay==null) {
				nbPlay.set(0);nbW.set(0);nbL.set(0);
			}
			if(res.getInt("admin")!=1)goAdmin.setVisible(false);
			
			System.out.println(LogInController.id);
			pseudo.setText(res.getString("pseudo"));
			nbPlayed.setText(""+nbPlay.get());
			nbWin.setText(""+nbW.get());
			nbLoose.setText(""+nbL.get());
			faceProfil.setImage(new Image(getClass().getResourceAsStream(res.getString("urlProfil"))));
			nbPlayers.getItems().addAll(1,2,3);
			gameMod.getItems().addAll("pony","happy");
			gameMod.getSelectionModel().selectFirst();
			nbPlayers.getSelectionModel().selectFirst();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * launch game with number computer and style of carde game
	 * @throws IOException
	 */
	public void goGameLoad() throws IOException {
		styleCards = (String) gameMod.getValue();
		nbComputerPlay = (int) nbPlayers.getValue();
		System.out.println(styleCards);
		App.setRoot("TableGame");
	}
	//button for go in admin interface
	public void goAdminPage() throws IOException {
		App.setRoot("admin");
	}
	// Button for disconnect user and go in log in page
	public void goDisconnect() throws IOException {
		 App.setRoot("LogIn");
	}
	// button for go page to modify user data
	public void goModify() throws IOException {
		 App.setRoot("modifyUser");
	}
	// button for clear all game history
	public void goClear() {
		if(DataBase.getInstance().clearStat()) {
			nbPlay.set(0);
			nbPlayed.setText(""+nbPlay.get());
			nbWin.setText(""+nbPlay.get());
			nbLoose.setText(""+nbPlay.get());
		}
	}
}

	

