package duty.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import duty.entity.User;
import duty.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring.xml", "classpath*:/spring-mybatis.xml" })
public class TestMyBatis {

	@Autowired
	private UserService userService;

	@Test
	public void getUserByIdTest() {
		User user = userService.getUserById(1);
		System.out.println(user.getUsername());
	}

	@Test
	public void getUserListTest() {
		List<User> userList = userService.getUserList("zhangsan","张");
		for (User user : userList) {
			System.out.println("用户名："+user.getUsername() + "     真实姓名：" + user.getRealname());
		}
	}

	@Test
	public void loginTest() {
		Boolean logResult = userService.login("admin", "123456");
		System.out.println(logResult);
	}
}