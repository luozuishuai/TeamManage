package com.atguigu.team.service;

import java.sql.Date;

import com.atguigu.team.domain.Architect;
import com.atguigu.team.domain.Designer;
import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Equipment;
import com.atguigu.team.domain.NoteBook;
import com.atguigu.team.domain.PC;
import com.atguigu.team.domain.Printer;
import com.atguigu.team.domain.Programmer;
import static com.atguigu.team.service.Data.*;

/**
 * 
 * @Dsecription 负责将Data中的数据封装到Employee[]数组中，同时提供相关操作Employee[]的方法。
 * @author luozhuishuai Email:luozuishuai@126.com
 * @version 1.0
 * @date 2020年9月17日下午4:19:22
 *
 */
public class NameListService {
	public static void main(String[] args) {
		
		NameListService test = new NameListService();
//		for (int i = 0; i < test.employees.length; i++) {			
//			System.out.println(test.employees[i]);
//		}
		try{
			System.out.println(test.getEmployee(101));
			
		}catch(TeamException e){
			System.out.println(e.getMessage());
		}
		
	}

	public Employee[] employees;

	public NameListService() {

		employees = new Employee[EMPLOYEES.length]; // employees用来保存公司所有员工对象

		for (int i = 0; i < employees.length; i++) {
			// 获取基本属性
			int id = Integer.parseInt(EMPLOYEES[i][1]);
			String name = EMPLOYEES[i][2];
			int age = Integer.parseInt(EMPLOYEES[i][3]);
			double salary = Integer.parseInt(EMPLOYEES[i][4]);
			
			Equipment equipment;
			double bonus;
			int stock;

			int type = Integer.parseInt(EMPLOYEES[i][0]);
			switch (type) {
			case EMPLOYEE:
				employees[i] = new Employee(id, name, age, salary);
				break;
			case PROGRAMMER:
				equipment = creatEquipment(i);
				employees[i] = new Programmer(id, name, age, salary, equipment);
				break;
			case DESIGNER:
				equipment = creatEquipment(i);
				bonus = Double.parseDouble(EMPLOYEES[i][5]);
				employees[i] = new Designer(id, name, age, salary, equipment, bonus);
				break;
			case ARCHITECT:
				equipment = creatEquipment(i);
				bonus = Double.parseDouble(EMPLOYEES[i][5]);
				stock = Integer.parseInt(EMPLOYEES[i][6]);
				employees[i] = new Architect(id, name, age, salary, equipment, bonus, stock);
				break;
			}

		}
	}

	private Equipment creatEquipment(int index) {
		// 获取指定索引位置对象对应的工具
		// 先判断工具是什么类型
		switch (EQIPMENTS[index][0]) {
		case "21":
			return new PC(EQIPMENTS[index][1], EQIPMENTS[index][2]);
		case "22":
			return new NoteBook(EQIPMENTS[index][1], Double.parseDouble(EQIPMENTS[index][2]));
		case "23":
			return new Printer(EQIPMENTS[index][1], EQIPMENTS[index][2]);
		}
		return null;
	}

	public Employee[] getAllEmployees() {
		return employees;
	}

	public Employee getEmployee(int id) throws TeamException {
		for (int i = 0; i < employees.length; i++) {
			if (employees[i].getId() == id) {
				return employees[i];			
			}
		}
		throw new TeamException("找不到id为" + id + "的员工");
	}

}
