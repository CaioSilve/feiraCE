package model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import model.enums.Permissoes;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codi_usua")
	private Long codi;
	@Column(name = "desc_usua")
	private String nome;
	@Column(name = "senh_usua")
	private String senha;
	@Column(name = "nivi_perm_usua")
	private Permissoes permi;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(String nome, String senha, Permissoes permi) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.permi = permi;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Permissoes getPermi() {
		return permi;
	}

	public void setPermi(Permissoes permi) {
		this.permi = permi;
	}

	
	
}
