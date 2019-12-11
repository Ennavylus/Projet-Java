package net.JeuxDeMob;


import java.io.IOException;
import java.sql.*;
import java.sql.SQLException;

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
	
	public void returnLogIn() {
		 App.setRoot("LogIn");
	}
	
}
