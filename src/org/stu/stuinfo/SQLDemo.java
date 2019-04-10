package org.stu.stuinfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.stu.Data.Dbconnection;
import org.stu.students.Students;

public class SQLDemo {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InstantiationException, SQLException {
		Connection conn = Dbconnection.getConnection();
		String sql = "select * from stu";
		Students stu = new Students();
		SQLexecute<Students> sq = new SQLexecute<Students>();
		List<Students> list =sq.executeQuery(conn, sql, stu);
		for(Students st:list) {
			System.out.println(st.toString());
		}
	}

}
