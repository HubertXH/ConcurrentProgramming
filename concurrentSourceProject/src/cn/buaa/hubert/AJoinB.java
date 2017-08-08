package cn.buaa.hubert;

public class AJoinB {

	private static int a = 0;

	public static void increase() {
		a++;
	}
	
	public static void main(String[] args) {
		
		Thread A = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while(a<20) {
						increase();
						System.out.println(Thread.currentThread().getName()+"-Thread:"+"THE NUM="+a);
						Thread.sleep(500);
					}
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread B = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					A.join();
					while(a<40) {
						increase();
						System.out.println(Thread.currentThread().getName()+"-Thread:"+"THE NUM="+a);
						Thread.sleep(500);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		A.setName("A");
		B.setName("B");
		A.start();
		B.start();
		try {
			B.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"-Thread"+"THE END");
	}
	
}
