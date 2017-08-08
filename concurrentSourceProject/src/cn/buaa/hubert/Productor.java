package cn.buaa.hubert;

public class Productor implements Runnable{

	
	private Restaurant restaurant;
	
	public Productor(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public void run() {
		while(true) {
			restaurant.put();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
