package com.atguigu.team.domain;

import com.atguigu.team.service.Status;

public class Designer extends Programmer {
	private double bonus;

	public Designer() {
		super();
	}

	public Designer(int id, String name, int age, double salary, Equipment equipment,
			double bonus) {
		super(id, name, age, salary, equipment);
		this.bonus = bonus;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	
	public String toString() {
		return getDetail() + "设计师\t" + getStatus() + "\t" + getBonus() + "\t\t" + getEquipment().getDescription();
	}
	
	public String getTeamDetail(){
		return getBaseDetail() + "\t设计师\t" + getBonus();
	}
	
}
