package org.stu.stuinfo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.stu.Data.Dbconnection;
import org.stu.forout.Forout;
import org.stu.mainui.Mainui;
import org.stu.students.Students;


public class Stuinfoui {
	static Connection conn = null;
	static Scanner scan = new Scanner(System.in);
	public static void Stuinfoui() throws Exception {
		long begintime = new Date().getTime();
		Forout.ForOut(12);
		Forout.stuprin();
		Forout.ForOut(12);
		System.out.print("\n||\t\t 1、所有学生\t\t||");
		System.out.print("\n||\t\t 2、查询学生\t\t||");
		System.out.print("\n||\t\t 3、返回上级\t\t||");
		System.out.print("\n||\t\t 4、退       出\t\t||\n");
		Forout.ForOut(42);
		long endtime = new Date().getTime();
		System.out.println("\r\n加载完成,耗时："+(endtime-begintime)+"ms");
		System.out.print("请输入您的选项：");
		String n = scan.nextLine();
		switch(n) {
			case "1":allinfo();break;
			case "2":Selectstu.selectstu();break;
			case "3":Mainui.Stuinfo();break;
			case "4":Mainui.Exit();break;
			default:System.out.println("输入有误，请重新输入");break;
		}  
		scan.close();
	}
	public static void allinfo(){
		conn =  Dbconnection.getConnection();
		if(conn ==null) {
			System.out.println("获取失败");
		}
		else {
			try {
				String sql = "select * from stu";
				PreparedStatement pstat = conn.prepareStatement(sql);
				boolean flag = pstat.execute();
				if(flag) {
					System.out.println("学号\t姓名\t性别\t年龄\t手机号\t");
					/*while(rs.next()) {
						String sex = "";
						if(rs.getInt("sex")==1) {
							sex="男";
						}else {
							sex="女";
						}
						System.out.println(rs.getInt("stucode")+"\t"+
											rs.getString("name")+"\t"+
											sex+"\t"+
											rs.getInt("age")+"\t"+
											rs.getString("phoneid")+"\t");
					}*/
					Students stu = new Students();
					SQLexecute<Students> sq = new SQLexecute<Students>();
					List<Students> list =sq.executeQuery(conn, sql, stu);
					for(Students st:list) {
						System.out.println(st.toString());
					}
					System.out.println("打印完成,按任意键返回上一级");
					String sc = scan.nextLine();
					if("".equals(sc)) {
						Stuinfoui();
					}else {
						Stuinfoui();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}	
}