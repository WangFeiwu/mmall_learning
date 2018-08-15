package com.wfw.mmall.dao;
/**
* @author F7689334
* @version 创建时间:2018年8月15日 下午3:20:09
* 类说明 
*/

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wfw.mmall.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
public class UserMapperTest {

	@Autowired
	private UserMapper userMapper;
	
	public void testInsertSelective() {
		User user=new User();
		user.setUsername("wfw");
		user.setPassword("wfw");
		user.setEmail("wfw@qq.com");
		user.setPhone("13125131650");
		user.setQuestion("问题");
		user.setAnswer("答案");
		user.setRole(1);
		int a=userMapper.insertSelective(user);
		System.out.println(a);
	}
}
