# ConcurrentProgramming

Note Of Learning
# 1、currentThread() 
返回正在调用代码的线程信息。
# 2、start and run ：
    start 方法的作用是启动一个新的线程，线程会执行run方法，start方法不可以重复调用
    run 方法与普通成员方法一样可以重复调用，单独调用run方法会在当前线程中调用run方法，并不会新开启一个线程。
    start 源码：
    ` public synchronized void start(){
      //  线程的状态，判断线程是否已经启动，0为没有启动
      if(threadStatus !=0){
           throw new IllegalThreadStateException();
          }
      group.add(this);
      boolean started = false;
      try {
            // 新建线程并启动
            start0();
            started = true;
        } finally {
            try {
                if (!started) {
                    group.threadStartFailed(this);
                }
            } catch (Throwable ignore) {
                /* do nothing. If start0 threw a Throwable then
                  it will be passed up the call stack */
            }
        }
    }

    run 源码：
    public void run（）{
      // target是Runnable 类型的变量
      if（target != null ）{
         target.run();
      }
    }`
   
# 3、线程的停止：
使用 interrupt 方法来中断线程
使用stop方法来强制线程终止（过期作废）（使用stop方法后会对锁进行解锁操作，可能会导致同步的处理出现异常，出现数据不一致的情况）  
## interrupt 使线程中断
Thread 类下面的3个方法：
### pubic void interrupt(){}  中断线程，
如果是线程自己调用interrupt方法则允许中断，如果是别的线程调用该方法则，先检查权限。如果线程被调用的wait,join,sleep等方法所阻塞则，则中断的状态请会被清除，并且收到一个InterruptedException的异常
如果线程是被IO操作所阻塞，则IO通道将会被关闭，线程将被设置为中断状态，并且收到一个java.nio.channels.ClosedByInterruptException异常
### public static boolean interrupted()方法
测试当前的线程是否中断，并且清楚线程的中断状态。
### public boolean isInterrupted()
测试当前线程是否已经被中断，该方法不会对线程的中断状态作出任何的改变。
若调用了Thread.interrupt方法在run方法中并没有相应的处理即抛出interrupedException异常则线程继续运行。  
可以使用interrupt和return来是线程停止。（不建议使用该方法）
 
# 线程暂停：
## suspend（已被抛弃）
First,Determines if the currently running thread has permission to modify this thread
If the thread is alive, it is suspended and makes no further progress unless and until it is resumed.
## resume （已被抛弃）
First,Determines if the currently running thread has permission to modify this thread
if the thread is alive but suspended,it is resume and is permitted to make progress in its execution
### The weakness Of suspend
It is easily to make the common resource locked by one thread,And no other thread can access in if the thread is suspended.Unless the thread resume and unlock the resource.

# yield:
## public static native void yield();
A hint to the scheduler that the current thread is willing to yield its current user of a proecssor. The scheduler is free to ignore this hint.

# Daemon:(守护线程)
*    There are two kinds of thread: UserThread,DaemonThread
*    The java Virtual Machine exits when the only threads running are all daemon threads.
*    setDaemon can marks this thread as a daemon thread.
*    setDaemon() is used must before Thread.start() or a IllegalThreadStateException() will be throwed
*    the thread was created in Deamon which are daemon thread
*    This thread is forbidden access to the resource ,because we don't what's time the thread was interrupted.

# Join:
1.join(){join(0)}-Waits for this thread to die;
2.join(Long){}-Waits at most Long milliseconds for this thread to die.A timeout of Long means to wait forever
3.join(Long,Int){}-Wait at most Long milliseconds plus Int for this thread to die.

# Volatile
1.volatile关键字是synchronized关键字的轻量级实现;
2.volatile关键字只能用于修饰变量;
3.volatile关键字保证数据的可见行，而不保证数据的原子性
4.线程访问volatile关键字不会阻塞，而访问synchronized关键字会形成阻;
5.synchronzed关键字可以保证原子性和间接的可见性;

# 原子类：
AtomicBoolean
AtomicInteger
AtomicLong
AtomicReference
若使用原子类进行操作则其操作为原子性，而如果有多个原子性操作则必须对多个原子操作进行同步。


