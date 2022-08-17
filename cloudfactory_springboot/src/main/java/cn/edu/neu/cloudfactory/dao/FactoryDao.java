package cn.edu.neu.cloudfactory.dao;

import cn.edu.neu.cloudfactory.bean.Factory;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface FactoryDao {
    int insert(@Param("factory") Factory factory);
    boolean deleteById(@Param("f_id") int f_id);
    List<Factory> getAllFactory(String s);
    Factory searchById(@Param("f_id")int f_id);
    boolean update(@Param("factory") Factory factory);

    void updateState(@Param("f_id") int f_id,@Param("f_state") String f_state);
}
