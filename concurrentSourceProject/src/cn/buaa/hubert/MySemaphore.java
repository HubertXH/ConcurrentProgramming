package cn.buaa.hubert;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class MySemaphore {

	public static void main(String[] args) {
		Obj obj = new Obj();
		ServiceThreadA a = new ServiceThreadA(obj);
		ServiceThreadA b = new ServiceThreadA(obj);
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(a);
		executorService.execute(b);
		executorService.shutdown();
		
	}

	static class ServiceThreadA implements Runnable {

		private Obj obj;

		public ServiceThreadA(Obj obj) {
			this.obj = obj;
		}

		@Override
		public void run() {
				obj.TestMethod();
		}
	}

	static class Obj {
//		private int num;
//		public Obj(int num) {
//			this.num = num;
//		}
		Semaphore semaphore = new Semaphore(1);
		public void TestMethod() {
			try {
				int i = semaphore.availablePermits();
				System.out.println("availablePermits:"+i);
				semaphore.acquire();
				System.out.println(Thread.currentThread().getName() + " begin start;" + System.currentTimeMillis());
				Thread.sleep(5000);
				System.out.println(Thread.currentThread().getName() + " end;" + System.currentTimeMillis());
				semaphore.release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
