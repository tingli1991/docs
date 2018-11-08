package duty.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import duty.entity.User;
import duty.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {   
	@Autowired
	private UserService userService;
	
	@RequestMapping("/getUserInfo")
	public String getUserInfo(int id,HttpServletRequest request) {
		User user=userService.getUserById(id);
		request.setAttribute("user",user);
		return "getUserInfo";
	}
	
	@RequestMapping("/login")
	public Boolean login(String userName,String passWord) {
		return userService.login(userName, passWord);		
	}
}