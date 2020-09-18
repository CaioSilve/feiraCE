package views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import dao.DAO;
import model.Cliente;
import model.Funcionario;
import model.Produto;

public class testeClasses {

	public static void main(String[] args) throws ParseException {
		
		DAO<Cliente> dao = new DAO<>(Cliente.class);
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		
		Date data = formato.parse("25/02/1997");
		
		Cliente clie = new Cliente("Erick", data, "112345", "437031978-11", "981901770", "981901770", "Av:E 1265", "14620000");
		
		//data = formato.parse("09/06/2002");
		
		//Funcionario func = new Funcionario("Caio Silveira", data, "57517297-6", "50748096817", "", "992941653", "Avenida E", "14620000", 3);
		
		//JOptionPane.showMessageDialog(null, func.getCep() + ": \n" + func.getEsta() + ", " + func.getCida());
		
		data = formato.parse("07/09/2020");
	
		//Produto prod = new Produto("Pão Francês", "Panco", 3.2, "Alimenticia", "Pães", data);	
		
		
		//dao.incluirAgora(clie);
		
		
		//List<Cliente> clies = dao.consultarUm("obterNome", "nome", "%ck%");
		
		//Cliente clies = dao.consultarUm("obterNome", "esta", "SP");
		
		//JOptionPane.showMessageDialog(null, clies.getNome());
		
//		for (Cliente cliente : clies) {
//			JOptionPane.showMessageDialog(null, cliente.getNome());
//		}
//		
		
		//daoProd.fechar();
		dao.fechar();
	}
}
