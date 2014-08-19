package com.eudemon.taurus.app.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class BaseEntity implements Serializable{
	private static final long serialVersionUID = -317385461424764469L;
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
