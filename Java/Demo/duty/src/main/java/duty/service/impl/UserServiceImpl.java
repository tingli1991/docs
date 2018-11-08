package duty.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duty.dao.UserMapper;
import duty.entity.User;
import duty.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	// Alt+Shift+S -> Generate Getters and Setters
	@Autowired
	private UserMapper userMapper;

	public User getUserById(int id) {
		return userMapper.selectByPrimaryKey(1);
	}

	public List<User> getUserList(String searchText,String realName) {
        return userMapper.getUserList(searchText,realName);
	}

	public Boolean login(String userName, String passWord) {
		User user = userMapper.getUserByUserNameAndPwd(userName, passWord);
		if (user != null) {
			return true;
		}
		return false;
	}
}