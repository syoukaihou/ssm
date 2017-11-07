package com.snsprj.base;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml","classpath:spring-servlet.xml", 
	"classpath:spring-shiro.xml", "classpath:spring-mail.xml","classpath:spring-bean.xml"})
// 使用事务
@Transactional
@Rollback(true)
public class BaseJunit4Test {

}
