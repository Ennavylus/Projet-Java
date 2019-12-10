package net.JeuxDeMob;

import java.sql.*;

public class DataBase {
	static private DataBase instance;
	Connection cnx;
	// PreparedStatement  for player insert
	private PreparedStatement  insertStatement;
	private PreparedStatement  updateStatement;
	
	private DataBase() {
		String url = "jdbc:mysql://localhost/jeuxdumob?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	    String user = "root", pswd = "56421988";
		try {
			cnx = DriverManager.getConnection(url,user,pswd);
			insertStatement = cnx.prepareStatement("INSERT INTO Utilisateur (Pseudo,mail,mdp) VALUE(?,?,?)",Statement.RETURN_GENERATED_KEYS);
			updateStatement = cnx.prepareStatement("UPDATE task SET task=? WHERE id=?");
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
	
	public Player insertTodo(String pseudo) {
		try {
			insertStatement.setString(1, pseudo);
			int inserted = insertStatement.executeUpdate();
			ResultSet res  = insertStatement.getGeneratedKeys();	
			if(res.next() && inserted>0 ) {
				var lastId = res.getInt(1);
				return new Player(lastId, pseudo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

//	public void updatePlayer(Player player) {
//		try {
//			updateStatement.setString(1, todo.getTask());
//			updateStatement.setInt(2, todo.getId());
//			updateStatement.executeUpdate();
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		}
//	}
}


