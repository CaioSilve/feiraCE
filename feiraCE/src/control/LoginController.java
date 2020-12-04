package control;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import animatefx.animation.ZoomIn;
import dao.DAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.entities.Usuario;

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
			JOptionPane.showMessageDialog(null, "Favor inserir um usuário", "Usuário", 2);
			return;
		}
		
		if (txtPass.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Favor inserir uma senha", "Senha", 2);
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
					new ZoomIn(loader).play();
				} else {
					JOptionPane.showMessageDialog(null, "Senha não bate com o usuário", "Senha", 0);
				}
			}
		} catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro aqui cara", 1);
			//JOptionPane.showMessageDialog(null, "Usuário não encontrado", "Usuário", 0);
		}
		
		
		
	}
	
	
	public static Usuario getUsuario() {
		return usua;
	}
}











