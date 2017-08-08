package cn.buaa.hubert;

public class TestRunable implements Runnable {

	private int num;
	private String name;
	
	public TestRunable(int num,String name) {
		this.name = name;
		this.num = num;
	}

	@Override
	public void run() {
		System.out.println("task "+num+";name:"+name+"started");
	}
	
	
	
}
