package model.entities;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaQuery;

@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@Column(name = "cpf_clie") //
	private String cpf;
	@Column(name = "nome_clie") //
	private String nome;
	@Column(name = "nasc_clie") //
	private Date nasc;
	@Column(name = "rg_clie") //
	private String rg;
	@Column(name = "tele_clie") //
	private String tele;
	@Column(name = "cele_clie") //
	private String cele;
	@Column(name = "cep_clie") //
	private String cep;
	@Column(name = "ende_clie") //
	private String ende;
	@Column(name = "bair_clie") //
	private String bair;
	@Column(name = "nume_clie") //
	private String nume;
	@Column(name = "cida_clie") //
	private String cida;
	@Column(name = "esta_clie") //
	private String esta;
	@Column(name = "email_clie") //
	private String email;
	@Column(name = "contas_abertas_clie") //
	private int contas;
	// se ele quer receber promoções ou não
	@Column(name = "rece_clie") //
	private boolean rece;

	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public Cliente(String nome, Date nasc, String rg, String cpf, String tele, String cele, String cep, String ende,
			String bair, String nume, String email, int contas, boolean rece) {
		super();
		this.nome = nome;
		this.nasc = nasc;
		this.rg = rg;
		this.cpf = cpf;
		this.tele = tele;
		this.cele = cele;
		this.cep = cep;
		buscarCep(cep);
		this.ende = ende;
		this.bair = bair;
		this.nume = nume;
		this.email = email;
		this.contas = contas;
		this.rece = rece;
	}

	public void buscarCep(String cep) {
		String json;

		try {
			URL url = new URL("http://viacep.com.br/ws/" + cep + "/json");
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			StringBuilder jsonSb = new StringBuilder();

			br.lines().forEach(l -> jsonSb.append(l.trim()));
			json = jsonSb.toString();

			// JOptionPane.showMessageDialog(null, json);

			json = json.replaceAll("[{},:]", "");
			json = json.replaceAll("\"", "\n");
			String array[] = new String[30];
			array = json.split("\n");

			// JOptionPane.showMessageDialog(null, array);

			this.cida = array[19];
			this.esta = array[23];
			// JOptionPane.showMessageDialog(null, logradouro + " " + bairro + " " + cidade
			// + " " + uf);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bair;
	}

	public void setBairro(String bairro) {
		this.bair = bairro;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getContas() {
		return contas;
	}

	public void setContas(int contas) {
		this.contas = contas;
	}

	public boolean isRece() {
		return rece;
	}

	public void setRece(boolean rece) {
		this.rece = rece;
	}

}
