package com.sfeir.prototype.sfangular.services;

import java.text.ParseException;

import com.sfeir.prototype.sfangular.dtos.ListDto;
import com.sfeir.prototype.sfangular.dtos.UserDto;
import com.sfeir.prototype.sfangular.exceptions.EntityDoesNotExistException;

public interface IUserService {

	public Long saveOrUpdate(UserDto userDto) throws ParseException;
	public void delete(Long id);
	public UserDto get(Long id) throws EntityDoesNotExistException;
	public ListDto<UserDto> list(String textSearched, Integer firstResult, Integer maxResults, String orderBy, String order);
	
}
