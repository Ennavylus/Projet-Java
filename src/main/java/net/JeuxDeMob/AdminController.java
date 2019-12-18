package net.JeuxDeMob;

import java.io.IOException;
import java.sql.*;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
	@FXML Label Warning;
	
	@FXML ObservableList<User> userCollection;

	@FXML TableView listUser;
	@FXML TableColumn pseudo;
	@FXML TableColumn email;
	@FXML TableColumn mdp;
	@FXML TableColumn admin;
	@FXML TextField inputModify;
	
	private ResultSet res;
	protected IntegerProperty nbPlay;
	protected IntegerProperty nbW;
	protected IntegerProperty nbL;
	private User focusUser;
	private String focusColumn;
	
	
	
	public void initialize(){
		userCollection =  FXCollections.observableArrayList();
		var db= DataBase.getInstance();
		res = db.query("SELECT * FROM utilisateur;");
		try {
			while(res.next()) {
				userCollection.add(new User(res.getInt("id"),res.getString("pseudo"),res.getString("mail"),res.getString("mdp"),res.getInt("admin") ));
			}
		} catch (SQLException e) {
				e.printStackTrace();
			}
		listUser.setItems(userCollection);
		pseudo.setCellValueFactory(new PropertyValueFactory<User,String>("pseudo"));
		email.setCellValueFactory(new PropertyValueFactory<User,String>("mail"));
		mdp.setCellValueFactory(new PropertyValueFactory<User,String>("mdp"));
		admin.setCellValueFactory(new PropertyValueFactory<User,Integer>("admin"));
		listUser.getSelectionModel().selectedItemProperty().addListener(new OnSelectUser());
	//	listUser.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		listUser.getSelectionModel().setCellSelectionEnabled(true);
		//listUser.setEditable(true);
		
	}
	
	
	
	
	
	public void lookClick(MouseEvent e) throws SQLException {
		TablePosition pos = (TablePosition) listUser.getSelectionModel().getSelectedCells().get(0);
		int row = pos.getRow();
		User user = (User) listUser.getItems().get(row);
		this.focusUser= user;
		TableColumn column = pos.getTableColumn();
		this.focusColumn = column.getText();
		res = DataBase.getInstance().query("SELECT pseudo, nbPartie,Victoire,defaite,urlProfil,admin FROM utilisateur as U LEFT JOIN historique as H ON U.id = H.id_utilisateur where U.pseudo='"+user.getPseudo()+"';");
		res.next();
		this.nbPlay = new SimpleIntegerProperty(res.getInt("nbPartie"));
		this.nbW = new SimpleIntegerProperty(res.getInt("victoire"));
		this.nbL = new SimpleIntegerProperty(res.getInt("defaite"));
		if(nbPlay==null) {
			nbPlay.set(0);nbW.set(0);nbL.set(0);
		}
		pseudoUser.setText(res.getString("pseudo"));
		nbPlayed.setText(""+nbPlay.get());
		nbWin.setText(""+nbW.get());
		nbLoose.setText(""+nbL.get());
		faceProfil.setImage(new Image(getClass().getResourceAsStream(res.getString("urlProfil"))));


	}
	public void goClear() {
		if(DataBase.getInstance().clearStat()) {
			nbPlay.set(0);
			nbPlayed.setText(""+nbPlay.get());
			nbWin.setText(""+nbPlay.get());
			nbLoose.setText(""+nbPlay.get());
		}
	}
	public void addUser() {
		String pseudo = inputModify.getText();
		if(pseudo.length()>2) {
			var newUser = DataBase.getInstance().insertUtilisateur(pseudo, "A saisir", "123");
			if(newUser == null) return;
			userCollection.add(newUser); 
			inputModify.clear();
			}	
	}
	public void deleteUser() throws SQLException {
		System.out.println(this.focusUser.getId());
		 DataBase.getInstance().deleteUser(this.focusUser.getId());
		 userCollection.remove(focusUser);
	}
	public void modifyUser() throws SQLException {
		String newValue = inputModify.getText();
		if(this.focusColumn.equals("Email")) {
			this.focusColumn = "mail";
			this.focusUser.setMail(newValue);
		}
		if(this.focusColumn.equals("Password")) {
			this.focusColumn = "mdp";
			this.focusUser.setMdp(newValue);
		}
		if(this.focusColumn.equals("admin")) {
			//int value=Integer.valueOf(newValue);
			this.focusUser.setAdmin(Integer.valueOf(newValue));
		}
		else {
			this.focusUser.setPseudo(newValue);
		}
		
		DataBase.getInstance().updateThis(focusUser.getId(), this.focusColumn, newValue);
		inputModify.clear();
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
