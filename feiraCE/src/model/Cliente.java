package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codi_clie", unique = true)
	private Long codi;
	@Column(name = "nome_clie")
	private String nome;
	@Column(name = "nasc_clie")
	private Date nasc;
	@Column(name = "rg_clie")
	private String rg;
	@Column(name = "cpf_clie")
	private String cpf;
	@Column(name = "tele_clie")
	private String tele;
	@Column(name = "cele_clie")
	private String cele;
	@Column(name = "cep_clie");
	private String cep;
	@Column(name = "ende_clie")
	private String ende;
	@Column(name = "cida_clie")
	private String cida;
	@Column(name = "esta_clie")
	private String esta;
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Cliente(String nome, Date nasc, String rg, String cpf, String tele, String cele, String ende, String cida,
			String esta) {
		super();
		this.nome = nome;
		this.nasc = nasc;
		this.rg = rg;
		this.cpf = cpf;
		this.tele = tele;
		this.cele = cele;
		this.ende = ende;
		this.cida = cida;
		this.esta = esta;
	}


	public Long getCodi() {
		return codi;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Date getNasc() {
		return nasc;
	}


	public void setNasc(Date nasc) {
		this.nasc = nasc;
	}


	public String getRg() {
		return rg;
	}


	public void setRg(String rg) {
		this.rg = rg;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getTele() {
		return tele;
	}


	public void setTele(String tele) {
		this.tele = tele;
	}


	public String getCele() {
		return cele;
	}


	public void setCele(String cele) {
		this.cele = cele;
	}


	public String getEnde() {
		return ende;
	}


	public void setEnde(String ende) {
		this.ende = ende;
	}


	public String getCida() {
		return cida;
	}


	public void setCida(String cida) {
		this.cida = cida;
	}


	public String getEsta() {
		return esta;
	}


	public void setEsta(String esta) {
		this.esta = esta;
	}
	
	
	
	
}
