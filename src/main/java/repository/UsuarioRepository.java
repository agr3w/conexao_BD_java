package repository;

import entity.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

/**
 * Classe responsável por realizar operações no banco de dados referente a
 * entidade Usuário: - Inserir - Atualizar - Deletar - Consultar
 */
public class UsuarioRepository {

	/**
	 * Método responsável por inserir um usuário no banco de dados.
	 * 
	 * @param usuario dados do usuário
	 */
	public void inserir(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

	public void remover(long id) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
    		session.remove(id);   
            transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
		}
	}



}
