package com.test;

import java.util.ArrayList;
import java.util.List;


public class TestList {

	public static void main(String[] args) {
		
		List<String> list = new ArrayList();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		list.add("g");
		list.add("h");
		list.add("i");
		List list2 = new ArrayList();
		list2 = list.subList(1, 5);
		list2.add(0, "5");
		for (int i = 0; i < list2.size(); i++) {
			System.out.println(list2.get(i));
		}
	}

}
