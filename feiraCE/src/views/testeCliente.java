package views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import dao.DAO;
import model.Cliente;

public class testeCliente {

	public static void main(String[] args) throws ParseException {
		
		DAO<Cliente> dao = new DAO<>(Cliente.class);
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		
		Date data = formato.parse("25/02/1997");
		
		Cliente clie = new Cliente("Erick", data, "112345", "437031978-11", "981901770", "981901770", "Av:E 1265", "Orlândia", "SP");
		
		//CONSULTA
//		List<Cliente> clies = dao.consultar("obterNome", "nome", "%veira%");
//		
//		for (Cliente cliente : clies) {
//			JOptionPane.showMessageDialog(null, cliente.getNome());
//		}
		
		
		
		dao.fechar();
	}
}
