package cn.buaa.singleton;

import cn.buaa.hubert.Consumer;
import cn.buaa.hubert.Restaurant;

public class EnumSingleton {
	
	public enum ObjectEnum{
		objectFactory;
		private Consumer consumer;
		private ObjectEnum() {
			consumer = new Consumer(new Restaurant());
		}
		
		public Consumer getConsumer() {
			return consumer;
		}
	}
	
	public static Consumer getConsumer() {
		return ObjectEnum.objectFactory.getConsumer();
	}

}
