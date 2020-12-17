package views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import dao.DAO;
import model.entities.Cliente;
import model.entities.Compra;
import model.entities.Fornecedor;
import model.entities.Funcionario;
import model.entities.ItemCompra;
import model.entities.ItemVenda;
import model.entities.Produto;
import model.entities.Usuario;
import model.entities.Venda;
import model.enums.Categorias;
import model.enums.Niveis;
import model.enums.Pagamentos;
import model.enums.Permissoes;
import model.enums.Status;
import model.enums.Tipos;
import model.enums.TiposForn;

public class testeClasses {

	public static void main(String[] args) throws ParseException {
		
		
		//teste(5.0, 5.6);
		
		
		DAO<Object> dao = new DAO<>(Object.class);
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		
		Date data = formato.parse("02/12/2001");
		
		
		//CLIENTE--------------------------------------------
		Cliente clie = new Cliente("Erick Neves", data, "57.517.297-6", "507.480.968-17", "12665", "(16) 99294-1653", "14620-000", "Avenida E", "Jardim Benini", "1355", "caio.evil.silveira@gmail.com", 1, true);
//		List<Cliente> clies = dao.consultar("obterNome", "nome", "%ck%");
//		
//		Cliente clies = dao.consultarUm("obterNome", "esta", "SP");
//				
//		JOptionPane.showMessageDialog(null, clies.getNome());
//				
//		for (Cliente cliente : clies) {
//			JOptionPane.showMessageDialog(null, cliente.getNome());
//		}
		
		
		//FORNECEDOR--------------------------------------------
		Fornecedor forn = new Fornecedor("Natura", TiposForn.PRIVADO, "São caetnao", "75410000", "125415", "natureza", 200.00, 200.00);
		
		
		//FUNCIONÁRIO--------------------------------------------
		Funcionario func = new Funcionario("Erick", data, "7188371878", "93819341873891", "dakshjdklajd", "8998891", "erick_nabotinha@gmail.com", "14620000", "Benini", "Avenida E", "1355", Niveis.ADMINISTRADOR, data);
		
		//PRODUTO--------------------------------------------
		Produto prod = new Produto("Bisnaguinha", "Economico", 0.75, Categorias.PAES, Tipos.COMIDAS, true, data, 10);
		Produto prod2 = new Produto("Smartphone", "Xiaomi", 1800.0, Categorias.ELETRONICOS, Tipos.EQUIPAMENTOS, false, data, 3);
//		System.out.println(Categorias.values()[7]);
//		for (Categorias cat : Categorias.values()) {
//			if(cat.getIndice() == 1)
//			System.out.println(cat);
//		}
		
		//USUÁRIO--------------------------------------------
		Usuario usua = new Usuario("Caio Silveira", "caio123", Permissoes.ADMINISTRADOR);
			
	
		
		//ITEMCOMPRA--------------------------------------------
		ItemCompra itemCp = new ItemCompra(prod, 0, 3);
		ItemCompra itemCp2 = new ItemCompra(prod2, 1900.0, 1);
		
		List<ItemCompra> itensCp = new ArrayList<ItemCompra>();
		itensCp.add(itemCp);
		itensCp.add(itemCp2);
		
		
		//COMPRA--------------------------------------------
		Compra cp = new Compra(new Date(), itensCp, Pagamentos.DINHEIRO, 0.0, forn, Status.PAGO);
		
		itemCp.setCompra(cp);
		itemCp2.setCompra(cp);
		//ITEMVENDA----------------------------------------
		
		Produto p1 = (Produto)dao.consultarUm("obterProduto", "desc", prod.getDesc());
		Produto p2 = (Produto)dao.consultarUm("obterProduto", "desc", prod2.getDesc());
		
		ItemVenda itemVd = new ItemVenda(prod, 20, 0);
		ItemVenda itemVd2 = new ItemVenda(prod2, 10, 0);
		
		List<ItemVenda> itensVd = new ArrayList<ItemVenda>();
		itensVd.add(itemVd);
		itensVd.add(itemVd2);
		
		//VENDA--------------------------------------------
		Venda vd = new Venda(new Date(), itensVd, Pagamentos.CREDITO, 0, clie, Status.ABERTO);
		
		itemVd.setVenda(vd);
		itemVd2.setVenda(vd);
		
		//----------------------------------------------------------------------------------------
		
		
		
		dao.iniTrans();
		
		
		for(ItemVenda x : itensVd) {
			if(dao.consultarUm("obterProduto", "desc", x.getProd().getDesc()) == null) {
				dao.incluir(x.getProd());
			}else {
				x.setProd((Produto)dao.consultarUm("obterProduto", "desc", x.getProd().getDesc()));
				
			}
		}
		
		if(dao.consultarUm("obterCliente", "nome", clie.getNome()) == null) {
			dao.incluir(clie);
		}else {
			vd.setClie((Cliente)dao.consultarUm("obterCliente", "nome", clie.getNome()));
		}
		

		dao.incluir(itemVd);
		dao.incluir(itemVd2);
		dao.incluir(vd);
		
		
		
		
		
		//NAMED NATIVE QUERY
		//List<Compra> lcp = dao.consultar("todasCompras");
		
		//System.out.println(lcp.get(0).getQtdeItens());
		
		
		
		
		//JPQL------------- NAME QUERY
//		Compra ccp = dao.consultarUm("obterCompra", "paga", Pagamentos.DINHEIRO);
//		
//		System.out.println(ccp.getQtdeItens());

		
		
		
		dao.fecTrans();
		
		dao.encerrar();
	}
	
	public static void teste(Double ... campos) {
		System.out.println(campos[0]);
	}
}
