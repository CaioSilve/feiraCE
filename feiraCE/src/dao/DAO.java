package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

public class DAO<E> {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Class<E> classe;
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory("feiraCE");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Deu erro aqui cara " +e.getMessage());
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
	
	public void fechar() {
		em.close();
	}
	
	public List<E> consultar(String nomeconsul, Object... params){
		TypedQuery<E> query = em.createNamedQuery(nomeconsul, classe);
		
		for (int i = 0; i < params.length; i += 2) {
			query.setParameter(params[i].toString(), params[i + 1]);
		}
		return query.getResultList();
	}
	
	public E consultarUm(String nomeconsul, Object... params){
		List<E> lista = consultar(nomeconsul, params);
		return lista.isEmpty() ? null : lista.get(0);
	}
	
	
	
}
