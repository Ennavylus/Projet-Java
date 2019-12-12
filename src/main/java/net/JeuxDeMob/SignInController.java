package net.JeuxDeMob;


import java.io.IOException;
import java.sql.*;

import javafx.fxml.*;
import javafx.scene.control.*;


public class SignInController {
	@FXML
	Button inscription;
	
	@FXML
	Button annuler;
	@FXML
	TextField pseudo;
	@FXML
	TextField mail;
	@FXML
	PasswordField password;
	@FXML
	PasswordField passwordConfirm;
	@FXML
	Label errorPseudo;
	@FXML
	Label errorPass;
	@FXML
	Label errorMail;
	
	public void returnLogIn() throws IOException {
		 App.setRoot("LogIn");
	}
	

	public void valideInscription() {
		errorPseudo.setText(null);
		errorMail.setText(null);
		errorPass.setText(null);
		String email = mail.getText();
		String pseudoLog = pseudo.getText();
		String passLog = password.getText();
		String passConf = passwordConfirm.getText();
		
		var db= DataBase.getInstance();
		ResultSet res = db.query("SELECT mail FROM utilisateur where mail='"+email+"';");
		int count=0;
		try {
			if(res.next()) {
				count++;
				errorMail.setText("Email non valide ou deja enregistré ");
			}
			ResultSet res1 = db.query("SELECT pseudo FROM utilisateur where pseudo='"+pseudoLog+"';");
			
			if(res1.next()) {
				count++;
				errorPseudo.setText("Pseudo deja utilisé ");
			}			
			if(!passLog.equals(passConf)) {
				count++;
				errorPass.setText("Mot de passe non identique");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	if(count==0) {
		DataBase.getInstance().insertUtilisateur(pseudoLog, email, passLog);
		try {
			App.setRoot("LogIn");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		
		
		
	}
	
}
