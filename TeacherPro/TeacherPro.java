/*

*Ŀ�꣺1. �ɿ��иڽӿ���չһ���½ӿڡ���Ȼ����иڽӿڡ����ýӿ����ӡ�ÿ�βμӹ���ѧ���������û�Ծ�ȡ��͡�����ѧ����Ծ�ȷ�����������Ȳι���ѧ����������� �ԡ�ÿ�βμӹ���ѧ���������û�Ծ�ȡ��õ�����
	    2. �ɽ�ѧ�ڽӿ���չһ���½ӿڡ������ͽ�ѧ�ڽӿڡ����ýӿ����ӡ�ÿ�βμӽ��л������û�Ծ�ȡ� �͡����л�Ծ�ȷ�����������Ȳμӽ��л���������ԡ�ÿ�βμӽ��л������û�Ծ�ȡ��õ�����
	   3. ����һ���������ʦ�࣬ʵ�֡���Ȼ����иڽӿڡ��͡������ͽ�ѧ�ڽӿڡ���
          4. ����ʵ���ˡ����иڽӿڡ��ġ������ѧ�ƿ�����Ա���ࡢ��ͨ��ѧ�ƿ�����Ա���ࡢ������ѧ�ƿ�����Ա����͡�����ѧ�ƿ�����Ա���ࡣҪ�������ʵ�ּ�����н����������㷨�������ͬѧ���й涨�����硰�����ѧ�ƿ�����Ա�����п��н��������ڼ����㷨��ÿ���зֳ��Կ��зֻ�����������10%��Ϊ��������
	  5. �ڲ������������涨����������ض��󣬲������Ӧ����Ϣ��

*˼·��1.������Ȼ���нӿڼ̳п��нӿڣ�����������ÿ�βμӹ���ѧ���������û�Ծ�� �� ����ѧ����Ծ�ȷ�����ÿ��μӴ�������ÿ�����û�Ծ�ȣ���
	   2.��������ͽ�ѧ�ӿڼ̳н�ѧ��λ�ӿڣ�����������ÿ��μӽ��л���û�Ծ�� �� ���л�Ծ�ȷ�����ÿ��μӽ��л����������ÿ�βμӽ��л������û�Ծ�ȣ���
	   3.����������ʦ�࣬ʵ����Ȼ���иڽӿںͽ����ͽ�ѧ�ӿڡ�
	   4.��������ѧ�ƿ�����Ա�ࡢͨ��ѧ�ƿ�����Ա�ࡢ����ѧ�ƿ�����Ա��Ϳ���ѧ�ƿ�����Ա�࣬ʵ�ֿ��иڽӿڣ����㽱��������ͬ��
	   5.����������������Ӧ���������ʾ��

*ʵ�֣�1.interface NaturalScientific extends ScientificResearch
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


interface ScientificResearch								//���нӿ�
{
	static int moneyPeerResearchGrade=1000;
	public double calculateResearchAllowance();
}

interface Teaching											//��ѧ�ӿ�
{
	static int moneyPeerTeachGrade=600;
	public int calculateTeachAllowance();
}

interface NaturalScientific extends ScientificResearch		//��Ȼ���нӿڼ̳п��нӿ�
{
	static int NaturalLivenessEveryTime=1000;
	public int calNaturalMoneyEveryYear();
}
interface TeachingManagement extends Teaching			//��ѧ���нӿڼ̳н�ѧ�ӿ�
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
	//ѧ�š����Ų��ṩ�޸ķ���
	public void setResearchGrade(int ResearchGrade) {		//˽�б����ṩ�Ķ���ӿ�
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
	public ComputerTeacher() {										//Ĭ�Ϲ��캯��		
	}
	public ComputerTeacher(String TeacherName) {					//��д���캯��
		WorkNumber=++WORKNUMBER;
		this.TeacherName=TeacherName;
	}
	public double calculateResearchAllowance() {				//ʵ�ֽӿں���
		return moneyPeerResearchGrade*ResearchGrade;
	}
	public int calculateTeachAllowance() {					//ʵ�ֽӿں���
		return moneyPeerTeachGrade*TeachGrade;
	}
	public int calNaturalMoneyEveryYear() {					//ʵ�ּ�����Ȼ���н����ӿں���
		return TimeOfNatural*NaturalLivenessEveryTime;
	}
	public int calTeachingMoneyEveryYear() {					//ʵ�ּ����ѧ���н����ӿں���
		return TimeOfTeaching*TeachingLivenessEveryTime;
	}

	public String toString() {								//��дtoString����
		return (" Name: "+getTeacherName()+"\t WorkNumber: "+new java.text.DecimalFormat("000000").format(getWorkNumber())+"\n ResearchAllowance: "+calculateResearchAllowance()+"\t TeachAllowance: "+calculateTeachAllowance()+"\n NaturalMoneyEveryYear: "+calNaturalMoneyEveryYear()+"\t TeachingMoneyEveryYear: "+calTeachingMoneyEveryYear());
	}
}
class ComputerSciencePerson implements ScientificResearch			//���������ʵ�ֿ��нӿ�
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
	public ComputerSciencePerson() {										//Ĭ�Ϲ��캯��		
	}
	public ComputerSciencePerson(String ComputerScienceName,int age,String major,int ResearchGrade) {					//��д���캯
		this.ComputerScienceName=ComputerScienceName;
		this.age=age;
		this.major=major;
		this.ResearchGrade=ResearchGrade;
	}

	public double calculateResearchAllowance() {				//ʵ�ֽӿں���
		return moneyPeerResearchGrade*ResearchGrade*(1+0.1);
	}

	public String toString() {								//��дtoString
		return (" Name: "+getComputerScienceName()+"\t age: "+getAge()+"\t Major: "+getMajor()+"\n ResearchAllowance: "+calculateResearchAllowance());
	}

}

class CommunicationSciencePerson implements ScientificResearch			//ͨ�ſ���ʵ�ֿ��нӿ�
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
	public CommunicationSciencePerson() {										//Ĭ�Ϲ��캯��		
	}
	public CommunicationSciencePerson(String CommunicationScienceName,int age,String major,int ResearchGrade) {					//��д���캯
		this.CommunicationScienceName=CommunicationScienceName;
		this.age=age;
		this.major=major;
		this.ResearchGrade=ResearchGrade;
	}

	public double calculateResearchAllowance() {				//ʵ�ֽӿں���
		return moneyPeerResearchGrade*ResearchGrade*(1+0.5);
	}

	public String toString() {								//��дtoString
		return (" Name: "+getCommunicationScienceName()+"\t age: "+getAge()+"\t Major: "+getMajor()+"\n ResearchAllowance: "+calculateResearchAllowance());
	}

}

class ElectronSciencePerson implements ScientificResearch			//���ӿ���ʵ�ֿ��нӿ�
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
	public ElectronSciencePerson() {										//Ĭ�Ϲ��캯��		
	}
	public ElectronSciencePerson(String ElectronScienceName,int age,String major,int ResearchGrade) {					//��д���캯
		this.ElectronScienceName=ElectronScienceName;
		this.age=age;
		this.major=major;
		this.ResearchGrade=ResearchGrade;
	}

	public double calculateResearchAllowance() {				//ʵ�ֽӿں���
		return moneyPeerResearchGrade*ResearchGrade*(1+0.3);
	}

	public String toString() {								//��дtoString
		return (" Name: "+getElectronScienceName()+"\t age: "+getAge()+"\t Major: "+getMajor()+"\n ResearchAllowance: "+calculateResearchAllowance());
	}

}

class ControlSciencePerson implements ScientificResearch			//���ƿ���ʵ�ֿ��нӿ�
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
	public ControlSciencePerson() {										//Ĭ�Ϲ��캯��		
	}
	public ControlSciencePerson(String ControlScienceName,int age,String major,int ResearchGrade) {					//��д���캯
		this.ControlScienceName=ControlScienceName;
		this.age=age;
		this.major=major;
		this.ResearchGrade=ResearchGrade;
	}

	public double calculateResearchAllowance() {				//ʵ�ֽӿں���
		return moneyPeerResearchGrade*ResearchGrade*(1+0.7);
	}

	public String toString() {								//��дtoString
		return (" Name: "+getControlScienceName()+"\t age: "+getAge()+"\t Major: "+getMajor()+"\n ResearchAllowance: "+calculateResearchAllowance());
	}

}

class Teacher implements ScientificResearch,Teaching				//��ʦ��  ʵ�ֿ��С���ѧ�ӿ�
{
	public static int WORKNUMBER=0;							//��̬���������ɹ���
	private String TeacherName;
	private int WorkNumber;
	private int ResearchGrade;
	private int TeachGrade;

	//ѧ�š����Ų��ṩ�޸ķ���
	public void setResearchGrade(int ResearchGrade) {		//˽�б�������ӿ�
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

	public Teacher() {										//Ĭ�Ϲ��캯��		
	}
	public Teacher(String TeacherName) {					//��д���캯��
		WorkNumber=++WORKNUMBER;
		this.TeacherName=TeacherName;
	}

	public double calculateResearchAllowance() {				//ʵ�ֽӿں���
		return moneyPeerResearchGrade*ResearchGrade;
	}
	public int calculateTeachAllowance() {					//ʵ�ֽӿں���
		return moneyPeerTeachGrade*TeachGrade;
	}

	public String toString() {								//��дtoString
		return (" Name: "+getTeacherName()+"\t WorkNumber: "+new java.text.DecimalFormat("000000").format(getWorkNumber())+"\n ResearchAllowance: "+calculateResearchAllowance()+"\t TeachAllowance: "+calculateTeachAllowance());
	}
}

public class TeacherPro
{
	public static void main(String[] args) {				//��ں���
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
