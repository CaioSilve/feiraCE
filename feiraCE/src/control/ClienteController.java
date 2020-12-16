package control;

import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import dao.DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.entities.Cliente;
import model.enums.Estados;
import views.Utilitarios.Alerta;
import views.Utilitarios.Formatacoes;

public class ClienteController implements Initializable {
	@FXML
	private Label lblBemVindo;
	@FXML
	private TextField txtNome;
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
	private TableView<Cliente> tblClie;
	@FXML
	private TableColumn<Cliente, String> colNome;
	@FXML
	private TableColumn<Cliente, String> colEsta;
	@FXML
	private TableColumn<Cliente, String> colCida;
	@FXML
	private TableColumn<Cliente, String> colCel;
	@FXML
	private TableColumn<Cliente, String> colCpf;
	@FXML
	private TableColumn<Cliente, Integer> colCon;
	@FXML
	private DatePicker txtData;
	@FXML
	private TextField txtRg;
	@FXML
	private TextField txtCpf;
	@FXML
	private TextField txtTele;
	@FXML
	private TextField txtCel;
	@FXML
	private TextField txtBairro;
	@FXML
	private TextField txtNume;
	@FXML
	private TextField txtContas;
	@FXML
	private CheckBox chkRece;
	
	
	private DAO<Cliente> daoClie = new DAO<>(Cliente.class);
	
	private Cliente clie = new Cliente();
	
	private List<Cliente> clies = new ArrayList<>();
	
	private boolean limpo = true;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		txtCep.focusedProperty().addListener((ov, oldV, newV) ->{
			if(!newV) {
				if(txtCep.getText().isEmpty() || txtCep.getLength() < 9) {
					Alerta.showAlert("CEP", null, "Cep inserido incorretamente", AlertType.WARNING);
					txtCep.requestFocus();
				} else {
					clie.buscarCep(txtCep.getText().trim());
					txtCida.setText(clie.getCida());
					cboEsta.getSelectionModel().select(Estados.valueOf(clie.getEsta()));
					txtCida.setDisable(false);
					cboEsta.setDisable(false);
					txtEnde.requestFocus();
				}
			}
		});
		
		txtNome.focusedProperty().addListener((ov, oldV, newV) ->{
			if(!newV) {
				if(txtNome.getText().trim().isEmpty()) {
					return;
				} else {
					if(!limpo) return;
					Cliente conClie = daoClie.consultarUm("obterCliente", "nome", txtNome.getText());
					if(conClie == null) return;
					
					if(Alerta.showConfirm("Alterar cliente", "Trazer o cliente para alteração?", "Já existe um cliente com este Nome")) {
						clie = conClie;
						setCampos();
					}
						
					carregarTbl(conClie);
				}
			}
		});
		
		txtCpf.focusedProperty().addListener((ov, oldV, newV) -> {
			if(!newV) {
				if(txtNome.getText().trim().isEmpty()) {
					return;
				} else {
					if(!limpo) return;
					Cliente conClie = daoClie.consultarUm("obterClienteCpf", "cpf", txtCpf.getText());
				
					if(conClie == null) return;
					
					if(Alerta.showConfirm("Alterar cliente", "Trazer o cliente para alteração?", "Já existe um cliente com este Nome")) {
						clie = conClie;
						setCampos();
					}
						
					carregarTbl(conClie);
				}
			}
		});

		
		tblClie.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)){
		            if(event.getClickCount() == 2){
		            	clie = pegarTbl();
		            	if(clie != null) setCampos();
		            }
		        }
			};
		});
		
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colEsta.setCellValueFactory(new PropertyValueFactory<>("esta"));
		colCida.setCellValueFactory(new PropertyValueFactory<>("cida"));
		colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		colCel.setCellValueFactory(new PropertyValueFactory<>("cele"));
		colCon.setCellValueFactory(new PropertyValueFactory<>("contas"));
		Formatacoes.emailField(txtEmail);
		Formatacoes.mascaraData(txtData);
		Formatacoes.cepField(txtCep);
		Formatacoes.formaField(txtTele, "tele");
		Formatacoes.formaField(txtCel, "cel");
		Formatacoes.formaField(txtCpf, "cpf");
		Formatacoes.formaField(txtRg, "rg");
		cboEsta.getItems().setAll(Estados.values());
		carregarTbl(null);
	}
	
	
	// Event Listener on Button.onMouseClicked
	@FXML
	public void recarregarTbl(MouseEvent event) {
		carregarTbl(null);
	}
	
	private void carregarTbl(Cliente clie) {
		clies = daoClie.consultar("todosClientes");
		if(clie == null) {
			tblClie.setItems(listaDeClientes());
		} else {
			ObservableList<Cliente> obsClie = FXCollections.observableArrayList(clie);
			tblClie.setItems(obsClie);
		}
		tblClie.getSelectionModel().clearSelection();
		tblClie.refresh();
	}
	
	private ObservableList<Cliente> listaDeClientes(){
		return FXCollections.observableArrayList(clies);
	}

	private Cliente pegarTbl() {
		return tblClie.getSelectionModel().getSelectedItem();
	}
	
	private boolean campoVazio() {
		if(txtNome.getText().isEmpty() || txtBairro.getText().isEmpty() || txtCel.getText().isEmpty() ||
			cboEsta.getSelectionModel().isEmpty() || txtCep.getText().isEmpty() || 
			txtCida.getText().isEmpty() || txtCpf.getText().isEmpty() || 
			txtEnde.getText().isEmpty() || txtNume.getText().isEmpty() || txtData.getValue() == null) {
			
			Alerta.showAlert("Campos vazios", null, "Não pode haver campos vazios", AlertType.WARNING);
			return true;
		}
		return false;
	}

	private void getCampos() {
		clie.setNome(txtNome.getText().trim());
		clie.setCpf(txtCpf.getText().trim());
		clie.setRg(txtRg.getText().trim());
		clie.setCep(txtCep.getText().trim());
		clie.setEnde(txtEnde.getText().trim());
		clie.setCida(txtCida.getText().trim());
		clie.setEsta(cboEsta.getSelectionModel().getSelectedItem().toString());
		clie.setBairro(txtBairro.getText().trim());
		clie.setCele(txtCel.getText().trim());
		clie.setTele(txtTele.getText().trim());
		clie.setNasc(Date.from(txtData.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		clie.setEmail(txtEmail.getText().trim());
		clie.setNume(txtNume.getText().trim());
		clie.setContas(Integer.parseInt(txtContas.getText()));
		clie.setRece(chkRece.isSelected());
	}
	
	private void setCampos() {
		limpo = false;
		txtNome.setText(clie.getNome());
		txtCpf.setText(clie.getCpf());
		txtRg.setText(clie.getRg());
		txtCep.setText(clie.getCep());
		txtEnde.setText(clie.getEnde());
		txtCida.setText(clie.getCida());
		cboEsta.getSelectionModel().select(Estados.valueOf(clie.getEsta()));
		txtBairro.setText(clie.getBairro());
		txtCel.setText(clie.getCele());
		txtTele.setText(clie.getTele());
		txtData.setValue(clie.getNasc().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		txtEmail.setText(clie.getEmail());
		txtNume.setText(clie.getNume());
		txtContas.setText(clie.getContas() + "");
		chkRece.setSelected(clie.isRece());
		cboEsta.setDisable(false);
		txtCida.setDisable(false);
	}

	public void limpar() {
		txtNome.setText("");
		txtCpf.setText("");
		txtRg.setText("");
		txtCep.setText("");
		txtEnde.setText("");
		txtCida.setText("");
		cboEsta.getSelectionModel().clearSelection();
		txtBairro.setText("");
		txtCel.setText("");
		txtTele.setText("");
		txtData.setValue(null);
		txtEmail.setText("");
		txtNume.setText("");
		txtContas.setText("");
		chkRece.setSelected(false);;
		cboEsta.setDisable(true);
		txtCida.setDisable(true);
		tblClie.getSelectionModel().clearSelection();
		txtNome.requestFocus();
		clie = null;
		limpo = true;
	}

	public void inserir() {
		if(campoVazio()) return;
		
		getCampos();
		Cliente conClie = new Cliente();
		
		String campo;
			
		conClie = daoClie.consultarUm("obterCliente", "nome", clie.getNome());
		if(conClie == null) {
			
			conClie = daoClie.consultarUm("obterClienteCpf", "cpf", clie.getCpf());
			if(conClie == null) {
				daoClie.incluirAgora(clie);
				Alerta.showAlert("Inserção Cliente", null, "Cliente cadastrado com sucesso!", AlertType.INFORMATION);
				carregarTbl(null);
				limpar();
				return;
			} else {
				campo = "CPF";
			}
				
		} else {
			campo = "nome";
		}
		
		
		if(Alerta.showConfirm("Alterar cliente", "Trazer o cliente para alteração?", "Já existe um cliente com este " + campo)) {
			clie = conClie;
			setCampos();
		}
		
		carregarTbl(conClie);
			
			
			
	}


	
	public void consultar() {
		if(txtNome.getText().trim().isEmpty() && txtCpf.getText().trim().isEmpty()) {
			Alerta.showAlert("Consulta Cliente", null, "Favor preencher ou o campo Nome ou o campo CPF", AlertType.INFORMATION);
			return;
		}
		
		clie = daoClie.consultarUm("obterCliente", "nome", txtNome.getText());
		if(clie == null) {
			clie = daoClie.consultarUm("obterClienteCpf", "cpf", txtCpf.getText());
			if(clie == null) {
				Alerta.showAlert("Consulta Cliente", "Cliente não encontrado", "Favor, verificar se os dados"
						+ " inseridos estão corretos", AlertType.INFORMATION);
				return;
			}
		}
		
		setCampos();
		carregarTbl(clie);
		
	}


	
	
	public void alterar() {
		if(campoVazio()) return;
		if(clie == null) {
			Alerta.showAlert("Alteração Cliente", null, "Favor selecionar um Cliente da tabela ou consultar", AlertType.INFORMATION);
			return;
		}
		
		getCampos();
		
		daoClie.alterarAgora(clie);
		
		limpar();
		
		Alerta.showAlert("Alteração Cliente", null, "Cliente alterado com sucesso!", AlertType.INFORMATION);
		
		carregarTbl(null);
		
		
	}


	public void deletar() {
		if(pegarTbl() == null) {
			Alerta.showAlert("Deletar Cliente", null, "Favor selecionar um Cliente da tabela ou consultar", AlertType.INFORMATION);
			return;
		}
		
		if(Alerta.showConfirm("Deletar Cliente?", null, "Deseja realmente deletar " + pegarTbl().getNome() + "?")) {
			daoClie.excluirAgora(pegarTbl());
			Alerta.showAlert("Exclusão", null, "Cliente excluído com sucesso!", AlertType.CONFIRMATION);
			carregarTbl(null);
		}
	}

	








}






