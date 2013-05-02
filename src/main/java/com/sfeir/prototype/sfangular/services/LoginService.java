package com.sfeir.prototype.sfangular.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sfeir.prototype.sfangular.daos.IUserDao;
import com.sfeir.prototype.sfangular.dtos.AuthenticationDto;
import com.sfeir.prototype.sfangular.dtos.LoginRequestDto;
import com.sfeir.prototype.sfangular.entities.UserEntity;
import com.sfeir.prototype.sfangular.exceptions.InvalidLoginException;

@Service
public class LoginService implements ILoginService {

	@Autowired
	private IUserDao dao;
	
	@Transactional(readOnly = true)
	public AuthenticationDto logIn(LoginRequestDto loginRequestDto) throws InvalidLoginException {
		UserEntity userEntity = dao.findByLogin(loginRequestDto.getLogin());
		
		if (userEntity == null) {
			throw new InvalidLoginException(loginRequestDto.getLogin());
		}
		
		AuthenticationDto authenticationDto = new AuthenticationDto();
		authenticationDto.setLogin(userEntity.getLogin());
		
		return authenticationDto;
	}
}
