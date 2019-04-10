package org.stu.stuinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import org.stu.Data.Dbconnection;
import org.stu.students.Students;

public class Addstuinfo{
	static Scanner scan = new Scanner(System.in);
	/**
	 * 添加学生信息
	 */
	public static void addstuinfo(){
		Connection conn = null;
		boolean flag = false;
		String name = "";
		String sex = ""; 
		int age = 0;
		String phoneid = "";
		try {
			conn = Dbconnection.getConnection();
			if(conn == null) {
				System.out.println("获取失败");
			}else {
				String sql = "insert into stu(name,sex,age,phoneid) values(?,?,?,?)";
				PreparedStatement pstat = conn.prepareStatement(sql);
				do {
					flag = false;
					System.out.println("请输入姓名:");
					name = scan.nextLine();
					if("".trim().equals(name)) {
						System.out.println("姓名不能为空, 请重新输入");
						flag = true;
					}else {
//						pstat.setString(1, name);
						
					}
				}while(flag);
				do {
					flag = false;
					System.out.println("请输入性别:男/女");
					sex = scan.nextLine();
					scan.nextLine();
					if(!sex.equals("男")||!sex.equals("女")) {
						System.out.println("输入错误,请重新输入:");
						flag = true;
					}else {
						//pstat.setInt(2, sex);
					}
				}while(flag);
				do {
					flag = false;
					System.out.println("请输入年龄:");
					age = scan.nextInt();
					scan.nextLine();
					if(age <1&&age >99) {
						System.out.println("输入错误,请重新输入:");
						flag = true;
					}else {
						//pstat.setInt(3, age);
					}
				}while(flag);
				do {
					flag = false;
					System.out.println("请输入手机号:");
					phoneid = scan.nextLine();
					if(!phoneid.trim().matches("^(13|15|18|17)\\d{9}$")) {
						System.out.println("输入错误,请重新输入:");
						flag = true;
					}else {
						//pstat.setString(4, phoneid);
					}
				}while(flag);
				Students stu = new Students();
				SQLexecute<Students> sq = new SQLexecute<Students>();
				int rows=sq.executeModify(conn, sql,name,sex,age,phoneid);
				boolean f = pstat.execute();
				if(f) {
					System.out.println("有结果集");
				}else {
					System.out.println("添加成功,影响:"+pstat.getUpdateCount()+"行");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
