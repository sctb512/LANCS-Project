/*

*目标：1. 由科研岗接口扩展一个新接口“自然类科研岗接口”，该接口增加“每次参加国际学术会议所得活跃度”和“国际学术活跃度方法”（由年度参国际学术会议次数乘 以“每次参加国际学术会议所得活跃度”得到）。
	    2. 由教学岗接口扩展一个新接口“教研型教学岗接口”，该接口增加“每次参加教研会议所得活跃度” 和“教研活跃度方法”（由年度参加教研会议次数乘以“每次参加教研会议所得活跃度”得到）。
	   3. 定义一个计算机教师类，实现“自然类科研岗接口”和“教研型教学岗接口”。
          4. 定义实现了“科研岗接口”的“计算机学科科研人员”类、“通信学科科研人员”类、“电子学科科研人员”类和“控制学科科研人员”类。要求各类在实现计算科研奖励方法的算法有所差别（同学自行规定，比如“计算机学科科研人员”类中科研奖励采用在计算算法在每科研分乘以科研分基础上再增加10%作为奖励金额。）
	  5. 在测试类中用上面定义类生成相关对象，并输出相应的信息。

*思路：1.定义自然科研接口继承科研接口，新增方法：每次参加国际学术会议所得活跃度 和 国际学术活跃度方法（每年参加次数乘以每次所得活跃度）。
	   2.定义教研型教学接口继承教学岗位接口，新增方法：每年参加教研活动所得活跃度 和 教研活跃度方法（每年参加教研会议次数乘以每次参加教研会议所得活跃度）。
	   3.定义计算机教师类，实现自然科研岗接口和教研型教学接口。
	   4.定义计算机学科科研人员类、通信学科科研人员类、电子学科科研人员类和控制学科科研人员类，实现科研岗接口，计算奖励方法不同。
	   5.在主函数中生成相应对象，输出显示。

*实现：1.interface NaturalScientific extends ScientificResearch
		{
			//new interface method
		}
		 interface TeachingManagement extends Teaching
		{
			//new interface method
		}
		class ComputerTeacher implements NaturalScientific,TeachingManagement
		{
			//codes of realize methods
		}
		public class TeacherPro 
		{
			public static void main(String[] args) {
				//realize object
			}
		}
*/


interface ScientificResearch								//科研接口
{
	static int moneyPeerResearchGrade=1000;
	public double calculateResearchAllowance();
}

interface Teaching											//教学接口
{
	static int moneyPeerTeachGrade=600;
	public int calculateTeachAllowance();
}

interface NaturalScientific extends ScientificResearch		//自然科研接口继承科研接口
{
	static int NaturalLivenessEveryTime=1000;
	public int calNaturalMoneyEveryYear();
}
interface TeachingManagement extends Teaching			//教学科研接口继承教学接口
{
	static int TeachingLivenessEveryTime=800;
	public int calTeachingMoneyEveryYear();
}

class ComputerTeacher implements NaturalScientific,TeachingManagement
{
	public static int WORKNUMBER=0;							
	private String TeacherName;
	private int WorkNumber;
	private int ResearchGrade;
	private int TeachGrade;
	private int TimeOfNatural;
	private int TimeOfTeaching;
	//学号、工号不提供修改方法
	public void setResearchGrade(int ResearchGrade) {		//私有变量提供的对外接口
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
	public void SetTimeOfNatural(int TimeOfNatural) {
		this.TimeOfNatural=TimeOfNatural;
	}
	public void SetTimeOfTeaching(int TimeOfTeaching) {
		this.TimeOfTeaching=TimeOfTeaching;
	}
	public int getTimeOfNatural() {
		return TimeOfNatural;
	}
	public int getTimeOfTeaching() {
		return TimeOfTeaching;
	}
	public ComputerTeacher() {										//默认构造函数		
	}
	public ComputerTeacher(String TeacherName) {					//重写构造函数
		WorkNumber=++WORKNUMBER;
		this.TeacherName=TeacherName;
	}
	public double calculateResearchAllowance() {				//实现接口函数
		return moneyPeerResearchGrade*ResearchGrade;
	}
	public int calculateTeachAllowance() {					//实现接口函数
		return moneyPeerTeachGrade*TeachGrade;
	}
	public int calNaturalMoneyEveryYear() {					//实现计算自然科研奖励接口函数
		return TimeOfNatural*NaturalLivenessEveryTime;
	}
	public int calTeachingMoneyEveryYear() {					//实现计算教学科研奖励接口函数
		return TimeOfTeaching*TeachingLivenessEveryTime;
	}

	public String toString() {								//重写toString函数
		return (" Name: "+getTeacherName()+"\t WorkNumber: "+new java.text.DecimalFormat("000000").format(getWorkNumber())+"\n ResearchAllowance: "+calculateResearchAllowance()+"\t TeachAllowance: "+calculateTeachAllowance()+"\n NaturalMoneyEveryYear: "+calNaturalMoneyEveryYear()+"\t TeachingMoneyEveryYear: "+calTeachingMoneyEveryYear());
	}
}
class ComputerSciencePerson implements ScientificResearch			//计算机科研实现科研接口
{
	private String ComputerScienceName;
	private int age;
	private String major;
	private int ResearchGrade;

	public void setComputerScienceName(String ComputerScienceName) {
		this.ComputerScienceName=ComputerScienceName;
	}
	public void setAge(int age) {
		this.age=age;
	}
	public void setMajor(String major) {
		this.major=major;
	}
	public void setResearchGrade(int ResearchGrade) {
		this.ResearchGrade=ResearchGrade;
	}
	public String getComputerScienceName() {
		return ComputerScienceName;
	}
	public int getAge() {
		return age;
	}
	public String getMajor() {
		return major;
	}
	public int getResearchGrade() {
		return ResearchGrade;
	}
	public ComputerSciencePerson() {										//默认构造函数		
	}
	public ComputerSciencePerson(String ComputerScienceName,int age,String major,int ResearchGrade) {					//重写构造函
		this.ComputerScienceName=ComputerScienceName;
		this.age=age;
		this.major=major;
		this.ResearchGrade=ResearchGrade;
	}

	public double calculateResearchAllowance() {				//实现接口函数
		return moneyPeerResearchGrade*ResearchGrade*(1+0.1);
	}

	public String toString() {								//重写toString
		return (" Name: "+getComputerScienceName()+"\t age: "+getAge()+"\t Major: "+getMajor()+"\n ResearchAllowance: "+calculateResearchAllowance());
	}

}

class CommunicationSciencePerson implements ScientificResearch			//通信科研实现科研接口
{
	private String CommunicationScienceName;
	private int age;
	private String major;
	private int ResearchGrade;

	public void setComputerScienceName(String CommunicationScienceName) {
		this.CommunicationScienceName=CommunicationScienceName;
	}
	public void setAge(int age) {
		this.age=age;
	}
	public void setMajor(String major) {
		this.major=major;
	}
	public void setResearchGrade(int ResearchGrade) {
		this.ResearchGrade=ResearchGrade;
	}
	public String getCommunicationScienceName() {
		return CommunicationScienceName;
	}
	public int getAge() {
		return age;
	}
	public String getMajor() {
		return major;
	}
	public int getResearchGrade() {
		return ResearchGrade;
	}
	public CommunicationSciencePerson() {										//默认构造函数		
	}
	public CommunicationSciencePerson(String CommunicationScienceName,int age,String major,int ResearchGrade) {					//重写构造函
		this.CommunicationScienceName=CommunicationScienceName;
		this.age=age;
		this.major=major;
		this.ResearchGrade=ResearchGrade;
	}

	public double calculateResearchAllowance() {				//实现接口函数
		return moneyPeerResearchGrade*ResearchGrade*(1+0.5);
	}

	public String toString() {								//重写toString
		return (" Name: "+getCommunicationScienceName()+"\t age: "+getAge()+"\t Major: "+getMajor()+"\n ResearchAllowance: "+calculateResearchAllowance());
	}

}

class ElectronSciencePerson implements ScientificResearch			//电子科研实现科研接口
{
	private String ElectronScienceName;
	private int age;
	private String major;
	private int ResearchGrade;

	public void setElectronScienceName(String ElectronScienceName) {
		this.ElectronScienceName=ElectronScienceName;
	}
	public void setAge(int age) {
		this.age=age;
	}
	public void setMajor(String major) {
		this.major=major;
	}
	public void setResearchGrade(int ResearchGrade) {
		this.ResearchGrade=ResearchGrade;
	}
	public String getElectronScienceName() {
		return ElectronScienceName;
	}
	public int getAge() {
		return age;
	}
	public String getMajor() {
		return major;
	}
	public int getResearchGrade() {
		return ResearchGrade;
	}
	public ElectronSciencePerson() {										//默认构造函数		
	}
	public ElectronSciencePerson(String ElectronScienceName,int age,String major,int ResearchGrade) {					//重写构造函
		this.ElectronScienceName=ElectronScienceName;
		this.age=age;
		this.major=major;
		this.ResearchGrade=ResearchGrade;
	}

	public double calculateResearchAllowance() {				//实现接口函数
		return moneyPeerResearchGrade*ResearchGrade*(1+0.3);
	}

	public String toString() {								//重写toString
		return (" Name: "+getElectronScienceName()+"\t age: "+getAge()+"\t Major: "+getMajor()+"\n ResearchAllowance: "+calculateResearchAllowance());
	}

}

class ControlSciencePerson implements ScientificResearch			//控制科研实现科研接口
{
	private String ControlScienceName;
	private int age;
	private String major;
	private int ResearchGrade;

	public void setControlScienceName(String ControlScienceName) {
		this.ControlScienceName=ControlScienceName;
	}
	public void setAge(int age) {
		this.age=age;
	}
	public void setMajor(String major) {
		this.major=major;
	}
	public void setResearchGrade(int ResearchGrade) {
		this.ResearchGrade=ResearchGrade;
	}
	public String getControlScienceName() {
		return ControlScienceName;
	}
	public int getAge() {
		return age;
	}
	public String getMajor() {
		return major;
	}
	public int getResearchGrade() {
		return ResearchGrade;
	}
	public ControlSciencePerson() {										//默认构造函数		
	}
	public ControlSciencePerson(String ControlScienceName,int age,String major,int ResearchGrade) {					//重写构造函
		this.ControlScienceName=ControlScienceName;
		this.age=age;
		this.major=major;
		this.ResearchGrade=ResearchGrade;
	}

	public double calculateResearchAllowance() {				//实现接口函数
		return moneyPeerResearchGrade*ResearchGrade*(1+0.7);
	}

	public String toString() {								//重写toString
		return (" Name: "+getControlScienceName()+"\t age: "+getAge()+"\t Major: "+getMajor()+"\n ResearchAllowance: "+calculateResearchAllowance());
	}

}

class Teacher implements ScientificResearch,Teaching				//教师类  实现科研、教学接口
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

	public double calculateResearchAllowance() {				//实现接口函数
		return moneyPeerResearchGrade*ResearchGrade;
	}
	public int calculateTeachAllowance() {					//实现接口函数
		return moneyPeerTeachGrade*TeachGrade;
	}

	public String toString() {								//重写toString
		return (" Name: "+getTeacherName()+"\t WorkNumber: "+new java.text.DecimalFormat("000000").format(getWorkNumber())+"\n ResearchAllowance: "+calculateResearchAllowance()+"\t TeachAllowance: "+calculateTeachAllowance());
	}
}

public class TeacherPro
{
	public static void main(String[] args) {				//入口函数
		Teacher teacherTest = new Teacher("ZhangSan");
		teacherTest.setResearchGrade(10);
		teacherTest.setTeachGrade(6);
		System.out.println();
		System.out.println(teacherTest);

		System.out.println();
		ComputerTeacher computerTeacherTest = new ComputerTeacher("lisi");
		computerTeacherTest.setResearchGrade(5);
		computerTeacherTest.setTeachGrade(3);
		computerTeacherTest.SetTimeOfNatural(12);
		computerTeacherTest.SetTimeOfTeaching(14);
		System.out.println(computerTeacherTest);

		System.out.println();

		ComputerSciencePerson ComputerPerson = new ComputerSciencePerson("wangwu",21,"NetWork",35);
		CommunicationSciencePerson CommunicationPerson = new CommunicationSciencePerson("zhaoliu",25,"Communication",38);
		ElectronSciencePerson ElectronPerson = new ElectronSciencePerson("jiangqi",19,"Capacity",28);
		ControlSciencePerson ControlPerson = new ControlSciencePerson("yiba",22,"Self-motion",30);
		System.out.println(ComputerPerson);
		System.out.println(CommunicationPerson);
		System.out.println(ElectronPerson);
		System.out.println(ControlPerson);
	}
}
