package com.sfeir.prototype.sfangular.daos;

import java.util.Collection;

import com.sfeir.prototype.sfangular.entities.RightEntity;

public interface IRightDao {

	public Long saveOrUpdate(RightEntity agenceEntity);
	public void delete(Long id);
	public RightEntity get(Long id);
	public Collection<RightEntity> list(Integer firstResult, Integer maxResults, String orderBy, String order);
	public Collection<RightEntity> search(String textSearched, Integer firstResult, Integer maxResults, String orderBy, String order);
	public Long count();
	public Long countSearch(String textSearched);
	public Collection<RightEntity> listAll();
}
