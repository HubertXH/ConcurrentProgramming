package cn.buaa.hubert;

public class Consumer implements Runnable{

	
	private Restaurant restaurant;
	
	public Consumer(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public void run() {
		while(true) {
			restaurant.get();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
