package duty.service;
import java.util.List;
import duty.entity.User;

public interface UserService {

	public User getUserById(int id);

	public List<User> getUserList(String userName,String realName);

	public Boolean login(String userName, String passWord);
}