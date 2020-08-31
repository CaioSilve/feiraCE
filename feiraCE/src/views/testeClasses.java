package views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import dao.DAO;
import model.Cliente;
import model.Funcionario;

public class testeClasses {

	public static void main(String[] args) throws ParseException {
		
		DAO<Cliente> dao = new DAO<>();
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		
		Date data = formato.parse("25/02/1997");
		
		Cliente clie = new Cliente("Erick", data, "112345", "437031978-11", "981901770", "981901770", "Av:E 1265", "14620000");
		
		data = formato.parse("09/06/2002");
		
		Funcionario func = new Funcionario("Caio Silveira", data, "57517297-6", "50748096817", "", "992941653", "Avenida E", "14620000", 3);
		
//		JOptionPane.showMessageDialog(null, func.getCep() + ": \n" + 
//				func.getEsta() + ", " + func.getCida());
//		
		
		dao.incluirAgora(clie);
		dao.incluirAgora(func);
		
		
		
		//CONSULTA
//		List<Cliente> clies = dao.consultar("obterNome", "nome", "%veira%");
//		
//		for (Cliente cliente : clies) {
//			JOptionPane.showMessageDialog(null, cliente.getNome());
//		}
		
		
		
		dao.fechar();
	}
}
