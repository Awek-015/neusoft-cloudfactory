package cn.edu.neu.cloudfactory.controller;

import cn.edu.neu.cloudfactory.bean.Equipment_Type;
import cn.edu.neu.cloudfactory.dao.EquipmentTypeDao;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class Equipment_TypeController {

    @Autowired
    private EquipmentTypeDao equipmentTypeDao;

    @CrossOrigin
    @RequestMapping("/aet")
    public String getList(@RequestParam("s") String s){
        String s1 = '%'+s+'%';
        List<Equipment_Type> list= equipmentTypeDao.getAllEquipment_Type(s1.trim());
//        System.out.println(s1);
        HashMap<String,Object> res = new HashMap<>();
        res.put("list",list);
//        System.out.println(list);
        String str=JSON.toJSONString(res);
//        System.out.println(str);
        return str;
    }
    @RequestMapping("/aetdelete")
    public String deleteById(@RequestParam("et_id") int et_id){
        int i = equipmentTypeDao.deleteById(et_id);
        return i>0? "success" : "error";
    }
    @RequestMapping("/aetadd")
    public String add(@RequestBody Equipment_Type equipment_type){
//        System.out.println();
        int i = equipmentTypeDao.insert(equipment_type.getEt_name());
        return i>0? "success" :"error";
    }
    @RequestMapping("/aetupdate")
    public String update(@RequestBody Equipment_Type equipment_type){
     //   System.out.println(equipment_type);
        int i = equipmentTypeDao.update(equipment_type.getEt_id(),equipment_type.getEt_name());
        return i>0? "success" :"error";
    }
}
