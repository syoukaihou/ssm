package com.snsprj.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.snsprj.base.BaseJunit4Test;

public class TestMailService extends BaseJunit4Test {

	@Autowired
	private IMailService iMailService;

	@Test
	public void testSendMail() {

		String mail = "zhaijt@thundersoft.com";
		String[] receiver = { mail,"sssss@thundersoft.com" };
		String subject = "重置密码";

		Map<String, String> map = new HashMap<>();
		map.put("username", mail);
		map.put("url", "http://www.baidu.com");
		map.put("admin", "admin@quarkdata.com");

		String templateName = "userActive.ftl";

		Integer resultCode = iMailService.sendMailByTemplate(receiver, subject, map, templateName);

		System.out.println(resultCode);
	}
}
