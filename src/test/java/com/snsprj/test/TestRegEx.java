package com.snsprj.test;

import org.junit.Assert;
import org.junit.Test;

import com.snsprj.utils.RegularExpressionUtil;

/**
 * 测试正则表达式工具类
 * @author skh
 * @Date 2017年11月21日
 *
 */
public class TestRegEx {

	@Test
	public void testMail(){
		String mail = "xiaoA_011@baidu.com.cn";
		boolean result = RegularExpressionUtil.isMatch(mail, RegularExpressionUtil.REGEX_MIAL);
		Assert.assertEquals(result, true);
	}
}
