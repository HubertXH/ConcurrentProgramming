package cn.buaa.hubert;

import java.util.concurrent.Callable;

public class TestCallable implements Callable<String> {
	
	private int num;
	private String name;
	
	public TestCallable(int num,String name) {
		this.name = name;
		this.num = num;
	}
	

	@Override
	public String call() throws Exception {
		Thread.sleep(10000);
		System.out.println("");
		return "num:"+num+";name:"+name;
	}

}
