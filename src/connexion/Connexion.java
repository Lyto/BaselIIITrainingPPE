package connexion;

import java.sql.*;

public class Connexion {

	private Connection conn;
	private Statement stmt;
	private ResultSet res;

	private int id = 0; // id de l'utilisateur connecté

	public Connexion(String database, String login, String password)
			throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");
		String urlDatabase = "jdbc:mysql://localhost:3306/";

		conn = DriverManager.getConnection(urlDatabase + database, login,
				password);
		stmt = conn.createStatement();

	}

	// *************************************************************************************
	// OPERATIONS SUR LA TABLE USERS
	// *************************************************************************************

	public boolean authentification(String login, String password)
			throws SQLException {

		String sql = "SELECT id FROM users WHERE login = '" + login
				+ "' AND password = '" + password + "' ";
		res = stmt.executeQuery(sql);

		if (res.next()) {
			id = res.getInt(1);
			System.out.println(login + " connected ! (ID: " + id + ")");
			return true;
		} else {
			System.out.println("Authentification failed!");
			return false;
		}

	}
	
	public void register(String login, String password) {
		try {
			String sql = "INSERT INTO users(login, password) VALUES ('" + login + "', '" + password + "')";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		System.out.println("ID = " + id);
		return id;
	}

	public String getName() {
		try {
			String sql = "SELECT login FROM users WHERE id = " + id;
			res = stmt.executeQuery(sql);
			if (res.next()) {
				return res.getString(1);
			} else {
				return null;
			}
		} catch (SQLException e) {
			return null;
		}
	}
	
	public String getPassword() {
		try {
			String sql = "SELECT password FROM users WHERE id = " + id;
			res = stmt.executeQuery(sql);
			if (res.next()) {
				return res.getString(1);
			} else {
				return null;
			}
		} catch (SQLException e) {
			return null;
		}
	}
	
	public void registerPoints(int id_chap, int points) {
		try {
			String sql = "UPDATE users SET chapitre" + id_chap + " = " + points + " WHERE id = " + id;
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getPoints(int id_chap) {
		int points = 0;
		try {
			String sql = "SELECT chapitre" + id_chap + " FROM users WHERE id = " + id;
			res = stmt.executeQuery(sql);
			if (res.next()) {
				points = res.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return points;
	}

	// *************************************************************************************
	// OPERATIONS SUR LA TABLE QUESTIONS
	// *************************************************************************************

	public int getNbQuestions(int id_chap) {
		int nb = 0;

		try {
			String sql = "SELECT count(*) FROM questions WHERE id_chap = "
					+ id_chap;
			res = stmt.executeQuery(sql);
			if (res.next()) {
				nb = res.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nb;
	}

	public String[][] getQuestionsFromChap(int id_chap) {
		int nb = getNbQuestions(id_chap);
		String[][] table = new String[nb][2];

		int i = 0;
		try {
			String sql = "SELECT id, nom FROM questions WHERE id_chap = "
					+ id_chap;
			res = stmt.executeQuery(sql);
			while (res.next()) {
				table[i][0] = res.getString(1);
				table[i][1] = res.getString(2);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return table;
	}

	public int getReponse(int id_question) {
		int reponse = 0;

		try {
			String sql = "SELECT id_rep FROM questions WHERE id = " + id_question;
			res = stmt.executeQuery(sql);
			if (res.next()) {
				reponse = res.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reponse;

	}

	// *************************************************************************************
	// OPERATIONS SUR LA TABLE REPONSES
	// *************************************************************************************

	public int getNbReponses(int id_question) {
		int nb = 0;

		try {
			String sql = "SELECT count(*) FROM reponses WHERE id_question = "
					+ id_question;
			res = stmt.executeQuery(sql);
			if (res.next()) {
				nb = res.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nb;
	}

	public String[][] getReponsesFromQ(int id_question) {
		int nb = getNbReponses(id_question);
		String[][] table = new String[nb][2];

		int i = 0;
		try {
			String sql = "SELECT id, nom FROM reponses WHERE id_question = "
					+ id_question;
			res = stmt.executeQuery(sql);
			while (res.next()) {
				table[i][0] = res.getString(1);
				table[i][1] = res.getString(2);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return table;
	}

}
