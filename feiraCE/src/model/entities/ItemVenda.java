package model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.swing.JOptionPane;


@Entity
@Table(name = "itens_venda")
public class ItemVenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codi_itemvenda")
	private Long codi;
	@ManyToOne
	private Venda venda;
	@ManyToOne
	private Produto prod;
	@Column(name = "qtde_itemvenda", nullable = false)
	private int qtde;
	@Column(name = "valor_itemvenda", nullable = false)
	private double valor;
	
	public ItemVenda() {
		// TODO Auto-generated constructor stub
	}
	
	public ItemVenda(Produto prod, int qtde, double valor) {
		super();
		this.qtde = qtde;
		this.valor = valor;
		this.setProd(prod);
	}
	

	public Produto getProd() {
		return prod;
	}
	
	public void setProd(Produto prod) {
		this.prod = prod;
		
		if (prod != null && this.valor == 0.0) {
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
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public double precoTotal() {
		return qtde * valor;
	}
	
}
