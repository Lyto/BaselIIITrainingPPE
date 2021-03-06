package view;

import connexion.Connexion;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Profil extends GridPane {

	Connexion connexion;
	
	// Nombre de chapitres
	int nb_chap = 5;
	
	// C1: num�ro de chapitre, C2: points de l'utilisateur, C3: nb de questions
	int[][] points = new int[nb_chap][3];
	
	double[] pourcentage = new double[nb_chap];

	public Profil(Connexion connexion) {

		super();
		this.connexion = connexion;

		this.setPrefWidth(935);
		this.setAlignment(Pos.TOP_CENTER);
		this.setVgap(25);
		this.setId("background");

		Label user = new Label(connexion.getName());
		user.setStyle("-fx-font-size: 300%; -fx-font-weight: bold;");
		GridPane.setHalignment(user, HPos.CENTER);
		this.add(user, 0, 1);

		// Mon compte
		displayAccount();

		// Mes r�sultats
		displayResults();
		
		Label espace = new Label(" ");
		this.add(espace, 0, 4);

	}

	public void displayAccount() {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setId("pane");
		pane.setVgap(10);
		pane.setPrefWidth(750);

		Image img = new Image("file:compte.jpg");
		ImageView news = new ImageView();
		news.setImage(img);
		news.setId("section");
		GridPane.setHalignment(news, HPos.CENTER);
		pane.add(news, 0, 0, 2, 1);

		Label user = new Label("Nom d'utilisateur: ");
		user.setId("label_profil");
		pane.add(user, 0, 1);

		TextField username = new TextField(connexion.getName());
		username.setDisable(true);
		username.setId("label_profil");
		pane.add(username, 1, 1);

		Label pwd = new Label("Mot de passe: ");
		pwd.setId("label_profil");
		pane.add(pwd, 0, 2);

		PasswordField password = new PasswordField();
		password.setText(connexion.getPassword());
		password.setDisable(true);
		password.setId("label_profil");
		pane.add(password, 1, 2);
 
		Hyperlink hl = new Hyperlink("Changer le mot de passe");
		hl.setStyle("-fx-padding: 0 0 10 0;");
		hl.setOnAction((event) -> {
			changePwd();
		});
		pane.add(hl, 1, 3);

		this.add(pane, 0, 2);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void displayResults() {
		GridPane pane = new GridPane();
		pane.setId("pane");
		pane.setVgap(10);
		pane.setPrefWidth(750);
		pane.setAlignment(Pos.CENTER);

		Image img = new Image("file:resultats.jpg");
		ImageView res = new ImageView();
		res.setId("section");
		res.setImage(img);
		GridPane.setHalignment(res, HPos.CENTER);
		pane.add(res, 0, 0, 4, 1);

		points[0][0] = 1; // id chap: 1
		Label chap1 = new Label("Chapitre 1: Introduction au CVA");
		chap1.setId("chapitre");
		pane.add(chap1, 0, 2);

		points[1][0] = 2; // id chap: 2
		Label chap2 = new Label("Chapitre 2: Loss Given Default");
		chap2.setId("chapitre");
		pane.add(chap2, 0, 3);
		
		points[2][0] = 3; // id chap: 3
		Label chap3 = new Label("Chapitre 3: Probabilit� de d�faut");
		chap3.setId("chapitre");
		pane.add(chap3, 0, 4);
		
		points[3][0] = 4; // id chap: 4
		Label chap4 = new Label("Chapitre 4: Probabilit� de d�faut simplifi�e");
		chap4.setId("chapitre");
		pane.add(chap4, 0, 5);
		
		points[4][0] = 5; // id chap: 5
		Label chap5 = new Label("Chapitre 5: L'exposition de cr�dit");
		chap5.setId("chapitre");
		pane.add(chap5, 0, 6);
		
		for (int i=0; i<nb_chap; i++) {
			
			// Remplir le tableau points (C1: id chap, C2: points, C3: nb de qsts)
			int id_chap = points[i][0];
			points[i][1] = connexion.getPoints(id_chap);
			points[i][2] = connexion.getNbQuestions(id_chap);
			
			// Label avec les points/nb_questions
			Label resultats = new Label(points[i][1] + " / " + points[i][2]);
			resultats.setId("chapitre");
			pane.add(resultats, 1, i+2);
			
			// Calcul du pourcentage
			double d = (double) points[i][1] / (double) points[i][2];
			pourcentage[i] = ((int)(d*100))/100.;
			
			// Graphe de progression
			ProgressBar pb = new ProgressBar();
			pb.setProgress(pourcentage[i]);
			pb.setPrefSize(200, 25);
			pb.setStyle("-fx-padding: 0 10 0 50;");
			pane.add(pb, 2, i+2);
			
			// Label avec le pourcentage
			double prct = pourcentage[i] * 100;
			Label pourcent = new Label(prct + " %");
			pane.add(pourcent, 3, i+2);
		}
		
		
		// GRAPHE
		
		final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        
        final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Basel III Training");
                                
        XYChart.Series series = new XYChart.Series();
        series.setName("R�sultats de " + connexion.getName());
        
        for(int i=0; i<nb_chap; i++) {
        	series.getData().add(new XYChart.Data("Chapitre " + points[i][0], pourcentage[i] * 100));
        }
        
        lineChart.getData().add(series);
		pane.add(lineChart, 0, 8, 4, 1);
		
		this.add(pane, 0, 3);
	}
	
	
	public void changePwd() {
		
		Stage stage = new Stage();
		GridPane pane = new GridPane();
		pane.setHgap(15);
		pane.setVgap(15);
		pane.setAlignment(Pos.CENTER);
		
		Label pwd = new Label("Nouveau mot de passe:");
		pwd.setId("subtitle");
		pane.add(pwd, 0, 0);
		
		PasswordField password = new PasswordField();
		pane.add(password, 0, 1);
		
		Label pwd2 = new Label("Confirmez le nouveau mot de passe:");
		pwd2.setId("subtitle");
		pane.add(pwd2, 0, 2);

		PasswordField password2 = new PasswordField();
		password2.setId("subtitle");
		pane.add(password2, 0, 3);
		
		Button button = new Button("Changer de mot de passe");
		button.setId("button");
		GridPane.setHalignment(button, HPos.CENTER);
		pane.add(button, 0, 5);
		
		button.setOnAction((event) -> {
			String p1 = password.getText();
			String p2 = password2.getText();
			if (p1.compareTo(p2) == 0) {
				connexion.changePassword(p1);
				stage.close();
			} else {
				Label warning = new Label("Mots de passe diff�rents");
				GridPane.setHalignment(warning, HPos.CENTER);
				warning.setId("warning");
				pane.add(warning, 0, 6);
			}
		});
		
		Scene scene = new Scene(pane, 400, 350);
		scene.getStylesheets().add(this.getClass().getResource("Inscription.css").toString());
		
		stage.setTitle("S'inscrire");
		stage.setScene(scene);
		stage.show();
		
	}

}
