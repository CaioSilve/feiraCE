package control;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import dao.DAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
import model.entities.Produto;
import model.enums.Categorias;
import model.enums.Tipos;
import views.Utilitarios.Alerta;
import views.Utilitarios.Formatacoes;

public class ProdutoController implements Initializable {
	@FXML
	private Label lblBemVindo;
	@FXML
	private Label lblRegua;
	@FXML
	private TextField txtDesc;
	@FXML
	private TextField txtCodi;
	@FXML
	private TextField txtMarca;
	@FXML
	private ChoiceBox<Tipos> cboTipo;
	@FXML
	private ChoiceBox<Categorias> cboCate;
	@FXML
	private TextField txtValor;
	@FXML
	private TextField txtQtde;
	@FXML
	private TextField txtQtdeMin;
	@FXML
	private DatePicker txtData;
	@FXML
	private TableView<Produto> tblProd;
	@FXML
	private TableColumn<Produto, String> colDesc;
	@FXML
	private TableColumn<Produto, Tipos> colTipo;
	@FXML
	private TableColumn<Produto, Categorias> colCate;
	@FXML
	private TableColumn<Produto, Double> colValor;
	@FXML
	private TableColumn<Produto, Integer> colQtde;
	@FXML
	private TableColumn<Produto, String> colVali;
	@FXML
	private CheckBox chkPere;
	
	private DAO<Produto> daoProd = new DAO<Produto>(Produto.class);
	
	private Produto prod = new Produto();
	
	private List<Produto> prods = new ArrayList<>();
	
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
	
	private boolean limpo = true;
	
	

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cboTipo.getItems().setAll(Tipos.values());
		cboTipo.valueProperty().addListener(new ChangeListener<Tipos>() {
				@Override
				public void changed(ObservableValue<? extends Tipos> observable, Tipos oldValue, Tipos newValue) {
					cboCate.getItems().clear();
					if(newValue == null) {
						cboCate.setDisable(true);
						return;
					}
					
					cboCate.setDisable(false);
					for (Categorias cat : Categorias.values()) {
						if(cat.getIndice() == newValue.getIndice()) {
							cboCate.getItems().add(cat);
						}
						
					}
					
				}
		});
		
		tblProd.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)){
		            if(event.getClickCount() == 2){
		            	prod = pegarTbl();
		            	if(prod != null) setCampos();
		            }
		        }
			}
		});
		
		
		txtDesc.focusedProperty().addListener((ov, oldV, newV) ->{
			if(!newV) {
				if(txtDesc.getText().trim().isEmpty()) {
					return;
				} else {
					if(!limpo) return;
					Produto conProd = daoProd.consultarUm("obterProduto", "desc", txtDesc.getText());
					if(conProd == null) return;
					
					if(Alerta.showConfirm("Alterar produto", "Trazer o produto para alteração?", "Já existe um produto com esta Descrição")) {
						prod = conProd;
						setCampos();
					}
						
					carregarTbl(conProd);
				}
			}
		});
		
		txtCodi.focusedProperty().addListener((ov, oldV, newV) -> {
			if(!newV) {
				if(txtCodi.getText().trim().isEmpty()) {
					return;
				} else {
					if(!limpo) return;
					Produto conProd = daoProd.consultarUm("obterProdutoBarra", "barra", txtCodi.getText());
					if(conProd == null) return;
					
					if(Alerta.showConfirm("Alterar produto", "Trazer o produto para alteração?", "Já existe um produto com este código de barras")) {
						prod = conProd;
						setCampos();
					}
						
					carregarTbl(conProd);
				}
			}
		});
		
		Formatacoes.mascaraData(txtData);
		colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
		colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		colCate.setCellValueFactory((param) -> new ReadOnlyObjectWrapper<>((param.getValue().getCategoria())));
		colValor.setCellValueFactory((param) -> new ReadOnlyObjectWrapper<>((param.getValue().valorEsto())));
		colQtde.setCellValueFactory(new PropertyValueFactory<>("qtde"));
		colVali.setCellValueFactory((param) ->{
			if(param.getValue().getValidade() != null) {
				return new ReadOnlyObjectWrapper<>(formato.format((param.getValue().getValidade())));
			} else {
				return new SimpleStringProperty("Não Perecível");
			}
			
		});
		carregarTbl(null);
		
	}
	
	
	@FXML
	public void perecivel() {
		if(chkPere.isSelected()) {
			txtData.setDisable(false);
		} else {
			txtData.setDisable(true);
		}
	}
	
	// Event Listener on Button.onMouseClicked
	@FXML
	public void recarregarTbl(MouseEvent event) {
		carregarTbl(null);
	}
	
	private void carregarTbl(Produto p) {
		prods = daoProd.consultar("todosProdutos");
		if(p == null) {
			tblProd.setItems(listaDeProds());
		} else {
			tblProd.setItems(FXCollections.observableArrayList(p));
		}
		tblProd.getSelectionModel().clearSelection();
		tblProd.refresh();
	}
	
	private Produto pegarTbl() {
		return tblProd.getSelectionModel().getSelectedItem();
	}
	
	private ObservableList<Produto> listaDeProds(){
		return FXCollections.observableArrayList(prods);
	}
	

	
	private void setCampos() {
		txtDesc.setText(prod.getDesc());
		txtCodi.setText(prod.getBarra());
		txtMarca.setText(prod.getMarca());
		cboTipo.getSelectionModel().select(prod.getTipo());
		cboCate.getSelectionModel().select(prod.getCategoria());
		txtValor.setText(prod.getValor().toString());
		txtQtde.setText(prod.getQtde() + "");
		txtQtdeMin.setText(prod.getQtdeMin() + "");
		chkPere.setSelected(prod.isPere());
		if(prod.isPere()) {
			txtData.setDisable(false);
			txtData.setValue(prod.getValidade().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		} else {
			txtData.setDisable(true);
			txtData.setValue(null);
		}
		txtQtde.setDisable(false);
		txtQtdeMin.setDisable(false);
		limpo = false;
	}
	
	private void getCampos() {
		prod.setDesc(txtDesc.getText());
		prod.setBarra(txtCodi.getText());
		prod.setMarca(txtMarca.getText());
		prod.setTipo(cboTipo.getSelectionModel().getSelectedItem());
		prod.setCategoria(cboCate.getSelectionModel().getSelectedItem());
		prod.setValor(Double.parseDouble(txtValor.getText()));
		prod.setQtde(txtQtde.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtQtde.getText().trim()));
		prod.setQtdeMin(txtQtdeMin.getText().trim().isEmpty() ? 5 : Integer.parseInt(txtQtdeMin.getText().trim()));
		prod.setPere(chkPere.isSelected());
		if(prod.isPere())
		prod.setValidade(Date.from(txtData.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
	}
	
	private boolean campoVazio() {
		if(txtDesc.getText().trim().isEmpty() || txtCodi.getText().trim().isEmpty() ||
			txtMarca.getText().trim().isEmpty() || cboTipo.getSelectionModel().isEmpty() ||
			cboCate.getSelectionModel().isEmpty() || txtValor.getText().trim().isEmpty()) {
			Alerta.showAlert("Campos vazios", null, "Não pode haver campos vazios", AlertType.WARNING);
			return true;
		}
		
		return false;
	}
	
	public void inserir() {
		if(campoVazio()) return;
		
		getCampos();
		Produto conProd;
		String campo;
		
		conProd = daoProd.consultarUm("obterProduto", "desc", prod.getDesc());
		if(conProd == null) {
			conProd = daoProd.consultarUm("obterProdutoBarra", "barra", prod.getBarra());
			if(conProd == null) {
				daoProd.incluirAgora(prod);
				Alerta.showAlert("Inserção Produto", null, "Produto cadastrado com sucesso!", AlertType.INFORMATION);
				carregarTbl(null);
				limpar();
				return;
			} else {
				campo = "Barra";
			}
		} else {
			campo = "Descrição";
		}
		
		
		carregarTbl(conProd);
		
		if(Alerta.showConfirm("Alterar Produto", "Trazer o produto para alteração?", "Já existe um produto com este campo " + campo)) {
			prod = null;
			prod = conProd;
			setCampos();
		}
		
		
		
	}

	public void limpar() {
		txtDesc.setText("");
		txtCodi.setText("");
		txtMarca.setText("");
		cboTipo.getSelectionModel().clearSelection();;
		cboCate.getSelectionModel().clearSelection();;
		txtValor.setText("");
		txtQtde.setText("");
		txtQtdeMin.setText("");
		chkPere.setSelected(false);
		txtData.setValue(null);
		txtQtde.setDisable(true);
		txtQtdeMin.setDisable(true);
		txtData.setDisable(true);
		tblProd.getSelectionModel().clearSelection();
		txtDesc.requestFocus();
		prod = null;
		limpo = true;
		carregarTbl(null);
	}

	public void consultar() {
		if(txtDesc.getText().trim().isEmpty() || txtCodi.getText().trim().isEmpty()) {
			Alerta.showAlert("Consultar Cliente", null, "Favor preencher ou o campo Descrição ou o campo Código de barras", AlertType.INFORMATION);
			return;
		}
		
		prod = daoProd.consultarUm("obterProduto", "desc", txtDesc.getText().trim());
		if(prod == null) {
			prod = daoProd.consultarUm("obterProdutoBarra", "barra", txtCodi.getText().trim());
			if(prod == null) {
				Alerta.showAlert("Consultar Produto", "Produto não encontrado", "Favor, verificar se os dados"
						+ " inseridos estão corretos", AlertType.INFORMATION);
				return;
			}
		}
		
		getCampos();
		carregarTbl(prod);
		
	}

	public void alterar() {
		if(campoVazio()) return;
		if(prod == null) {
			Alerta.showAlert("Alteração Produto", null, "Favor selecionar um Produto da tabela ou consultar", AlertType.INFORMATION);
			return;
		}
		
		getCampos();
		
		daoProd.alterarAgora(prod);
		
		limpar();
		
		Alerta.showAlert("Alteração Produto", null, "Produto alterado com sucesso!", AlertType.INFORMATION);
		
		carregarTbl(null);
	}

	public void deletar() {
		if(pegarTbl() == null && prod == null) {
			Alerta.showAlert("Deletar Produto", null, "Favor selecionar um Produto da tabela ou consultar", AlertType.INFORMATION);
			return;
		}
		
		if(Alerta.showConfirm("Deletar Produto?", null, "Deseja realmente deletar " + pegarTbl().getDesc() + "?")) {
			daoProd.excluirAgora(pegarTbl() == null ? prod : pegarTbl());
			Alerta.showAlert("Exclusão", null, "Produto excluído com sucesso!", AlertType.CONFIRMATION);
			carregarTbl(null);
		}
	}
}
