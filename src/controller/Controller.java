package controller;

import java.sql.SQLException;

import connexion.Connexion;
import javafx.stage.Stage;
import view.SeConnecter;

public class Controller {

	public Controller(Stage stage) {

		Connexion connexion;
		
		try {
			connexion = new Connexion("basel_training", "root", "");
			SeConnecter seConnecter = new SeConnecter(stage, connexion);
			seConnecter.setController(this);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
