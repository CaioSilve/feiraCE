package views;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerta {

	
	public static void showAlert(String titu, String cabe, String cont, AlertType tipo) {
		Alert ale = new Alert(tipo);
		ale.setTitle(titu);
		ale.setHeaderText(cabe);
		ale.setContentText(cont);
		ale.show();
	}
}
