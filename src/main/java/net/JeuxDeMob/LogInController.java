package net.JeuxDeMob;

import java.io.IOException;
import java.sql.*;
import java.sql.SQLException;

import javafx.fxml.*;
import javafx.scene.control.*;

public class LogInController {

	static Integer id;
	
	@FXML
	Button logIn;
	
	@FXML
	Button signIn;
	
	@FXML
	TextField pseudo;
	
	@FXML
	PasswordField password;
	
	@FXML
	Label error;
	
	@FXML
	public void goSignIn() throws IOException {
		 App.setRoot("SignIn");
	}
	
	public void over() {
		
	}
	
	public void goLogIn() throws IOException {
		error.setText(null);
		String pseudolog = pseudo.getText();
		String passLog = password.getText();
		var db= DataBase.getInstance();
		String request = "SELECT id, pseudo, mdp FROM utilisateur where pseudo='"+pseudolog+"';";
		ResultSet res = db.query(request);
		try {

			if(!res.next()) {
				error.setText("Pseudo inconnu");
				return;
			}
			System.out.println("merci momo");
			String verif = res.getString("mdp");
			if(verif.equals(passLog)) {
				id= res.getInt("id");
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
