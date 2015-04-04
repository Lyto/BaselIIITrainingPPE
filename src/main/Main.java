package main;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		
		new Controller(stage);
		
	}
	
	/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
	
}