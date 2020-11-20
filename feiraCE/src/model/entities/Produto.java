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
	@Column(name = "marca_prod")
	private String marca;
	@Column(name = "valor_prod")
	private double valor;
	@Column(name = "categ_prod")
	private Categorias categoria;
	@Column(name = "tipo_prod")
	private Tipos tipo;
	@Column(name = "valid_prod")
	private Date validade;
	@Column(name = "qtde_prod")
	private int qtde;
	@Column(name = "qtde_min_prod")
	private int qtdeMin;
	


	public Produto() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Produto(String desc, String marca, double valor, Categorias categoria, Tipos tipo, Date validade, int qtde) {
		super();
		this.desc = desc;
		this.marca = marca;
		this.valor = valor;
		this.categoria = categoria;
		this.tipo = tipo;
		this.validade = validade;
		this.qtde = qtde;
		this.qtdeMin = 5;
	}








	public Long getCodi() {
		return codi;
	}
	public void setCodi(Long codi) {
		this.codi = codi;
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
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Categorias getCategoria() {
		return categoria;
	}
	public void setCategoria(Categorias categoria) {
		this.categoria = categoria;
	}
	public Tipos getTipo() {
		return tipo;
	}
	public void setTipo(Tipos tipo) {
		this.tipo = tipo;
	}
	public Date getValidade() {
		return validade;
	}
	public void setValidade(Date validade) {
		this.validade = validade;
	}
	public int getQtde() {
		return qtde;
	}
	public void setQtde(int qtde) {
		this.qtde = qtde;
	}
	public double valorEsto() {
		return valor*qtde;
	}
	public int getQtdeMin() {
		return qtdeMin;
	}
	public void setQtdeMin(int qtdeMin) {
		this.qtdeMin = qtdeMin;
	}

	
	
	
	
}
