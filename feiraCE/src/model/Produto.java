package model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.swing.JOptionPane;


@Entity
@Table(name="produtos")
public class Produto {

	public Produto() {
		// TODO Auto-generated constructor stub
	}
	
	public Produto(String nome, String marca, Float valor, String categoria, String tipo, Date validade) {
		
		super();
		this.nome = nome;
		this.marca = marca;
		this.valor = valor;
		this.categoria = categoria;
		this.tipo = tipo;
		this.validade = validade;
		
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codi_prod", unique = true)
	private Long codi;
	@Column(name = "nome_prod")
	private String nome;
	@Column(name = "marca_prod")
	private String marca;
	@Column(name = "valor_prod")
	private Float valor;
	@Column(name = "categ_prod")
	private String categoria;
	@Column(name = "tipo_prod")
	private String tipo;
	@Column(name = "valid_prod")
	private Date validade;
	
	
	
	
	
	
	public Long getCodi() {
		return codi;
	}
	public void setCodi(Long codi) {
		this.codi = codi;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Date getValidade() {
		return validade;
	}
	public void setValidade(Date validade) {
		this.validade = validade;
	}
	
	
	
}
