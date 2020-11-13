package views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import dao.DAO;
import model.entities.Cliente;
import model.entities.Funcionario;
import model.entities.Produto;
import model.enums.Categorias;

public class testeClasses {

	public static void main(String[] args) throws ParseException {
		
		DAO<Cliente> dao = new DAO<>(Cliente.class);
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		
		Date data = formato.parse("09/06/2002");
		
		Cliente clie = new Cliente("Caio Silveira", data, "57.517.297-6", "507.480.968-17", "", "(16) 99294-1653", "14620-000", "Avenida E", "Jardim Benini", "1355", "caio.evil.silveira@gmail.com", true);
		
		//data = formato.parse("09/06/2002");
		
		//Funcionario func = new Funcionario("Caio Silveira", data, "57517297-6", "50748096817", "", "992941653", "Avenida E", "14620000", 3);
		
		//JOptionPane.showMessageDialog(null, func.getCep() + ": \n" + func.getEsta() + ", " + func.getCida());
		
		//data = formato.parse("07/09/2020");
	
		//Produto prod = new Produto("Pão Francês", "Panco", 3.2, "Alimenticia", "Pães", data);	
		
//		for (Categorias cat : Categorias.values()) {
//			if(cat.getIndice() == 1)
//			System.out.println(cat);
//		}
		
		
		
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
