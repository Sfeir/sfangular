package com.sfeir.prototype.sfangular.daos;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sfeir.prototype.sfangular.controllers.DaoUtils;
import com.sfeir.prototype.sfangular.entities.RightEntity;

@Repository
public class RightDao implements IRightDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Long saveOrUpdate(RightEntity rightEntity) {
		sessionFactory.getCurrentSession().saveOrUpdate(rightEntity);
		return rightEntity.getId();
	}

	@Override
	public void delete(Long id) {
		RightEntity rightEntity = new RightEntity();
		rightEntity.setId(id);
		sessionFactory.getCurrentSession().delete(rightEntity);
	}

	@Override
	public RightEntity get(Long id) {
		return (RightEntity) sessionFactory.getCurrentSession().get(RightEntity.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<RightEntity> list(Integer firstResult, Integer maxResults, String orderBy, String order) {
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Rights ORDER BY " + orderBy + " " + order);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		return query.list();
	}

	@Override
	public Long count() {
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT COUNT(id) FROM Rights");
		return (Long) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<RightEntity> search(String textSearched, Integer firstResult, Integer maxResults, String orderBy, String order) {
		Query query = sessionFactory.getCurrentSession().createQuery(
			"FROM Rights "
			+ "WHERE UPPER(label) LIKE :textSearched "
			+ "ORDER BY " + orderBy + " " + order);
		query.setParameter("textSearched", DaoUtils.jokerifyUpperCase(textSearched));
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		return query.list();
	}

	@Override
	public Long countSearch(String textSearched) {
		Query query = sessionFactory.getCurrentSession().createQuery(
			"SELECT COUNT(id) "
			+ "FROM Rights "
			+ "WHERE UPPER(label) LIKE :textSearched");
		query.setParameter("textSearched", DaoUtils.jokerifyUpperCase(textSearched));

		return (Long) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<RightEntity> listAll() {
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Rights ORDER BY label ASC");
		return query.list();
	}
	
}
