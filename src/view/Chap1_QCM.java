package view;

import connexion.Connexion;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Chap1_QCM extends GridPane {

	Connexion connexion;

	// Les points de l'utilisateur sur ce QCM
	int points = 0;

	// L'indice du grid pane affiché
	int current = 0;

	public Chap1_QCM(Connexion connexion) {

		super();
		this.connexion = connexion;

		this.setPrefWidth(935);
		this.setAlignment(Pos.CENTER);
		this.setVgap(25);

		// Titre

		Label welcome = new Label(
				"Testez vos connaissances sur le Chapitre 1: Bâle III");
		welcome.setId("title_qcm");
		GridPane.setHalignment(welcome, HPos.CENTER);
		this.add(welcome, 0, 1, 2, 1);

		// QCM

		String[][] questions = connexion.getQuestionsFromChap(1);

		RadioButton[] radio = new RadioButton[questions.length * 4];
		int count = 0;

		GridPane[] panes = new GridPane[questions.length];

		for (int i = 0; i < questions.length; i++) {

			panes[i] = new GridPane();
			panes[i].setId("pane");

			Label question = new Label("Question n°" + (i + 1) + ": "
					+ questions[i][1]);
			GridPane.setHalignment(question, HPos.LEFT);
			question.setPrefWidth(850);
			question.setId("question");
			panes[i].add(question, 0, 0);

			int id_question = Integer.parseInt(questions[i][0]);
			String[][] reponses = connexion.getReponsesFromQ(id_question);
			ToggleGroup group = new ToggleGroup();

			for (int j = 0; j < 4; j++) {
				radio[count] = new RadioButton(reponses[j][1]);
				radio[count].setId("reponse");
				radio[count].setToggleGroup(group);
				panes[i].add(radio[count], 0, j + 1);
				count++;
			}

			panes[i].setVisible(false);
			this.add(panes[i], 0, 2, 2, 1);

		}

		panes[0].setVisible(true);

		// BOUTONS Suivant & Précédent

		Button precedent = new Button();
		Image gauche = new Image("file:gauche.jpg");
		precedent.setGraphic(new ImageView(gauche));
		precedent.setStyle("-fx-background-color: transparent;");

		GridPane.setHalignment(precedent, HPos.LEFT);
		precedent.setDisable(true);
		this.add(precedent, 0, 3);

		precedent.setOnAction((event) -> {
			if (current == 0) {
				precedent.setDisable(true);
			} else if (current == 1) {
				precedent.setDisable(true);
				panes[current].setVisible(false);
				current--;
				panes[current].setVisible(true);
			} else {
				precedent.setDisable(false);
				panes[current].setVisible(false);
				current--;
				panes[current].setVisible(true);
			}
		});

		Button suivant = new Button();
		Image droite = new Image("file:droite.jpg");
		suivant.setGraphic(new ImageView(droite));
		suivant.setStyle("-fx-background-color: transparent;");

		GridPane.setHalignment(suivant, HPos.RIGHT);
		this.add(suivant, 1, 3);

		suivant.setOnAction((event) -> {
			if (current == (questions.length - 2)) {
				panes[current].setVisible(false);
				current++;
				panes[current].setVisible(true);
			} else if (current == (questions.length - 1)) {

				this.getChildren().clear();

				points = 0;
				for (int k = 0; k < questions.length; k++) {
					int id_question = Integer.parseInt(questions[k][0]);
					int solution = connexion.getReponse(id_question);

					String[][] reponses = connexion.getReponsesFromQ(id_question);
					for (int l = 0; l < 4; l++) {
						int id_rep = Integer.parseInt(reponses[l][0]);
						int radio_count = 4 * k + l;
						if (id_rep == solution) {
							radio[radio_count].setId("solution");
							if (radio[radio_count].isSelected()) {
								points++;
							}
						} else {
							if (radio[radio_count].isSelected()) {
								radio[radio_count].setId("mauvaise_reponse");
							}
						}

					}
				}

				connexion.registerPoints(1, points);
				afficherScore(questions.length);

				// AFFICHER RESULTATS
				this.add(welcome, 0, 1);
				int row = 2;
				for (int z = 0; z < questions.length; z++) {
					panes[z].setVisible(true);
					this.add(panes[z], 0, row);
					row++;
				}
				
				Label espace = new Label(" ");
				this.add(espace, 0, row);

			} else {
				precedent.setDisable(false);
				panes[current].setVisible(false);
				current++;
				panes[current].setVisible(true);
			}

		});

		

	}

	public void afficherScore(int nb_questions) {

		Stage stage = new Stage();

		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setVgap(15);

		Label qcm = new Label("Chapitre 1: Bâle III - QCM ");
		qcm.setStyle("-fx-font-size: 150%; -fx-font-weight: bold;");
		GridPane.setHalignment(qcm, HPos.CENTER);
		pane.add(qcm, 0, 0, 2, 1);

		Label title = new Label("Vos résultats: " + points + "/" + nb_questions);
		title.setStyle("-fx-font-size: 120%;");
		GridPane.setHalignment(title, HPos.CENTER);
		pane.add(title, 0, 1, 2, 1);

		double progression = (double) points / (double) nb_questions;
		ProgressBar pb = new ProgressBar();
		pb.setProgress(progression);
		pb.setPrefSize(200, 25);
		pb.setStyle("-fx-padding: 0 10 0 50;");
		pane.add(pb, 0, 2, 1, 1);

		Label pourcent = new Label(progression * 100 + " %");
		pourcent.setStyle("-fx-font-size: 15; -fx-padding: 0 10 0 5;");
		pane.add(pourcent, 1, 2, 1, 1);

		Button ok = new Button("OK");
		ok.setStyle("-fx-padding: 5 15 5 15;");
		GridPane.setHalignment(ok, HPos.CENTER);
		pane.add(ok, 0, 3, 2, 1);

		ok.setOnAction((event) -> {
			stage.close();
		});

		stage.setTitle("");
		stage.setScene(new Scene(pane, 300, 180));
		stage.show();
	}

}
