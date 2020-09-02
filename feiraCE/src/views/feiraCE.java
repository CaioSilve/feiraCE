package views;

//--module-path "/home/erick/Projeto/javafx-sdk-14.0.2.1/lib" --add-modules javafx.controls,javafx.fxml

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class feiraCE extends Application {
	
	Pane pane;
	String css;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {		
		
		css = getClass().getResource("/views/lindeza.css").toExternalForm();
		pane = FXMLLoader.load(getClass().getResource("/views/FXMLLogin.fxml"));
		
		Scene cena = new Scene(pane, 800, 600);
		cena.getStylesheets().add(css);
		primaryStage.setTitle("Login");
		primaryStage.setResizable(false);
		primaryStage.setScene(cena);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
