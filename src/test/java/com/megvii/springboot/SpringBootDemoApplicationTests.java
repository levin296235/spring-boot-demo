package com.megvii.springboot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.megvii.springboot.mapper.SysUserMapper;
import com.megvii.springboot.model.SysUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.net.URI;
import java.util.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootDemoApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@Autowired
	private RedisTemplate<String, String> strRedisTemplate;
	@Autowired
	private RedisTemplate<String, Serializable> serializableRedisTemplate;

	@BeforeEach
	public void setup(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	@Test
	public void testMvc() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON_VALUE))
			// 判断返回状态 200 404
			.andExpect(MockMvcResultMatchers.status().isOk())
			// 返回值等于 什么
			.andExpect(MockMvcResultMatchers.content().string("hello,springboot2.0!"));
	}

	@Test
	public void testHelloController() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(new URI("/hello")))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Autowired
	private SysUserMapper userInfoMapper;

	@Test
	public void testPageueryUserInfo() {
		Page<SysUser> page = userInfoMapper.selectPage(new Page<>(1,5), new QueryWrapper<SysUser>().eq("sex", "男"));
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + page.toString());

		System.out.println("--------------------分隔符--------------------");
		Page<Map<String, Object>> page2 =userInfoMapper.selectMapsPage(new Page<>(1,5), new QueryWrapper<SysUser>().eq("sex", "男"));
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + page2.toString());
	}

	@Test
	public void testQueryUserInfo() {
//		List<UserInfo> users = userInfoMapper.selectUserList();
//		System.out.println(users);
		int count = userInfoMapper.selectCount(new QueryWrapper<SysUser>().eq("sex", "男"));
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + count);

		System.out.println("--------------------分隔符--------------------");
		QueryWrapper<SysUser> queryWrapper=new QueryWrapper<SysUser>();
		SysUser user=new SysUser();
		user.setPhone("13905535621");
		queryWrapper.setEntity(user);
		List<Object> objs= userInfoMapper.selectObjs(queryWrapper);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + objs.toString());

		System.out.println("--------------------分隔符--------------------");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("sex","男");
		List<SysUser> list = userInfoMapper.selectList(new QueryWrapper<SysUser>().allEq(map));
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + list.toString());

		System.out.println("--------------------分隔符--------------------");
		Map<String, Object> map2=new HashMap<String, Object>();
		map.put("sex","男");
		List<Map<String, Object>> list2 = userInfoMapper.selectMaps(new QueryWrapper<SysUser>().allEq(map));
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + list2.toString());

		System.out.println("--------------------分隔符--------------------");
		List<Integer> ids=new ArrayList<>();
		ids.add(1);
		ids.add(9);
		ids.add(10);
		List<SysUser> list3 = userInfoMapper.selectBatchIds(ids);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + list3.toString());
	}

	@Test
	public void testInsertUserInfo() {
		for(int i=10; i < 19; i++){
			SysUser u = new SysUser();
			u.setId(i);
			u.setUserName("liming" + i);
			boolean flag = userInfoMapper.insertUser(u);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + flag);
		}

	}

	@Test
	public void testUpdateUserInfo() {

		SysUser user= new SysUser();
		user.setPhone("13905535621");
		int result = userInfoMapper.update(user,new QueryWrapper<SysUser>().eq("user_name", "liudehua"));
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + result);
	}

	@Test
	public void testDeleteUserInfo() {
		boolean flag = userInfoMapper.deleteUserById(12);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + flag);
	}

	@Test
	public void testJava8(){
		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
		Collections.sort(names, (a, b) -> b.compareTo(a));
		Converter<String, Integer> converter = Integer::valueOf; //Java 8 允许你使用 :: 关键字来传递方法或者构造函数引用
		Integer converted = converter.convert("123");
		System.out.println(converted);   // 123
	}


	@Test
	public void testRedisString() {
		strRedisTemplate.opsForValue().set("xm", "levin296235");
		System.out.println(strRedisTemplate.opsForValue().get("xm"));
	}

	@Test
	public void testSerializable() {
		SysUser user=new SysUser();
		user.setId(1);
		user.setUserName("朝雾轻寒");
		user.setPhone("13905535621");
		serializableRedisTemplate.opsForValue().set("user", user);
		SysUser user2 = (SysUser) serializableRedisTemplate.opsForValue().get("user");
//		System.out.println("user:"+user2.getId()+","+user2.getUserName()+","+user2.getSex());
	}
}
