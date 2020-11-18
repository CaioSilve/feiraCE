package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import model.entities.Cliente;

public class DAO<E> {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Class<E> classe;
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory("feiraCE");
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "Deu erro aqui cara " +e.getMessage());
			System.out.println(e.getCause());
		}
	}
	
	public DAO() {
		this(null);
	}

	public DAO(Class<E> classe) {
		this.classe = classe;
		em = emf.createEntityManager();
	}
	
	public DAO<E> iniTrans() {
		em.getTransaction().begin();
		return this;
	}
	
	public DAO<E> fecTrans() {
		em.getTransaction().commit();
		return this;
	}
	
	public DAO<E> incluir(Object algo){
		em.persist(algo);
		return this;
	}
	
	public DAO<E> incluirAgora(Object algo){
		return this.iniTrans().incluir(algo).fecTrans();
	}
	
	public void encerrar() {
		em.close();
	}
	
	public E consultarporID(Long id) {
		return em.find(classe, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<E> consultar(Object ... param){
		if(param.length == 1) {
			return em.createNamedQuery(param[0].toString()).getResultList();
		} else {
			TypedQuery<E> query = em.createNamedQuery(param[0].toString(), classe);
			
			for (int i = 1; i < param.length; i += 2) {
				query.setParameter(param[i].toString(), param[i + 1]);
			}
			return query.getResultList();
		}
		
	}
	
	public E consultarUm(String nomeconsul, Object... params){
		List<E> lista = consultar(nomeconsul, params);
		return lista.isEmpty() ? null : lista.get(0);
	}
	
	
	
}
