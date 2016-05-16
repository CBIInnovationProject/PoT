package com.cybertrend.pot.persistence.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.type.Type;

import com.cybertrend.pot.persistence.PersistenceException;
import com.cybertrend.pot.persistence.PersistenceManager;

public class HibernatePersistenceManager implements PersistenceManager,
		HibernateSessionFactoryAware { 

	private static Map classToHibernateTypeMap = new HashMap();

	static {
		classToHibernateTypeMap.put(BigDecimal.class, Hibernate.BIG_DECIMAL);
		classToHibernateTypeMap.put(Boolean.class, Hibernate.BOOLEAN);
		classToHibernateTypeMap.put(Byte.class, Hibernate.BYTE);
		classToHibernateTypeMap.put(Character.class, Hibernate.CHARACTER);
		classToHibernateTypeMap.put(Date.class, Hibernate.DATE);
		classToHibernateTypeMap.put(Double.class, Hibernate.DOUBLE);
		classToHibernateTypeMap.put(Float.class, Hibernate.FLOAT);
		classToHibernateTypeMap.put(Integer.class, Hibernate.INTEGER);
		classToHibernateTypeMap.put(Long.class, Hibernate.LONG);
		classToHibernateTypeMap.put(Short.class, Hibernate.SHORT);
		classToHibernateTypeMap.put(String.class, Hibernate.STRING);
		classToHibernateTypeMap.put(Timestamp.class, Hibernate.TIMESTAMP);
	}

	private HibernateSessionFactory hibernateSessionFactory;
	private SessionFactory sessionFactory;
	private Session session;

	public Session getSession() {
		return session;
	}

	public HibernateSessionFactory getHibernateSessionFactory() {
		return hibernateSessionFactory;
	}

	public void setHibernateSessionFactory(HibernateSessionFactory hsf) {
		this.hibernateSessionFactory = hsf;
	}

	public void init() {
		session = hibernateSessionFactory.createSession();
	}

	public void dispose() {
		try {
			// session.close();
			endSession();
		} catch (Exception e) {
			throw new PersistenceException(
					"Couldn't dispose HibernatePersistenceManager", e);
		} finally {
			try {
				hibernateSessionFactory.closeSession(session);
				// session.close();
			} catch (Exception e) {
				throw new PersistenceException("Couldn't close the session", e);
			}
		}
	}

	public void endSession() throws SQLException, HibernateException {
		hibernateSessionFactory.endSession(session);
	}

	public void save(Object objectToSave) {
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(objectToSave);
		tx.commit();
	}

	public void remove(Object objectToRemove) {
		Transaction tx = session.beginTransaction();
		session.delete(objectToRemove);
		tx.commit();
	}

	public List findAll(Class type) {
		return findAllSorted(type, null);
	}

	public List findAllSorted(Class type, String sortField) {
		String query = "FROM " + type.getSimpleName() + " as result";
		if (sortField != null)
			query += " ORDER BY LOWER(result." + sortField + ")";
		return session.createQuery(query).list();
	}

	public List findAllSortedDirected(Class type, String sortField,
			String direction) {
		String query = "FROM " + type.getSimpleName() + " as result";
		if (sortField != null)
			query += " ORDER BY LOWER(result." + sortField + ")";
		if (direction != null) {
			if (direction.equalsIgnoreCase("asc")) {
				query += " asc";
			}
			if (direction.equalsIgnoreCase("desc")) {
				query += " desc";
			}
		}
		return session.createQuery(query).list();
	}

	public Object getByUniqueField(Class type, Object pk, String fieldName) {
		Query query = session.createQuery("SELECT a FROM " + type.getName()
				+ " a WHERE a." + fieldName + "=:pk");
		query.setParameter("pk", pk);
		return query.uniqueResult();
	}

	public Object getById(Class aClass, long id) {
		return session.load(aClass, new Long(id));
	}

	public List getList(String query) {
		Session session = null;
		List results = null;

		session = this.session;
		results = session.createQuery(query).list();
		return results;
	}

	private static Type[] getHibernatedParameterTypes(Class[] types) {
		if (types == null) {
			return null;
		}

		Type[] hib_types = new Type[types.length];

		for (int i = 0; i < types.length; i++) {
			Class type = types[i];

			hib_types[i] = (Type) classToHibernateTypeMap.get(type);
		}

		return hib_types;
	}

	public Object getById(Class aClass, String id) {
		return session.load(aClass, new String(id));
	}

	/**
	 * @return Returns the sessionFactory.
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            The sessionFactory to set.
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
