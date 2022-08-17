package cn.edu.neu.cloudfactory.controller;

import cn.edu.neu.cloudfactory.bean.Product;
import cn.edu.neu.cloudfactory.bean.Product_Type;
import cn.edu.neu.cloudfactory.dao.ProductDao;
import cn.edu.neu.cloudfactory.dao.ProductTypeDao;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductDao productDao;
    @Autowired
    ProductTypeDao productTypeDao;

    @RequestMapping("/ap")
    public String getList(@Param("s") String s){
        String s1 = "%"+s+"%";
        List<Product> productList=productDao.getAllProduct(s1.trim());
        List<Product_Type> productTypeList = productTypeDao.getAllProduct_Type(null);
        HashMap<String,Object> res = new HashMap<>();
        res.put("productList",productList);
        res.put("productTypeList",productTypeList);
        String str= JSON.toJSONString(res);
        return str;
    }
    @RequestMapping("/apdelete")
    public String deleteById(@Param("p_id") int p_id){
        int i = productDao.deleteById(p_id);
        return i>0? "success" : "error";
    }
    @RequestMapping("/apadd")
    public String add(@RequestBody Product product){

        int i = productDao.insert(product.getP_num(),product.getP_name(),product.getP_type(),product.getP_details());
        return i>0? "success" :"error";
    }
    @RequestMapping("/apupdate")
    public String update(@RequestBody Product product){
        int i = productDao.update(product.getP_id(),product.getP_details());
        return i>0? "success" :"error";
    }
}
