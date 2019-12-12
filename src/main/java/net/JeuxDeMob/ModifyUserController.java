package net.JeuxDeMob;

import java.io.IOException;
import java.sql.*;
import javafx.beans.property.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;

public class ModifyUserController {
	@FXML ImageView faceProfil;
	@FXML Label actualPseudo;
	@FXML Button Validate;
	@FXML Button annuler;
	@FXML TextField pseudo;
	@FXML TextField mail;
	@FXML PasswordField password;
	@FXML PasswordField passwordConfirm;
	@FXML Label errorPseudo;
	@FXML Label errorPass;
	@FXML Label errorMail;
	@FXML Button changeAvatar;
	@FXML ImageView mailModify;
	@FXML ImageView pseudoModify;
	@FXML ImageView mdpModify;
	
	private ResultSet res;
	protected StringProperty view;
	private TextField tamp;
	
	public void initialize(){
		 res = DataBase.getInstance().query("SELECT pseudo,mail,mdp, urlProfil FROM utilisateur where id="+LogInController.id+";");
		try {
			res.next();

			actualPseudo.setText(res.getString("pseudo"));
			view = new SimpleStringProperty(res.getString("mail"));
			mail.setText(view.get());
			mail.setOpacity(0.5);
			view.set(res.getString("pseudo"));
			pseudo.setText(view.get());
			pseudo.setOpacity(0.5);
			
			faceProfil.setImage(new Image(getClass().getResourceAsStream(res.getString("urlProfil"))));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void returnInterfaceUser() throws IOException {
		 App.setRoot("InterfaceUser");
	}
	
	public void mousseClickedPass(MouseEvent e) {
		PasswordField test = (PasswordField) e.getSource();
		System.out.println(tamp);
		if(test.getId()!=pseudo.getId()||test.getId()!=mail.getId()) {
			try {
				if(tamp==null)return;
				if(tamp.getText()!=null) {
					tamp = null;
					return;
				}
				view.set(res.getString(tamp.getId()));
				tamp.setText(view.get());
				tamp.setOpacity(0.5);
				tamp = null;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			return;
		}
	}
	public void mouseClickedMail(MouseEvent e) throws SQLException {
		TextField test = (TextField) e.getSource();
		System.out.println(!test.getText().equals(res.getString(test.getId())));
		if(!test.getText().equals(res.getString(test.getId()))) {
			if(tamp!=null ) {
				System.out.println("1"+tamp.getText());
				if(tamp.getText()==null||tamp.getLength()==0) {
					view.set(res.getString(tamp.getId()));
					tamp.setOpacity(0.5);
					tamp.setText(view.get());
					System.out.println("view   "+view.get());
					return;
				}
			}
			tamp = test;
			return;
		}
		if(tamp==null) tamp = test;
		view.set(null);
		test.setText(view.get());
		test.setOpacity(1);
		if(test.equals(tamp)) {
			tamp = test;
			return;
		}
		if(tamp.getText()!=null) {
			tamp = test;
			return;
		}
		view.set(res.getString(tamp.getId()));
		tamp.setOpacity(0.5);
		tamp.setText(view.get());
		tamp = test;
	}

	public void valideModify() {
		errorPseudo.setText(null);
		errorMail.setText(null);
		errorPass.setText(null);
		String email = mail.getText();
		String pseudoLog = pseudo.getText();
		String passLog = password.getText();
		String passConf = passwordConfirm.getText();

		ResultSet res = DataBase.getInstance().query("SELECT mail FROM utilisateur where mail='"+email+"';");
		int count=0;
		try {
			if(res.next()) {
				count++;
				errorMail.setText("Email non valide ou deja enregistré ");
			}
			ResultSet res1 = DataBase.getInstance().query("SELECT pseudo FROM utilisateur where pseudo='"+pseudoLog+"';");
			
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
		
		try {
			App.setRoot("InterfaceUser");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	}
	

}
