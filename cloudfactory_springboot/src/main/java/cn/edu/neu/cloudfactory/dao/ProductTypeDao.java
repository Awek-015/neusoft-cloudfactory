package cn.edu.neu.cloudfactory.dao;

import cn.edu.neu.cloudfactory.bean.Product_Type;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeDao {
    int insert(@Param("pt_name") String pt_name);
    int deleteById(@Param("pt_id") int pt_id);
    List<Product_Type> getAllProduct_Type(String s);
    Product_Type searchById(@Param("pt_id") int pt_id);
    int update(@Param("pt_id") int pt_id,@Param("pt_name") String pt_name);

}
