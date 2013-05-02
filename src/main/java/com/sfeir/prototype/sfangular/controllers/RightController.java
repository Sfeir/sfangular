package com.sfeir.prototype.sfangular.controllers;

import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfeir.prototype.sfangular.dtos.RightDto;
import com.sfeir.prototype.sfangular.dtos.ListDto;
import com.sfeir.prototype.sfangular.exceptions.EntityDoesNotExistException;
import com.sfeir.prototype.sfangular.exceptions.UserNotLoggedException;
import com.sfeir.prototype.sfangular.services.IRightService;

@Controller
@RequestMapping("/rights")
public class RightController extends ParentController {
	
	private static final Logger logger = Logger.getLogger(RightController.class);
	
	@Autowired
	private IRightService service;
	
	@RequestMapping(
			method = RequestMethod.POST,
			value = "/save",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> save(@RequestBody RightDto rightDto) throws UserNotLoggedException, ParseException {
		super.checkAuthLogin();
		
		Long id = service.saveOrUpdate(rightDto);
		
		Map<String, Object> returnValues = new HashMap<String, Object>();
		returnValues.put("id", id);
		
		return new ResponseEntity<Map<String, Object>>(returnValues, HttpStatus.OK);
	}
	
	@RequestMapping(
			method = RequestMethod.DELETE,
			value = "/delete/{userId}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable Long userId) throws UserNotLoggedException {
		super.checkAuthLogin();
		service.delete(userId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(
			method = RequestMethod.GET,
			value = "/get/{rightId}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RightDto> get(@PathVariable Long rightId) throws UserNotLoggedException, EntityDoesNotExistException {
		super.checkAuthLogin();
		
		RightDto rightDto = service.get(rightId);
		
		return new ResponseEntity<RightDto>(rightDto, HttpStatus.OK);
	}

	@RequestMapping(
			method = RequestMethod.GET,
			value = "/list",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ListDto<RightDto>> list(
			@RequestParam(required = true) String textSearched,
			@RequestParam(required = true) Integer firstResult,
			@RequestParam(required = true) Integer maxResults,
			@RequestParam(required = true) String orderBy,
			@RequestParam(required = true) String order) throws UserNotLoggedException {
		super.checkAuthLogin();
		
		ListDto<RightDto> listDto = this.service.list(textSearched, firstResult, maxResults, orderBy, order);
		
		return new ResponseEntity<ListDto<RightDto>>(listDto, HttpStatus.OK);
	}

	@RequestMapping(
			method = RequestMethod.GET,
			value = "/listAll",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RightDto>> listAll() throws UserNotLoggedException {
		super.checkAuthLogin();
		
		Collection<RightDto> rightDtos = this.service.listAll();
		
		return new ResponseEntity<Collection<RightDto>>(rightDtos, HttpStatus.OK);
	}
}
