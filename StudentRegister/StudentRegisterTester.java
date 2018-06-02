public class StudentRegisterTester 
{
	public static void main(String[] args) 
	{
		System.out.println();
		StudentRegister SRtest1 = new StudentRegister();
		Student stu_01 = new Student("zhangsan");
		SRtest1.Register(stu_01);
		SRtest1.start();

		StudentRegister SRtest2 = new StudentRegister();
		Student stu_02 = new Student("lisi");
		SRtest2.Register(stu_02);
		SRtest2.start();

		StudentRegister SRtest3 = new StudentRegister();
		Student stu_03 = new Student("wangwu");
		SRtest3.Register(stu_03);
		SRtest3.start();

		StudentRegister SRtest4 = new StudentRegister();
		Student stu_04 = new Student("zhaoliu");
		SRtest4.Register(stu_04);
		SRtest4.start();

		StudentRegister SRtest5 = new StudentRegister();
		Student stu_05 = new Student("abin");
		SRtest5.Register(stu_05);
		SRtest5.start();
	}
}

class StudentNum
{
	int size;
	public StudentNum(int size)
	{
		this.size=size;
	}
}

class Student
{
	private String name;
	static int NOW_NUMBER = 0;
	private int number;
	public Student(String name) {
		this.name=name;
		number = ++NOW_NUMBER;
	}
	public int getNumber() {
		return number;
	}
	public String getName() {
		return name;
	}
	public String toString() {
		return ("Welcome ! "+name+" You are the "+number+"th student . your number is "+new java.text.DecimalFormat("000000").format(number));
	}
}

class StudentRegister extends Thread
{
	StudentNum SN = new StudentNum(1000);
	Student stu;
	public void Register(Student stu) {
		this.stu=stu;
	}
	public void run() {
		synchronized(SN) {
			System.out.println("Thread "+Thread.currentThread().getName()+" is starts .");
			if(stu.getNumber()<SN.size)
			{
				System.out.println(stu);
			}
			System.out.println("Thread "+Thread.currentThread().getName()+" is ends .");
		}
	}	
}