package cn.buaa.hubert;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MyExecutor {

	private static final int NTHREADS = 100;
	
//	private Semaphore sem = new Semaphore(1);
	
	/**
	 * newFixedThreadPool 
	 * corePoolSize = NTHREADS;
	 * maximumPoolSize = NTHREADS;
	 * keepAliveTime = 0L;
	 * unit = TimeUnit.SECONDS
	 * workQueue = LinkedBlockingQueue<Runnable>;
	 * 
	 */
	private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);
	
	/**
	 * newCachedThreadPool 
	 * corePoolSize = 0;
	 * maximumPoolSize = Integer.MAX_VALUE;
	 * keepAliveTime = 60L;
	 * unit = TimeUnit.SECONDS
	 * workQueue = SynchronousQueue<Runnable>
	 * 
	 */
	private static final ExecutorService cachExec = Executors.newCachedThreadPool();
	
	private static int corePoolSize = 3; //线程池中保存活的线程数， 包括空闲的线程数量
	private static int maximumPoolSize = 5;  //线程池中允许最大的线程数
	private static long keepAliveTime = 2;  //当线程数量大于corePoolSize的时候，在没有超过指定的时间内是不从线程池中将空闲线程删除的，如果超过此时间单位，则删除空闲线程
	private static TimeUnit unit  = TimeUnit.SECONDS; // 参数的时间单位
	private static BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(); // 用于保存任务的队列，只保存由Executor提交的Runnable任务
	
	private static final ThreadPoolExecutor poolExcutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	
	public static void main(String[] args) {
		
		TestRunable runnableOne = new TestRunable(1,"FristRunnable");
		TestRunable runnableTwo = new TestRunable(2,"SecondRunnable");
		TestCallable callableOne = new TestCallable(1,"FristCallable");
		TestCallable callableTwo = new TestCallable(2,"SecondCallable");
		
//		Runnable task  = new Runnable() {
//			@Override
//			public void run() {
//				System.out.println("start task");
//			}
//		};
		exec.execute(runnableOne);
		poolExcutor.execute(runnableTwo);
		Future<String> futureOne = cachExec.submit(callableOne);
		
		Future<String> futureTwo = poolExcutor.submit(callableTwo);
		try {
			System.out.println(futureOne.get());
			System.out.println(futureTwo.get(2, TimeUnit.SECONDS));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(poolExcutor.getCompletedTaskCount()+"-"+poolExcutor.getPoolSize());
		poolExcutor.shutdown();
		System.out.println(futureOne.cancel(true)+"-"+futureOne.isCancelled());
		
	}
}
