package com.atguigu.team.service;

public class Status {
	
	private final String name;
	
	private Status(String name){
		this.name = name;
	}
	
	public static final Status FREE = new Status("FREE");
	public static final Status BUSY = new Status("BUSY");
	public static final Status VACATION = new Status("VACATION");

	@Override
	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}	
	
}
