package com.sfeir.prototype.sfangular.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Rights")
@Table(name = "RIGHTS")
public class RightEntity {

	@Id
	/*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = "SEQ_RIGHTS", allocationSize = 1)*/
	@Column(name = "ID")
	private Long id = null;
	
	@Column(name = "LABEL")
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
