package org.stu.stuinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Selectstu {
	public static void selectstu(){
		Scanner scan = new Scanner(System.in);
		Connection conn = org.stu.Data.Dbconnection.getConnection();
		boolean r = false;
		if(conn == null) {
			System.out.println("获取失败");
		}else {
			try {
				do{
					r = false;
					System.out.println("请输入要查询的学生id");
					int id = scan.nextInt();
					scan.nextLine();
					String sql = "select * from stu where stucode = ?";
					PreparedStatement pstat = conn.prepareStatement(sql);
					pstat.setInt(1, id);
					boolean flag = pstat.execute();
					ResultSet rs = pstat.getResultSet();
					if(flag){
						if(rs.next()){
							System.out.println("学号:"+rs.getInt("stucode")+"\t"+
												"姓名:"+rs.getString("name")+"\t"+
												"性别"+rs.getString("sex")+"\t"+
												"年龄:"+rs.getInt("age")+"\t"+
												"手机号"+rs.getString("phoneid"));
						}else {
							System.out.println("没有数据");
							r = true;
						}
					}else {
						System.out.println("没有这个学生,请重新输入");
						r = true;
					}
				}while(r);
				System.out.println("打印完成,按任意键返回上一级");
				String sc = scan.nextLine();
				if("".equals(sc)) {
					Stuinfoui.Stuinfoui();
				}else {
					Stuinfoui.Stuinfoui();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		scan.close();
	}
}
