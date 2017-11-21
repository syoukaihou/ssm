package com.snsprj.test;

import java.util.Date;

import org.junit.Test;

public class TestDate {

	@Test
	public void testGetTime(){
		Date now = new Date();
		System.out.println(now.getTime());
	}
}
