package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import dao.DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.entities.Fornecedor;
import model.enums.Estados;
import model.enums.TiposForn;
import views.Utilitarios.Alerta;

public class FornecedorController implements Initializable {
	@FXML
	private Label lblBemVindo;
	@FXML
	private TextField txtDesc;
	@FXML
	private TextField txtTele;
	@FXML
	private ChoiceBox<TiposForn> cboTipo;
	@FXML
	private Label lblRegua;
	@FXML
	private TextField txtEnde;
	@FXML
	private TextField txtCep;
	@FXML
	private TextField txtCida;
	@FXML
	private ChoiceBox<Estados> cboEsta;
	@FXML
	private TextField txtEmail;
	@FXML
	private TableView<Fornecedor> tblForns;
	@FXML
	private TableColumn<Fornecedor, String> colDesc;
	@FXML
	private TableColumn<Fornecedor, TiposForn> colTipo;
	@FXML
	private TableColumn<Fornecedor, String> colEsta;
	@FXML
	private TableColumn<Fornecedor, String> colTele;
	@FXML
	private TableColumn<Fornecedor, String> colDivi;
	@FXML
	private TableColumn<Fornecedor, String> colGast;
	
	Locale localBrasil = new Locale("pt", "BR");
	
	//String brasil = NumberFormat.getCurrencyInstance(localBrasil).format(valor);
	
	private DAO<Fornecedor> daoForns = new DAO<>(Fornecedor.class);
	
	private List<Fornecedor> forns = new ArrayList<>();
	
	private Fornecedor forn = new Fornecedor();
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarTbl(null);
		cboEsta.getItems().setAll(Estados.values());
		cboTipo.getItems().setAll(TiposForn.values());
		txtCep.focusedProperty().addListener((ov, oldV, newV) ->{
			if(!newV) {
				if(txtCep.getText().isEmpty() || txtCep.getLength() < 9) {
					Alerta.showAlert("CEP", null, "Cep inserido incorretamente", AlertType.WARNING);
					txtCep.requestFocus();
				} else {
					forn.buscarCep(txtCep.getText().trim());
					txtCida.setText(forn.getCida());
					cboEsta.getSelectionModel().select(Estados.valueOf(forn.getEsta()));
					txtCida.setDisable(false);
					cboEsta.setDisable(false);
				}
			}
		});
		colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
		colEsta.setCellValueFactory(new PropertyValueFactory<>("esta"));
		colTele.setCellValueFactory(new PropertyValueFactory<>("tele"));
		colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		colDivi.setCellValueFactory(new PropertyValueFactory<>("divida"));
		colGast.setCellValueFactory(new PropertyValueFactory<>("gastos"));
	}
	
	

	// Event Listener on Button.onMouseClicked
	@FXML
	public void recarregarTbl(MouseEvent event) {
		carregarTbl(null);
	}
	
	private void carregarTbl(Fornecedor f) {
		forns = daoForns.consultar("todosFornecedores");
		if (f == null) {
			tblForns.setItems(listaDeFornecedores());
		} else {
			ObservableList<Fornecedor> obsForn = FXCollections.observableArrayList(f);
			tblForns.setItems(obsForn);
		}
	}
	
	private ObservableList<Fornecedor> listaDeFornecedores() {
    	return FXCollections.observableArrayList(forns);
	}
	
	private void pegarForn() {
		if(!campoVazio()) {
			forn.setDesc(txtDesc.getText().trim());
			forn.setCida(txtCida.getText().trim());
			forn.setEsta(cboEsta.getSelectionModel().getSelectedItem().toString());
			forn.setCep(txtCep.getText().trim());
			forn.setEnde(txtEnde.getText().trim());
			forn.setEmail(txtEmail.getText().trim());
			forn.setTele(txtTele.getText().trim());
			forn.setTipo(cboTipo.getSelectionModel().getSelectedItem());
			forn.setDivida(0);
			forn.setGastos(0);
		} 
	}



	private boolean campoVazio() {
		if(txtDesc.getText().isEmpty() || txtTele.getText().isEmpty() || txtEnde.getText().isEmpty() ||
			txtCep.getText().isEmpty() || txtEmail.getText().isEmpty() || txtCida.getText().isEmpty() ||
			cboEsta.getSelectionModel().isEmpty() || cboTipo.getSelectionModel().isEmpty()) {
			return true;
		}
		Alerta.showAlert("Campos Vazios", null, "Nâo pode haver campos vazios", AlertType.WARNING);
		return false;
	}



	public void inserir() {
		// TODO Auto-generated method stub
		
	}



	public void limpar() {
		txtCida.setDisable(true);
		cboEsta.setDisable(true);
		txtDesc.setText("");
		cboTipo.getSelectionModel().clearSelection();
		txtTele.setText("");
		txtEnde.setText("");
		txtCep.setText("");
		txtCida.setText("");
		cboEsta.getSelectionModel().clearSelection();
		txtEmail.setText("");
		txtDesc.requestFocus();
		tblForns.getSelectionModel().clearSelection();
	}
}
