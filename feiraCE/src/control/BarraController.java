package control;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import views.Alerta;

public class BarraController implements Initializable {
	
	
	
	
	@FXML
	private BorderPane borderPane;
	@FXML
	private Button btnHome;
	@FXML
	private Button btnVendas;
	@FXML
	private Button btnCompras;
	@FXML
	private Button btnProdutos;
	@FXML
	private Button btnClientes;
	@FXML
	private Button btnFuncionarios;
	@FXML
	private Button btnFornecedores;
	@FXML
	private Button btnUsuarios;
	@FXML
	private Label lblUsuario;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private Label lblBemVindo;
	
	
	private String telaAberta = "Home";

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblBemVindo.setText("Bem-vindo, " + LoginController.getUsuario().getNome());
		lblUsuario.setText(LoginController.getUsuario().getNome());
	}
	
	
	// Event Listener on Button[#btnHome].onMouseClicked
	@FXML
	public void Home(MouseEvent event) {
		telaAberta = "Home";
		resetarBtns();
		borderPane.setCenter(anchorPane);
	}
	// Event Listener on Button[#btnVendas].onMouseClicked
	@FXML
	public void Vendas(MouseEvent event) {
		telaAberta = "Vendas";
		resetarBtns();
		btnVendas.setStyle("-fx-background-color:  #040230;");
		carregarTela("Venda");
	}
	// Event Listener on Button[#btnCompras].onMouseClicked
	@FXML
	public void Compras(MouseEvent event) {
		telaAberta = "Compras";
		resetarBtns();
		btnCompras.setStyle("-fx-background-color:  #040230;");
		//carregarTela("Compra");
	}
	// Event Listener on Button[#btnProdutos].onMouseClicked
	@FXML
	public void Produtos(MouseEvent event) {
		telaAberta = "Produtos";
		resetarBtns();
		btnProdutos.setStyle("-fx-background-color:  #040230;");
		//carregarTela("Produto");
	}
	// Event Listener on Button[#btnClientes].onMouseClicked
	@FXML
	public void Clientes(MouseEvent event) {
		telaAberta = "Clientes";
		resetarBtns();
		btnClientes.setStyle("-fx-background-color:  #040230;");
		//carregarTela("Cliente");
	}
	// Event Listener on Button[#btnFuncionarios].onMouseClicked
	@FXML
	public void Funcionarios(MouseEvent event) {
		telaAberta = "Funcionarios";
		resetarBtns();
		btnFuncionarios.setStyle("-fx-background-color:  #040230;");
		//carregarTela("Funcionario");
	}
	// Event Listener on Button[#btnFornecedores].onMouseClicked
	@FXML
	public void Forncedores(MouseEvent event) {
		telaAberta = "Fornecedores";
		resetarBtns();
		btnFornecedores.setStyle("-fx-background-color:  #040230;");
		//carregarTela("Fornecedor");
	}
	// Event Listener on Button[#btnUsuarios].onMouseClicked
	@FXML
	public void Usuarios(MouseEvent event) {
		telaAberta = "Usuarios";
		resetarBtns();
		btnUsuarios.setStyle("-fx-background-color:  #040230;");
		//carregarTela("Usuario");
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
	
	private void resetarBtns() {
		btnVendas.setStyle("-fx-background-color:   #075965;");
		btnCompras.setStyle("-fx-background-color:   #075965;");
		btnProdutos.setStyle("-fx-background-color:   #075965;");
		btnFornecedores.setStyle("-fx-background-color:   #075965;");
		btnFuncionarios.setStyle("-fx-background-color:   #075965;");
		btnClientes.setStyle("-fx-background-color:   #075965;");
		btnUsuarios.setStyle("-fx-background-color:   #075965;");
	}
}
