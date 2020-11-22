package dao;

import domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {

    @Select("SELECT * FROM traveller where id in(select travellerId from order_traveller where orderId=#{id})")
    List<Traveller> findByOrdersId(String id) throws Exception;
}
