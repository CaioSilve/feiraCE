package model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import model.enums.Pagamentos;
import model.enums.Status;

@Entity
@Table(name = "compras")
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codi_compra")
	private Long codi;
	@Column(name = "data_compra", nullable = false)
	private Date data;
	@OneToMany(mappedBy = "compra")
	private List<ItemCompra> itens;
	@Column(name = "form_paga_compra", nullable = false)
	private Pagamentos paga;
	@Column(name = "total_compra", nullable = false)
	private Double total;
	@OneToOne
	@Column(name = "forn_compra")
	private Fornecedor forn;
	@Column(name = "stat_compra", nullable = false)
	private Status stat;

	
	
	
	public Compra() {
		// TODO Auto-generated constructor stub
	}

	
	
	public Compra(Date data, List<ItemCompra> itens, Pagamentos paga, Double total, Fornecedor forn, Status stat) {
		super();
		this.data = data;
		this.itens = itens;
		this.paga = paga;
		this.total = total;
		this.forn = forn;
		this.stat = stat;
	}





	public Pagamentos getPaga() {
		return paga;
	}



	public void setPaga(Pagamentos paga) {
		this.paga = paga;
	}



	public Status getStat() {
		return stat;
	}



	public void setStat(Status stat) {
		this.stat = stat;
	}



	public Fornecedor getForn() {
		return forn;
	}

	public void setForn(Fornecedor forn) {
		this.forn = forn;
	}

	public Double getTotal() {
		return total;
	}

	public List<ItemCompra> getItens() {
		return itens;
	}



	public void setItens(List<ItemCompra> itens) {
		this.itens = itens;
	}



	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
}
