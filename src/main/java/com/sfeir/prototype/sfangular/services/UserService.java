package com.sfeir.prototype.sfangular.services;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sfeir.prototype.sfangular.daos.IUserDao;
import com.sfeir.prototype.sfangular.dtos.ListDto;
import com.sfeir.prototype.sfangular.dtos.UserDto;
import com.sfeir.prototype.sfangular.entities.UserEntity;
import com.sfeir.prototype.sfangular.exceptions.EntityDoesNotExistException;
import com.sfeir.prototype.sfangular.mapping.UserMapper;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDao dao;

	@Transactional
	public Long saveOrUpdate(UserDto userDto) throws ParseException {

		UserEntity userEntity = UserMapper.dtoToEntity(userDto);

		Date date = new Date();
		
		userEntity.setUpdatingDatetime(date);
		if (userEntity.getId() == null) {
			userEntity.setCreatingDatetime(date);
		}
		
		return dao.saveOrUpdate(userEntity);
	}

	@Transactional
	public void delete(Long id) {
		dao.delete(id);
	}

	@Transactional(readOnly = true)
	public UserDto get(Long id) throws EntityDoesNotExistException {
		UserEntity userEntity = dao.get(id);
		
		if (userEntity == null) {
			throw new EntityDoesNotExistException(UserEntity.class, id);
		}
		
		UserDto userDto = UserMapper.entityToDto(userEntity);
		UserMapper.fetchRights(userEntity, userDto);
		
		return userDto;
	}
	
	@Transactional(readOnly = true)
	public ListDto<UserDto> list(String textSearched, Integer firstResult, Integer maxResults, String orderBy, String order) {
		
		Collection<UserEntity> list = null;
		Long count = null;
		
		if (StringUtils.isEmpty(textSearched)) {
			list = dao.list(firstResult, maxResults, orderBy, order);
			count = dao.count();
		} else {
			list = dao.search(textSearched, firstResult, maxResults, orderBy, order);
			count = dao.countSearch(textSearched);
		}

		ListDto<UserDto> listDto = new ListDto<UserDto>(UserMapper.entitiesToDtos(list), count);
		
		return listDto;
	}
		
}
