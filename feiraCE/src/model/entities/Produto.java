package model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import model.enums.Categorias;
import model.enums.Tipos;


@Entity
@Table(name="produtos")
public class Produto {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codi_prod")
	private Long codi;
	@Column(name = "desc_prod")
	private String desc;
	@Column(name = "barra_prod")
	private String barra;
	@Column(name = "marca_prod")
	private String marca;
	@Column(name = "valor_prod")
	private Double valor;
	@Column(name = "categ_prod")
	private Categorias cate;
	@Column(name = "tipo_prod")
	private Tipos tipo;
	@Column(name = "pere_prod")
	private boolean pere;
	@Column(name = "valid_prod")
	private Date vali;
	@Column(name = "qtde_prod")
	private int qtde;
	@Column(name = "qtde_min_prod")
	private int qtdeMin;

	

	public Produto() {
		this.pere = false;
	}
	
	

	public Produto(String desc, String marca, double valor, Categorias categoria, Tipos tipo, boolean pere, Date validade, int qtde) {
		super();
		this.desc = desc;
		this.marca = marca;
		this.valor = valor;
		this.cate = categoria;
		this.tipo = tipo;
		this.pere = pere;
		this.vali = validade;
		this.qtde = qtde;
		this.qtdeMin = 5;
	}




	public Long getCodi() {
		return codi;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String nome) {
		this.desc = nome;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getBarra() {
		return barra;
	}
	public void setBarra(String barra) {
		this.barra = barra;
	}
	public Categorias getCategoria() {
		return cate;
	}
	public void setCategoria(Categorias categoria) {
		this.cate = categoria;
	}
	public Tipos getTipo() {
		return tipo;
	}
	public void setTipo(Tipos tipo) {
		this.tipo = tipo;
	}
	public Date getValidade(){
		return vali;
	}
	public void setValidade(Date validade) {
		if(pere) {
			this.vali = validade;
		} else {
			this.vali = null;
		}
	}
	public int getQtde() {
		return qtde;
	}
	public void setQtde(int qtde) {
		this.qtde = qtde;
	}
	public Double valorEsto() {
		return valor*qtde;
	}
	public int getQtdeMin() {
		return qtdeMin;
	}
	public void setQtdeMin(int qtdeMin) {
		this.qtdeMin = qtdeMin;
	}
	public boolean isPere() {
		return pere;
	}
	public void setPere(boolean pere) {
		this.pere = pere;
	}

	
	
	
	
}
