package model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.swing.JOptionPane;


@Entity
@Table(name = "itensvenda")
public class ItemVenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codi_itemvenda")
	private Long codi;
	@ManyToOne
	private Produto prod;
	@Column(name = "qtde_itemvenda", nullable = false)
	private int qtde;
	@Column(name = "valor_itemvenda", nullable = false)
	private Double valor;
	
	public ItemVenda() {
		// TODO Auto-generated constructor stub
	}
	
	public ItemVenda(Produto prod, int qtde) {
		super();
		this.prod = prod;
		this.qtde = qtde;
		this.valor = prod.getValor();
	}
	

	public Produto getProd() {
		return prod;
	}
	
	public void setProd(Produto prod) {
		this.prod = prod;
		
		if (prod != null && this.valor == null) {
			this.valor = prod.getValor();
		}
	}
	
	public int getQtde() {
		return qtde;
	}
	
	public void setQtde(int qtde) {
		if(qtde <= prod.getQtde()) {
			this.qtde = qtde;
		}else {
			JOptionPane.showMessageDialog(null, "Quantidade indisponível", "Estoque", 1);
			qtde = prod.getQtde();
		}
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public double precoTotal() {
		return qtde * valor;
	}
	
}
