/*
 * Development of Software Systems based on Components and Services
 * Master in Computer Engineering
 *
 * 2017 © Copyleft - All Wrongs Reserved
 *
 * Ernesto Serrano <erseco@correo.ugr.es>
 *
 */
package modelo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class BDUsuario {
	private static final String PERSISTENCE_UNIT_NAME = "usuario";
	private static EntityManagerFactory factory;

	/**
	 * Inserta un nuevo usuario en la base de datos
	 * @param usuario
	 */
	public static void insertar(Usuario usuario) {
		if (factory == null) 
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		if (!existeEmail(usuario.getEmail())) {
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
			em.close();
		}
	}

	/**
	 * Actualiza el usuairo especificado en la base de datos
	 * @param usuario
	 */
	public static void actualizar(Usuario usuario) {
		if (factory == null) 
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		if (existeEmail(usuario.getEmail())) {
			Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email");
			q.setParameter("email", usuario.getEmail());
			
			Usuario resultado = (Usuario) q.getSingleResult();
			resultado.setNombre(usuario.getNombre());
			resultado.setApellido(usuario.getApellido());
			
			em.getTransaction().begin();
			em.merge(resultado);
			em.getTransaction().commit();
			em.close();
		}
	}

	/**
	 * Borra el usuario de la base de datos
	 * @param usuario
	 */
	public static void eliminar(Usuario usuario) {
		if (factory == null) 
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		if (existeEmail(usuario.getEmail())) {
			Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email");
			q.setParameter("email", usuario.getEmail());

			Usuario resultado = (Usuario) q.getSingleResult();
			
			em.getTransaction().begin();
			em.remove(resultado);
			em.getTransaction().commit();			
			em.close();
		}
	}

	/**
	 * Devuelve un usuario de la basede datos en base al email especificado como parámetro
	 * @param email
	 * @return usuario si existe, null si no existe
	 */
	public static Usuario seleccionarUsuario(String email) {
		if (factory == null) 
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		
		Usuario resultado = null;

		if (existeEmail(email)) {
			Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email");
			q.setParameter("email", email);
			resultado = (Usuario) q.getSingleResult();
			em.close();
		}

		return resultado;
	}

	/**
	 * Comprueba si existe un usuario en la base de datos con el email especifiado
	 * @param email
	 * @return true si existe, false si no existe
	 */
	public static boolean existeEmail(String email) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		
		Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email");
		q.setParameter("email", email);
		
		@SuppressWarnings("unchecked")
		List<Usuario> resultado = q.getResultList();
		em.close();
		if(resultado.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}
	
	/**
	 * Devuelve todos los usuarios de la base de datos
	 * @return lista con todos los usuarios de la base de datos
	 */
	public static List<Usuario> listarUsuarios() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		
		Query q = em.createQuery("SELECT u FROM Usuario u");
		
		@SuppressWarnings("unchecked")
		List<Usuario> resultado = q.getResultList();
		em.close();
		
		return resultado;
	}
}
