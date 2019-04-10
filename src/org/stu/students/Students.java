package org.stu.students;
/**
 * 学生对象
 */
import java.io.Serializable;

import org.stu.stuinfo.ColumnName;

public class Students implements Serializable{
	private static final long serialVersionUID = 1991651058100165058L;
	@ColumnName("name")
	private String name;
	@ColumnName("stucode")
	private int stuid;
	@ColumnName("sex")
	private String sex;
	@ColumnName("age")
	private int age;
	@ColumnName("phoneid")
	private String phoneid;
	@Override
	public String toString() {
		return stuid+"\t"+name+"\t"+sex+"\t"+age+"\t"+phoneid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStuid() {
		return stuid;
	}
	public void setStuid(int stuid) {
		this.stuid = stuid;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhoneid() {
		return phoneid;
	}
	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}
	
}
