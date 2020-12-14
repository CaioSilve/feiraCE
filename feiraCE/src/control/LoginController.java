package control;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import animatefx.animation.SlideInDown;
import dao.DAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.entities.Usuario;
import views.Utilitarios.Alerta;

public class LoginController {
	
	private DAO<Usuario> daoUsua = new DAO<>(Usuario.class); 
	
	private static Usuario usua = null;

	@FXML
	public JFXTextField txtUser;
	
	@FXML
	public JFXPasswordField txtPass;
	
	@SuppressWarnings("static-access")
	public void entrar() throws Exception {	
		if (txtUser.getText().isEmpty()) {
			Alerta.showAlert("Usuário", null, "Nome de usuário vazio", AlertType.INFORMATION);
			return;
		}
		
		if (txtPass.getText().isEmpty()) {
			Alerta.showAlert("Usuário", null, "Senha de usuário vazia", AlertType.INFORMATION);
			return;
		}
		
		
		
		try {
			this.usua = daoUsua.consultarUm("obterUsuario", "nome", txtUser.getText());
			
			if (txtUser.getText().equalsIgnoreCase(usua.getNome())) {
				if (txtPass.getText().equals(usua.getSenha())) {
					Pane loader = FXMLLoader.load(getClass().getResource("../views/FXMLBarra.fxml"));
					Stage stage = (Stage) txtUser.getScene().getWindow();
					stage.setTitle("Tela Inicial");
					stage.setResizable(true);
					Scene scene = new Scene(loader, 1300, 700);
					stage.setScene(scene);
					stage.centerOnScreen();
					new SlideInDown(loader).play();
				} else {
					Alerta.showAlert("Usuário", null, "Senha não bate com usuário", AlertType.ERROR);
				}
			}
		} catch(Exception ex) {
			Alerta.showAlert("Usuário", null, "Usuário não encontrado", AlertType.WARNING);
			System.out.println(ex);
		}
		
		
		
	}
	
	
	public static Usuario getUsuario() {
		return usua;
	}
}











