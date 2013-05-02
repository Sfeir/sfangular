package com.sfeir.prototype.sfangular.mapping;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import com.sfeir.prototype.sfangular.dtos.UserDto;
import com.sfeir.prototype.sfangular.entities.RightEntity;
import com.sfeir.prototype.sfangular.entities.UserEntity;
import com.sfeir.prototype.sfangular.utils.DateUtils;

public class UserMapper {
	
	public static Collection<UserDto> entitiesToDtos(Collection<UserEntity> userEntities) {
		Collection<UserDto> userDtos = new ArrayList<UserDto>();
		for (UserEntity userEntity : userEntities) {
			userDtos.add(UserMapper.entityToDto(userEntity));
		}
		return userDtos;
	}

	public static UserEntity dtoToEntity(UserDto userDto) throws ParseException {
		
		UserEntity userEntity = new UserEntity();
		userEntity.setId(userDto.getId());
		userEntity.setLastname(userDto.getLastname());
		userEntity.setFirstname(userDto.getFirstname());
		userEntity.setLogin(userDto.getLogin());
		userEntity.setEmail(userDto.getEmail());
		userEntity.setCreatingDatetime(DateUtils.stringToTimestamp(userDto.getCreatingDate(), userDto.getCreatingTime()));
		userEntity.setUpdatingDatetime(DateUtils.stringToTimestamp(userDto.getUpdatingDate(), userDto.getUpdatingTime()));
		userEntity.setLeavingDatetime(DateUtils.stringToTimestamp(userDto.getLeavingDate(), userDto.getLeavingTime()));
		
		if (userDto.getRights() != null) {
			userEntity.setRights(RightMapper.idsToEntities(userDto.getRights()));
		}
		
		return userEntity;
	}

	public static UserDto entityToDto(UserEntity userEntity) {
		
		UserDto userDto = new UserDto();
		userDto.setId(userEntity.getId());
		userDto.setLastname(userEntity.getLastname());
		userDto.setFirstname(userEntity.getFirstname());
		userDto.setLogin(userEntity.getLogin());
		userDto.setEmail(userEntity.getEmail());
		userDto.setCreatingDate(DateUtils.timestampToDateString(userEntity.getCreatingDatetime()));
		userDto.setCreatingTime(DateUtils.timestampToTimeString(userEntity.getCreatingDatetime()));
		userDto.setUpdatingDate(DateUtils.timestampToDateString(userEntity.getUpdatingDatetime()));
		userDto.setUpdatingTime(DateUtils.timestampToTimeString(userEntity.getUpdatingDatetime()));
		userDto.setLeavingDate(DateUtils.timestampToDateString(userEntity.getLeavingDatetime()));
		userDto.setLeavingTime(DateUtils.timestampToTimeString(userEntity.getLeavingDatetime()));
		
		return userDto;
	}
	
	public static void fetchRights(UserEntity userEntity, UserDto userDto) {
		Collection<RightEntity> rightEntities = userEntity.getRights();
		Collection<Long> rightIds = new ArrayList<Long>();
		
		for (RightEntity rightEntity : rightEntities) {
			rightIds.add(rightEntity.getId());
		}
		
		userDto.setRights(rightIds);
	}
}
