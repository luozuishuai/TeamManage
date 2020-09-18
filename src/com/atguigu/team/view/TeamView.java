package com.atguigu.team.view;

import static com.atguigu.team.service.Data.EMPLOYEES;

import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Programmer;
import com.atguigu.team.service.NameListService;
import com.atguigu.team.service.TeamException;
import com.atguigu.team.service.TeamService;

public class TeamView {
	private NameListService listSvc = new NameListService();
	private TeamService teamSvc = new TeamService();

	public void enterMainMenu() {
		boolean loopFlag = true;
		char menu = 0;
		while (loopFlag) {
			if (menu != '1') {
				System.out.println(
						"\n-------------------------------------开发团队调度软件--------------------------------------\n");
				System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备");
				listAllEmployees();
				System.out.println(
						"\n---------------------------------------------------------------------------------------\n");
			}
			System.out.print("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)：");
			menu = TSUtility.readMenuSelection();
			switch (menu) {
			case '1':
				getTeam();
				break;
			case '2':
				addMember();
				break;
			case '3':
				deleteMember();
				break;
			case '4':
				System.out.print("确认是否退出(Y/N)：");
				char isExit = TSUtility.readConfirmSelection();
				if (isExit == 'Y') {
					loopFlag = false;
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		TeamView view = new TeamView();
		view.enterMainMenu();
	}

	// 以表格形式列出公司所有成员
	private void listAllEmployees() {
		Employee[] employees = listSvc.getAllEmployees();
		for (int i = 0; i < employees.length; i++) {
			System.out.println(employees[i]);
		}
	}

	// 显示团队成员列表操作
	private void getTeam() {
		System.out.println("\n-------------------------------------团队成员列表--------------------------------------\n");
		Programmer[] team = teamSvc.getTeam();
		if (team == null || team.length <= 0) {
			System.out.println("开发团队目前没有成员！");
		} else {
			System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票");
			for (int i = 0; i < team.length; i++) {
				System.out.println(team[i].getTeamDetail());
			}
		}
		System.out.println("-----------------------------------------------------");
	}

	// 实现添加成员操作
	private void addMember() {
		System.out.println("\n---------------------添加成员---------------------");
		System.out.print("请输入要添加的员工ID：");
		int id = TSUtility.readInt();
		try {
			Employee employee = listSvc.getEmployee(id);
			try {
				teamSvc.addMember(employee);
				System.out.println("添加成功");
			} catch (Exception e) {
				System.out.println("添加失败：" + e.getMessage());
			}
		} catch (TeamException e) {
			System.out.println("添加失败：" + e.getMessage());
		}
		TSUtility.readReturn();
	}

	// 实现删除成员操作
	private void deleteMember() {
		System.out.println("---------------------删除成员---------------------");
		System.out.print("请输入要删除员工的TID：");
		int memberId = TSUtility.readInt();
		System.out.print("确认是否删除(Y/N)：");	
		char isDelete = TSUtility.readConfirmSelection();
		if(isDelete == 'Y'){			
			try {
				teamSvc.removeMember(memberId);
				System.out.println("删除成功");
			} catch (TeamException e) {
				System.out.println("删除失败：" + e.getMessage());
			}
			TSUtility.readReturn();
		}

	}
}
