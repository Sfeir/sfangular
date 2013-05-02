package com.sfeir.prototype.sfangular.mapping;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import com.sfeir.prototype.sfangular.dtos.RightDto;
import com.sfeir.prototype.sfangular.entities.RightEntity;

public class RightMapper {
	
	public static Collection<RightDto> entitiesToDtos(Collection<RightEntity> rightEntities) {
		Collection<RightDto> rightDtos = new ArrayList<RightDto>();
		for (RightEntity rightEntity : rightEntities) {
			rightDtos.add(RightMapper.entityToDto(rightEntity));
		}
		return rightDtos;
	}

	public static RightEntity dtoToEntity(RightDto rightDto) throws ParseException {
		
		RightEntity rightEntity = new RightEntity();
		rightEntity.setId(rightDto.getId());
		rightEntity.setLabel(rightDto.getLabel());
		
		return rightEntity;
	}

	public static RightDto entityToDto(RightEntity rightEntity) {
		
		RightDto rightDto = new RightDto();
		rightDto.setId(rightEntity.getId());
		rightDto.setLabel(rightEntity.getLabel());
		
		return rightDto;
	}
	
	public static RightEntity idToEntity(Long id) {
		RightEntity rightEntity = new RightEntity();
		rightEntity.setId(id);
		return rightEntity;
	}
	
	public static Collection<RightEntity> idsToEntities(Collection<Long> ids) {
		Collection<RightEntity> rightEntities = new ArrayList<RightEntity>();
		for (Long id : ids) {
			rightEntities.add(RightMapper.idToEntity(id));
		}
		return rightEntities;
	}
}
