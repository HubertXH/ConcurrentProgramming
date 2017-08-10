package cn.buaa.singleton;

public class SingletonOfDCL {
	
	private volatile static SingletonOfDCL object;
	
	private SingletonOfDCL() {
		
	}
	
	public static SingletonOfDCL getInstance() {
		try {
			Thread.sleep(1000);
			if(object != null) {
				return object;
			}else {
				synchronized (SingletonOfDCL.class) {
					if(object == null) {
						object = new SingletonOfDCL();
					}
				}
			}
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		return object;
	}
	
}
