package cn.buaa.hubert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DoRun {

	
	
	public static void main(String[] args) {
		Restaurant restaurant = new Restaurant();
		
		List<Consumer> consumerList = new ArrayList<>();
		
		List<Productor> productorList = new ArrayList<>();
		
		for(int i = 0;i<20;i++) {
			consumerList.add(new Consumer(restaurant));
		}
		
		for(int i = 0;i<4;i++) {
			productorList.add(new Productor(restaurant));
		}
		
		ExecutorService service = Executors.newCachedThreadPool();
		
		for(int i = 0;i<20;i++) {
			service.submit(consumerList.get(i));
		}
		for(int i = 0;i<4;i++) {
			service.submit(productorList.get(i));
		}
		
		service.shutdown();
	}
}
