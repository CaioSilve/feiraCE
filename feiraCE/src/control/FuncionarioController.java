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
import model.entities.Funcionario;
import model.enums.Estados;
import model.enums.Niveis;
import views.Utilitarios.Alerta;
import views.Utilitarios.Formatacoes;

public class FuncionarioController implements Initializable {
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
	private TableView<Funcionario> tblFunc;
	@FXML
	private TableColumn<Funcionario, String> colNome;
	@FXML
	private TableColumn<Funcionario, String> colEsta;
	@FXML
	private TableColumn<Funcionario, String> colCida;
	@FXML
	private TableColumn<Funcionario, String> colCel;
	@FXML
	private TableColumn<Funcionario, String> colCpf;
	@FXML
	private TableColumn<Funcionario, Integer> colCon;
	@FXML
	private TableColumn<Funcionario, Integer> colNiv;
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
	private ChoiceBox<Niveis> cboNivel;
	@FXML
	private DatePicker txtAdmi;
	
	
	
	private DAO<Funcionario> daoFunc = new DAO<>(Funcionario.class);
	
	private Funcionario func = new Funcionario();
	
	private List<Funcionario> funcs = new ArrayList<>();
	
	private boolean limpo = true;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		txtCep.focusedProperty().addListener((ov, oldV, newV) ->{
			if(!newV) {
				if(txtCep.getText().isEmpty() || txtCep.getLength() < 9) {
					Alerta.showAlert("CEP", null, "Cep inserido incorretamente", AlertType.WARNING);
					txtCep.requestFocus();
				} else {
					func.buscarCep(txtCep.getText().trim());
					txtCida.setText(func.getCida());
					cboEsta.getSelectionModel().select(Estados.valueOf(func.getEsta()));
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
					Funcionario conFunc = daoFunc.consultarUm("obterFuncionario", "nome", txtNome.getText());
					if(conFunc == null) return;
					
					if(Alerta.showConfirm("Alterar funcionário", "Trazer o funcionário para alteração?", "Já existe um funcionário com este Nome")) {
						func = conFunc;
						setCampos();
					}
						
					carregarTbl(conFunc);
				}
			}
		});
		
		txtCpf.focusedProperty().addListener((ov, oldV, newV) -> {
			if(!newV) {
				if(txtNome.getText().trim().isEmpty()) {
					return;
				} else {
					if(!limpo) return;
					Funcionario conFunc = daoFunc.consultarUm("obterFuncionarioCpf", "cpf", txtCpf.getText());
				
					if(conFunc == null) return;
					
					if(Alerta.showConfirm("Alterar Funcionario", "Trazer o funcionario para alteração?", "Já existe um funcionario com este Nome")) {
						func = conFunc;
						setCampos();
					}
						
					carregarTbl(conFunc);
				}
			}
		});

		
		tblFunc.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)){
		            if(event.getClickCount() == 2){
		            	func = pegarTbl();
		            	if(func != null) setCampos();
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
	
	private void carregarTbl(Funcionario conFunc) {
		funcs = daoFunc.consultar("todosFuncionarios");
		if(conFunc == null) {
			tblFunc.setItems(listaDeFuncionarios());
		} else {
			ObservableList<Funcionario> obsFunc = FXCollections.observableArrayList(conFunc);
			tblFunc.setItems(obsFunc);
		}
		tblFunc.getSelectionModel().clearSelection();
		tblFunc.refresh();
	}
	
	private ObservableList<Funcionario> listaDeFuncionarios(){
		return FXCollections.observableArrayList(funcs);
	}

	private Funcionario pegarTbl() {
		return tblFunc.getSelectionModel().getSelectedItem();
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
		func.setNome(txtNome.getText().trim());
		func.setCpf(txtCpf.getText().trim());
		func.setRg(txtRg.getText().trim());
		func.setCep(txtCep.getText().trim());
		func.setEnde(txtEnde.getText().trim());
		func.setCida(txtCida.getText().trim());
		func.setEsta(cboEsta.getSelectionModel().getSelectedItem().toString());
		func.setBair(txtBairro.getText().trim());
		func.setCele(txtCel.getText().trim());
		func.setTele(txtTele.getText().trim());
		func.setNasc(Date.from(txtData.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		func.setEmai(txtEmail.getText().trim());
		func.setNume(txtNume.getText().trim());
		func.setNivel(cboNivel.getSelectionModel().getSelectedItem());
		//func.setRece(chkRece.isSelected());
	}
	
	private void setCampos() {
		limpo = false;
		txtNome.setText(func.getNome());
		txtCpf.setText(func.getCpf());
		txtRg.setText(func.getRg());
		txtCep.setText(func.getCep());
		txtEnde.setText(func.getEnde());
		txtCida.setText(func.getCida());
		cboEsta.getSelectionModel().select(Estados.valueOf(func.getEsta()));
		// --------- EDITAR AQUI txtBairro.setText(func.getBairro());
		txtCel.setText(func.getCele());
		txtTele.setText(func.getTele());
		txtData.setValue(func.getNasc().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		// --------- EDITAR AQUI txtEmail.setText(func.getEmail());
		txtNume.setText(func.getNume());
		// --------- EDITAR AQUI txtContas.setText(clie.getContas() + "");
		// --------- EDITAR AQUI chkRece.setSelected(func.isRece());
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
		// --------- EDITAR AQUI txtContas.setText("");
		cboEsta.setDisable(true);
		txtCida.setDisable(true);
		tblFunc.getSelectionModel().clearSelection();
		txtNome.requestFocus();
		func = null;
		limpo = true;
	}

	public void inserir() {
		if(campoVazio()) return;
		
		getCampos();
		Funcionario conFunc = new Funcionario();
		
		String campo;
			
		conFunc = daoFunc.consultarUm("obterFuncionario", "nome", func.getNome());
		if(conFunc == null) {
			
			conFunc = daoFunc.consultarUm("obterFuncionarioCpf", "cpf", func.getCpf());
			if(conFunc == null) {
				daoFunc.incluirAgora(func);
				Alerta.showAlert("Inserção Funcionario", null, "Funcionario cadastrado com sucesso!", AlertType.INFORMATION);
				carregarTbl(null);
				limpar();
				return;
			} else {
				campo = "CPF";
			}
				
		} else {
			campo = "nome";
		}
		
		
		if(Alerta.showConfirm("Alterar funcionário", "Trazer o funcionário para alteração?", "Já existe um funcionário com este " + campo)) {
			func = conFunc;
			setCampos();
		}
		
		carregarTbl(conFunc);
			
			
			
	}


	
	public void consultar() {
		if(txtNome.getText().trim().isEmpty() && txtCpf.getText().trim().isEmpty()) {
			Alerta.showAlert("Consulta Funcionário", null, "Favor preencher ou o campo Nome ou o campo CPF", AlertType.INFORMATION);
			return;
		}
		
		func = daoFunc.consultarUm("obterFuncionario", "nome", txtNome.getText());
		if(func == null) {
			func = daoFunc.consultarUm("obterFuncionarioCpf", "cpf", txtCpf.getText());
			if(func == null) {
				Alerta.showAlert("Consulta Funcionario", "Funcionário não encontrado", "Favor, verificar se os dados"
						+ " inseridos estão corretos", AlertType.INFORMATION);
				return;
			}
		}
		
		setCampos();
		carregarTbl(func);
		
	}


	
	
	public FuncionarioController(Label lblBemVindo, TextField txtNome, Label lblRegua, TextField txtEnde,
			TextField txtCep, TextField txtCida, ChoiceBox<Estados> cboEsta, TextField txtEmail,
			TableView<Funcionario> tblFunc, TableColumn<Funcionario, String> colNome,
			TableColumn<Funcionario, String> colEsta, TableColumn<Funcionario, String> colCida,
			TableColumn<Funcionario, String> colCel, TableColumn<Funcionario, String> colCpf,
			TableColumn<Funcionario, Integer> colCon, DatePicker txtData, TextField txtRg, TextField txtCpf,
			TextField txtTele, TextField txtCel, TextField txtBairro, TextField txtNume, ChoiceBox<Niveis> cboNiveis,
			CheckBox chkRece, DAO<Funcionario> daoFunc, Funcionario func, List<Funcionario> funcs, boolean limpo) {
		super();
		this.lblBemVindo = lblBemVindo;
		this.txtNome = txtNome;
		this.lblRegua = lblRegua;
		this.txtEnde = txtEnde;
		this.txtCep = txtCep;
		this.txtCida = txtCida;
		this.cboEsta = cboEsta;
		this.txtEmail = txtEmail;
		this.tblFunc = tblFunc;
		this.colNome = colNome;
		this.colEsta = colEsta;
		this.colCida = colCida;
		this.colCel = colCel;
		this.colCpf = colCpf;
		this.colCon = colCon;
		this.txtData = txtData;
		this.txtRg = txtRg;
		this.txtCpf = txtCpf;
		this.txtTele = txtTele;
		this.txtCel = txtCel;
		this.txtBairro = txtBairro;
		this.txtNume = txtNume;
		this.cboNivel = cboNiveis;
		this.daoFunc = daoFunc;
		this.func = func;
		this.funcs = funcs;
		this.limpo = limpo;
	}


	public void alterar() {
		if(campoVazio()) return;
		if(func == null) {
			Alerta.showAlert("Alteração Funcionário", null, "Favor selecionar um Funcionário da tabela ou consultar", AlertType.INFORMATION);
			return;
		}
		
		getCampos();
		
		daoFunc.alterarAgora(func);
		
		limpar();
		
		Alerta.showAlert("Alteração Funcionário", null, "Funcionário alterado com sucesso!", AlertType.INFORMATION);
		
		carregarTbl(null);
		
		
	}


	public void deletar() {
		if(pegarTbl() == null && func == null) {
			Alerta.showAlert("Deletar Funcionário", null, "Favor selecionar um Funcionário da tabela ou consultar", AlertType.INFORMATION);
			return;
		}
		
		if(Alerta.showConfirm("Deletar Cliente?", null, "Deseja realmente deletar " + pegarTbl().getNome() + "?")) {
			daoFunc.excluirAgora(pegarTbl());
			Alerta.showAlert("Exclusão", null, "Funcionário excluído com sucesso!", AlertType.CONFIRMATION);
			carregarTbl(null);
		}
	}

	








}






