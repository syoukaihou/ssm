package com.snsprj.test;

import org.junit.Test;

import com.snsprj.base.BaseJunit4Test;
import com.snsprj.properties.PropertyPlaceholder;

public class TestProperties extends BaseJunit4Test{

	@Test
	public void testGetProperties(){
		String username = PropertyPlaceholder.getProperty("mail.username");
		System.out.println(username);
		String redisHost = PropertyPlaceholder.getProperty("redis.host");
		System.out.println(redisHost);
	}
	
	
	
}
