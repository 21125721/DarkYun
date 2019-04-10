package org.stu.index;


import java.io.File;
import java.io.IOException;

import org.stu.mainui.Mainui;
import org.stu.stuinfo.Addstuinfo;
import org.stu.stuinfo.Selectstu;
import org.stu.stuinfo.Updatestu;



/**
 * 入口
 * @author zcy12
 *
 */
public class Index {
	
		static File f = null;
	public static void main(String[] args) throws Exception {
		//初始化账号密码
		//Userdata.Initialize();
		//调用登陆方法
		Mainui.login();
		//Updatestu.update();
//		Selectstu.selectstu();
		//Addstuinfo.addstuinfo();
	}
	
	
}
