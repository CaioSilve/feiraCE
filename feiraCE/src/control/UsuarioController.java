package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.entities.Usuario;
import model.enums.Permissoes;
import views.Utilitarios.Alerta;

public class UsuarioController implements Initializable {
	@FXML
	private Label lblBemVindo;
	@FXML
	private TableView<Usuario> tblUsuarios;
	@FXML
	private TableColumn<Usuario, String> colDesc;
	@FXML
	private TableColumn<Usuario, Permissoes> colPermi;
	@FXML
	private TextField txtDesc;
	@FXML
	private PasswordField txtSenha;
	@FXML
	private ComboBox<Permissoes> cboPermi;
	
	
	private DAO<Usuario> daoUsua = new DAO<Usuario>(Usuario.class);
	
	private List<Usuario> usuas = new ArrayList<>();
	
	private Usuario sele;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tblUsuarios.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)){
		            if(event.getClickCount() == 2){
		            	setCampos();
		            }
		        }
			};
		});
		
		cboPermi.getItems().setAll(Permissoes.values());
		colDesc.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colPermi.setCellValueFactory(new PropertyValueFactory<>("permi"));
		carregarTbl(null);
	}
	
	@FXML
	public void recarregarTbl(MouseEvent event) {
		carregarTbl(null);
	}
	
	private void carregarTbl(Usuario usua) {
		usuas = daoUsua.consultar("todosUsuarios");
		if(usua == null) {
			tblUsuarios.setItems(listaDeUsuarios());
		} else {
			ObservableList<Usuario> obsUsua = FXCollections.observableArrayList(usua);
			tblUsuarios.setItems(obsUsua);
		}
		tblUsuarios.getSelectionModel().clearSelection();
	}
	
	private Usuario pegarTbl() {
		return tblUsuarios.getSelectionModel().getSelectedItem();
	}
	
	private ObservableList<Usuario> listaDeUsuarios() {
        	return FXCollections.observableArrayList(usuas);
    }
	
	private void setCampos() {
		sele = pegarTbl();
        txtDesc.setText(sele.getNome());
        txtSenha.setText(sele.getSenha());
        cboPermi.getSelectionModel().select(sele.getPermi());
	}
	
	private boolean campoVazio() {
		if((txtDesc.getText().isEmpty()) || (txtSenha.getText().isEmpty()) 
				|| (cboPermi.getSelectionModel().getSelectedItem() == null)) {
			Alerta.showAlert("Campo Vazio", null, "Não pode haver campos vazios", AlertType.WARNING);
			return true;
		}
		return false;
	}

	public void inserir() {
		if(campoVazio()) return;
		Usuario usua = new Usuario(txtDesc.getText(), txtSenha.getText(), cboPermi.getSelectionModel().getSelectedItem());
		Usuario usua2 = daoUsua.consultarUm("obterUsuario", "nome", usua.getNome());
		if(usua2 == null) {
			daoUsua.incluirAgora(usua);
			Alerta.showAlert("Inserir Usuário", null, "Usuário Inserido com sucesso", AlertType.CONFIRMATION);
			carregarTbl(null);
		} else {
			Alerta.showAlert("Usuário", null, "O usuário já esta cadastrado!", AlertType.INFORMATION);
			usua = usua2;
			carregarTbl(usua);
			sele = usua;
			setCampos();
		}
		
	}

	public void limpar() {
		txtDesc.setText("");
		txtSenha.setText("");
		cboPermi.getSelectionModel().clearSelection();
		sele = null;
		tblUsuarios.getSelectionModel().clearSelection();
		
	}

	public void consultar() {
		if(txtDesc.getText().isEmpty()) {
			Alerta.showAlert("Erro ao consultar", null, "O campo descrição não pode estar vazio", AlertType.WARNING);
			return;
		}
		Usuario usua = daoUsua.consultarUm("obterUsuario", "nome", txtDesc.getText());
		if(usua == null) {
			Alerta.showAlert("Consulta Usuário", null, "Usuário não encontrado", AlertType.INFORMATION);
			return;
		}
		
		carregarTbl(usua);
	}
	
	public void alterar() {
		if(campoVazio()) return;
		if(sele == null) {
			Alerta.showAlert("Alterar Usuário", null, "Favor selecionar usuário na tabela", AlertType.WARNING);
			return;
		}
		
		sele.setNome(txtDesc.getText().trim());
		sele.setSenha(txtSenha.getText().trim());
		sele.setPermi(cboPermi.getSelectionModel().getSelectedItem());
		
		daoUsua.alterarAgora(sele);
		sele = null;
		
		Alerta.showAlert("Alteração", null, "Usuário alterado com sucesso!", AlertType.CONFIRMATION);
		carregarTbl(null);
		
	}

	
	public void deletar() {
		if(pegarTbl() == null) {
			Alerta.showAlert("Deletar", null, "Favor selecione um registro", AlertType.INFORMATION);
			return;
		}
		
		if(Alerta.showConfirm("Deletar Usuário?", null, "Deseja realmente deletar " + pegarTbl().getNome() + "?")) {
			daoUsua.excluirAgora(pegarTbl());
			Alerta.showAlert("Exclusão", null, "Usuário excluído com sucesso!", AlertType.CONFIRMATION);
			carregarTbl(null);
		}
		
		
	}


	
}
