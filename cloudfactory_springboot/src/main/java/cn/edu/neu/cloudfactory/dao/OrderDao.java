package cn.edu.neu.cloudfactory.dao;

import cn.edu.neu.cloudfactory.bean.Bidding;
import cn.edu.neu.cloudfactory.bean.Order;
import cn.edu.neu.cloudfactory.bean.ScheduleInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {
    int insert(Order order);
    int deleteById(@Param("o_id") int o_id);
    List<Order> getAllOrder(String s);
    int update(@Param("o_id") int o_id, @Param("o_pid") int o_pid,@Param("o_amount") int o_amount);
    void changState(@Param("id") int id);
    Bidding getBidding(Bidding bidding);
    Bidding setPrice(Bidding bidding);

    List<Bidding> getBiddinglist(@Param("o_id") int o_id);

    void selectBidding(@Param("id") int id);

    List<Order> getAllScheduleOrder(@Param("s") String s,@Param("f_id") int f_id);
    List<ScheduleInfo> getScheduleinfoByOid(@Param("o_id") int o_id);

    int addschedule(ScheduleInfo scheduleInfo);
}
