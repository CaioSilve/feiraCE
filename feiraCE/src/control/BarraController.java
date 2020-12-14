package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import animatefx.animation.FadeIn;
import animatefx.animation.SlideInRight;
import animatefx.animation.SlideOutRight;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.enums.Permissoes;
import views.Utilitarios.Alerta;

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
	private AnchorPane anchorPane;
	@FXML
	private Label lblBemVindo;
	@FXML
	private AnchorPane anchorBtns;

	private String telaAberta;
	private boolean barraBtns;
	private ProdutoController prodCtrl;
	private CompraController cmpCtrl;
	private UsuarioController usuCtrl;
	private FornecedorController fornCtrl;
	private ClienteController clieCtrl;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblBemVindo.setText("Bem-vindo, " + LoginController.getUsuario().getNome());
		anchorBtns.setVisible(false);
		barraBtns = false;
	}
	
	
	// Event Listener on Button[#btnHome].onMouseClicked
	@FXML
	public void Home(MouseEvent event) {
		telaAberta = "Home";
		resetarBtns();
		
		if(barraBtns) {
			new SlideOutRight(anchorBtns).play();
			barraBtns = false;
		}
		
		borderPane.setCenter(anchorPane);
		new FadeIn(anchorPane).play();
		
	}
	
	
	// Event Listener on Button[#btnVendas].onMouseClicked
	@FXML
	public void Vendas(MouseEvent event) {
		telaAberta = "Vendas";
		resetarBtns();
		btnVendas.setStyle("-fx-background-color:  #040230;");
		carregarTela("Venda");
		new FadeIn(btnVendas).play();
	}
	
	
	// Event Listener on Button[#btnCompras].onMouseClicked
	@FXML
	public void Compras(MouseEvent event) {
		telaAberta = "Compras";
		resetarBtns();
		btnCompras.setStyle("-fx-background-color:  #040230;");
		carregarTela("Compra");
		new FadeIn(btnCompras).play();
	}
	
	
	// Event Listener on Button[#btnProdutos].onMouseClicked
	@FXML
	public void Produtos(MouseEvent event) {
		telaAberta = "Produtos";
		resetarBtns();
		btnProdutos.setStyle("-fx-background-color:  #040230;");
		carregarTela("Produto");
		new FadeIn(btnProdutos).play();
	}
	
	
	// Event Listener on Button[#btnClientes].onMouseClicked
	@FXML
	public void Clientes(MouseEvent event) {
		telaAberta = "Clientes";
		resetarBtns();
		btnClientes.setStyle("-fx-background-color:  #040230;");
		carregarTela("Cliente");
		new FadeIn(btnClientes).play();
	}
	
	
	// Event Listener on Button[#btnFuncionarios].onMouseClicked
	@FXML
	public void Funcionarios(MouseEvent event) {
		telaAberta = "Funcionarios";
		resetarBtns();
		btnFuncionarios.setStyle("-fx-background-color:  #040230;");
		carregarTela("Funcionario");
		new FadeIn(btnFuncionarios).play();
	}
	
	
	// Event Listener on Button[#btnFornecedores].onMouseClicked
	@FXML
	public void Forncedores(MouseEvent event) {
		telaAberta = "Fornecedores";
		resetarBtns();
		btnFornecedores.setStyle("-fx-background-color:  #040230;");
		carregarTela("Fornecedor");
		new FadeIn(btnFornecedores).play();
	}
	
	
	// Event Listener on Button[#btnUsuarios].onMouseClicked
	@FXML
	public void Usuarios(MouseEvent event) {
		if(LoginController.getUsuario().getPermi() != Permissoes.ADMINISTRADOR) {
			Alerta.showAlert("Permissão negada", "Você não tem permissão para acessar esta opção", 
			"O usuário " + LoginController.getUsuario().getNome() + " não é Administrador", AlertType.WARNING);
			return;
		}
		telaAberta = "Usuarios";
		resetarBtns();
		btnUsuarios.setStyle("-fx-background-color:  #040230;");
		carregarTela("Usuario");
		new FadeIn(btnUsuarios).play();
	}
	
	
	private void carregarTela(String tela) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/FXML" + tela + ".fxml"));
			Parent root = loader.load();
			if (telaAberta == "Vendas") {
				
			} else if(telaAberta == "Compras") {
				cmpCtrl = loader.getController();
			} else if(telaAberta == "Produtos") {
				prodCtrl = loader.getController();
			} else if(telaAberta == "Clientes") {
				clieCtrl = loader.getController();
			} else if(telaAberta == "Funcionarios") {
				
			} else if(telaAberta == "Fornecedores") {
				fornCtrl = loader.getController();
			} else {
				usuCtrl = loader.getController();
			}
			borderPane.setCenter(root);
			new FadeIn(root).play();
		} catch (Exception e) {
			Alerta.showAlert("Erro", "Tela indisponível", "" + e.getCause() /*+ tela*/, AlertType.ERROR);
			System.out.println(e);
		}
		
		
	}
	
	
	private void resetarBtns() {
		btnVendas.setStyle("-fx-background-color:   #075965;");
		btnCompras.setStyle("-fx-background-color:   #075965;");
		btnProdutos.setStyle("-fx-background-color:   #075965;");
		btnFornecedores.setStyle("-fx-background-color:   #075965;");
		btnFuncionarios.setStyle("-fx-background-color:   #075965;");
		btnClientes.setStyle("-fx-background-color:   #075965;");
		btnUsuarios.setStyle("-fx-background-color:   #075965;");
		if(!barraBtns) {
			new SlideInRight(anchorBtns).play();
			anchorBtns.setVisible(true);
			barraBtns = true;
		}
	}
	
	
	
	@FXML
	public void inserir(MouseEvent event) {
		if (telaAberta == "Vendas") {
			
		} else if(telaAberta == "Compras") {
			
		} else if(telaAberta == "Produtos") {
			prodCtrl.inserir();
		} else if(telaAberta == "Clientes") {
			clieCtrl.inserir();
		} else if(telaAberta == "Funcionarios") {
			
		} else if(telaAberta == "Fornecedores") {
			fornCtrl.inserir();
		} else {
			usuCtrl.inserir();
		}
	}
	
	
	// Event Listener on Button.onMouseClicked
	@FXML
	public void limpar(MouseEvent event) {
		if (telaAberta == "Vendas") {
			
		} else if(telaAberta == "Compras") {
			
		} else if(telaAberta == "Produtos") {
			prodCtrl.limpar();
		} else if(telaAberta == "Clientes") {
			clieCtrl.limpar();
		} else if(telaAberta == "Funcionarios") {
			
		} else if(telaAberta == "Fornecedores") {
			fornCtrl.limpar();
		} else {
			usuCtrl.limpar();
		}
	}
	
	
	// Event Listener on Button.onMouseClicked
	@FXML
	public void consultar(MouseEvent event) {
		if (telaAberta == "Vendas") {
			
		} else if(telaAberta == "Compras") {
			
		} else if(telaAberta == "Produtos") {
			prodCtrl.consultar();
		} else if(telaAberta == "Clientes") {
			clieCtrl.consultar();
		} else if(telaAberta == "Funcionarios") {
			
		} else if(telaAberta == "Fornecedores") {
			fornCtrl.consultar();
		} else {
			usuCtrl.consultar();
		}
	}
	
	// Event Listener on Button.onMouseClicked
	@FXML
	public void alterar(MouseEvent event) {
if (telaAberta == "Vendas") {
			
		} else if(telaAberta == "Compras") {
			
		} else if(telaAberta == "Produtos") {
			prodCtrl.alterar();
		} else if(telaAberta == "Clientes") {
			clieCtrl.alterar();
		} else if(telaAberta == "Funcionarios") {
			
		} else if(telaAberta == "Fornecedores") {
			fornCtrl.alterar();
		} else {
			usuCtrl.alterar();
		}
	}
	
	// Event Listener on Button.onMouseClicked
	@FXML
	public void deletar(MouseEvent event) {
		if (telaAberta == "Vendas") {
			
		} else if(telaAberta == "Compras") {
			
		} else if(telaAberta == "Produtos") {
			prodCtrl.limpar();
		} else if(telaAberta == "Clientes") {
			clieCtrl.deletar();
		} else if(telaAberta == "Funcionarios") {
			
		} else if(telaAberta == "Fornecedores") {
			fornCtrl.deletar();
		} else {
			usuCtrl.deletar();
		}
	}
	
	
	// Event Listener on Button.onMouseClicked
	@FXML
	public void sair(MouseEvent event) throws IOException{
		Stage stage = (Stage) borderPane.getScene().getWindow();
		
		Pane pane = FXMLLoader.load(getClass().getResource("/views/FXMLLogin.fxml"));

		Scene cena = new Scene(pane);
		stage.setTitle("Login");
		stage.setWidth(800);
		stage.setHeight(600);
		stage.setResizable(false);
		stage.setScene(cena);
		stage.show();
		stage.centerOnScreen();
	}
}
