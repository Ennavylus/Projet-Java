package net.JeuxDeMob;

import java.sql.*;

public class DataBase {
	static private DataBase instance;
	Connection cnx;
	// PreparedStatement  for player insert
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	
	
	private DataBase() {
		String url = "jdbc:mysql://localhost/jeuxdumob?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	    String user = "root", pswd = "56421988";
		try {
			cnx = DriverManager.getConnection(url,user,pswd);
			insertStatement = cnx.prepareStatement("INSERT INTO Utilisateur (Pseudo,mail,mdp,urlProfil) VALUE(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			updateStatement = cnx.prepareStatement("UPDATE Utilisateur SET Pseudo=?, mail = ?, mdp = ?, urlProfil = ? WHERE id=?");
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
	
	public boolean insertUtilisateur(String pseudo, String mail, String mdp) {
		try {
			insertStatement.setString(1, pseudo);
			insertStatement.setString(2, mail);
			insertStatement.setString(3, mdp);
			insertStatement.setString(4, "prof/prof.png");
			int inserted = insertStatement.executeUpdate();
			ResultSet res  = insertStatement.getGeneratedKeys();	
			if(res.next() && inserted>0 ) {
				var lastId = res.getInt(1);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean clearStat() {
		try {
			deleteStatement.executeUpdate("DELETE FROM Historique WHERE id_utilisateur ="+LogInController.id+";");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void deleteUser(int id) {
		try {
			deleteStatement = cnx.prepareStatement("DELETE FROM Utilisateur WHERE id="+id+";");
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


