package control;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ProdutoController {
	@FXML
	private Label lblBemVindo;
	@FXML
	private TextField txtNome;
	
	
	void inserir() {
		JOptionPane.showMessageDialog(null, txtNome.getText());
	}

	
}
