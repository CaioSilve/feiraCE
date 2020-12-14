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


	public void limpar() {
		// TODO Auto-generated method stub
		
	}


	public void consultar() {
		// TODO Auto-generated method stub
		
	}


	public void alterar() {
		// TODO Auto-generated method stub
		
	}

	
}
