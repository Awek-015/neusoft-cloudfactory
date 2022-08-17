package cn.edu.neu.cloudfactory.dao;

import cn.edu.neu.cloudfactory.bean.Role;
import cn.edu.neu.cloudfactory.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {
    User getByMessage(@Param("user_account") String user_account,@Param("user_password")String user_password);
    List<User> getAllUser(@Param("s") String s);
    int deleteById(@Param("user_id") int user_id);
    List<Role> getAllRole();
    String register(Map map);
}
