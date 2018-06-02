
class Student		//xueshenglei
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

class StuMonitor extends Student
{
	private String reponsibility;
	public int setPole(double score) {
		return (int)score/8-5;					//how to rewrite ?
	}
	public StuMonitor(String name,int age,double chinese,double math) {
		super(name,age,chinese,math);
		reponsibility = "monitor";
	}
	public String getReponsibility() {
		return reponsibility;
	}
	public String toString () {
		return ("Monitor: "+getName()+"\t number:"+new java.text.DecimalFormat("000000").format(getNumber())+"\t age:"+getAge()+"\t grade:"+sumScore());
	}
}

class TwoStudent
{
	private Student student_01;
	private Student student_02;
	public TwoStudent(Student student_01,Student student_02) {
		this.student_01=student_01;
		this.student_02=student_02;
	}
	public int getMathAverPole() {
		return (int)(student_01.getMathPole()+student_02.getMathPole())/2;
	}
	public int getChineseAverPole() {
		return (int)(student_01.getChinesePole()+student_02.getChinesePole())/2;
	}
}

public class StudentDemo 
{

	public static void main(String[] args) 
	{
		Student stu_01 = new Student("zhansan",20,91,88);
		Student stu_02 = new Student("lisi",18,85,97);
		Student stu_03 = new Student("wangwu",19,93,82);
		Student stu_04 = new Student("zhaoliu",17,82,95);
		Student stu_05 = new Student("liqi",21,94,90);
		
		Student stuArr[] = new Student[5];
		stuArr[0]=stu_01;
		stuArr[1]=stu_02;
		stuArr[2]=stu_03;
		stuArr[3]=stu_04;
		stuArr[4]=stu_05;
		
		System.out.println();
		for (int i=0;i<stuArr.length;i++ )
		{
			System.out.println(stuArr[i]);
		}
		
		System.out.println();
		sort(stuArr);
		
		System.out.println("After Sort :");
		for (int i=0;i<stuArr.length;i++ )
		{
			System.out.println(stuArr[i]);
		}
		
		System.out.println();
		StuMonitor monitorTest = new StuMonitor("banzhang",21,95,98);
		System.out.println(monitorTest);

		//Equals Test

		Student equ_01=new Student("twostu_01",18,95,85);
		Student equ_02=new Student("twostu_01",18,95,85);

		if(equ_01.equals(equ_01))
		{
			System.out.println("the two demo is same !");
		}
		else
		{
			System.out.println("the two demo is not same !");
		}

		//TwoStudent Test
		Student twostu1=new Student("twostu_01",18,95,85);
		Student twostu2=new Student("twostu_01",21,92,87);
		
		TwoStudent team=new TwoStudent(twostu1,twostu2);

		System.out.println("the team's average Math Pole is : "+team.getMathAverPole()+"\n"+"the team's average Chinese Pole is : "+team.getChineseAverPole());
		
	}

	public static void sort (Student stuArray[]) {
		Student temp;
		for (int i=0;i<stuArray.length-1;i++ )
		{
			for (int j=i+1;j<stuArray.length;j++ )
			{
				if (stuArray[i].sumScore()>stuArray[j].sumScore())
				{
					
					temp = stuArray[i];
					stuArray[i]= stuArray[j];
					stuArray[j]= temp;
				}
			}
		}		
	}
}