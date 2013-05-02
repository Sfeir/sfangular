package com.sfeir.prototype.sfangular.daos;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sfeir.prototype.sfangular.controllers.DaoUtils;
import com.sfeir.prototype.sfangular.entities.UserEntity;

@Repository
public class UserDao implements IUserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Long saveOrUpdate(UserEntity userEntity) {
		sessionFactory.getCurrentSession().saveOrUpdate(userEntity);
		return userEntity.getId();
	}

	@Override
	public void delete(Long id) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(id);
		sessionFactory.getCurrentSession().delete(userEntity);
	}

	@Override
	public UserEntity get(Long id) {
		return (UserEntity) sessionFactory.getCurrentSession().get(UserEntity.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<UserEntity> list(Integer firstResult, Integer maxResults, String orderBy, String order) {
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Users ORDER BY " + orderBy + " " + order);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		return query.list();
	}

	@Override
	public Long count() {
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT COUNT(id) FROM Users");
		return (Long) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<UserEntity> search(String textSearched, Integer firstResult, Integer maxResults, String orderBy, String order) {
		Query query = sessionFactory.getCurrentSession().createQuery(
			"FROM Users "
			+ "WHERE UPPER(lastname) LIKE :textSearched "
			+ "OR UPPER(firstname) LIKE :textSearched "
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
			+ "FROM Users "
			+ "WHERE UPPER(lastname) LIKE :textSearched "
			+ "OR UPPER(firstname) LIKE :textSearched");
		query.setParameter("textSearched", DaoUtils.jokerifyUpperCase(textSearched));

		return (Long) query.uniqueResult();
	}
	
	@Override
	public UserEntity findByLogin(String login) {
		Query query = sessionFactory.getCurrentSession().createQuery(
			"FROM Users "
			+ "WHERE login = :login");
		query.setParameter("login", login);
		return (UserEntity) query.uniqueResult();
	}
	
}
