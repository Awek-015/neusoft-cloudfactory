package cn.edu.neu.cloudfactory.controller;

import cn.edu.neu.cloudfactory.bean.Role;
import cn.edu.neu.cloudfactory.bean.User;
import cn.edu.neu.cloudfactory.dao.UserDao;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserDao userDao;


    @RequestMapping("/au")
    public String getList(@RequestParam("s") String s){
        String s1="%"+s+"%";
        List<User> userList = userDao.getAllUser(s1.trim());
        List<Role> roleList = userDao.getAllRole();
        HashMap<String,Object> res = new HashMap<>();
        res.put("userList",userList);
        res.put("roleList",roleList);
        String str= JSON.toJSONString(res);
        return str;
    }

    @RequestMapping("/audelete")
    public String deleteById(@RequestParam("user_id") int user_id){
        int i = userDao.deleteById(user_id);
        return i>0? "success" : "error";
    }
}
