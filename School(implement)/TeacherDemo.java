/*
*Ŀ�꣺1.��дһ�����и�λ���ýӿڰ�����ÿ���зֽ������ľ�̬������������н����ķ�����
	   2.��дһ����ѧ��λ�ӿڣ��ýӿڰ�����ÿ��ѧ�ֽ������ľ�̬�����������ѧ�����ķ�����
	   2.����һ����У��ʦ�࣬��ʵ�ֿ��и�λ�ӿںͽ�ѧ��λ�ӿڣ����������ʦ��������ְ���š���ѧ�֡����з֡�
*˼·��1.����һ�����нӿ����һ����ѧ�ӿ��࣬������̬�����ͳ��󷽷���
	   2.�����ʦ�࣬ʵ�ֿ��кͽ�ѧ��λ�ӿ�
	   3.����SchoolDemo��д�ӿں�����������ʦ���󣬲������ʾ��
*ʵ�֣�interface ScientificResearch {
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

interface ScientificResearch								//���нӿ�
{
	static int moneyPeerResearchGrade=1000;
	public int calculateResearchAllowance();
}

interface Teaching											//��ѧ�ӿ�
{
	static int moneyPeerTeachGrade=600;
	public int calculateTeachAllowance();
}

class Teacher implements ScientificResearch,Teaching		//��ʦ�࣬ʵ�ֿ��С���ѧ�ӿ�
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

	public int calculateResearchAllowance() {				//ʵ�ֽӿں���
		return moneyPeerResearchGrade*ResearchGrade;
	}
	public int calculateTeachAllowance() {					//ʵ�ֽӿں���
		return moneyPeerTeachGrade*TeachGrade;
	}

	public String toString() {								//��дtoString
		return (" Name: "+getTeacherName()+"\t WorkNumber: "+new java.text.DecimalFormat("000000").format(getWorkNumber())+"\n ResearchAllowance: "+calculateResearchAllowance()+"\t TeachAllowance: "+calculateTeachAllowance());
	}
}

public class TeacherDemo+
{
	public static void main(String[] args) {				//��ں���
		Teacher teacherTest = new Teacher("ZhangSan");
		teacherTest.setResearchGrade(10);
		teacherTest.setTeachGrade(6);
		System.out.println();
		System.out.println(teacherTest);
	}
}