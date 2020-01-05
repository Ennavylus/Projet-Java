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
	
	@FXML TextField pseudo;
	@FXML PasswordField password;
	@FXML ImageView adminImage;
	@FXML Button logIn;
	@FXML Button signIn;
	@FXML Button admin;
	@FXML AnchorPane log; 
	@FXML AnchorPane logHome; 
	@FXML Label error;
	

	// for button , redirects to register page
	public void goRegister() throws IOException {
		 App.setRoot("Register");
	}
	// if admin detected show new windows for new choice to redirects
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
	// for button , redirects to user page
	public void goLogByAdmin() {
		try {
			App.setRoot("InterfaceUser");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// for button , if admin to connect  show redirects to admin page
	public void goAdmin() {
		try {
			App.setRoot("admin");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 *  for button , redirects to interface user if all verification is OK page
	 * @throws IOException
	 */
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
