package net.JeuxDeMob;

import javafx.beans.property.*;

public class User {
	protected IntegerProperty id;
	protected StringProperty pseudo;
	protected StringProperty mail;
	protected StringProperty mdp;
	protected IntegerProperty admin;
	
	
	public User(int id,String pseudo, String email, String mdp, int admin) {
		this.id = new SimpleIntegerProperty(id);
		this.pseudo = new SimpleStringProperty(pseudo);
		this.mail = new SimpleStringProperty(email);
		this.mdp = new SimpleStringProperty(mdp);
		this.admin = new SimpleIntegerProperty(admin);
	}
	
	
	
	public StringProperty mdpProperty() {
		return mdp;
	}
	public String getMdp() {
		return mdp.get();
	}
	public void setMdp(String mdp) {
		this.mdp.set(mdp);
	}
	
	public StringProperty mailProperty() {
		return mail;
	}
	public String getMail() {
		return mail.get();
	}
	public void setMail(String email) {
		this.mail.set(email);
	}
	
	public IntegerProperty adminProperty() {
		return admin;
	}
	public int getAdmin() {
		return admin.get();
	}
	public void setAdmin(int admin) {
		this.admin.set(admin);
	}
	
	public IntegerProperty idProperty() {
		return id;
	}
	public int getId() {
		return id.get();
	}
	public void setId(int id) {
		this.id.set(id);
	}
	
	public StringProperty pseudoProperty() {
		return pseudo;
	}
	public String getPseudo() {
		return pseudo.get();
	}
	public void setPseudo(String pseudo) {
		this.pseudo.set(pseudo);
	}

}
