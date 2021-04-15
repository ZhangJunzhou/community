package com.zjz.community;

import com.zjz.community.dao.AlphaDao;
import com.zjz.community.dao.AlphaDaoImpl;
import com.zjz.community.dao.AlphaDaoMybatisImpl;
import com.zjz.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {
	// 用来管理bean,（1）该代码所在 包及其子包所有的类，（2）使用@controller 修饰的类；
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Test
	public void testApplicationContext(){
		System.out.println(applicationContext);

		AlphaDao alphaDao1 = applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao1.select());

		AlphaDao alphaDao2 = applicationContext.getBean("alphaDaoImpl", AlphaDao.class);
		System.out.println(alphaDao2.select());

	}

	@Test
	public void testBeanManagement(){
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);

		AlphaService alphaService2 = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
	}

	@Test
	public void testBeanConfig(){
		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	@Autowired
	@Qualifier("alphaDaoImpl")
	private AlphaDao alphaDao;
	@Autowired
	private AlphaDaoImpl alphaDaoImpl;
	@Autowired
	private AlphaDaoMybatisImpl alphaDaoMybatis;
	@Test
	public void testDI(){
		System.out.println(alphaDao);
		System.out.println(alphaDaoImpl);
		System.out.println(alphaDaoMybatis);
	}

}


