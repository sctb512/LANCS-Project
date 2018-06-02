/*
*目标：编写一个方法，在方法中使用Iterator来遍历Collection，并输出此集合类中每个对象的hashCode()值。用对象填充不同类型的Collection	类对象，并将此方法应用于每一种Collection类对象。
*思路：1.新建学生类对象，保存在数组中。
	   2.新建 Vector 向量集合对象，用于存放学生对象。
	   3.新建Iterator 对象，用于存放Vector中对象。
*实现：1.new Student();
	   2.Vector<Student> aVector = new Vector<!-- <Student> -->();    aVectore.add();
	   3.Iterator<!-- <Student> --> aIterator = aVector.iterator();
*/

import java.util.*;
import java.lang.*;

class Student		//定义学生类
{
	public static int NUMBER;
	private String name;
	private int number;
	private int age;
	private double chinese;
	private double math;
	private int Mathpole;
	private int Chinesepole;

	public Student () {
	}
	public Student(String name,int age,double chinese,double math) {
		this.name = name;
		this.number = ++NUMBER;
		this.age = age;
		this.chinese = chinese;
		this.math = math;
		Mathpole=setPole(this.math);
		Chinesepole=setPole(this.chinese);
	}
	public double sumScore() {
		return (this.chinese+this.math);
	}
	public String getName() {
		return name;
	}
	public int getNumber() {
		return number;
	}
	public int getAge() {
		return age;
	}
	public int getMathPole() {
		return Mathpole;
	}
	public int getChinesePole() {
		return Chinesepole;
	}
	public String toString () {
		return ("student:"+name+"\t number:"+new java.text.DecimalFormat("000000").format(number)+"\t age:"+age+"\t grade:"+sumScore()+"\t chinese_class:"+getClass(this.chinese)+"\t pole:"+setPole(this.chinese)+"\t math_class:"+getClass(this.math)+"\t pole:"+setPole(this.math));
	}
	public String getClass (double grade) {
		switch ((int)grade/10)
		{
		case 10:
		case 9: return ("A");
		case 8: return ("B");
		case 7: return ("B");
		case 6: return ("B");
		default:return ("E");		
		}
	}	
	public int setPole(double score) {		
		return (int)score/10-5;
	}
	public boolean equals(Student StuObj) {
		if (StuObj.getName()==this.getName()&&StuObj.getNumber()==this.getNumber())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}

public class IteratorTester 
{
	public static void main(String[] args) 
	{



		Student stu = new Student("zhansan",20,91,88);
		String str = new String("hello java world!");
		Vector<Object> aVector = new Vector<Object>();
		aVector.add(stu);
		aVector.add(str);
		
		Iterator<Object> i = aVector.iterator();
		while(i.hasNext())
		{
			Object oj = new Object();
			oj=i.next();
			System.out.println("* hashCode is :  "+oj.hashCode());
		}
/*
		
		Student stu_01 = new Student("zhansan",20,91,88);
		Student stu_02 = new Student("lisi",18,85,97);
		Student stu_03 = new Student("wangwu",19,93,82);
		Student stu_04 = new Student("zhaoliu",17,82,95);
		Student stu_05 = new Student("liqi",21,94,90);
		Student stu_06 = new Student("zhansan",20,91,88);
		Student stu_07= new Student("lisi",18,85,97);
		Student stu_08 = new Student("wangwu",19,93,82);
		Student stu_09 = new Student("zhaoliu",17,82,95);
		Student stu_10 = new Student("liqi",21,94,90);
		
		Student stuArr[] = new Student[10];
		stuArr[0]=stu_01;
		stuArr[1]=stu_02;
		stuArr[2]=stu_03;
		stuArr[3]=stu_04;
		stuArr[4]=stu_05;
		stuArr[5]=stu_06;
		stuArr[6]=stu_07;
		stuArr[7]=stu_08;
		stuArr[8]=stu_09;
		stuArr[9]=stu_10;

		Vector<Student> aVector = new Vector<Student>();
		
		for (int i=0;i<stuArr.length ;i++ )
		{
			aVector.add(stuArr[i]);
		}

		System.out.println();

		Iterator<Student> i = aVector.iterator();
		while (i.hasNext())
		{
			Student temp = new Student();
			temp=i.next();
			System.out.println("* hashCode is :  "+temp.hashCode());
		}
*/
	}

}
