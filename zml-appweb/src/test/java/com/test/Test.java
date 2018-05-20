package com.test;

import com.jce.framework.core.util.PasswordUtil;

public class Test {

	public static void main(String[] args) {
		String password = PasswordUtil.encrypt("111955", "123456", PasswordUtil.getStaticSalt());
		System.out.println(password);
	}

}
