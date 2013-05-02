package com.sfeir.prototype.sfangular.dtos;

import java.io.Serializable;
import java.util.Collection;

public class ListDto<T> implements Serializable {

	private static final long serialVersionUID = -6865899843287243031L;

	private Collection<T> list = null;
	
	private Long count = null;
	
	public ListDto(Collection<T> list, Long count) {
		this.list = list;
		this.count = count;
	}

	public Collection<T> getList() {
		return list;
	}

	public Long getCount() {
		return count;
	}
	
}
