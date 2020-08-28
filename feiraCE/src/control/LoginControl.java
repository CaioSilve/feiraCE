package control;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginControl {

	@FXML
	public TextField txtUser;
	
	@FXML
	public PasswordField txtPass;
	
	public void entrar() {
		JOptionPane.showMessageDialog(null, txtUser.getText());
		JOptionPane.showMessageDialog(null, txtPass.getText());
	}
}
