package cn.edu.neu.cloudfactory.dao;

import cn.edu.neu.cloudfactory.bean.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {
    int insert(@Param("p_num") String p_num ,@Param("p_name") String p_name,@Param("p_type") int p_type,@Param("p_details") String p_details);
    int deleteById(@Param("p_id") int p_id);
    List<Product> getAllProduct(String s);
    Product searchById(@Param("pt_id") int pt_id);
    int update(@Param("p_id") int p_id, @Param("p_details") String p_details);

}
