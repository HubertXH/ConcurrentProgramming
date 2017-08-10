package cn.buaa.hubert;

public class ProductorBlock implements Runnable{
	
	private  BlockQueueRestaurant rest;
	
	public ProductorBlock(BlockQueueRestaurant rest) {
		this.rest = rest;
	}

	@Override
	public void run() {
		try {
			while(true) {
				rest.put((int)(Math.random()*100)+"");
				Thread.sleep(1000);
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	
	
	
}
