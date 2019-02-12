package com.samsunglife.sample.core;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SessionObject implements  java.lang.Cloneable, java.io.Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 4058611399224770211L;
	//public class SessionObject implements Serializable{	
	public SessionObject() {
		
	}
	String aaa;
	String bbb;
	int level;
}
