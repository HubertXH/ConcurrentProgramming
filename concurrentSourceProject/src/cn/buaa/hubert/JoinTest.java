package cn.buaa.hubert;

public class JoinTest implements Runnable{

	private String name;
	
	public JoinTest(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Thread named:"+name+" start");
			Thread.sleep(1000);
			System.out.println("Thread named:"+name+" end ");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
