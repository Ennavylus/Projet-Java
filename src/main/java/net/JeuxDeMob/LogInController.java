package net.JeuxDeMob;

import java.io.IOException;
import java.sql.*;
import java.sql.SQLException;

import javafx.fxml.*;
import javafx.scene.control.*;

public class LogInController {

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
		String pseudolog = pseudo.getText();
		String passLog = password.getText();
		var db= DataBase.getInstance();
		String request = "SELECT pseudo, mdp FROM utilisateur where pseudo='"+pseudolog+"';";
		ResultSet res = db.query(request);
		try {
			res.next();
			System.out.println("merci momo");
			String verif = res.getString("mdp");
			if(verif.equals(passLog)) {
				App.setRoot("InterfaceUser");
			}
			else {
				System.out.println(" no !!!");
				error.setText("Mot de passe ou Pseudo incorect");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	
		
		
		
	}

	
}
