package cn.buaa.hubert;

public class ConsumerBlock implements Runnable{
	
	
	private  BlockQueueRestaurant rest;
	
	public ConsumerBlock(BlockQueueRestaurant rest) {
		this.rest = rest;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				rest.get();
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
