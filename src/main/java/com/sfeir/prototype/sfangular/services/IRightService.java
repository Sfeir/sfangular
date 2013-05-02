package com.sfeir.prototype.sfangular.services;

import java.text.ParseException;
import java.util.Collection;

import com.sfeir.prototype.sfangular.dtos.RightDto;
import com.sfeir.prototype.sfangular.dtos.ListDto;
import com.sfeir.prototype.sfangular.exceptions.EntityDoesNotExistException;

public interface IRightService {

	public Long saveOrUpdate(RightDto rightDto) throws ParseException;
	public void delete(Long id);
	public RightDto get(Long id) throws EntityDoesNotExistException;
	public ListDto<RightDto> list(String textSearched, Integer firstResult, Integer maxResults, String orderBy, String order);
	public Collection<RightDto> listAll();
}
