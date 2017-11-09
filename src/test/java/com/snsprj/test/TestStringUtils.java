package com.snsprj.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class TestStringUtils {

	@Test
	public void testSubstringBefore(){
		
		String oldStr = "同福客栈酒店管理有限公司/财务部/后勤部/我改的/88888888";
		String ret = StringUtils.substringBefore(oldStr,"88888888");
		
		System.out.println(ret);
	}
	
	@Test
	public void testSubstringAfterLast(){
		String email = "asfg@daf@outlook.com.cn";
		
		String ret1 = StringUtils.substringAfterLast(email, "@");
		String ret2 = StringUtils.substringAfter(email, "@");
		
		System.out.println("ret1=" + ret1);
		System.out.println("ret2=" + ret2);
	}
}
