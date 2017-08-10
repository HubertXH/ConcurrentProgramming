package cn.buaa.hubert;

import java.util.concurrent.LinkedBlockingQueue;

public class BlockQueueRestaurant {
	
	private LinkedBlockingQueue<String> strQueue = new LinkedBlockingQueue<>(5);
	
	public void put(String str) throws InterruptedException {
		strQueue.put(str);
		System.out.println(Thread.currentThread().getName()+"-put some str;  The number of queue is"+strQueue.size());
	}
	
	public void get() throws InterruptedException {
		strQueue.take();
		System.out.println(Thread.currentThread().getName()+"-get some str;  The number of queue is"+strQueue.size());
	}
	

	
}
