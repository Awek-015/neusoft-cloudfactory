package cn.edu.neu.cloudfactory.test;

import cn.edu.neu.cloudfactory.controller.EquipmentController;


public class test1 {

    public static void main(String[] args) {

        EquipmentController equipmentController = new EquipmentController();
        String str =equipmentController.rentEquipment(2,4,"2080-08-08","2080-09-10");
        System.out.println(str);
    }
}
