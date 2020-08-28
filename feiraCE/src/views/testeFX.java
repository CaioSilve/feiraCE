package views;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class testeFX extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		String css = getClass().getResource("/views/estilo.css").toExternalForm();
		Pane pane = FXMLLoader.load(getClass().getResource("/views/FXMLLogin.fxml"));
		
		Scene cena = new Scene(pane, 300, 330);
		cena.getStylesheets().add(css);
		primaryStage.setTitle("Login");
		primaryStage.setResizable(false);
		primaryStage.setScene(cena);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
	}
}
