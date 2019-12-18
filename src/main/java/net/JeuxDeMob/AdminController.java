package net.JeuxDeMob;

import java.io.IOException;
import java.sql.*;


import javafx.beans.value.*;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.image.*;
import javafx.scene.input.*;

public class AdminController {
	@FXML Button clearStat;
	@FXML Button modifyUser;
	@FXML Button deleteUser;
	@FXML Button addUser;
	@FXML Button goInterfaceUser;
	@FXML Label pseudoUser;
	@FXML Label nbPlayed;
	@FXML Label nbWin;
	@FXML Label nbLoose;
	@FXML ImageView faceProfil;
	
	@FXML ObservableList<User> UserCollection;

	@FXML TableView listUser;
	@FXML TableColumn pseudo;
	@FXML TableColumn email;
	@FXML TableColumn mdp;
	@FXML TableColumn admin;
	
	private ResultSet res;
	public void initialize(){
		UserCollection =  FXCollections.observableArrayList();
		var db= DataBase.getInstance();
		res = db.query("SELECT pseudo, mail, mdp, admin FROM utilisateur;");
		try {
			while(res.next()) {
				UserCollection.add(new User(res.getString("pseudo"),res.getString("mail"),res.getString("mdp"),res.getInt("admin") ));
				System.out.println(res.getString("pseudo"));
			}
		} catch (SQLException e) {
				e.printStackTrace();
			}
		listUser.setItems(UserCollection);
		pseudo.setCellValueFactory(new PropertyValueFactory<User,String>("pseudo"));
		email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
		mdp.setCellValueFactory(new PropertyValueFactory<User,String>("mdp"));
		admin.setCellValueFactory(new PropertyValueFactory<User,Integer>("admin"));
		listUser.getSelectionModel().selectedItemProperty().addListener(new OnSelectUser());
	}
	
	
	
	
	
	public void lookClick(MouseEvent e) {
		
	}
	
	public void goLogByAdmin() {
		try {
			App.setRoot("InterfaceUser");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




	public class OnSelectUser implements ChangeListener<User> {
		@Override
		public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
		}
	}
}
