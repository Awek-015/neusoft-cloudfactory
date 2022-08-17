package cn.edu.neu.cloudfactory.controller;

import cn.edu.neu.cloudfactory.bean.Product_Type;
import cn.edu.neu.cloudfactory.dao.ProductTypeDao;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class Product_TypeController {

    @Autowired
    private ProductTypeDao productTypeDao;

    @CrossOrigin
    @RequestMapping("/apt")
    public String getList(@RequestParam("s") String s){
        String s1 = '%'+s+'%';
        List<Product_Type> list= productTypeDao.getAllProduct_Type(s1.trim());
//        System.out.println(s1);
        HashMap<String,Object> res = new HashMap<>();
        res.put("list",list);
//        System.out.println(list);
        String str=JSON.toJSONString(res);
//        System.out.println(str);
        return str;
    }
    @RequestMapping("/aptdelete")
    public String deleteById(@RequestParam("pt_id") int pt_id){
        int i = productTypeDao.deleteById(pt_id);
        return i>0? "success" : "error";
    }
    @RequestMapping("/aptadd")
    public String add(@RequestBody Product_Type product_type){
//        System.out.println(product_type);
        int i = productTypeDao.insert(product_type.getPt_name());
        return i>0? "success" :"error";
    }
    @RequestMapping("/aptupdate")
    public String update(@RequestBody Product_Type product_type){
        int i = productTypeDao.update(product_type.getPt_id(),product_type.getPt_name());
        return i>0? "success" :"error";
    }
}
