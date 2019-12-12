package net.JeuxDeMob;


import java.io.IOException;
import java.sql.*;
import javafx.beans.property.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;

public class InterfaceUserController {
	private ResultSet res;
	protected IntegerProperty nbPlay;
	protected IntegerProperty nbW;
	protected IntegerProperty nbL;
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
	ChoiceBox gameMod;
	@FXML
	ChoiceBox nbPlayers;
	public void initialize(){
		var db= DataBase.getInstance();
		res = db.query("SELECT pseudo, nbPartie,Victoire,defaite,urlProfil FROM utilisateur as U LEFT JOIN historique as H ON U.id = H.id_utilisateur where U.id="+LogInController.id+";");
		try {
			res.next();
			this.nbPlay = new SimpleIntegerProperty(res.getInt("nbPartie"));
			this.nbW = new SimpleIntegerProperty(res.getInt("victoire"));
			this.nbL = new SimpleIntegerProperty(res.getInt("defaite"));
			if(nbPlay==null) {
				nbPlay.set(0);nbW.set(0);nbL.set(0);
			}
			
			System.out.println(LogInController.id);
			pseudo.setText(res.getString("pseudo"));
			nbPlayed.setText(""+nbPlay.get());
			nbWin.setText(""+nbW.get());
			nbLoose.setText(""+nbL.get());
			faceProfil.setImage(new Image(getClass().getResourceAsStream(res.getString("urlProfil"))));
			nbPlayers.getItems().addAll(1,2,3);
			gameMod.getItems().addAll("Partie contre ordinateur","Joueur contre Joueur");
			
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
		if(DataBase.getInstance().clearStat()) {
			nbPlay.set(0);
			nbPlayed.setText(""+nbPlay.get());
			nbWin.setText(""+nbPlay.get());
			nbLoose.setText(""+nbPlay.get());
		}
	}
}
	
	

