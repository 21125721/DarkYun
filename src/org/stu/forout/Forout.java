package org.stu.forout;
/**
 * 循环输出
 * @author zcy12
 *
 */
public class Forout {
	//循环输出=，外边框
		public static void ForOut(int index) throws  InterruptedException {
			for(int i=0;i<index;i++) {	
				System.out.print("=");
			}
		}
		//循环输出=，外边框
		public static void ForOut(int index,int n) throws  InterruptedException {
			for(int i=0;i<index;i++) {	
				System.out.print("=");
				/*Robot Sleep = new Robot();
				Sleep.delay(100);*/
				
				Thread.currentThread().sleep(n);
			}
		}
		//学生信息管理系统输出
		public static void stuprin() throws InterruptedException {
			char a[] = {'学','生','信','息',' ',' ','管','理','系','统'};
			for(int i=0;i<a.length;i++) {
				System.out.print(a[i]);
			}
		}
		public static void stuprin(int index) throws InterruptedException {
			char a[] = {'学','生','信','息',' ',' ','管','理','系','统'};
			for(int i=0;i<a.length;i++) {
				System.out.print(a[i]);
				Thread.currentThread().sleep(index);
			}
		}
}
