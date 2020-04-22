package com.megvii.springboot;

import com.megvii.springboot.controller.HelloController;
import com.megvii.springboot.mapper.UserInfoMapper;
import com.megvii.springboot.model.UserInfo;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;
import java.util.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootDemoApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@Before
	public void setup(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
//	@Test
//	public void testMvc() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON_UTF8))
//			// 判断返回状态 200 404
//			.andExpect(MockMvcResultMatchers.status().isOk())
//			// 返回值等于 什么
//			.andExpect(MockMvcResultMatchers.content().string("hello,springboot2.0!"));
//	}

//	@Test
//	public void testHello() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get(new URI("/hello")))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andDo(MockMvcResultHandlers.print());
//	}

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Test
	public void test() {
		List<UserInfo> users = userInfoMapper.selectUserList();
		System.out.println(users);
	}
	@Test
	public void testJava8(){

		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
		Collections.sort(names, (a, b) -> b.compareTo(a));
		Converter<String, Integer> converter = Integer::valueOf; //Java 8 允许你使用 :: 关键字来传递方法或者构造函数引用
		Integer converted = converter.convert("123");
		System.out.println(converted);   // 123
	}
}
