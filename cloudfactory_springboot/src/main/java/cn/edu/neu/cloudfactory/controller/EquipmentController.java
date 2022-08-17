package cn.edu.neu.cloudfactory.controller;

import cn.edu.neu.cloudfactory.bean.*;
import cn.edu.neu.cloudfactory.dao.EquipmentDao;
import cn.edu.neu.cloudfactory.dao.EquipmentTypeDao;
import cn.edu.neu.cloudfactory.dao.FactoryDao;
import cn.edu.neu.cloudfactory.dao.ProductDao;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EquipmentController {

    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    EquipmentTypeDao equipmentTypeDao;
    @Autowired
    FactoryDao factoryDao;
    @Autowired
    ProductDao productDao;

    @CrossOrigin
    @RequestMapping("/ae")
    public String getList(@RequestParam("s") String s){
        String s1 = '%'+s+'%';
        List<Equipment> equipmentlist= equipmentDao.getAllEquipment(s1.trim());
        List<Equipment_Type> equipment_typeList = equipmentTypeDao.getAllEquipment_Type(null);
        List<Factory> factoryList = factoryDao.getAllFactory(null);

        HashMap<String,Object> res = new HashMap<>();
        res.put("equipmentList",equipmentlist);
        res.put("equipment_typeList",equipment_typeList);
        res.put("factoryList",factoryList);
        String str=JSON.toJSONString(res);
        return str;
    }

    @RequestMapping("/aeswitch")
    public void updateState(@Param("e_id") int e_id, @Param("e_state") String e_state){
        equipmentDao.updateState(e_id,e_state);
    }

    @RequestMapping("/aedelete")
    public String deleteById(@RequestParam("e_id") int e_id){
        int i = equipmentDao.deleteById(e_id);
        System.out.println(i);
        return i>0? "success" : "error";
    }

    @RequestMapping("/aeadd")
    public String add(@RequestBody Equipment equipment){
        int i = equipmentDao.insert(equipment.getE_num(),equipment.getE_name(),equipment.getE_type(),equipment.getE_details(),equipment.getE_factory());
        return i>0? "success" :"error";
    }
    @RequestMapping("/aeupdate")
    public String update(@RequestBody Equipment equipment){
        int i = equipmentDao.update(equipment.getE_id(),equipment.getE_details());
        return i>0? "success" :"error";
    }

    //根据
    @RequestMapping("/me")
    public String getListByfactory(@RequestParam("s") String s,@RequestParam("factory_id") int factory){
        String s1 = '%'+s+'%';
        List<Equipment> equipmentlist1= equipmentDao.getAllEquipment(s1.trim());
        List<Equipment_Type> equipment_typeList = equipmentTypeDao.getAllEquipment_Type(null);
        List<Factory> factoryList = factoryDao.getAllFactory(null);
        List<Equipment> equipmentlist = new ArrayList<>();
        for(Equipment e :equipmentlist1){
            if (e.getE_factory()==factory) equipmentlist.add(e);
        }
        List<Equipment> equipmentlist2 = equipmentDao.getRentEquipment(s1.trim(),factory);
        for(Equipment e1:equipmentlist2){
            equipmentlist.add(e1);
        }

        HashMap<String,Object> res = new HashMap<>();
        res.put("equipmentList",equipmentlist);
        res.put("equipment_typeList",equipment_typeList);
        res.put("factoryList",factoryList);
        String str=JSON.toJSONString(res);
        return str;
    }
    @RequestMapping("merent")
    public String getNorentList(){
        List<Equipment> equipmentlist = equipmentDao.getNorentList();
        HashMap<String,Object> res = new HashMap<>();
        res.put("equipmentList",equipmentlist);
        String str=JSON.toJSONString(res);
        return str;
    }

    @RequestMapping("merentequipment")
    public String rentEquipment(@RequestParam("f_id") int f_id,@RequestParam("e_id") int e_id,@RequestParam("start") String start ,@RequestParam("end") String end ){
        Map<String,Object> param = new HashMap<>();
        param.put("f_id",f_id);
        param.put("e_id",e_id);
        param.put("start",start);
        param.put("end",end);
        param.put("str","");
        this.equipmentDao.rent(param);
        String s = (String) param.get("str");
        return s;

    }

    @RequestMapping("meset")
    public String setCapacity(@RequestBody Throughput throughput){
        int i = equipmentDao.addThroughput(throughput.getE_id(),throughput.getP_id(),throughput.getCapacity());
        return i>0? "success" :"error";

    }
    @RequestMapping("megetcapacity")
    public String getCapacityList(@RequestParam("e_id") int e_id){
        List<Throughput> tlist = equipmentDao.getInfoByEquipment(e_id);
        List<Product> plist = productDao.getAllProduct("%%");
        HashMap<String,Object> res = new HashMap<>();
        res.put("tlist",tlist);
        res.put("plist",plist);
        String str=JSON.toJSONString(res);
        return str;

    }

}
