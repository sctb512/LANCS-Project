
import java.io.*;
import java.util.zip.*;
/*
*目标：
	1. 用前面所定义学生类创建10个学生对象，将它们保存在一个“students.dat”，然后从该文件中读出并显示10个学生对象。
	2. 上面考虑采用压缩方式将10个学生对象保存在另一个文件“students.zip”，解压读出各对象并显示。
*思路：
	1.创建数组，存储10个学生对象
	2.写入文件：先创建文件输出流 FileOutputStream 的对象，
*实现：

*/


//复制以前学生类作业代码

class Student implements Serializable		//xueshenglei
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

public class FileTester {

	public static void main(String[] args) throws Exception
	{

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
		
		String fileName = "student.dat";
		String zipfileName = "student.zip";
		String unzipfileName = "newStudent.dat";
		
		Student readStu[] = new Student[10];
		Student zipreadStu[] = new Student[10];

		//写入文件信息

		ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));

		

		for (int i=0 ;i<10 ;i++ )
		{
			out.writeObject(stuArr[i]);
		}
		out.close();

		//读取文件信息并显示
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
		for (int i=0 ;i<10 ;i++ )
		{
			readStu[i] = (Student)in.readObject();
		}
		in.close();

		System.out.println();
		for (int i=0;i<readStu.length;i++ )
		{
			System.out.println(readStu[i]);
		}

		//写入文件信息，并生成压缩文件
		ZipOutputStream zipout = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipfileName)));
		
		zipout.putNextEntry(new ZipEntry(fileName));
		ObjectOutputStream zipoutw = new ObjectOutputStream(zipout);
		for (int i=0 ;i<10 ;i++ )
		{
			zipoutw.writeObject(stuArr[i]);
		}
		zipoutw.close();
		//解压文件信息并生成新文件 newStudent.dat
		ZipInputStream zipin = new ZipInputStream(new FileInputStream(zipfileName));
		BufferedInputStream Bin = new BufferedInputStream(zipin);
		File fout=null;

		ZipEntry ze;
		while ((ze=zipin.getNextEntry())!=null && !ze.isDirectory())
		{
			fout=new File(unzipfileName);
			if (!fout.exists())
			{
				fout.createNewFile();
			}

			BufferedOutputStream zipinoutw = new BufferedOutputStream(new FileOutputStream(fout));
			int temp;
			while ((temp = Bin.read())!=-1)
			{
				zipinoutw.write(temp);
			}
			zipinoutw.close();
		}
		
		//读取新生成的文件，并显示输出
		ObjectInputStream newin = new ObjectInputStream(new BufferedInputStream(new FileInputStream(unzipfileName)));
		for (int i=0 ;i<10 ;i++ )
		{
			zipreadStu[i] = (Student)newin.readObject();
		}
		newin.close();

		System.out.println();
		for (int i=0;i<zipreadStu.length;i++ )
		{
			System.out.println(zipreadStu[i]);
		}
	}

}