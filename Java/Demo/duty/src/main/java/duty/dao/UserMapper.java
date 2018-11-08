package duty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import duty.entity.User;

public interface UserMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	List<User> getUserList(@Param("userName") String userName, @Param("realName") String realName);

	User getUserByUserNameAndPwd(@Param("userName") String userName,@Param("password") String password);
}