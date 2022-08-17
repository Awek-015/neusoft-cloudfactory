package cn.edu.neu.cloudfactory.controller;

import cn.edu.neu.cloudfactory.bean.*;
import cn.edu.neu.cloudfactory.dao.EquipmentDao;
import cn.edu.neu.cloudfactory.dao.FactoryDao;
import cn.edu.neu.cloudfactory.dao.OrderDao;
import cn.edu.neu.cloudfactory.dao.ProductDao;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderDao orderDao;
    @Autowired
    ProductDao productDao;
    @Autowired
    FactoryDao factoryDao;
    @Autowired
    EquipmentDao equipmentDao;

    @RequestMapping("ao")
    public String getAllOrder(@RequestParam("s") String s){
        String s1 = '%'+s+'%';
        List<Product> productList = productDao.getAllProduct("%%");
        List<Order> orderlist = orderDao.getAllOrder(s1.trim());

        HashMap<String,Object> res = new HashMap<>();
        res.put("olist",orderlist);
        res.put("plist",productList);
        String str= JSON.toJSONString(res);
        return str;

    }
    @RequestMapping("addorder")
    public String addOrder(@RequestBody Order order){
        int i = 0;
        order.setO_state("未保存");
        try{
           i = orderDao.insert(order);
        }catch (Exception e){
            e.printStackTrace();
        }
        return i>0? "success" :"error";

    }

    @RequestMapping("dcs")
    public void changeState(@RequestParam("id") int id){
        orderDao.changState(id);

    }

    @RequestMapping("orderdelete")
    public String deleteOrder(@RequestParam("o_id") int id){
        int i = orderDao.deleteById(id);
        return i>0? "success" : "error";
    }

    @RequestMapping("/orderupdate")
    public String update(@RequestBody Order order){
        int i = orderDao.update(order.getO_id(),order.getO_pid(),order.getO_amount());
        return i>0? "success" :"error";
    }

    @RequestMapping("getbidding")
    public String getBidding(@RequestParam("o_id") int o_id, @RequestParam("f_id") int f_id){
        Bidding bidding =orderDao.getBidding(new Bidding(o_id,f_id));
        return JSON.toJSONString(bidding);
    }

    @RequestMapping("setbidding")
    public void setprice(@RequestBody Bidding bidding){
        orderDao.setPrice(bidding);
    }

    @RequestMapping("showbidding")
    public String getBiddinglist(@RequestParam("oid") int o_id){
        List<Bidding> blist = orderDao.getBiddinglist(o_id);
        List<Factory> flist = factoryDao.getAllFactory("%%");
        HashMap<String,Object> res = new HashMap<>();
        res.put("blist",blist);
        res.put("flist",flist);
        String str= JSON.toJSONString(res);
        return str;
    }

    @RequestMapping("select")
    public void select(@RequestParam("id") int id,@RequestParam("oid") int oid){
        orderDao.selectBidding(id);
        orderDao.changState(oid);
    }

    @RequestMapping("aos")
    public String getAllScheduleOrder(@RequestParam("s") String s,@RequestParam("id")int f_id){
        String s1 = '%'+s+'%';
        List<Product> productList = productDao.getAllProduct("%%");
        List<Order> orderlist = orderDao.getAllScheduleOrder(s1.trim(),f_id);

        HashMap<String,Object> res = new HashMap<>();
        res.put("olist",orderlist);
        res.put("plist",productList);
        String str= JSON.toJSONString(res);
        return str;
    }

    @RequestMapping("ss")
    public String showScheduleInfo(@RequestParam("oid") int o_id ,@RequestParam("fid") int factory){

        List<ScheduleInfo> slist = orderDao.getScheduleinfoByOid(o_id);

        List<Equipment> equipmentlist1= equipmentDao.getAllEquipment(null);
        List<Equipment> equipmentlist = new ArrayList<>();
        for(Equipment e :equipmentlist1){
            if (e.getE_factory()==factory) equipmentlist.add(e);
        }
        List<Equipment> equipmentlist2 = equipmentDao.getRentEquipment(null,factory);
        for(Equipment e1:equipmentlist2){
            equipmentlist.add(e1);
        }


        HashMap<String,Object> res = new HashMap<>();
        res.put("elist",equipmentlist);
        res.put("slist",slist);
        String str=JSON.toJSONString(res);
        return str;
    }

    @RequestMapping("schedule")
    public String schedule(@RequestBody ScheduleInfo scheduleInfo){
        System.out.println(scheduleInfo);
        int i= orderDao.addschedule(scheduleInfo);
        orderDao.changState(scheduleInfo.getO_id());
        return i>0? "success" :"error";
    }

}
