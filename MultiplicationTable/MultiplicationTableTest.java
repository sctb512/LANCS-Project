/*
*目标：利用多线程实现九九乘法表
*思路：创建乘法表类，继承 Thread接口 将新线程执行的代码段放在 public void run()； 完成对接口中 run() 方法的实现
*实现：class MultiplicationTable extends Thread{}
	   
	   乘法表核心内容：for (int i=1 ;i<=num ;i++ )
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