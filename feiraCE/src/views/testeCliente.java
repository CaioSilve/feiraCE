package views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import dao.DAO;
import model.Cliente;

public class testeCliente {

	public static void main(String[] args) throws ParseException {
		
		DAO<Cliente> dao = new DAO<>(Cliente.class);
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		Date data = formato.parse("09/06/2002");
		
		Cliente c = new Cliente("Caio Silveira", data, "57.517.297-6", "507.480.968-17",
				"(16) 3826-2607", "(16) 99294-1653", "Avenida E - 1355", "Orlândia", "SP");
		
		dao.incluirAgora(c);
		
		dao.fechar();
	}
}
