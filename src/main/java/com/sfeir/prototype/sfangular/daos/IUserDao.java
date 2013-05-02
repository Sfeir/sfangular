package com.sfeir.prototype.sfangular.daos;

import java.util.Collection;

import com.sfeir.prototype.sfangular.entities.UserEntity;

public interface IUserDao {

	public Long saveOrUpdate(UserEntity userEntity);
	public void delete(Long id);
	public UserEntity get(Long id);
	public Collection<UserEntity> list(Integer firstResult, Integer maxResults, String orderBy, String order);
	public Collection<UserEntity> search(String textSearched, Integer firstResult, Integer maxResults, String orderBy, String order);
	public Long count();
	public Long countSearch(String textSearched);
	public UserEntity findByLogin(String login);
}
