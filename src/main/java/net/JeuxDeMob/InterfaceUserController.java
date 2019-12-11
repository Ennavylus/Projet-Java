package net.JeuxDeMob;


import java.io.IOException;
import java.sql.*;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;

public class InterfaceUserController {

	@FXML
	Button clear;
	@FXML
	Button modify;
	@FXML
	Button disconnect;
	@FXML
	Label pseudo;
	@FXML
	Label nbPlayed;
	@FXML
	Label nbWin;
	@FXML
	Label nbLoose;
	@FXML
	ImageView faceProfil;
	@FXML
	ChoiceBox<String> gameMod;
	@FXML
	ChoiceBox<Integer> nbPlayers;
	public void initialize(){
		var db= DataBase.getInstance();
		ResultSet res = db.query("SELECT pseudo, nbPartie,Victoire,defaite,urlProfil FROM utilisateur as U JOIN historique as H ON U.id = H.id_utilisateur where U.id="+LogInController.id+";");
		try {
			if(res.next()) {
				// App.setRoot("LogIn");
			}
	
			pseudo.setText(res.getString("pseudo"));
			nbPlayed.setText(res.getString("nbPartie"));
			nbWin.setText(res.getString("victoire"));
			nbLoose.setText(res.getString("defaite"));
		
			
			faceProfil.setImage(new Image(getClass().getResourceAsStream("prof/enna.png")));
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void goDisconnect() throws IOException {
		 App.setRoot("LogIn");
	}
	public void goModify() throws IOException {
		 App.setRoot("modifyUser");
	}
	public void goClear() {
		
	}
	
	
}
