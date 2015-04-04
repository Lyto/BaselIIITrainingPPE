package view;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import connexion.Connexion;
import controller.Controller;

public class SeConnecter {
	
	Controller controller;
	
	public SeConnecter(Stage stage, Connexion connexion) {
		
		GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(30);
        
        // [Label] Bienvenue
        Label welcome = new Label("Bienvenue sur Basel III Training");
        welcome.setId("welcome");
        GridPane.setHalignment(welcome, HPos.CENTER);
        pane.add(welcome, 0, 0, 2, 1);
        
        // [Labels + Buttons] GridPane pour le username + password
        GridPane p = new GridPane();
        p.setAlignment(Pos.CENTER);
        p.setHgap(10);
        p.setVgap(10);
        
        Label username_label = new Label("Identifiant");
        username_label.setId("username");
        username_label.setPrefWidth(120);
        p.add(username_label, 0, 0);
        TextField username = new TextField();
        username.setPrefWidth(200);
        p.add(username, 1, 0);
        
        Label password_label = new Label("Mot de passe");
        password_label.setId("password");
        p.add(password_label, 0, 1);
        PasswordField password = new PasswordField();
        p.add(password, 1, 1);
        
        pane.add(p, 0, 1, 2, 1);
        
        // [Button] Connexion
        Button connexion_button = new Button("Connexion !");
        GridPane.setHalignment(connexion_button, HPos.CENTER);
        pane.add(connexion_button, 0, 2, 2, 1);
        
        // [Label + Button] S'inscrire
        GridPane p2 = new GridPane();
        p2.setAlignment(Pos.CENTER);
        p2.setHgap(10);
        p2.setVgap(10);
        
        Label forgot = new Label("Vous n'êtes pas encore inscrit?");
        forgot.setId("inscription");
        p2.add(forgot, 0, 0);
        
        Button sub = new Button("S'inscrire");
        p2.add(sub, 1, 0);
        
        pane.add(p2, 0, 3);
        
        // [Action On Button] Inscription
        sub.setOnAction((event) -> {
        	new Inscription(connexion);
        });
        
        // [Action On Button] Lancement de la page Home
        connexion_button.setOnAction((event) -> {
        	try {
				if (connexion.authentification("YuuKii", "chocolat01")) {
					new Menu(connexion);
					stage.close();
				}
			
        	} catch (Exception e) {
				System.out.println("Erreur");
			}
        });
        
        // Scene & Stage
        
        Scene scene = new Scene(pane, 1200, 800);
        scene.getStylesheets().add(this.getClass().getResource("SeConnecter.css").toString());
        
        stage.setTitle("Basel III Training");
        stage.setScene(scene);
        stage.show();
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
}
