package com.atguigu.team.service;

import com.atguigu.team.domain.Architect;
import com.atguigu.team.domain.Designer;
import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Programmer;

public class TeamService {
	// 全局静态变量 用来为开发团队新增成员自动生成团队中的唯一ID 即memberId
	private static int count = 1;
	// 表示开发团队最大成员数
	private static final int MAX_MEMBER = 5;
	//初始化团队中三种职业人员个数
	private static int numOfArc = 0,numOfDes = 0, numOfPro = 0;
	// team数组：用来保存当前团队中的各成员对象
	private Programmer[] team = new Programmer[MAX_MEMBER];
	// total：记录团队成员的实际人数
	private int total;

	public TeamService() {
		super();
	}

	/**
	 * 
	 * @Dsecription getTeam()方法：返回当前团队的所有对象
	 * @author luozhuishuai
	 * @version 1.0
	 * @date 2020年9月17日下午5:45:09
	 * @return 包含所有成员对象的数组，数组大小与成员人数一致
	 */
	public Programmer[] getTeam() {
		Programmer[] team = new Programmer[total];
		for (int i = 0; i < team.length; i++) {
			team[i] = this.team[i];
		}
		return team;
	}

	/**
	 * 
	 * @Dsecription 将指定的员工添加到开发团队中
	 * @author luozhuishuai
	 * @version
	 * @date 2020年9月17日下午7:09:56
	 *
	 */
	public void addMember(Employee e) throws TeamException {
		// 失败信息包含以下几种：
		// 成员已满，无法添加
		if (total >= MAX_MEMBER) {
			throw new TeamException("成员已满，无法添加");
		}
		// 该成员不是开发人员，无法添加
		if (!(e instanceof Programmer)) {
			throw new TeamException("该成员不是开发人员，无法添加");
		}
		// 该员工已在本开发团队中
		if (isExct(e)) {
			throw new TeamException("该员工已在本开发团队中");
		}
		// 必定是程序员 可直接强转
		// 该员工已是某团队成员
		Programmer p = (Programmer) e;
		if ("BUSY".equals(p.getStatus())) {
			throw new TeamException("该员工已是某团队成员 ");
			// 该员正在休假，无法添加
		} else if ("VACATION".equals(p.getStatus())) {
			throw new TeamException("该员正在休假，无法添加");
		}

		// 团队中至多只能有一名架构师
		// 团队中至多只能有两名设计师
		// 团队中至多只能有三名程序员
		// 先遍历出团队中所有类型的员工的数量

		if (p instanceof Architect) {
			numOfArc++;
		} else if (p instanceof Designer) {
			numOfDes++;
		} else if (p instanceof Programmer) {
			numOfPro++;
		}

		if (p instanceof Architect) {
			if (numOfArc > 1) {
				throw new TeamException("团队中至多只能有一名架构师");
			}
		} else if (p instanceof Designer) {
			if (numOfDes > 2) {
				throw new TeamException("团队中至多只能有两名设计师");
			}
		} else if (p instanceof Programmer) {
			if (numOfPro > 3) {
				throw new TeamException("团队中至多只能有三名程序员");
			}
		}

		// 把p添加到数组中 然后再让计数器+1
		team[total++] = p;
		// 设置p的meberId 然后让计数器+1
		p.setMemberId(count++);
		// 将p的Status设置为BUSY
		p.setStatus(Status.BUSY);		

	}

	// 查找对象的id是否与团队中任一成员的id相同
	private boolean isExct(Employee e) {
		for (int i = 0; i < total; i++) {
			if (e.getId() == team[i].getId()) {
				return true;
			}
		}
		return false;
	}

	public void removeMember(int memberId) throws TeamException {
		for (int i = 0; i < total; i++) {
			 if(team[i].getMemberId() == memberId){
				 //如果有此员工 则将其设置为FREE
				 team[i].setStatus(Status.FREE);
				 //遍历team数组 将i及i以后的元素两两置换
				 for(;i<total-1;i++){
					 team[i] = team[i+1];
				 }
				 //置换完毕以后将最后一个元素设置空null
				 team[i+1] = null;
				 //team中员工总数-1
				 total--;
			//搜索不到则抛出异常
			 }else{
				 throw new TeamException("找不到TID为" + memberId + "的员工");
			 }
		}
	}

}
