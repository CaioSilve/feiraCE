package control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import views.Alerta;

public class BarraController {
	@FXML
	private BorderPane borderPane;
	@FXML
	private AnchorPane anchorPane;

	// Event Listener on Button.onMouseClicked
	@FXML
	public void Home(MouseEvent event) {
		borderPane.setCenter(anchorPane);
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void Vendas(MouseEvent event) {
		carregarTela("Venda");
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void Usuarios(MouseEvent event) {
		
	}
	
	private void carregarTela(String tela) {
		Parent root = null;
		
		try {
			root = FXMLLoader.load(getClass().getResource("/views/FXML" + tela + ".fxml"));
		} catch (Exception e) {
			Alerta.showAlert("Erro", "Tela indisponível", "Erro ao carregar a tela " + tela, AlertType.ERROR);
		}
		
		borderPane.setCenter(root);
	}
	
}
