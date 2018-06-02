/*
*目标：1.编写一个科研岗位，该接口包括：每科研分奖励金额的静态常量；计算科研津贴的方法。
	   2.编写一个教学岗位接口，该接口包括：每教学分奖励金额的静态常量；计算教学津贴的方法。
	   2.定义一个高校教师类，它实现科研岗位接口和教学岗位接口，该类包括教师姓名、教职工号、教学分、科研分。
*思路：1.定义一个科研接口类和一个教学接口类，包括静态常量和抽象方法。
	   2.定义教师类，实现科研和教学岗位接口
	   3.定义SchoolDemo类写接口函数，创建教师对象，并输出显示。
*实现：interface ScientificResearch {
			static moneyPeerGrade;
			public calculateAllowance ( int grade ) ;
		}
		class Teacher {
			// content
		}
		public class SchoolDemo {
			public static void main(String args[]) {
			// content
			}
		}
*/

interface ScientificResearch								//科研接口
{
	static int moneyPeerResearchGrade=1000;
	public int calculateResearchAllowance();
}

interface Teaching											//教学接口
{
	static int moneyPeerTeachGrade=600;
	public int calculateTeachAllowance();
}

class Teacher implements ScientificResearch,Teaching		//教师类，实现科研、教学接口
{
	public static int WORKNUMBER=0;							//静态变量，生成工号
	private String TeacherName;
	private int WorkNumber;
	private int ResearchGrade;
	private int TeachGrade;

	//学号、工号不提供修改方法
	public void setResearchGrade(int ResearchGrade) {		//私有变量对外接口
		this.ResearchGrade=ResearchGrade;
	}
	public void setTeachGrade(int TeachGrade) {
		this.TeachGrade=TeachGrade;
	}
	public String getTeacherName() {
		return TeacherName;
	}
	public int getWorkNumber() {
		return WorkNumber;
	}
	public int getResearchGrade() {
		return ResearchGrade;
	}
	public int getTeachGrade() {
		return TeachGrade;
	}

	public Teacher() {										//默认构造函数		
	}
	public Teacher(String TeacherName) {					//重写构造函数
		WorkNumber=++WORKNUMBER;
		this.TeacherName=TeacherName;
	}

	public int calculateResearchAllowance() {				//实现接口函数
		return moneyPeerResearchGrade*ResearchGrade;
	}
	public int calculateTeachAllowance() {					//实现接口函数
		return moneyPeerTeachGrade*TeachGrade;
	}

	public String toString() {								//重写toString
		return (" Name: "+getTeacherName()+"\t WorkNumber: "+new java.text.DecimalFormat("000000").format(getWorkNumber())+"\n ResearchAllowance: "+calculateResearchAllowance()+"\t TeachAllowance: "+calculateTeachAllowance());
	}
}

public class TeacherDemo+
{
	public static void main(String[] args) {				//入口函数
		Teacher teacherTest = new Teacher("ZhangSan");
		teacherTest.setResearchGrade(10);
		teacherTest.setTeachGrade(6);
		System.out.println();
		System.out.println(teacherTest);
	}
}