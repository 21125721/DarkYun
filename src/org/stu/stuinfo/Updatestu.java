package org.stu.stuinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.stu.mainui.Mainui;
import org.stu.students.Students;

public class Updatestu {
	public static void update() throws Exception {
		Scanner scan = new Scanner(System.in);
		Connection conn = org.stu.Data.Dbconnection.getConnection();
		boolean flag = false;
		boolean real = false;
		if(conn == null) {
			System.out.println("获取失败");
		}else {
			try {
				do {
					real = false;
					flag = false;
					System.out.println("请输入要修改的学生id:");
					int id = scan.nextInt();
					scan.nextLine();
					String sqlselct = "select * from stu where stucode = ?";
					PreparedStatement pstat = conn.prepareStatement(sqlselct);
					pstat.setInt(1, id);
					ResultSet p = pstat.executeQuery();
					if(p.next()){
						do {
							System.out.println("请输入要修改的学生信息:修改(1:姓名/2:性别:/3:年龄/4:手机号)");
							String column = scan.nextLine();
							switch (column) {
								case "1":
									do {
										System.out.println("请输入你想修改的值:");
										String name = scan.nextLine();
										if("".trim().equals(name)) {
											real = true;
											System.out.println("姓名不能为空,请重新输入");
										}else {
											String sql = "update stu set name = ? where stucode = ?";
											SQLexecute<Students> sq = new SQLexecute<Students>();
											int rows=sq.executeModify(conn, sql,name,id);
											if(rows<=0) {
												System.out.println("更新失败,请重新输入");
												real = true;
											}else {
												System.out.println("更新成功,影响:"+rows+"行");
												System.out.println("按任意键返回上一级");
												String sc = scan.nextLine();
												if("".equals(sc)) {
													Mainui.Stuinfo();
												}else {
													Mainui.Stuinfo();
												}
											}
										}
									}while(real);
									break;
								case "2":
									do {
										System.out.println("请输入你想修改的值:");
										String sex = scan.nextLine();
										if(!sex.equals("男")||!sex.equals("女")) {
											System.out.println("性别只能为男/女");
											real = true;
										}else {
											String sql = "update stu set sex = ? where stucode = ?";
											SQLexecute<Students> sq = new SQLexecute<Students>();
											int rows=sq.executeModify(conn, sql,sex,id);
											System.out.println("更新成功,影响:"+rows+"行");
											System.out.println("按任意键返回上一级");
											String sc = scan.nextLine();
											if("".equals(sc)) {
												Mainui.Stuinfo();
											}else {
												Mainui.Stuinfo();
											}
										}
									}while(real);
									break;
								case "3":
									do {
										System.out.println("请输入你想修改的值:");
										int age = scan.nextInt();
										scan.nextLine();
										if(age>99||age<0) {
											System.out.println("年龄只能为1-99");
											real = true;
										}else {
											String sql = "update stu set age = ? where stucode = ?";
											SQLexecute<Students> sq = new SQLexecute<Students>();
											int rows=sq.executeModify(conn, sql,age,id);
											System.out.println("更新成功,影响:"+rows+"行");
											System.out.println("按任意键返回上一级");
											String sc = scan.nextLine();
											if("".equals(sc)) {
												Mainui.Stuinfo();
											}else {
												Mainui.Stuinfo();
											}
										}
									}while(real);
									break;
								case "4":
									do {
										System.out.println("请输入你想修改的值:");
										String phoneid = scan.nextLine();
										if(phoneid.matches("^(13|15|18|17)\\d{9}$")) {
											System.out.println("手机号输入错误");
											real = true;
										}else {
											String sql = "update stu set phoneid = ? where stucode = ?";
											SQLexecute<Students> sq = new SQLexecute<Students>();
											int rows=sq.executeModify(conn, sql,phoneid,id);
											System.out.println("更新成功,影响:"+rows+"行");
											System.out.println("按任意键返回上一级");
											String sc = scan.nextLine();
											if("".equals(sc)) {
												Mainui.Stuinfo();
											}else {
												Mainui.Stuinfo();
											}
										}
									}while(real);
									break;
								default:
									System.out.println("输入错误,请重新输入");
									flag = true;
									break;
								}
						}while(flag);
					}else {
						flag = true;
						System.out.println("没有这个学生,请重新输入");
					}
				}while(flag);
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
