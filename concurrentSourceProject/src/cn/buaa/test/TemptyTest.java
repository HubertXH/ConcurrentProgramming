package cn.buaa.test;

import java.util.ArrayList;
import java.util.List;

public class TemptyTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>(5);
		for (int i = 0; i < 6; i++) {
			list.add("x");
		}
		System.out.println(list.size());
		System.out.println(list.isEmpty());
	}
	
	

}
