package cn.buaa.hubert;

public class BasicKnowledge {
	
	
	
	public static void main(String[] args) {
		JoinTest one = new JoinTest("one");
		JoinTest two = new JoinTest("two");
		
		Thread a = new Thread(one);
		Thread b = new Thread(two);
		
		a.start();
		b.start();
		try {
			a.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("The End");
	}

}
