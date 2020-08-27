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
		
		Date data = formato.parse("09/06/2002");
		
		Cliente clie = new Cliente(nome, nasc, rg, cpf, tele, cele, ende, cida, esta);
		
		//CONSULTA
//		List<Cliente> clies = dao.consultar("obterNome", "nome", "%veira%");
//		
//		for (Cliente cliente : clies) {
//			JOptionPane.showMessageDialog(null, cliente.getNome());
//		}
		
		
		
		dao.fechar();
	}
}
