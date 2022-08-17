package cn.edu.neu.cloudfactory.dao;

import cn.edu.neu.cloudfactory.bean.Equipment;
import cn.edu.neu.cloudfactory.bean.Throughput;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EquipmentDao {
    int insert(@Param("e_num") String e_num,@Param("e_name") String e_name ,@Param("e_type") int e_type,@Param("e_details") String e_details,@Param("e_factory") int e_factory);
    int deleteById(@Param("e_id") int e_id);
    List<Equipment> getAllEquipment(String s);
    Equipment searchById(@Param("e_id") int e_id);
    int update(@Param("e_id") int e_id, @Param("e_details") String e_details);

    void updateState(@Param("e_id")int e_id, @Param("e_state") String e_state);

    List<Equipment> getRentEquipment(@Param("s") String s,@Param("factoryId") int factoryId);

    List<Equipment> getNorentList();

    String rent(Map param);
    List<Throughput> getInfoByEquipment(@Param("e_id") int e_id);
    int addThroughput(@Param("e_id")int e_id,@Param("p_id")int p_id,@Param("capacity")int capacity);
}
