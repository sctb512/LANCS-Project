/*
*Ŀ�꣺���ö��߳�ʵ�־žų˷���
*˼·�������˷����࣬�̳� Thread�ӿ� �����߳�ִ�еĴ���η��� public void run()�� ��ɶԽӿ��� run() ������ʵ��
*ʵ�֣�class MultiplicationTable extends Thread{}
	   
	   �˷���������ݣ�for (int i=1 ;i<=num ;i++ )
						{
							for (int j=1 ;j<=i ;j++ )
							{
								System.out.print(i+"*"+j+"="+i*j+"  ");
							}
							System.out.println();
						}
@author:cwnu-abin
*/

public class MultiplicationTableTest
{
	public static void main(String[] args) 
	{
		System.out.println("Main thread starts .");
		MultiplicationTable Mthread = new MultiplicationTable(9);
		Mthread.start();
		System.out.println("Main thread ends .");
	}
}
class MultiplicationTable extends Thread
{
	private int num;
	public MultiplicationTable(int num) {
		this.num=num;
	}
	public void run() {
		System.out.println("New thread starts .");
		for (int i=1 ;i<=num ;i++ )
		{
			for (int j=1 ;j<=i ;j++ )
			{
				System.out.print(i+"*"+j+"="+i*j+"  ");
			}
			System.out.println();
		}
		System.out.println("New thread ends .");
	}
}