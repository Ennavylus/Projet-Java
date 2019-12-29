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
	    String user = "Please enter your Databases UserName ", pswd = "Please enter your Databases Password ";
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
	/**
	 *  allows to save in data base result of the game
	 * @param userWin this boolean if user win
	 * @param nbWin = integer correspond to the number figurine to win
	 * @param deckStyle =  correspond of the visual theme of card
	 */
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
	
/**
 * allows to refresh historique in data base for the number of win and loose game
 */
	public void refreshHistorique()  {
		ResultSet res = this.getInstance().query("select count(win) from joue as J where id_utilisateur="+LogInController.id+" and win = true;");
		int total=0;
		int win=0; 
		int loose=0;
		
		
			try {
				if(res.next())
				win=res.getInt(1);
				res = this.getInstance().query("select count(win) from joue as J where id_utilisateur="+LogInController.id+" and win = false;");
				if(res.next())loose=res.getInt(1);
				total = win+loose;
				updateStatement.executeUpdate("UPDATE historique SET nbPartie="+total+", Victoire = "+win+", defaite = "+loose+" WHERE id = "+LogInController.id+";");
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	/**
	 * allows to insert new user in data base
	 * 
	 * @param pseudo = String  pseudo for new user
	 * @param mail = String  mail for new user
	 * @param mdp = String password for new user
	 * @return new user if insert is ok
	 */
	public User insertUtilisateur(String pseudo, String mail, String mdp) {
		try {
			insertStatement.setString(1, pseudo);
			insertStatement.setString(2, mail);
			insertStatement.setString(3, mdp);
			insertStatement.setString(4, "backGround/prof.png");
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
	/**
	 * allows to modify one data in data bases for admin interface
	 * @param id = integer correspond id for user to modify
	 * @param focus = data focus change
	 * @param newValue =  new value for change
	 */
	public void updateThis(int id, String focus, String newValue ) {
		try {
			updateStatement.executeUpdate("UPDATE Utilisateur SET "+focus+"= '"+newValue+"'WHERE id="+id+";");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * allows to clear game history 
	 * @return true if clear is ok
	 */
	public boolean clearStat() {
		try {
			updateStatement.executeUpdate("UPDATE Historique SET nbPartie = 0,Victoire = 0, defaite = 0 WHERE id_utilisateur ="+LogInController.id+";");
			deleteStatement.executeUpdate("DELETE FROM joue WHERE id_Utilisateur="+LogInController.id+";");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * allows to delete user in data bases
	 * @param id = id for user to delete
	 */
	public void deleteUser(int id) {
		try {

			deleteStatement.executeUpdate("DELETE FROM Utilisateur WHERE id="+id+";");
			deleteStatement.executeUpdate("DELETE FROM Historique WHERE id="+id+";");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * allows to modify user profile in database in user interface
	 * @param pseudo 
	 * @param mail
	 * @param mdp
	 * @param url for new picture profile
	 */
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


