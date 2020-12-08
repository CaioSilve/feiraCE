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
	
	public static boolean showConfirm(String titu, String cabe, String cont) {
		Alert ale = new Alert(AlertType.CONFIRMATION);
		ale.setTitle(titu);
		ale.setHeaderText(cabe);
		ale.setContentText(cont);
		if(ale.showAndWait().get() != javafx.scene.control.ButtonType.OK) {
			return false;
		}
		return true;
	}
}
