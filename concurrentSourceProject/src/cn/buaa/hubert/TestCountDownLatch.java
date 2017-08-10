package cn.buaa.hubert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCountDownLatch {
	
	public static void main(String[] args) {
		
		Map<String, String> map = new ConcurrentHashMap<>();
		CountDownLatch arrived = new CountDownLatch(10);
		ExecutorService service = Executors.newCachedThreadPool();
		RunFactory runfactory = null;
		for(int i = 0;i<10;i++) {
			runfactory = new RunFactory(map, arrived);
			if (runfactory!= null) {
				service.execute(runfactory);
			}
		}
		try {
			arrived.await();
			System.out.println("The Size of Map:"+map.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		service.shutdown();
	}

}

class RunFactory implements Runnable{
	private Map<String, String> map;
	
	private CountDownLatch  countLatch;
	
	public RunFactory(Map<String, String> map,CountDownLatch latch) {
		this.map = map;
		this.countLatch = latch;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			map.put(Thread.currentThread().getName(),String.valueOf((int)Math.random()*1000));
			System.out.println(Thread.currentThread().getName()+"-arrived and put object in house");
			countLatch.countDown();
			System.out.println("count num = "+countLatch.getCount());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
