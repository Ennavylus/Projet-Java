package net.JeuxDeMob;

import java.sql.*;

public class DataBase {
	static private DataBase instance;
	Connection cnx;
	// PreparedStatement  for player insert
	private PreparedStatement insertStatement;
	private PreparedStatement insertPartieStatement;
	private PreparedStatement updateStatement;
	
	private PreparedStatement deleteStatement;
	
	
	private DataBase() {
		String url = "jdbc:mysql://localhost/jeuxdumob?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	    String user = "root", pswd = "56421988";
		try {
			cnx = DriverManager.getConnection(url,user,pswd);
			insertStatement = cnx.prepareStatement("INSERT INTO Utilisateur (Pseudo,mail,mdp,urlProfil, admin) VALUE(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			updateStatement = cnx.prepareStatement("UPDATE Utilisateur SET Pseudo=?, mail = ?, mdp = ?, urlProfil = ? WHERE id=?");
			insertPartieStatement = cnx.prepareStatement("INSERT INTO partie (JHPartie, nbMobWin, id_deck) VALUE(now(), ?, ?);",Statement.RETURN_GENERATED_KEYS);
			deleteStatement = cnx.prepareStatement("DELETE FROM ? WHERE ?=?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	static DataBase getInstance() {
		if(instance!=null) {
			return instance;
		}
		instance = new DataBase();
		return instance;
	}

/**
 * Execute an SQL query against the MySQL database
 * 
 * Example, execute an SQL query and iterate over the ResultSet :
 * 	var db = Database.getinstance();
 * 	var res =  db.query("SELECT * FROM MyTable");
 * 	while(res.next()){
 * 		sysout ....
 * 	}
 * 
 * @param queryString the sql query to execute
 * @return result of the given sql query
 */
	public ResultSet query(String queryString) {
		Statement stm;
		ResultSet res = null;
		try {
			stm = cnx.createStatement();
			res = stm.executeQuery(queryString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
		
	}
	
	public void addEndGame(boolean userWin, int nbWin, int deckStyle)  {

			try {
				insertPartieStatement.setInt(1, nbWin);
				insertPartieStatement.setInt(2, deckStyle);
				int inserted = insertPartieStatement.executeUpdate();
				ResultSet res  = insertPartieStatement.getGeneratedKeys();	
				if(res.next() && inserted>0 ) {
					var lastId = res.getInt(1);
				insertStatement.executeUpdate("INSERT INTO joue (id_Utilisateur, id_Partie, win) VALUE("+LogInController.id+","+lastId+","+userWin+" );");}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		refreshHistorique();
		

	}
	
	public void refreshHistorique()  {
		ResultSet res = this.getInstance().query("select * from(select count(win) as win, count(*) as partiTotal from joue as J where id_utilisateur="+LogInController.id+" group by win) as tab  ;");
		int total=0;
		int win=0; 
		int loose=0;
		
		
			try {
				if(res.next())
				win=res.getInt("win");
				if(res.next())loose=res.getInt("win");
				total = win+loose;
				updateStatement.executeUpdate("UPDATE historique SET nbPartie="+total+", Victoire = "+win+", defaite = "+loose+" WHERE id = "+LogInController.id+";");
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	public User insertUtilisateur(String pseudo, String mail, String mdp) {
		try {
			insertStatement.setString(1, pseudo);
			insertStatement.setString(2, mail);
			insertStatement.setString(3, mdp);
			insertStatement.setString(4, "prof/prof.png");
			insertStatement.setInt(5, 0);
			int inserted = insertStatement.executeUpdate();
			ResultSet res  = insertStatement.getGeneratedKeys();	
			if(res.next() && inserted>0 ) {
				var lastId = res.getInt(1);
				insertStatement.executeUpdate("INSERT INTO historique (id, nbPartie, Victoire, defaite, id_utilisateur) VALUES ("+lastId+",'0', '0', '0',"+lastId+");");
				return new User(lastId,pseudo, mail, mdp, 0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateThis(int id, String focus, String newValue ) {
		try {
			updateStatement.executeUpdate("UPDATE Utilisateur SET "+focus+"= '"+newValue+"'WHERE id="+id+";");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean clearStat() {
		try {
			updateStatement.executeUpdate("UPDATE Historique SET nbPartie = 0,Victoire = 0, defaite = 0 WHERE id_utilisateur ="+LogInController.id+";");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void deleteUser(int id) {
		try {

			deleteStatement.executeUpdate("DELETE FROM Utilisateur WHERE id="+id+";");
			deleteStatement.executeUpdate("DELETE FROM Historique WHERE id="+id+";");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateMe(String pseudo, String mail, String mdp, String url) {
		try {
			updateStatement.setString(1, pseudo );
			updateStatement.setString(2, mail);
			updateStatement.setString(3, mdp);
			updateStatement.setString(4, url);
			updateStatement.setInt(5, LogInController.id);
			updateStatement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}


