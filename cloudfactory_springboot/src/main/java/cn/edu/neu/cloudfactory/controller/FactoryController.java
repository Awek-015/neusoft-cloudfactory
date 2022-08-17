package cn.edu.neu.cloudfactory.controller;

import cn.edu.neu.cloudfactory.bean.Factory;
import cn.edu.neu.cloudfactory.bean.User;
import cn.edu.neu.cloudfactory.dao.FactoryDao;
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
public class FactoryController {
    @Autowired
    FactoryDao factoryDao;
    @Autowired
    UserDao userDao;

    @RequestMapping("/af")
    public String getList(@RequestParam("s") String s){
        String s1 = '%'+s+'%';
        List<Factory> factoryList = factoryDao.getAllFactory(s1.trim());
        List<User> userList = userDao.getAllUser("%%");
        HashMap<String,Object> res = new HashMap<>();
        res.put("userList",userList);
        res.put("factoryList",factoryList);
        String str = JSON.toJSONString(res);
        return str;
    }
    @RequestMapping("/afswitch")
    public void updateState(@Param("f_id") int f_id,@Param("f_state") String f_state){
         factoryDao.updateState(f_id,f_state);
    }
}
