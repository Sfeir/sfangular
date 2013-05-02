package com.sfeir.prototype.sfangular.services;

import com.sfeir.prototype.sfangular.dtos.AuthenticationDto;
import com.sfeir.prototype.sfangular.dtos.LoginRequestDto;
import com.sfeir.prototype.sfangular.exceptions.InvalidLoginException;

public interface ILoginService {

	public AuthenticationDto logIn(LoginRequestDto loginRequestDto) throws InvalidLoginException;
	
}
