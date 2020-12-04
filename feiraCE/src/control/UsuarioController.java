package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import dao.DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Usuario;
import model.enums.Permissoes;

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
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colDesc.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colPermi.setCellValueFactory(new PropertyValueFactory<>("permi"));
		carregarTbl();
	}


	
	private void carregarTbl() {
		cboPermi.getItems().setAll(Permissoes.values());
		usuas = daoUsua.consultar("todosUsuarios");
		tblUsuarios.setItems(listaDeUsuarios());
	}
	
	private ObservableList<Usuario> listaDeUsuarios() {
        return FXCollections.observableArrayList(usuas);
    }



	public void inserir() {
		Usuario usua = new Usuario(txtDesc.getText(), txtSenha.getText(), cboPermi.getSelectionModel().getSelectedItem());
		
		if(daoUsua.consultarUm("obterUsuario", "nome", usua.getNome()) == null) {
			daoUsua.incluirAgora(usua);
		}
		
		carregarTbl();
	}



	public void limpar() {
		txtDesc.setText("");
		txtSenha.setText("");
		cboPermi.getSelectionModel().clearSelection();
	}
	
	
	

}
