package model.entities;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fornecedores")
public class Fornecedor {
	
	public static enum TiposForn{
		PRIVADO, MERCADO, FEIRA
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codi_forn")
	private Long codi;
	@Column(name = "desc_forn")
	private String desc;
	@Column(name = "tipo_forn")
	private String tipo;
	@Column(name = "ende_forn")
	private String ende;
	@Column(name = "cep_forn")
	private String cep;
	@Column(name = "cida_forn")
	private String cida;
	@Column(name = "esta_forn")
	private String esta;
	@Column(name = "tele_forn")
	private String tele;
	@Column(name = "emai_forn")
	private String email;
	@Column(name = "gast_forn")
	private Double gastos;
	@Column(name = "divi_forn")
	private Double divida;
	
	public Fornecedor() {
		// TODO Auto-generated constructor stub
	}


	
	public Fornecedor(String desc, String tipo, String ende, String cep, String cida, String esta, String tele,
			String email, Double gastos, Double divida) {
		super();
		this.desc = desc;
		this.tipo = tipo;
		this.ende = ende;
		this.cep = cep;
		this.cida = cida;
		this.esta = esta;
		this.tele = tele;
		this.email = email;
		this.gastos = gastos;
		this.divida = divida;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnde() {
		return ende;
	}

	public void setEnde(String ende) {
		this.ende = ende;
	}

	public Double getGastos() {
		return gastos;
	}

	public void setGastos(Double gastos) {
		this.gastos = gastos;
	}

	public Double getDivida() {
		return divida;
	}

	public void setDivida(Double divida) {
		this.divida = divida;
	}
	
	
	
	
	
	
	
	

}
