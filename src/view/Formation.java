package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Pattern;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Formation extends GridPane {

	GridPane[] panes;
	int nb_panes = 0;
	int current = 0;
	
	Button suivant, precedent;

	public Formation(String path) {

		super();

		this.setPrefWidth(935);
		this.setAlignment(Pos.CENTER);
		this.setVgap(20);
		this.setHgap(25);
		this.setId("background");
		
		URL url = this.getClass().getResource("/resources/" + path);

		int row = 0;
		int row_pan = 0;

		int i = 0; // Indice du gridpane à remplir

		Pattern p = Pattern.compile(">");

		try {
			BufferedReader buff = new BufferedReader(new InputStreamReader(
					url.openStream()));

			try {
				String line;

				while ((line = buff.readLine()) != null) {

					String[] texte = p.split(line);

					if (texte[0].equals("title1")) {

						Label title = new Label(texte[1]);
						title.setId("form_title1");
						GridPane.setHalignment(title, HPos.CENTER);
						this.add(title, 0, row, 2, 1);
						row++;

					} else if (texte[0].equals("title2")) {

						Label title = new Label(texte[1]);
						title.setId("form_title2");
						title.setWrapText(true);
						panes[i].add(title, 0, row_pan, 2, 1);
						row_pan++;

					} else if (texte[0].equals("title3")) {
						
						Label title = new Label(texte[1]);
						title.setId("form_title3");
						title.setWrapText(true);
						panes[i].add(title, 0, row_pan, 2, 1);
						row_pan++;
						
					} else if (texte[0].equals("image")) {
					
						String s = "/resources/chapitre" + texte[1];
						URL u = this.getClass().getResource(s);
						ImageView image = new ImageView(u.toString());
						GridPane.setHalignment(image, HPos.CENTER);
						panes[i].add(image, 0, row_pan, 2, 1);
						row_pan++;

					} else if (texte[0].equals("next")) {

						i++;
						row_pan = 0;

					} else if (texte[0].equals("panes")) {

						nb_panes = Integer.parseInt(texte[1]);
						panes = new GridPane[nb_panes];

						for (int a = 0; a < nb_panes; a++) {
							panes[a] = new GridPane();
							panes[a].setPrefWidth(850);
							panes[a].setAlignment(Pos.CENTER);
							panes[a].setVgap(0);
							panes[a].setHgap(25);
						}

					} else {

						Label label = new Label(texte[0]);
						label.setPrefWidth(800);
						label.setWrapText(true);
						label.setId("form_label");
						panes[i].add(label, 0, row_pan, 2, 1);
						row_pan++;

					}
				}

				for (int a = 0; a < nb_panes; a++) {
					panes[a].setId("form_pane");
					panes[a].setVisible(false);
					this.add(panes[a], 0, row, 2, 1);
				}
				panes[0].setVisible(true);
				
				row++;

			} finally {
				buff.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		precedent = new Button();
		Image gauche = new Image("file:gauche.jpg");
		precedent.setGraphic(new ImageView(gauche));
		precedent.setStyle("-fx-background-color: transparent;");

		GridPane.setHalignment(precedent, HPos.LEFT);
		precedent.setDisable(true);
		this.add(precedent, 0, row, 1, 1);

		precedent.setOnAction((event) -> {
			if (current == 0) {
				precedent.setDisable(true);
				suivant.setDisable(false);
			} else if (current == 1) {
				precedent.setDisable(true);
				suivant.setDisable(false);
				panes[current].setVisible(false);
				current--;
				panes[current].setVisible(true);
			} else {
				precedent.setDisable(false);
				suivant.setDisable(false);
				panes[current].setVisible(false);
				current--;
				panes[current].setVisible(true);
			}
		});

		suivant = new Button();
		Image droite = new Image("file:droite.jpg");
		suivant.setGraphic(new ImageView(droite));
		suivant.setStyle("-fx-background-color: transparent;");

		GridPane.setHalignment(suivant, HPos.RIGHT);
		this.add(suivant, 1, row, 1, 1);

		suivant.setOnAction((event) -> {
			if (current == (nb_panes - 2)) {
				precedent.setDisable(false);
				suivant.setDisable(true);
				panes[current].setVisible(false);
				current++;
				panes[current].setVisible(true);
			} else {
				suivant.setDisable(false);
				precedent.setDisable(false);
				panes[current].setVisible(false);
				current++;
				panes[current].setVisible(true);
			}
		});

	}

}
