package view;

import connexion.Connexion;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Inscription {
	
		Connexion connexion;
		
	public Inscription(Connexion connexion) {
		 
		this.connexion = connexion;
		
		Stage stage = new Stage();
		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setAlignment(Pos.CENTER);
		
		Label title = new Label("Inscription");
		title.setId("title");
		GridPane.setHalignment(title, HPos.CENTER);
		pane.add(title, 0, 0);
		
		Label log = new Label("Identifiant :");
		log.setId("subtitle");
		pane.add(log, 0, 1);
		
		TextField login = new TextField();
		pane.add(login, 0, 2);
		
		Label pwd = new Label("Mot de passe:");
		pwd.setId("subtitle");
		pane.add(pwd, 0, 3);
		
		PasswordField password = new PasswordField();
		pane.add(password, 0, 4);
		
		Label pwd2 = new Label("Confirmez le mot de passe:");
		pwd2.setId("subtitle");
		pane.add(pwd2, 0, 5);
		
		PasswordField password2 = new PasswordField();
		password2.setId("subtitle");
		pane.add(password2, 0, 6);
		
		Button sinscrire = new Button("S'inscrire");
		sinscrire.setId("button");
		GridPane.setHalignment(sinscrire, HPos.CENTER);
		pane.add(sinscrire, 0, 10);
		
		sinscrire.setOnAction((event) -> {
			String l = login.getText();
			String p1 = password.getText();
			String p2 = password2.getText();
			if (p1.compareTo(p2) == 0) {
				connexion.register(l, p1);
				stage.close();
			} else {
				Label warning = new Label("Mots de passe différents");
				GridPane.setHalignment(warning, HPos.CENTER);
				warning.setId("warning");
				pane.add(warning, 0, 8);
			}
		});
		
		Scene scene = new Scene(pane, 400, 450);
		scene.getStylesheets().add(this.getClass().getResource("Inscription.css").toString());
		
		stage.setTitle("S'inscrire");
		stage.setScene(scene);
		stage.show();
		
	}
	
	
}
