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
import javax.swing.JOptionPane;

import model.enums.Estados;
import model.enums.TiposForn;

@Entity
@Table(name = "fornecedores")
public class Fornecedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codi_forn")
	private Long codi;
	@Column(name = "desc_forn")
	private String desc;
	@Column(name = "tipo_forn")
	private TiposForn tipo;
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
	private double gastos;
	@Column(name = "divi_forn")
	private double divida;
	
	public Fornecedor() {
		// TODO Auto-generated constructor stub
	}


	public Fornecedor(String desc, TiposForn tipo, String ende, String cep, String tele, String email, double gastos,
			double divida) {
		super();
		this.desc = desc;
		this.tipo = tipo;
		this.ende = ende;
		this.cep = cep;
		buscarCep(cep);
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
        	JOptionPane.showMessageDialog(null, "Conferir cep: " + this.cep);
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

	public double getGastos() {
		return gastos;
	}

	public void setGastos(double gastos) {
		this.gastos = gastos;
	}

	public double getDivida() {
		return divida;
	}

	public void setDivida(double divida) {
		this.divida = divida;
	}
	
	public TiposForn getTipo() {
		return tipo;
	}



	public void setTipo(TiposForn tipo) {
		this.tipo = tipo;
	}
	
	
	
	
	
	
	
	

}
