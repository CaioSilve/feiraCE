package control;

import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

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
	private TableColumn<Produto, Date> colVali;
	
	private DAO<Produto> daoProd = new DAO<Produto>(Produto.class);
	
	private Produto prod = new Produto();
	
	private List<Produto> prods = new ArrayList<>();
	
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
		colVali.setCellValueFactory((param) -> new ReadOnlyObjectWrapper<>((param.getValue().getValidade())));
		carregarTbl(null);
		
	}
	
	
	private Produto pegarTbl() {
		return tblProd.getSelectionModel().getSelectedItem();
	}
	
	private ObservableList<Produto> listaDeProds(){
		return FXCollections.observableArrayList(prods);
	}
	
	private void carregarTbl(Produto prod) {
		prods = daoProd.consultar("todosProdutos");
		if(prod == null) {
			tblProd.setItems(listaDeProds());
		} else {
			tblProd.setItems(FXCollections.observableArrayList(prod));
		}
		tblProd.getSelectionModel().clearSelection();
		tblProd.refresh();
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
		txtData.setValue(prod.getValidade().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
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
		prod.setQtde(Integer.parseInt(txtQtde.getText()));
		prod.setQtdeMin(Integer.parseInt(txtQtdeMin.getText()));
		prod.setValidade(Date.from(txtData.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
	}
	
	private boolean campoVazio() {
		if(txtDesc.getText().trim().isEmpty() || txtCodi.getText().trim().isEmpty() ||
			txtMarca.getText().trim().isEmpty() || cboTipo.getSelectionModel().isEmpty() ||
			cboCate.getSelectionModel().isEmpty() || txtValor.getText().trim().isEmpty() ||
			txtData.getValue() == null) {
			Alerta.showAlert("Campos vazios", null, "Não pode haver campos vazios", AlertType.WARNING);
			return true;
		}
		
		return false;
	}
	
	// Event Listener on Button.onMouseClicked
	@FXML
	public void recarregarTbl(MouseEvent event) {
		carregarTbl(null);
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
		txtData.setValue(null);
		txtQtde.setDisable(true);
		txtQtdeMin.setDisable(true);
		tblProd.getSelectionModel().clearSelection();
		txtDesc.requestFocus();
		prod = null;
		limpo = true;
	}

	public void consultar() {
		// TODO Auto-generated method stub
		
	}

	public void alterar() {
		// TODO Auto-generated method stub
		
	}

	public void deletar() {
		// TODO Auto-generated method stub
		
	}
}
