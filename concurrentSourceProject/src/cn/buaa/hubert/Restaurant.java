package cn.buaa.hubert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Restaurant {
	
	private final Semaphore consumerSemaphore = new Semaphore(6);
	private final Semaphore productorSemaphore = new Semaphore(3);
	private final ReentrantLock lock = new ReentrantLock();
	private volatile Condition consumerCondition = lock.newCondition();
	private volatile Condition productorCondition = lock.newCondition();
	private volatile List<String> containerList = new ArrayList<String>(5);
	
	public boolean isEmpty() {
		boolean flag  = false;
		if(containerList.isEmpty()) {
			flag = true;
		}
		return flag;
	}
	
	public boolean isFull() {
		boolean flag  = false;
		if (containerList.size() >= 5) {
			flag = true;
		}
		return flag;
	}
	
	public void put() {
		try {
			System.out.println("productor num:"+productorSemaphore.availablePermits());
			productorSemaphore.acquire();
			lock.lock();
			while(isFull()) {
				productorCondition.await();
			}
			int x = (int)(Math.random()*100);
			containerList.add("菜品"+x);
			System.out.println(Thread.currentThread().getName()+"生产了："+"菜品"+x);

			consumerCondition.signalAll();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
			productorSemaphore.release();
		}
	}
	
	public void get() {
		try {
			System.out.println("consumer num:"+consumerSemaphore.availablePermits());
			consumerSemaphore.acquire();
			lock.lock();
			while(isEmpty()) {
				consumerCondition.await();			
			}
			for (int i = 0;i<containerList.size();i++) {
				if(containerList.get(i) != null) {
					System.out.println(Thread.currentThread().getName()+"消费了："+containerList.get(i));
					containerList.remove(i);
					break;
				}
			}
			productorCondition.signalAll();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
			consumerSemaphore.release();
		}
	}
	
	
	

}
