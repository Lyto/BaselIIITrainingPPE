package view;

import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import connexion.Connexion;

public class Menu {
	
	BorderPane bpane;
	Connexion connexion;

	public Menu(Connexion connexion) {
		
		this.connexion = connexion;
		
		Stage stage = new Stage();
		
		bpane = new BorderPane();		
		bpane.setLeft(createMenu());
		
		bpane.setCenter(new Accueil(connexion, bpane));
		
		Scene scene = new Scene(bpane, 1200, 800);
		scene.getStylesheets().add(this.getClass().getResource("Pane.css").toString());
		
		stage.setTitle("Basel III Training");
		stage.setScene(scene);
		stage.show();
		
	}

	public VBox createMenu() {
		VBox vbox = new VBox();
		vbox.setPrefWidth(250);

		// TP: Home
		TitledPane tp_home = new TitledPane();
		tp_home.setText("Home");

		VBox vbox_home = new VBox();

		Hyperlink h1_home = new Hyperlink("Accueil");
		vbox_home.getChildren().add(h1_home);
		h1_home.setOnAction((event) -> {
			bpane.setCenter(new Accueil(connexion, bpane));
		});

		tp_home.setContent(vbox_home);

		// TP: Profil
		TitledPane tp_profil = new TitledPane();
		tp_profil.setText(connexion.getName());

		VBox vbox_profil = new VBox();

		Hyperlink h1_profil = new Hyperlink("Mon profil");
		vbox_profil.getChildren().add(h1_profil);
		h1_profil.setOnAction((event) -> {
			ScrollPane scroll = new ScrollPane(new Profil(connexion));
			scroll.setPrefWidth(950);
			scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
			bpane.setCenter(scroll);
		});

		tp_profil.setContent(vbox_profil);

		// TP: Chapitre 1
		TitledPane tp_chap1 = new TitledPane();
		tp_chap1.setText("Chapitre 1: Introduction au CVA");

		VBox vbox_chap1 = new VBox();
		
		Hyperlink h0_chap1 = new Hyperlink("Qu'est-ce que Bâle?");
		vbox_chap1.getChildren().add(h0_chap1);
		h0_chap1.setOnAction((event) -> {
			bpane.setCenter(new Formation("chapitre1/1.txt"));
		});

		Hyperlink h1_chap1 = new Hyperlink("Qu'est-ce que le CVA?");
		vbox_chap1.getChildren().add(h1_chap1);
		h1_chap1.setOnAction((event) -> {
			bpane.setCenter(new Formation("chapitre1/2.txt"));
		});

		Hyperlink h2_chap1 = new Hyperlink("Le CVA unilatéral");
		vbox_chap1.getChildren().add(h2_chap1);
		h2_chap1.setOnAction((event) -> {
			bpane.setCenter(new Formation("chapitre1/3.txt"));
		});

		Hyperlink h4_chap1 = new Hyperlink("QCM");
		vbox_chap1.getChildren().add(h4_chap1);
		h4_chap1.setOnAction((event) -> {
			ScrollPane scroll = new ScrollPane(new Chap1_QCM(connexion));
			scroll.setPrefWidth(950);
			scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
			bpane.setCenter(scroll);
		});

		tp_chap1.setContent(vbox_chap1);

		// TP: Chapitre 2
		TitledPane tp_chap2 = new TitledPane();
		tp_chap2.setText("Chapitre 2: La probabilité de défaut");

		VBox vbox_chap2 = new VBox();
		
		Hyperlink h0_chap2 = new Hyperlink("Notations et définitions");
		vbox_chap2.getChildren().add(h0_chap2);

		Hyperlink h1_chap2 = new Hyperlink("Partie 1");
		vbox_chap2.getChildren().add(h1_chap2);

		Hyperlink h2_chap2 = new Hyperlink("Partie 2");
		vbox_chap2.getChildren().add(h2_chap2);

		Hyperlink h3_chap2 = new Hyperlink("QCM");
		vbox_chap2.getChildren().add(h3_chap2);

		tp_chap2.setContent(vbox_chap2);

		// TP: Chapitre 3
		TitledPane tp_chap3 = new TitledPane();
		tp_chap3.setText("Chapitre 3: Calcul du CVA");

		VBox vbox_chap3 = new VBox();
		
		Hyperlink h0_chap3 = new Hyperlink("Introduction");
		vbox_chap3.getChildren().add(h0_chap3);

		Hyperlink h1_chap3 = new Hyperlink("Partie 1");
		vbox_chap3.getChildren().add(h1_chap3);

		Hyperlink h2_chap3 = new Hyperlink("Partie 2");
		vbox_chap3.getChildren().add(h2_chap3);

		Hyperlink h3_chap3 = new Hyperlink("Partie 3");
		vbox_chap3.getChildren().add(h3_chap3);

		Hyperlink h4_chap3 = new Hyperlink("Partie 4");
		vbox_chap3.getChildren().add(h4_chap3);

		Hyperlink h5_chap3 = new Hyperlink("QCM");
		vbox_chap3.getChildren().add(h5_chap3);

		tp_chap3.setContent(vbox_chap3);

		vbox.getChildren().addAll(tp_home, tp_profil, tp_chap1, tp_chap2, tp_chap3);
	
		return vbox;
	}
	
}
