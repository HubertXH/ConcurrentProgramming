package cn.buaa.hubert;

public class RunBlockProductorAndConsumer {

	
	
	public static void main(String[] args) {
		BlockQueueRestaurant rest = new BlockQueueRestaurant();
		ConsumerBlock cb = new ConsumerBlock(rest);
		ProductorBlock pb = new ProductorBlock(rest);
		
		for(int i = 0;i<5;i++) {
			new Thread(cb).start();
			new Thread(pb).start();
		}

	}

}
