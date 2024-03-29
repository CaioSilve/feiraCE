package model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.swing.JOptionPane;

@Entity
@Table(name = "itens_compra")
public class ItemCompra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codi_itemcompra")
	private Long codi;
	@ManyToOne
	private Compra compra;
	@ManyToOne
	private Produto prod;
	@Column(name = "qtde_itemcompra", nullable = false)
	private int qtde;
	@Column(name = "valor_itemcompra", nullable = false)
	private double valor;
	
	public ItemCompra() {
		// TODO Auto-generated constructor stub
	}

	public ItemCompra(Produto prod, double valor, int qtde) {
		super();
		this.valor = valor;
		this.setProd(prod);
		this.setQtde(qtde);
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
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
		this.qtde = qtde;
		prod.setQtde(prod.getQtde() + qtde);
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
