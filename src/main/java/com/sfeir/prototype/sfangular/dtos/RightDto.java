package com.sfeir.prototype.sfangular.dtos;

import java.io.Serializable;

public class RightDto implements Serializable {

	private static final long serialVersionUID = 7436064035407951684L;

	private Long id = null;
	
	private String label = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
