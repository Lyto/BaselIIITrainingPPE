package view;

import connexion.Connexion;
import controller.Controller;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Accueil extends GridPane {

	Connexion connexion;

	public Accueil(Connexion connexion, Stage stage) {

		super();

		this.connexion = connexion;
		
		this.setPrefWidth(935);

		this.setAlignment(Pos.CENTER);
		this.setHgap(30);
		this.setVgap(30);
		this.setId("background");

		Label welcome;
		if (connexion != null) {
			welcome = new Label("Bienvenue " + connexion.getName() + " !");
		} else {
			welcome = new Label("Bienvenue ! [Mode invité]");
		}
		welcome.setId("title");
		GridPane.setHalignment(welcome, HPos.CENTER);
		this.add(welcome, 0, 0);

		Label intro = new Label(
				"Dans cette section vous trouverez les dernières nouvelles concernant la règlementation Bâle III, les mises à jour du logiciel ainsi qu'un récapitulatif de votre avancée.");
		GridPane.setHalignment(intro, HPos.CENTER);
		this.add(intro, 0, 1);

		GridPane news = createNews();
		this.add(news, 0, 2);

		GridPane updates = createUpdates();
		this.add(updates, 0, 3);

		//GridPane recap = createRecap();
		//this.add(recap, 0, 5);
		
		Button deconnexion = new Button("Déconnexion");
		GridPane.setHalignment(deconnexion, HPos.RIGHT);
		deconnexion.setStyle("-fx-font-weight: bold;");
		deconnexion.setOnAction((event) -> {
			new Controller(new Stage());
			stage.close();
		});
		
		this.add(deconnexion, 0, 6);

	}

	// Affiche les 3 dernières news de la base de données
	public GridPane createNews() {
		GridPane pane = new GridPane();
		pane.setId("pane");
		pane.setAlignment(Pos.TOP_LEFT);
		pane.setOpacity(1);

		Label l = new Label("");
		l.setPrefWidth(800);
		pane.add(l, 0, 0, 2, 1);
		
		Image img = new Image("file:news.jpg");
		ImageView news = new ImageView();
		news.setImage(img);
		news.setId("section");
		GridPane.setHalignment(news, HPos.CENTER);
		pane.add(news, 0, 0, 3, 1);

		String[][] table = new String[3][3];

		table[0][0] = "Bâle III: comment sauver le système du capitalisme financier";
		table[0][1] = "29 Janvier 2015";
		table[0][2] = "Article sur http://www.huffingtonpost.fr/";

		table[1][0] = "Bâle III: risque de contrepartie";
		table[1][1] = "15 Juillet 2012";
		table[1][2] = "Document sur www.bis.org/publ/bcbs209_fr.pdf";

		table[2][0] = "Cadre réglementaire international du secteur bancaire (Bâle III)";
		table[2][1] = "03 Juin 2011";
		table[2][2] = "Les documents officiels de la règlementation Bâle III sur les fonds propres";

		int j = 1;

		for (int i = 0; i < 3; i++) {
			// Nom de la news
			Label name = new Label(table[i][0]);
			name.setId("title1");
			pane.add(name, 0, j, 1, 1);

			// Date de la news
			Label date = new Label(table[i][1]);
			date.setId("date");
			pane.add(date, 1, j, 1, 1);

			// Description de la news
			Label desc = new Label(table[i][2]);
			desc.setId("desc");
			pane.add(desc, 0, j + 1, 2, 1);

			j += 2;
		}

		/*Hyperlink h = new Hyperlink("Voir plus de news...");
		h.setId("hyperlink");
		h.setOnAction((event) -> {
			ScrollPane scroll = new ScrollPane(new Pane_News(connexion));
			scroll.setPrefWidth(950);
			scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
			scroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			bpane.setCenter(scroll);
		});
		pane.add(h, 0, 7, 2, 1);*/

		return pane;
	}

	public GridPane createUpdates() {
		GridPane pane = new GridPane();
		pane.setId("pane");
		pane.setAlignment(Pos.TOP_LEFT);
		pane.setOpacity(1);

		Label l = new Label("");
		l.setPrefWidth(800);
		pane.add(l, 0, 0, 2, 1);
		
		Image img = new Image("file:MAJ.jpg");
		ImageView maj = new ImageView();
		maj.setImage(img);
		maj.setId("section");
		GridPane.setHalignment(maj, HPos.CENTER);
		pane.add(maj, 0, 0, 2, 1);

		String[][] table = new String[3][3];
		
		table[0][0] = "Release 2.4.1";
		table[0][1] = "7 Avril 2015";
		table[0][2] = "Version bêta du logiciel terminée.";

		table[1][0] = "Release 2.0.0";
		table[1][1] = "13 Février 2015";
		table[1][2] = "Le logiciel est passé en JavaFX.";
		

		table[2][0] = "Release 1.0.0";
		table[2][1] = "29 Novembre 2014";
		table[2][2] = "Version d'origine du logiciel.";
		

		int j = 1;

		for (int i = 0; i < 3; i++) {
			// Nom de la news
			Label name = new Label(table[i][0]);
			name.setId("title1");
			pane.add(name, 0, j, 1, 1);

			// Date de la news
			Label date = new Label(table[i][1]);
			date.setId("date");
			pane.add(date, 1, j, 1, 1);

			// Description de la news
			Label desc = new Label(table[i][2]);
			desc.setId("desc");
			pane.add(desc, 0, j + 1, 2, 1);

			j += 2;
		}

		/*Hyperlink h = new Hyperlink("Voir plus de MàJ...");
		h.setId("hyperlink");
		h.setOnAction((event) -> {
		});
		pane.add(h, 0, 7, 2, 1);*/

		return pane;
	}

	public GridPane createRecap() {
		GridPane pane = new GridPane();
		pane.setId("pane");
		pane.setAlignment(Pos.TOP_LEFT);
		pane.setOpacity(1);

		Image img = new Image("file:recap.jpg");
		ImageView maj = new ImageView();
		maj.setImage(img);
		maj.setId("section");
		GridPane.setHalignment(maj, HPos.CENTER);
		pane.add(maj, 0, 0, 1, 1);

		Hyperlink h = new Hyperlink("Voir mes résultats");
		h.setId("hyperlink");
		h.setOnAction((event) -> {
		});
		pane.add(h, 0, 7, 1, 1);
		
		return pane;
	}

}
