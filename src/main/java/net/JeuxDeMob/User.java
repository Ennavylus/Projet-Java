package net.JeuxDeMob;

import javafx.beans.property.*;

public class User {
	protected StringProperty pseudo;
	protected StringProperty email;
	protected StringProperty mdp;
	protected IntegerProperty admin;
	
	
	public User(String pseudo, String email, String mdp, int admin) {
		this.pseudo = new SimpleStringProperty(pseudo);
		this.email = new SimpleStringProperty(email);
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
	
	public StringProperty emailProperty() {
		return email;
	}
	public String getEmail() {
		return email.get();
	}
	public void setEmail(String email) {
		this.email.set(email);
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
