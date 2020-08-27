package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class testeFX extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Pane pane = FXMLLoader.load(getClass().getResource("FXMLCliente.fxml"));
		
		Scene cena = new Scene(pane, 200, 250);
		primaryStage.setScene(cena);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
