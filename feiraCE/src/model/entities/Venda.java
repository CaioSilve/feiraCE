package model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import model.enums.Pagamentos;
import model.enums.Status;

@Entity
@Table(name="vendas")
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codi_venda")
	private Long codi;
	
	@Column(name = "data_venda")
	private Date data;
	@OneToMany(mappedBy = "venda")
	private List<ItemVenda> itens;
	@Column(name = "form_paga_venda")
	private Pagamentos paga;
	@Column(name = "total_venda")
	private double total;
	@OneToOne
	@JoinColumn(name = "clie_venda")
	private Cliente clie;
	@Column(name = "stat_venda")
	private Status stat;
	@OneToOne
	private Usuario usua;
	
	
	public Venda() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Venda(Date data, List<ItemVenda> itens, Pagamentos paga, double total, Cliente clie, Status stat) {
		super();
		this.data = data;
		this.itens = itens;
		this.paga = paga;
		this.setTotal(total);
		this.clie = clie;
		this.stat = stat;
	}
	
	public int getQtdeItens() {
		return itens.size();
	}



	public Date getVenda() {
		return data;
	}



	public void setVenda(Date data) {
		this.data = data;
	}



	public List<ItemVenda> getItens() {
		return itens;
	}



	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}



	public Pagamentos getPaga() {
		return paga;
	}



	public void setPaga(Pagamentos paga) {
		this.paga = paga;
	}



	public double getTotal() {
		return total;
	}



	public void setTotal(double total) {
		if(total == 0) {
			for(ItemVenda x: itens) {
				total += x.precoTotal();
			}
		}
		this.total = total;
	}



	public Cliente getClie() {
		return clie;
	}



	public void setClie(Cliente clie) {
		this.clie = clie;
	}



	public Status getStat() {
		return stat;
	}



	public void setStat(Status stat) {
		this.stat = stat;
	}
	
	
	
	
	
}
