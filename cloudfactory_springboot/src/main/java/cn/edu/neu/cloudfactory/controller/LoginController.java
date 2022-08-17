package cn.edu.neu.cloudfactory.controller;

import cn.edu.neu.cloudfactory.bean.User;
import cn.edu.neu.cloudfactory.dao.UserDao;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class LoginController {
    @Autowired
    private UserDao userDao;

    @RequestMapping("/login")
    public String login(@RequestBody User user){
        String flag="error";
        User user1=userDao.getByMessage(user.getUser_account(),user.getUser_password());
//        System.out.println("登录的user:"+user1);
        HashMap<String, Object> res = new HashMap<>();
        if(user1!=null){
            flag = "ok";
        }
        res.put("flag",flag);
        res.put("user",user1);
        String res_json = JSON.toJSONString(res);

        return res_json;
    }
    /*@RequestMapping("register")
    public String register(@RequestParam("user_account")String user_account,@RequestParam("user_password") String user_password,@RequestParam("user_name")String suer_name)
*/
    @RequestMapping("register")
    public String register(@RequestBody HashMap map){
        String res ="";
        map.put("res",res);
        this.userDao.register(map);
        String str= (String) map.get("res");
        return str;
    }
}
