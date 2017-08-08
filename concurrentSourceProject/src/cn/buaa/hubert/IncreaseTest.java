package cn.buaa.hubert;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IncreaseTest {

	public static void main(String[] args) {
		EnObj obj = new EnObj();
		ExecutorService executorService = Executors.newCachedThreadPool();
		RunOne one = new RunOne(obj);
		RunTwo two = new RunTwo(obj);
		executorService.execute(one);
		executorService.execute(two);
		executorService.shutdown();
		System.out.println(executorService.isShutdown());

	}

}

class EnObj {

	public int a;

	public void increace() {
		a++;
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "-Thread" + "NUM=" + a);
	}

}

class RunOne implements Runnable {

	private EnObj obj;

	public RunOne(EnObj obj) {
		this.obj = obj;
	}

	@Override
	public void run() {
		try {
			synchronized (obj) {
				System.out.println("Thread one");
				while (obj.a >= 0 && obj.a < 100) {
					obj.increace();
				}
				obj.wait();
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		obj.notifyAll();
	}
}

class RunTwo implements Runnable {

	private EnObj obj;

	public RunTwo(EnObj obj) {
		this.obj = obj;
	}

	@Override
	public void run() {
		try {
			synchronized (obj) {
				System.out.println("Thread Two");
				while (obj.a >= 100 && obj.a < 200) {
					obj.increace();
				}
				obj.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		obj.notifyAll();
	}
}
