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
		scene.getStylesheets().add(
				this.getClass().getResource("Pane.css").toString());

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
		tp_chap2.setText("Chapitre 2: Loss Given Default");

		VBox vbox_chap2 = new VBox();

		Hyperlink h0_chap2 = new Hyperlink("-");
		vbox_chap2.getChildren().add(h0_chap2);

		Hyperlink h1_chap2 = new Hyperlink("-");
		vbox_chap2.getChildren().add(h1_chap2);

		Hyperlink h3_chap2 = new Hyperlink("QCM");
		vbox_chap2.getChildren().add(h3_chap2);

		tp_chap2.setContent(vbox_chap2);

		// TP: Chapitre 3
		TitledPane tp_chap3 = new TitledPane();
		tp_chap3.setText("Chapitre 3: Probabilité de défault");

		VBox vbox_chap3 = new VBox();

		Hyperlink h0_chap3 = new Hyperlink("Notations & définition");
		vbox_chap3.getChildren().add(h0_chap3);
		h0_chap3.setOnAction((event) -> {
			bpane.setCenter(new Formation("chapitre3/1.txt"));
		});

		Hyperlink h1_chap3 = new Hyperlink(
				"Probabilité neutre à l'égard du risque");
		vbox_chap3.getChildren().add(h1_chap3);
		h1_chap3.setOnAction((event) -> {
			bpane.setCenter(new Formation("chapitre3/2.txt"));
		});

		Hyperlink h2_chap3 = new Hyperlink("CDS (Credit Default Swap)");
		vbox_chap3.getChildren().add(h2_chap3);
		h2_chap3.setOnAction((event) -> {
			bpane.setCenter(new Formation("chapitre3/3.txt"));
		});

		Hyperlink h5_chap3 = new Hyperlink("QCM");
		vbox_chap3.getChildren().add(h5_chap3);

		tp_chap3.setContent(vbox_chap3);

		// TP: Chapitre 4
		TitledPane tp_chap4 = new TitledPane();
		tp_chap4.setText("Chapitre 4: Probabilité de défault simplifiée");

		VBox vbox_chap4 = new VBox();

		Hyperlink h0_chap4 = new Hyperlink("Probabilité de défault simplifiée");
		vbox_chap4.getChildren().add(h0_chap4);
		h0_chap4.setOnAction((event) -> {
			bpane.setCenter(new Formation("chapitre4/1.txt"));
		});

		Hyperlink h1_chap4 = new Hyperlink("QCM");
		vbox_chap4.getChildren().add(h1_chap4);

		tp_chap4.setContent(vbox_chap4);

		// TP: Chapitre 5
		TitledPane tp_chap5 = new TitledPane();
		tp_chap5.setText("Chapitre 5: L'exposition de crédit");

		VBox vbox_chap5 = new VBox();

		Hyperlink h0_chap5 = new Hyperlink("Définition");
		vbox_chap5.getChildren().add(h0_chap5);
		h0_chap5.setOnAction((event) -> {
			bpane.setCenter(new Formation("chapitre5/1.txt"));
		});

		Hyperlink h1_chap5 = new Hyperlink("Les outils de réduction de l'exposition");
		vbox_chap5.getChildren().add(h1_chap5);
		h1_chap5.setOnAction((event) -> {
			bpane.setCenter(new Formation("chapitre5/2.txt"));
		});

		Hyperlink h2_chap5 = new Hyperlink("Les différents types d'exposition");
		vbox_chap5.getChildren().add(h2_chap5);
		h2_chap5.setOnAction((event) -> {
			bpane.setCenter(new Formation("chapitre5/3.txt"));
		});

		Hyperlink h3_chap5 = new Hyperlink("Mesure de l'exposition");
		vbox_chap5.getChildren().add(h3_chap5);
		h3_chap5.setOnAction((event) -> {
			bpane.setCenter(new Formation("chapitre5/4.txt"));
		});

		Hyperlink h4_chap5 = new Hyperlink("QCM");
		vbox_chap5.getChildren().add(h4_chap5);
		h4_chap5.setOnAction((event) -> {
			bpane.setCenter(new Formation("chapitre5/qcm.txt"));
		});

		tp_chap5.setContent(vbox_chap5);

		vbox.getChildren().addAll(tp_home, tp_profil, tp_chap1, tp_chap2,
				tp_chap3, tp_chap4, tp_chap5);

		return vbox;
	}

}
