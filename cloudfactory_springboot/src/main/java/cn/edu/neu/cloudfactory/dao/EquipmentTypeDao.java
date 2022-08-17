package cn.edu.neu.cloudfactory.dao;

import cn.edu.neu.cloudfactory.bean.Equipment_Type;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentTypeDao {
    int insert(@Param("et_name") String et_name);
    int deleteById(@Param("et_id") int et_id);
    List<Equipment_Type> getAllEquipment_Type(String s);
    Equipment_Type searchById(@Param("et_id") int et_id);
    int update(@Param("et_id") int et_id, @Param("et_name") String et_name);
}
