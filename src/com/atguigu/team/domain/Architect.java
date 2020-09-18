package com.atguigu.team.domain;

import com.atguigu.team.service.Status;

public class Architect extends Designer {
	private int stock;


	public Architect() {
		super();
	}

	public Architect(int id, String name, int age, double salary, Equipment equipment,
			double bonus, int stock) {
		super(id, name, age, salary,equipment, bonus);
		this.stock = stock;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public String toString() {
		return super.getDetail() + "架构师\t" + getStatus() + "\t" + getStock() + "\t"  + getStock() + "\t" + getEquipment().getDescription();
	}
	
	public String getTeamDetail(){
		return getBaseDetail() + "\t架构师\t" + getBonus() + "\t" + getStock();
	}
}
