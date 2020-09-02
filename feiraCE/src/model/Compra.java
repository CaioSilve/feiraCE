package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "compras")
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codi_compra")
	private Long codi;
	@ManyToOne
	@JoinColumn(name = "forn_compra")
	private Fornecedor forn;
	@Column(name = "total_compra", nullable = false)
	private Double total;
	@Column(name = "data_compra", nullable = false)
	private Date data;
	
	public Compra() {
		// TODO Auto-generated constructor stub
	}

	public Compra(Fornecedor forn, Double total, Date data) {
		super();
		this.forn = forn;
		this.total = total;
		this.data = data;
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
