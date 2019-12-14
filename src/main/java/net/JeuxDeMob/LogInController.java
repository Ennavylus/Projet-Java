package net.JeuxDeMob;

import java.io.IOException;
import java.sql.*;
import java.sql.SQLException;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class LogInController {

	static Integer id;
	@FXML
	TextField pseudo;
	
	@FXML
	PasswordField password;
	
	@FXML
	Button logIn;
	@FXML ImageView adminImage;
	@FXML
	Button signIn;
	@FXML AnchorPane log; 
	@FXML AnchorPane logHome; 

	@FXML
	Label error;
	
	@FXML
	public void goSignIn() throws IOException {
		 App.setRoot("Register");
	}
	
	public void over() {
		
	}
	
	public void admin() {
		AnchorPane pane;
		try {
			pane = FXMLLoader.load(getClass().getResource("LogAdmin.fxml"));
			log.getChildren().setAll(pane);
			adminImage.setOpacity(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void goLogByAdmin() {
		try {
			App.setRoot("InterfaceUser");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void goAdmin() {
		try {
			App.setRoot("InterfaceUser");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void goLogIn() throws IOException {
		error.setText(null);
		String pseudolog = pseudo.getText();
		String passLog = password.getText();
		var db= DataBase.getInstance();
		String request = "SELECT id, pseudo, mdp, admin FROM utilisateur where pseudo='"+pseudolog+"';";
		ResultSet res = db.query(request);
		try {

			if(!res.next()) {
				error.setText("Pseudo inconnu");
				return;
			}
			String verif = res.getString("mdp");
			if(verif.equals(passLog)) {
				id= res.getInt("id");
				System.out.println("merci momo");
				if( res.getInt("admin")==1) {
					admin();
					return;
				}
				App.setRoot("InterfaceUser");
			}
			else {
				error.setText("Mot de passe incorect");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	
		
		
		
	}

	
}
