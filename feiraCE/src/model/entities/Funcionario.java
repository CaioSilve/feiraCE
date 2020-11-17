package model.entities;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import model.enums.Niveis;

@Entity
@Table(name = "funcionarios")
public class Funcionario {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codi_func", unique = true)
	private Long codi;
	@Column(name = "nome_func")
	private String nome;
	@Column(name = "nasc_func")
	private Date nasc;
	@Column(name = "rg_func")
	private String rg;
	@Column(name = "cpf_func")
	private String cpf;
	@Column(name = "tele_func")
	private String tele;
	@Column(name = "cele_func")
	private String cele;
	@Column(name = "emai_func")
	private String emai;
	@Column(name = "cep_func")
	private String cep;
	@Column(name = "bair_func")
	private String bair;
	@Column(name = "ende_func")
	private String ende;
	@Column(name = "nume_func")
	private String nume;
	@Column(name = "cida_func")
	private String cida;
	@Column(name = "esta_func")
	private String esta;
	@Column(name = "nive_func")
	private Niveis nivel;
	@Column(name = "admis_func")
	private Date admis;
	
	public Funcionario() {
		// TODO Auto-generated constructor stub
	}


	
	
	
	public Funcionario(String nome, Date nasc, String rg, String cpf, String tele, String cele, String emai,
			String cep, String bair, String ende, String nume, Niveis nivel, Date admis) {
		super();
		this.nome = nome;
		this.nasc = nasc;
		this.rg = rg;
		this.cpf = cpf;
		this.tele = tele;
		this.cele = cele;
		this.emai = emai;
		this.cep = cep;
		buscarCep(cep);
		this.bair = bair;
		this.ende = ende;
		this.nume = nume;
		this.nivel = nivel;
		this.admis = admis;
	}





	public void buscarCep(String cep) 
    {
        String json;        

        try {
            URL url = new URL("http://viacep.com.br/ws/"+ cep +"/json");
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder jsonSb = new StringBuilder();

            br.lines().forEach(l -> jsonSb.append(l.trim()));
            json = jsonSb.toString();
            
            //JOptionPane.showMessageDialog(null, json);
            
            json = json.replaceAll("[{},:]", "");
            json = json.replaceAll("\"", "\n");                       
            String array[] = new String[30];
            array = json.split("\n");
            
            // JOptionPane.showMessageDialog(null, array);
                             
            this.cida = array[19]; 
            this.esta = array[23];
            //JOptionPane.showMessageDialog(null, logradouro + " " + bairro + " " + cidade + " " + uf);
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	

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



	public String getCep() {
		return cep;
	}



	public void setCep(String cep) {
		this.cep = cep;
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



	public Niveis getNivel() {
		return nivel;
	}

	public void setNivel(Niveis nivelFunc) {
		this.nivel = nivelFunc;
	}
	
	public String getEmai() {
		return emai;
	}



	public void setEmai(String emai) {
		this.emai = emai;
	}



	public String getBair() {
		return bair;
	}



	public void setBair(String bair) {
		this.bair = bair;
	}


	public String getNume() {
		return nume;
	}


	public void setNume(String nume) {
		this.nume = nume;
	}



	public Date getAdmis() {
		return admis;
	}


	public void setAdmis(Date admis) {
		this.admis = admis;
	}
	
	
}
