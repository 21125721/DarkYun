package org.stu.stuinfo;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 封装JDBC
 * @author 19392
 */
public class SQLexecute<T> {
		public List<T> executeQuery(Connection conn,String sql,T t,Object...params)
			throws SQLException, IllegalArgumentException,
				IllegalAccessException, InstantiationException{
			List<T> list = new ArrayList<T>();
			ResultSet rs = null;
			//去掉前后空格
			sql = sql.trim();
			//查看字符串SQL中是否前导select
			if(!sql.toLowerCase().startsWith("select")){
				throw new SQLException("在此应该执行select语句");
			}
			//查看SQL语句中是否有参数
			String tmp = sql.replace("?", "");
			int len = Math.abs(tmp.length()-sql.length());
			//判断SQL语句中的“？”个数与params的元素的个数是否相等
			if(len != params.length) {
				throw new SQLException("参数个数不匹配");
			}
			if(len>0) {
				//获得执行预编译的PreparedStatement
				PreparedStatement pstat = conn.prepareStatement(sql);
				//设置参数
				for(int i=0;i<params.length;i++) {
					pstat.setObject(i+1, params[i]);
				}
				//执行
				rs = pstat.executeQuery();
			}
			else {
				//执行没有参数的SQL语句
				Statement stat = conn.createStatement();
				rs = stat.executeQuery(sql);
			}
			//使用反射
			Class<?> clazz = t.getClass();
			//处理结果集
			while(rs.next()) {
				//使用反射
				//Class<?> clazz = t.getClass();
				//实例化反射的类的对象
				Object obj = clazz.newInstance();
				//反射类型中的域
				Field[] fields = clazz.getDeclaredFields();
				for(Field field : fields) {
					//使用注解进行映射
					if(field.isAnnotationPresent(ColumnName.class)) {
						ColumnName cn = field.getAnnotation(ColumnName.class);
						//获得注解中的字段名
						String cname = cn.value();
						//设置域成员的值
						field.setAccessible(true);
						field.set(obj, rs.getObject(cname));
					}
				}
				//将对象放入列表
				list.add((T)obj);
			}
			return list;
		}
		
		public int executeModify(Connection conn,
				String sql,
				Object...param)
			throws Exception{
			Integer result = null;
			//判断是否是执行insert,delete,update
			String pstr = "(insert|delete|update)";
			Pattern pattern = Pattern.compile(pstr);
			Matcher matcher = pattern.matcher(sql);
			if(!matcher.find()) {
				throw new Exception("在此执行insert/update/delete语句");
			}
			//判断是否有参数
			sql = sql.trim().toLowerCase();
			//参数个数
			String tmp = sql.replace("?", "");
			int len = Math.abs(tmp.length() - sql.length());
			//判断个数
			if(len!=param.length) {
				throw new Exception("参数个数不匹配");
			}
			//判断len是否大于零
			if(len>0) {
				PreparedStatement pstat = conn.prepareStatement(sql);
				for(int i=0;i<param.length;i++) {
					pstat.setObject(i+1, param[i]);
				}
				result = pstat.executeUpdate();
			}
			else {
				Statement stat = conn.createStatement();
				result = stat.executeUpdate(sql);
			}
			return result;
		}
}
